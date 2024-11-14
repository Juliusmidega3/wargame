public class Card {
    private String suit;  // Hearts, Diamonds, Clubs, Spades
    private String rank;  // 2, 3, 4, ..., Jack, Queen, King, Ace

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    // Helper method to get numerical value of the card for comparison
    public int getRankValue() {
        switch (rank) {
            case "2": return 2;
            case "3": return 3;
            case "4": return 4;
            case "5": return 5;
            case "6": return 6;
            case "7": return 7;
            case "8": return 8;
            case "9": return 9;
            case "10": return 10;
            case "Jack": return 11;
            case "Queen": return 12;
            case "King": return 13;
            case "Ace": return 14;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
