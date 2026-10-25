package unoGame;

public class Main {

    public static void main(String[] args) {
    	System.out.println("  Bienvenue à notre jeu UNO !  ");
        Game game = new Game();

        // Example: 3 human + 1 bot
        game.addHumanPlayers(3);

        game.dealCards();

        game.startGame();
    }
}