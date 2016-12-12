package siljis.util.deckservice.api;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Deck resource. Defines operations for interaction with decks.
 *
 * @author Silja Tirronen
 */
@Path("/decks")
public interface DeckResource {

    /**
     * List decks currently stored in deck service.
     *
     * @return
     */
    @GET
    @Path("/list")
    @Produces("application/json")
    public Response list();

    /**
     * Show details of a named deck.
     *
     * @param name
     * @return
     */
    @GET
    @Path("/{name}/show")
    @Produces("application/json")
    public Response show(@PathParam("name") String name);

    /**
     * Create a new named deck.
     *
     * @param name
     * @return
     */
    @PUT
    @Path("{name}/create")
    public Response create(@PathParam("name") String name);

    /**
     * Shuffle a named deck.
     *
     * @param name
     * @return
     */
    @POST
    @Path("/{name}/shuffle")
    public Response shuffle(@PathParam("name") String name);

    /**
     * Delete a named deck.
     *
     * @param name
     * @return
     */
    @DELETE
    @Path("/{name}/delete")
    public Response delete(@PathParam("name") String name);

}
