import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	  
	private int DeckSize = 52; 
	private int rank;
	private int suit;
	private int cardsDealt = 0;
	private int numOfDecks = 0;

	 //Card[] deck = new Card[DeckSize];  // An array of 52 Cards, representing the deck.
	  
	 
	 ArrayList<Card> newDeck = new ArrayList<Card>();

	  public Deck(int n) {
		  
		 numOfDecks = n;
		  
		 for (int h = 0; h < numOfDecks; h++)
		 {
			  for(int i = 0; i < DeckSize; i++)
			  {
				    if( i >= 0 && i < 13) {
				    	rank = i;
				    	suit = 0;
				    }
				    if( i > 12 && i < 26) {
				    	rank = i - 13;
				    	suit = 1;
				    }
				    if( i >= 26 && i <  39) {
				    	rank = i - 26;
				    	suit = 2;
				    }
				    if( i >= 39 && i < 52) {
				    	rank = i - 39;
				    	suit = 3;
				    }
				    
				    newDeck.add(new Card(rank,suit));
				    

			    }

		  }
	    }
	 
	    public void shuffle() {
	    	Collections.shuffle(newDeck);
	    }
	    
	    public int deckSize()
	    {
	    		return newDeck.size();
	    }
	    
	    public int numCardsInDeck()
	    {
	    		return newDeck.size() - cardsDealt;
	    }
	    
	    public int numCardsDealt() {
	    	
	    		return cardsDealt;
	    }
	   
	    public Card dealCard() {
	          // Deals one card from the deck and returns it.
	        cardsDealt++;
	        return newDeck.get(cardsDealt - 1);
	    } 
	    
	    
	  	public void printDeck() {
	  		 
	  		
	  	  for(int n = 0; n < numOfDecks; n++)
	  	  {
	  		for(int i = 0; i < DeckSize; i++)
			  {
	  			System.out.println(newDeck.get(i).rankToString() + " of " + newDeck.get(i).suitToString() + " with value of " + newDeck.get(i).cardValue()); 
	  			 
	  			if (i == 12) {
	  				System.out.println();
	  			}
	  			if (i == 25) {
	  				System.out.println();
	  			}
	  			if (i == 38) {
	  				System.out.println();
	  			}
			  }
	  	  }
	  	} 
	  }

