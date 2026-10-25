package unoGame;

import unoGame.Card.Color;
import unoGame.NumberCard.Number;

public class DrawTwoCard extends Card implements Actionable {
    public DrawTwoCard (Color color ) {
         super (color); }
     @Override
        public boolean canbeplay(Card topCard) {
         return ((this.getColor () == topCard.getColor()) ||  (topCard instanceof DrawTwoCard));

        }
        @Override
        public void applyEffect (Game game) {
            game.AddtwoNextPlayer();
        }
        public String toString() {
            return getColor() + " DRAW TWO";
        }
}
