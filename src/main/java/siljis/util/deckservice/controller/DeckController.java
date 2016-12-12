package siljis.util.deckservice.controller;

import siljis.util.deckservice.model.Card;

/**
 * Deck controller. Defines supported deck modification operations.
 *
 * @author Silja Tirronen
 */
public interface DeckController {

    /**
     * Initialize an array of cards.
     *
     * @return
     */
    public Card[] init();

    /**
     * Shuffle given array of cards.
     *
     * @param cards
     */
    public void shuffle(Card[] cards);

}
