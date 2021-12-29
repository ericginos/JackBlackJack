import java.util.ArrayList; 

public class CardHand {
ArrayList<Card> hand = new ArrayList<Card>();
		int cardTotal = 0;
		String status;
		Boolean aceFlag = false;
		Boolean blackJackFlag = false;
		public CardHand() {
		cardTotal = 0;
		}
		public void addCard(Card c) {
		hand.add(c);
		}
		
		public int handValue() {
				cardTotal = 0;
				aceFlag = false;
				blackJackFlag = false;
				for (int i = 0; i < hand.size(); i++) {      
				         cardTotal += hand.get(i).cardValue();
				        // System.out.println("Card: " + i + " : " + hand.get(i).cardValue());
				        // System.out.println("CardTotal: " + cardTotal);
				         if(hand.get(i).rankToString() == "Ace") {
				        	 //System.out.println("AceFlag: " + hand.get(i).rankToString());
				        	 aceFlag = true;
				         }
				  }
				
				if((cardTotal == 11) && (hand.size() == 2) && (aceFlag == true)){
					cardTotal = 21;
					blackJackFlag = true;
				}
				
				
				if ((aceFlag == true) && (cardTotal <= 10)) {
					//System.out.println("aceFlag");
					cardTotal += 10;
				}
				
				
				
				return cardTotal;
			}
		
		
		public String handStatus(){
		if (cardTotal == 0) {
		status = "start";
		}
		else if((hand.get(0).rankToString() == hand.get(1).rankToString()) && (hand.size() == 2)){
		            status = "split";
		        }
		else if((cardTotal == 21) && (hand.size() == 2) && (blackJackFlag == true)){
		status = "blackjack";
		}
		else if((cardTotal == 21) && (hand.size() > 2)){
		status = "twentyone";
		}
		else if   ((cardTotal == 9 || cardTotal == 10 || cardTotal == 11) && (hand.size() == 2)) {
		status = "doubledown";
		}
		else if(((cardTotal > 0) && (cardTotal < 9)) || ((cardTotal > 11) && (cardTotal < 21))){
		status = "play";
		}
		else if (cardTotal > 21) {
		status = "bust";
		} 
		return status; 
		}
		public String dealersHandStatus(){
		if((cardTotal == 21) && (hand.size() == 2) && (blackJackFlag == true)){
		status = "blackjack";
		}
		if((cardTotal == 21) && (hand.size() > 2)){
		status = "twentyone";
		}
		if((cardTotal > 0) && (cardTotal < 17)){
		status = "play";
		}
		if((cardTotal == 17) && (aceFlag == true)) {
		status = "play";
		}
		if((cardTotal >= 17) && (cardTotal < 21)) {
		status = "stand";
		}
		if (cardTotal > 21) {
		status = "bust";
		} 
		return status;
		}

		public String printCardImage(int i)
		{
			return hand.get(i).cardImageName();
		}
		
		// new method 10/30/2020
		public int getCardValue(int i)
		{
			return hand.get(i).cardValue();
		}
		//
		
		public void printHand() {
		for (int i = 0; i < hand.size(); i++) {      
		System.out.println(hand.get(i).rankToString() + " of " + hand.get(i).suitToString());
		     }
		}
		public void printHandImage() {
		for (int i = 0; i < hand.size(); i++) {      
		System.out.println(hand.get(i).cardImageName());
		     }
		}
		public Card getSplitCard(int i) {
			return hand.get(i);
		}
		public void deleteSplitCard(int i) {
		        hand.trimToSize();
		    }
		
		public void clearHand() {
		    hand.clear();
		}
		
		public void emptyHand() {
		hand.clear();
		}
}

