
public class Suit {

	private String currentSuit;
	private static final String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
	private int suitIndex = 0;
	private int min = 0;
	private int max = 3;

// Constructor 
	public Suit(int s) {
		if(s <= max || s >= min) {
			suitIndex = s;
			currentSuit = suits[suitIndex];
		}
		else
			currentSuit = "Error: exceeded number of suits";
	}

/*
* getSuit(0) method returns a suit for a card.
*/
	
		public String getSuit() {
			return currentSuit;
		}

}

