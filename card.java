package unoGame;


public abstract class Card {

        //Description of the type enum
    public enum Color {
        RED,GREEN, BLUE, NONE, YELLOW
        }

    //Attributs of class Card
    private Color color ;
    //Constructor of class Card
    public Card (Color color){
        this.color=color;
    }
    //getter
    public Color getColor () {
     return color;
    }
    // Abstract method
    public abstract boolean canbeplay(Card topCard);
    }
