
public class Rank {

    private static String[] rank = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private int rankValue;
    private String rankName;
    private int index;
   
    
    public Rank(int r) {

	    this.index = r;
	    
	    this.rankName = rank[index];
	    
	    if (index >= 10) {
	    	this.rankValue = 10;
	    }
	    else
	    	this.rankValue = index + 1;
	    
    }
    
    public String getRank() {     
           return rankName;
  }
    
    public int getValue() {
    	return rankValue;
    }
}