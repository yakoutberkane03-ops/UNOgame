package unoGame;



public class SkipCard extends Card implements Actionable{
    public SkipCard (Color color ) {
         super (color); }
    @Override
    public boolean canbeplay (Card topCard) {

        return ((this.getColor () == topCard.getColor()) ||  (topCard instanceof SkipCard));

    }
    @Override
    public void applyEffect (Game game) {
        game.skipNextPlayer();
    }
    public String toString() {
        return getColor() + " SKIP";
    }
}