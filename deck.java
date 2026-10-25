package unoGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> drawList = new ArrayList<>();
    private List<Card> discardList = new ArrayList<>();
    private Random rand = new Random();

    public Deck() {
        initDeck();
        shuffle(); //Tout deck créé est automatiquement complet et mélangé.
    }

    public void initDeck() {
        for (Card.Color c : new Card.Color[]{
                Card.Color.RED,
                Card.Color.YELLOW,
                Card.Color.GREEN,
                Card.Color.BLUE}) {

            drawList.add(new NumberCard(c, NumberCard.Number.Zero));

            for (NumberCard.Number n : NumberCard.Number.values()) {
                if (n != NumberCard.Number.Zero) {
                    drawList.add(new NumberCard(c, n));
                    drawList.add(new NumberCard(c, n));
                }
            }

            for (int i = 0; i < 2; i++) {
                drawList.add(new SkipCard(c));
                drawList.add(new ReverseCard(c));
                drawList.add(new DrawTwoCard(c));
            }
        }

        for (int i = 0; i < 4; i++) {
            drawList.add(new WildCard(Card.Color.NONE));
            drawList.add(new WildDrawCard(Card.Color.NONE));
        }
    }

    // Mélange 
    public void shuffle() {
        for (int i = drawList.size() - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);

            Card temp = drawList.get(i);
            drawList.set(i, drawList.get(j));
            drawList.set(j, temp);
        }
    }

    public Card drawCard() {
        if (drawList.isEmpty()) refillFromDiscard();
        if (drawList.isEmpty()) return null;

        return drawList.remove(drawList.size() - 1);
    }

    public void discard(Card c) {
        discardList.add(c);
    }

    private void refillFromDiscard() {
        if (discardList.size() <= 1) return;

        Card top = discardList.remove(discardList.size() - 1);

        for (Card c : discardList) {
            drawList.add(c);
        }

        discardList.clear();
        discardList.add(top);
        shuffle();
    }

    public Card getTopDiscard() {
        return discardList.get(discardList.size() - 1);
    }
}
