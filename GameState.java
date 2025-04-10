import java.util.*;

public class GameState {
    
    public static Player[] players;
    private Game game;
    private int playerLastTurnIndex;
    private int currentPlayer;
    public static int turn;
    public static int gamestate; 
    private boolean lastTurn;
    private static int[]score;
    
    public GameState() {
        players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
        lastTurn = false;
        currentPlayer = 0;
    }

    public boolean checkGameEnd() {
        for(Player x : players) {
            if(x.getNumTrains() <=2) {
                lastTurn = true;
                playerLastTurnIndex = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public void nextTurn() {
        if(lastTurn == false) {
            checkGameEnd();
        }
        
        currentPlayer = (currentPlayer + 1) % 4;
        
        if(lastTurn && (currentPlayer == playerLastTurnIndex)) {
            gameEnd();
        }
    }
    
    public void gameEnd() {
        for (int i = 0; i < 4; i++) {
            score[i] = players[i].getScore();
        }
        //set screen to endscreen
    }

}