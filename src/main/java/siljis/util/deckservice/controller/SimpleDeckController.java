package siljis.util.deckservice.controller;

import java.util.Random;
import javax.enterprise.context.RequestScoped;
import siljis.util.deckservice.model.Card;
import siljis.util.deckservice.model.Rank;
import siljis.util.deckservice.model.Suit;

/**
 * Simple deck controller implementation.
 *
 * @author Silja Tirronen
 */
@RequestScoped
public class SimpleDeckController implements DeckController {

    /**
     * Initialize an array of cards. Initially, cards are ordered by their suit,
     * and by their rank within each suit.
     *
     * @return
     */
    @Override
    public Card[] init() {

        Suit[] suits = Suit.values();
        Rank[] ranks = Rank.values();

        Card[] cards = new Card[suits.length * ranks.length];
        int index = 0;

        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                cards[index] = new Card(suit, rank);
                index++;
            }
        }

        return cards;
    }

    /**
     * Shuffle given array of cards using modernized Fisher-Yates shuffle
     * algorithm.
     *
     * @param cards
     */
    @Override
    public void shuffle(Card[] cards) {

        Random rnd = new Random();

        for (int i = cards.length - 1; i > 0; i--) {

            int r = rnd.nextInt(i + 1);

            swap(cards, i, r);
        }

    }

    /**
     * Helper method to swap Cards at positions i1 and i2 in given array of
     * Cards.
     *
     * @param cards
     * @param i1
     * @param i2
     */
    private static void swap(Card[] cards, int i1, int i2) {
        Card tmp = cards[i1];
        cards[i1] = cards[i2];
        cards[i2] = tmp;
    }


}
