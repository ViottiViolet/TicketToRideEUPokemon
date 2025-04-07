import java.util.*;

public class GameState {
    
    public static Player[] players;
    public static int turn;
    public static int gamestate; 
    public static final int BEGINNINGTURN = 0, DRAWINGCARDS = 1, BUYROUTE = 2, PLACESTATION = 3;
    public static int gameEndCounter;
    public static HashMap<Integer, BufferedImage> cardBacks;
    //public static HashMap<Integer, ArrayList<Card>> cards;
    
    public GameState() {
        players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
        turn = 0;
        gamestate = BEGINNINGTURN;
        gameEndCounter = -1;
    }

    public boolean checkGameEnd() {
        for(Player x : players) {

        }
    }
    
}