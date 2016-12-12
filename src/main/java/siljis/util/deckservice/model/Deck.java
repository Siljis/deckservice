package siljis.util.deckservice.model;

/**
 * Deck model. Represents a named deck of cards.
 *
 * @author Silja Tirronen
 */
public class Deck {

    private String name;
    private Card[] cards;

    public Deck(String name, Card[] cards) {
        this.name = name;
        this.cards = cards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

}
