import java.util.ArrayList;
import java.util.List;

public class WarGame {
    private Player player1;
    private Player player2;
    private Deck deck;

    public WarGame(String player1Name, String player2Name) {
        player1 = new Player(player1Name);
        player2 = new Player(player2Name);
        deck = new Deck();
    }

    public void startGame() {
        deck.shuffle();
        dealCards();

        System.out.println(player1.getName() + " vs " + player2.getName() + ": Let's Play War!");
        while (player1.hasCards() && player2.hasCards()) {
            playRound();
        }

        // Determine the winner
        if (player1.getHandSize() > player2.getHandSize()) {
            System.out.println(player1.getName() + " wins the game!");
        } else if (player2.getHandSize() > player1.getHandSize()) {
            System.out.println(player2.getName() + " wins the game!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private void dealCards() {
        // Deal cards to players evenly
        while (deck.cardsRemaining() > 0) {
            player1.addCardToHand(deck.dealCard());
            if (deck.cardsRemaining() > 0) {
                player2.addCardToHand(deck.dealCard());
            }
        }
    }

    private void playRound() {
        Card card1 = player1.playCard();
        Card card2 = player2.playCard();

        System.out.println(player1.getName() + " plays " + card1);
        System.out.println(player2.getName() + " plays " + card2);

        if (card1.getRankValue() > card2.getRankValue()) {
            System.out.println(player1.getName() + " wins this round!\n");
            player1.addCardToHand(card1);
            player1.addCardToHand(card2);
        } else if (card2.getRankValue() > card1.getRankValue()) {
            System.out.println(player2.getName() + " wins this round!\n");
            player2.addCardToHand(card1);
            player2.addCardToHand(card2);
        } else {
            System.out.println("It's a tie! War!\n");
            handleWar(card1, card2);
        }
    }

    private void handleWar(Card card1, Card card2) {
        List<Card> warPile = new ArrayList<>();
        warPile.add(card1);
        warPile.add(card2);

        if (player1.getHandSize() < 4 || player2.getHandSize() < 4) {
            // If a player does not have enough cards to continue the war, they lose
            return;
        }

        System.out.println("Both players place three cards face down...");

        for (int i = 0; i < 3; i++) {
            warPile.add(player1.playCard());
            warPile.add(player2.playCard());
        }

        // Draw a final card to resolve the war
        Card warCard1 = player1.playCard();
        Card warCard2 = player2.playCard();

        System.out.println(player1.getName() + " plays " + warCard1);
        System.out.println(player2.getName() + " plays " + warCard2);

        warPile.add(warCard1);
        warPile.add(warCard2);

        if (warCard1.getRankValue() > warCard2.getRankValue()) {
            System.out.println(player1.getName() + " wins the war!\n");
            warPile.forEach(player1::addCardToHand);
        } else if (warCard2.getRankValue() > warCard1.getRankValue()) {
            System.out.println(player2.getName() + " wins the war!\n");
            warPile.forEach(player2::addCardToHand);
        } else {
            System.out.println("The war is a tie again! Continuing war...\n");
            handleWar(warCard1, warCard2);  // Recursive call to resolve further ties
        }
    }
}
