package unoGame;
import java.util.ArrayList;
import java.util.List ;
import java.util.Scanner;
public class Player {
	
	private String name ;
	private  List<Card> hand;
	private  int handsize;
	private boolean saidUNO;
	public Player (String name) {
	   this.name=name;
	   this.hand=new ArrayList<>();//creates an empty hand for the player to store cards this.handsize=0;//intiallay no card
	   this.saidUNO=false;
	    }
	public void DrawCard(Deck deck, int i, boolean showDrawn) {
	    for (int j = 0; j < i; j++) {
	        Card c = deck.drawCard();
	        hand.add(c);
	        handsize++;
	        if (showDrawn) {
	            System.out.println(name + " drew: " + c);
	        }
	    }
	}

	public void DrawCard(Deck deck, int i) {
		DrawCard(deck, i, false); // par défaut, ne montre rien
	}

	      public void showhand() {
	        System.out.println(name+"'s hand:");
	        for (int i=0;i<hand.size();i++) {
	          System.out.println(i+"->"+hand.get(i));

	        }
	      }

	      public Card getCard(int index) {//we can not check the card without accessing to it
	        if(index>=0 && index<handsize) {
	             return hand.get(index);//return card at that index

	        }else {
	          System.out.println("error:invalid card index");
	          return null;
	        }
	      }

	      public Card playCard(int index) {
	    	    if (index < 0 || index >= handsize) {
	    	        System.out.println("Invalid card index");
	    	        return null;
	    	    }
	    	    Card c = hand.remove(index); // automatically shifts elements
	    	    handsize = hand.size();      // update handsize
	    	    return c;
	    	}
	      // Checks if player has a playable card
	      
	    	  public Card PlayOrOdraw(Deck deck, Card topCard) {
		    	Scanner sc = new Scanner(System.in);
	    	    while (true) {
	    	        System.out.println("Top card: " + topCard);
	    	        System.out.print("Choose a card index to play (or -1 to draw): ");
	    	        int choice = sc.nextInt();

	    	        if (choice == -1) {
	    	            Card drawn = deck.drawCard();
	    	            hand.add(drawn);
	    	            handsize++;
	    	            System.out.println(getName() + " drew: " + drawn);
	    	            if (drawn.canbeplay(topCard)) {
	    	                System.out.println("You can play the drawn card.");
	    	                System.out.print("Do you want to play it? (true/false): ");
	    	                boolean playDrawn = sc.nextBoolean();
	    	                if (playDrawn) {
	    	                    hand.remove(handsize - 1);
	    	                    handsize--;
	    	                    return drawn;
	    	                }
	    	            }
	    	            return null;
	    	        }

	    	        if (choice >= 0 && choice < handsize) {
	    	            Card selected = getCard(choice);
	    	            if (selected.canbeplay(topCard)) {
	    	                return playCard(choice);
	    	            } else {
	    	                System.out.println("Cannot play this card. Choose another one.");
	    	            }
	    	        } else {
	    	            System.out.println("Invalid choice. Try again.");
	    	        }
	    	    }
	    	}


	      // Returns true if player won

	        public boolean hasWon() {
	        	return handsize == 0;
	        }


	        public  boolean hasUNO(Deck deck, boolean announce) {
	            if (handsize == 1) {
	                if (announce) {
	                    return true;
	                } else if (!saidUNO) { // player did not announce
	                    DrawCard(deck, 2);
	                  
	                }

	                return false; // ensure the flag is ready for the next turn
	            }

	            return false; // Or whatever default you want when handsize != 1
	        }

           public String getName() {
					    return name;
					}
	          public int getHandsize() {
	        	    return handsize;
	        	}
}