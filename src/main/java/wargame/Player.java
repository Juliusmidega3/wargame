import java.util.LinkedList;
import java.util.Queue;

public class Player {
    private String name;
    private Queue<Card> hand;  // Using a Queue to manage the player's hand

    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card playCard() {
        return hand.poll();  // Removes and returns the top card
    }

    public int getHandSize() {
        return hand.size();
    }

    public boolean hasCards() {
        return !hand.isEmpty();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "'s hand size: " + hand.size();
    }
}
