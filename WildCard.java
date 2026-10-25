package unoGame;
import unoGame.Card.Color;
public class WildCard extends Card implements Actionable {
    public WildCard (Color color ) {
         super (color); }
    @Override
    //on peut la jouer dans tt les cas
    public boolean canbeplay (Card topCard) {
        return true; }
        @Override
        public void applyEffect(Game game) {
            // Demande la couleur au joueur
            Card.Color chosen = game.askPlayerTochooseColor();
            game.setCurrentColor(chosen);  // Change la couleur courante dans le jeu
        }
    public String toString() {
        return getColor() + " WILD";
    }
}
