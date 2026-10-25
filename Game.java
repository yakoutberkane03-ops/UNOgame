package unoGame;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> players;
    private Deck deck;
    private  Card topCard;
    private int currentPlayer;// color.card au lieux de int
    private int direction; // 1 = horaire, -1 = antihoraire
    private Scanner scanner;
    private static int TOTAL_PLAYERS = 4;
     
    
    // Constructeur
    public Game() {
        players = new ArrayList<>();
        deck = new Deck();
        currentPlayer = 0;
        direction = 1;
        scanner = new Scanner(System.in);
    }

    // Ajouter joueurs humains 
    public void addHumanPlayers(int humanPlayers) {
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= humanPlayers; i++) {
            System.out.print("Enter name for Player " + i + ": ");
            String name = sc.nextLine();
            players.add(new Player(name));
        }

        int botCount = 1;
        while (players.size() < TOTAL_PLAYERS) {
            players.add(new Bot("Bot " + botCount));
            botCount++;
        }
    }

    // Distribuer 7 cartes
    public void dealCards() {
        for (Player p : players) {
            for (int i = 1; i < 8; i++) {
                p.DrawCard(deck ,1);
            }
        }
    }

    
    // Lancer le jeu
    public void startGame() {
    	
        topCard = deck.drawCard();
        System.out.println("First card: " + topCard);

        while (true) {

            Player p = players.get(currentPlayer);
            System.out.println("\n" + p.getName() + "'s turn");

            // Show hand
            p.showhand();

            // Player plays a card
            Card playedCard = p.PlayOrOdraw(deck, topCard);

            // If a card was played
            if (playedCard != null) {
                topCard = playedCard;           // update top card
                deck.discard(playedCard);       // put it in discard pile

                // Apply special effects (Reverse, Skip, Draw, Wild)
                if (playedCard instanceof Actionable) {
                   ((Actionable) playedCard).applyEffect(this); //Vérifie si la carte jouée a un effet spécial et l’applique au jeu si c’est le cas
                }

                System.out.println(p.getName() + " played: " + playedCard);
            } else {
                System.out.println(p.getName() + " drew a card.");
            }

            //  Ask UNO ONLY if handsize == 1
            if (p.getHandsize() == 1) {
                p.hasUNO(deck, askUNO());
            }

            // Win check
            if (p.hasWon()) {
                System.out.println(p.getName() + " WON!");
                break;
            }

            // Move to next player
            nextPlayer();
        }
    }

    
    // Gestion des tours
    public void nextPlayer() {
    	currentPlayer += direction;

    	if (currentPlayer < 0) {
    	    currentPlayer = players.size() - 1;
    	} else if (currentPlayer >= players.size()) {
    	    currentPlayer = 0;
    	}
    }

    public Player getNextPlayer() {
        nextPlayer(); // move to the next player
        return players.get(currentPlayer);
    }

    public void skipNextPlayer() {
        nextPlayer(); // move to the next player

    }
   
    public void AddtwoNextPlayer() {
        getNextPlayer().DrawCard(deck, 2);
    }

    public void Reversedirection() {
        direction *= -1;
    }

    // WildCard 
     public Card.Color askPlayerTochooseColor() {
    Scanner scanner = new Scanner(System.in);
        Card.Color chosen = null;
        while (chosen == null) {
            System.out.println("Choose a color: RED, BLUE, GREEN, or YELLOW");
            String input = scanner.nextLine().toUpperCase();

            try {
                chosen = Card.Color.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid color. Please try again.");
            }
        }

        return chosen;
       
    }
    public void setCurrentColor(Card.Color color) {
        topCard = new WildCard(color); // changement
    }

    
    public Deck getDeck() {
        return deck;
    }

    public boolean askUNO() {
    	
        System.out.println("Did you say UNO? (true / false)");
    	
    	 boolean said = scanner.nextBoolean();
    	    scanner.nextLine();
    	    return said;
        
    }
    }










