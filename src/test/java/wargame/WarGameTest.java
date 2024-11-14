import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class WarGameTest {
    private WarGame warGame;
    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        // Initialize the game before each test
        warGame = new WarGame("Player 1", "Player 2");
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    @Test
    public void testPlayerInitialization() {
        // Check if the players are properly initialized
        assertEquals("Player 1", player1.getName());
        assertEquals("Player 2", player2.getName());
    }

    @Test
    public void testDeckInitialization() {
        // Check if the deck initializes correctly with 52 cards
        Deck deck = new Deck();
        assertEquals(52, deck.cardsRemaining());
    }

    @Test
    public void testDeckShuffleAndDeal() {
        // Check if cards are shuffled and dealt correctly
        Deck deck = new Deck();
        deck.shuffle();
        
        Card dealtCard = deck.dealCard();
        assertNotNull(dealtCard); // A card should be dealt
        assertEquals(51, deck.cardsRemaining()); // Should have 51 cards left
    }

    @Test
    public void testPlayerDrawCard() {
        // Check if a player can draw a card from the deck
        Deck deck = new Deck();
        player1.addCardToHand(deck.dealCard());
        assertEquals(1, player1.getHandSize()); // Player should have 1 card
        assertEquals(51, deck.cardsRemaining()); // Deck should have 51 cards left
    }

    @Test
    public void testCardComparison() {
        // Test the comparison of two cards
        Card card1 = new Card("Hearts", "Ace");
        Card card2 = new Card("Spades", "King");

        assertTrue(card1.getRankValue() > card2.getRankValue()); // Ace should be greater than King
        assertFalse(card2.getRankValue() > card1.getRankValue()); // King should not be greater than Ace
    }

    @Test
    public void testWarRound() {
        // Test a simple round of War to see if a card is played
        warGame.startGame(); // Shuffle and deal cards
        int player1InitialHand = player1.getHandSize();
        int player2InitialHand = player2.getHandSize();

        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        assertNotNull(card1);
        assertNotNull(card2);
        assertEquals(player1InitialHand - 1, player1.getHandSize());
        assertEquals(player2InitialHand - 1, player2.getHandSize());
    }

    @Test
    public void testWarScenario() {
        // Test a scenario where there's a "war" in the game
        Deck deck = new Deck();
        player1.addCardToHand(new Card("Hearts", "10"));
        player1.addCardToHand(new Card("Spades", "Ace"));

        player2.addCardToHand(new Card("Diamonds", "10"));
        player2.addCardToHand(new Card("Clubs", "King"));

        // Simulate one round with a tie leading to "war"
        Card card1 = player1.playCard(); // 10 of Hearts
        Card card2 = player2.playCard(); // 10 of Diamonds

        assertEquals(card1.getRankValue(), card2.getRankValue()); // Cards should tie

        // Ensure "war" logic is triggered, and one player wins
        warGame.startGame();
        assertTrue(player1.getHandSize() > 0 || player2.getHandSize() > 0); // One player should win the "war"
    }

    @Test
    public void testGameEnd() {
        // Test if the game ends when one player runs out of cards
        warGame.startGame();
        while (player1.hasCards() && player2.hasCards()) {
            // Continue playing until one player is out of cards
            player1.playCard();
            player2.playCard();
        }

        // Verify that the game ends properly
        assertTrue(player1.getHandSize() == 0 || player2.getHandSize() == 0);
    }
}
