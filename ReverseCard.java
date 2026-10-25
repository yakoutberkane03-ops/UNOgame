package unoGame;

import unoGame.Card.Color;

public class ReverseCard extends Card implements Actionable {
    public ReverseCard (Color color ) {
         super (color); }
     @Override
        public boolean canbeplay (Card topCard) {
         return ((this.getColor () == topCard.getColor()) ||  (topCard instanceof ReverseCard)); }
        @Override
        public void applyEffect (Game game) {
            game.Reversedirection();
        }
        public String toString() {
            return getColor() + " REVERSE";
        }
}
