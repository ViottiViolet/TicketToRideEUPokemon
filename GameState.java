public class GameState {
   
    public static Player[] players;
    private Game game;
    private int playerLastTurnIndex;
    public static int currentPlayer = 1;
    public static int turn;
    public static int gamestate;
    private boolean lastTurn;
    public static int[] score;
   
    public GameState(Game g) {
        //players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
        lastTurn = false;
        game = g;
        score = new int[4];
    }

    public static void makePlayers()
    {
        players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
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

    public static void nextTurn() {
        currentPlayer = (currentPlayer + 1) % 4;
        if (currentPlayer == 0) currentPlayer = 4;
        
    }
   
    public void gameEnd() {
        for (int i = 0; i < 4; i++) {
            score[i] = players[i].getScore();
        }
        //set screen to endscreen
    }


    public static int getTurn()
    {
        return currentPlayer;
    }


}