public class GameState {
   
    public static Player[] players;
    private Game game;
    private int playerLastTurnIndex;
    public  int currentPlayer = 1;
    public  int turn;
    public  int gamestate;
    private boolean lastTurn;
    public  int[] score;
    private int countdown ;
    private boolean start;
    private boolean endGame;
   
    public GameState(Game g) {
        //players = new Player[]{new Player(1), new Player(2), new Player(3), new Player(4)};
        lastTurn = false;
        game = g;
        score = new int[4];
        makePlayers();
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

    public void seTurn (int i)
    {


    }
    public void endGame()
    {
        if (getCurrentPlayer().getNumTrains()<=2)
        {
            endGame= true;
        }
        endGame= false;
    }
    public boolean getEnd()
    {
        return endGame;
    }

   

    public  void nextTurn() {
        if(endGame&& !start)
    {
            start = true;
            countdown = 3; 

        }
        else if(endGame)
        {
            countdown--;

        }
        if(endGame&&countdown ==0)
        {
            gameEnd();
        }
        currentPlayer = (currentPlayer + 1) % 4;
        if (currentPlayer == 0) currentPlayer = 4;
        
    }
    public Player getCurrentPlayer()
    {
        return players[currentPlayer-1];
    }
   
    public void gameEnd() {
        for (int i = 0; i < 4; i++) {
            score[i] = players[i].getScore();
        }
        //set screen to endscreen
    }

    public Player[] getPlayers()
    {
        return players;
    }


    public   int getTurn()
    {
        return currentPlayer;
    }


}