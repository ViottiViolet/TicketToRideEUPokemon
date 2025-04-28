
public class GameState {
   
    public static Player[] players;
    private Game game;
    private int playerLastTurnIndex;
    private int currentPlayer;
    public static int turn;
    public static int gamestate;
    private boolean lastTurn;
    public static int[] score;
   
    public GameState(Game g) {
        players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
        lastTurn = false;
        currentPlayer = 0;
        game = g;
        score = new int[4];
    }

    public boolean checkGameEnd() {
        for(Player x : players) {
            if(x.getNumTrains() <= 2) {
                lastTurn = true;
                playerLastTurnIndex = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public void nextTurn() {        
        currentPlayer = (currentPlayer + 1) % 4;

        if(!lastTurn) {
            checkGameEnd();
        } else if(lastTurn && (currentPlayer == playerLastTurnIndex)) {
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