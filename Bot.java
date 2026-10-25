package unoGame;
import java.util.List;
public class Bot extends Player {
    public Bot(String name) {
        super(name); // call Player constructor
    }

    // Bot automatically chooses a card to play
    @Override
    public Card PlayOrOdraw(Deck deck, Card topCard) {
        for (int i = 0; i < getHandsize(); i++) {
            Card c = getCard(i);
            if (c != null && c.canbeplay(topCard)) {
                return playCard(i);
            }
        }
        // Pioche si aucune carte jouable
        DrawCard(deck, 1, true);
        return null;
    }

    // Bot always announces UNO automatically
    @Override
    public boolean hasUNO(Deck deck, boolean announce) {
        if (getHandsize()== 1) {
            // Bot never forgets to say UNO
            super.hasUNO(deck, true);
        }
        return true ;
    }
    @Override

    public void showhand() {
        System.out.println(getName() + "'s hand:"); // use getter

        for (int i = 0; i < getHandsize(); i++) { // use getter
            System.out.println(i + "->" + getCard(i)); // use Player's getCard()
        }
    }


}
