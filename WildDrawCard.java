package unoGame;

public class WildDrawCard extends WildCard {

     public WildDrawCard(Color color) {
        super(color);

    }

     @Override
        public void applyEffect(Game game) {

            // 1. Wild effect: choose color handled by the parents
            super.applyEffect(game);

            // 2. Extra wild draw effect
            Player next = game.getNextPlayer();
            next.DrawCard(game.getDeck(), 4);

}
     public String toString() {
         return getColor() + " WILDDRAWFOUR";
     }
}
