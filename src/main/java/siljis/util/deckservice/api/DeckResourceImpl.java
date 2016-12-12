package siljis.util.deckservice.api;

import com.google.gson.Gson;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import siljis.util.deckservice.controller.DeckController;
import siljis.util.deckservice.model.Card;
import siljis.util.deckservice.model.Deck;
import siljis.util.deckservice.persistence.DeckRepository;

/**
 * Implementation of deck operations.
 *
 * @author Silja Tirronen
 */
@RequestScoped
public class DeckResourceImpl implements DeckResource {

    @Inject
    @Any
    private DeckController controller;
    @Inject
    private DeckRepository repository;

    /**
     * Read names of currently persisted decks.
     *
     * @return HTTP/1.1 200 OK
     */
    @Override
    public Response list() {

        List<String> names = repository.readNames();

        return response(Response.Status.OK, gson().toJson(names));
    }

    /**
     * Read deck by given name.
     *
     * @param name
     * @return HTTP/1.1 200 OK if deck exists, HTTP/1.1 404 NOT FOUND otherwise.
     */
    @Override
    public Response show(String name) {

        String value = repository.read(name);

        if (value != null) {
            return response(Response.Status.OK, value);
        }

        return response(Response.Status.NOT_FOUND);

    }

    /**
     * Create a new deck with given name. Deck can only be created if no deck
     * with the same name exists.
     *
     * @param name
     * @return HTTP/1.1 201 CREATED if deck was successfully created, HTTP/1.1
     * 409 CONFLICT otherwise.
     */
    @Override
    public Response create(String name) {

        Card[] cards = controller.init();

        Deck deck = new Deck(name, cards);

        if (repository.create(name, gson().toJson(deck))) {
            return response(Response.Status.CREATED);
        }

        return response(Response.Status.CONFLICT, "Deck '" + name + "' exists already");
    }

    /**
     * Shuffle deck with given name.
     *
     * @param name
     * @return HTTP/1.1 200 OK if deck was successfully shuffled, HTTP/1.1 404
     * NOT FOUND otherwise.
     */
    @Override
    public Response shuffle(String name) {

        String value = repository.read(name);

        if (value == null) {
            return response(Response.Status.NOT_FOUND);
        }

        Deck deck = gson().fromJson(value, Deck.class);

        controller.shuffle(deck.getCards());

        if (repository.update(deck.getName(), gson().toJson(deck))) {
            return response(Response.Status.OK);
        }


        return response(Response.Status.NOT_FOUND);
    }

    /**
     * Delete deck by given name.
     *
     * @param name
     * @return HTTP/1.1 200 OK if deck was successfully deleted, HTTP/1.1 404
     * NOT_FOUND otherwise.
     */
    @Override
    public Response delete(String name) {

        if (repository.delete(name)) {
            return response(Response.Status.OK);
        }

        return response(Response.Status.NOT_FOUND);
    }

    /**
     * Build Response with given status.
     *
     * @param status
     * @return
     */
    private Response response(Response.Status status) {
        return Response.status(status).build();
    }

    /**
     * Build Response by given status and String body.
     *
     * @param status
     * @param body
     * @return
     */
    private Response response(Response.Status status, String body) {
        return Response.status(status).entity(body).build();
    }

    /**
     * Instantiate Gson with default configuration.
     *
     * @return
     */
    private Gson gson() {
        return new Gson();
    }

}
