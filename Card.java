
public class Card {

	private String rank;
	private String suit;
	private int    value;
	private String imageFileName;
	private String imageFileType = ".gif";
	

	public Card(int r,int s) {
		
		Suit newSuit = new Suit(s);
		Rank newRank = new Rank(r);
		
		this.rank = newRank.getRank();
		this.value = newRank.getValue();
		this.suit = newSuit.getSuit();
	   }

	public String suitToString() {
		return suit;
	}
	
	public String rankToString() {
		return rank;
	}
	
	public int cardValue() {
		return value;
	}
	
	public String cardImageName() {
				
		switch(rank) {
			case "Ace":
				imageFileName = "1";
				break;
			case "Two":
				imageFileName = "2";
				break;
			case "Three":
				imageFileName = "3";
				break;
			case "Four":
				imageFileName = "4";
				break;
			case "Five":
				imageFileName = "5";
				break;
			case "Six":
				imageFileName = "6";
				break;
			case "Seven":
				imageFileName = "7";
				break;
			case "Eight":
				imageFileName = "8";
				break;
			case "Nine":
				imageFileName = "9";
				break;
			case "Ten":
				imageFileName = "10";
				break;
			case "Jack":
				imageFileName = "j";
				break;
			case "Queen":
				imageFileName = "q";
				break;
			case "King":
				imageFileName = "k";
				break;		
		}
		
		switch(suit) {
			case "Hearts":
				imageFileName = imageFileName + "h" + imageFileType;
				break;
			case "Clubs":
				imageFileName = imageFileName + "c" + imageFileType;
				break;
			case "Diamonds":
				imageFileName = imageFileName + "d" + imageFileType;
				break;
			case "Spades":
				imageFileName = imageFileName + "s" + imageFileType;
				break;
		}
		
		return imageFileName;
	}
	
}
