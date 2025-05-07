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
        System.out.println("hello i am here ");
       
            if(getCurrentPlayer().getNumTrains() <= 40) {
                System.out.println("END GAMEEE");
                lastTurn = true;
                playerLastTurnIndex = currentPlayer;
                return true;
            }
       
        return false;
    }
 
 
     public boolean getEnd()
    {
        return lastTurn;
    }
 
 
  
 
 
    public  void nextTurn() {
        checkGameEnd();
        if(lastTurn&& !start)
    {
            start = true;
            countdown = 3;
 
 
        }
        else if(lastTurn)
        {
 
 
            countdown--;
            System.out.println("Countdown:"+countdown);
 
 
        }
        if(lastTurn&&countdown ==0)
        {
            System.out.println("gameEnd called");
           // gameEnd();
           endGame = true;
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
        EndScreen.main(new String[0]);
 
 
    }
 
 
    public Player[] getPlayers()
    {
        return players;
    }
 
 
 
 
    public   int getTurn()
    {
        return currentPlayer;
    }
    public boolean getEndGame()
    {
        return endGame;
    }
 
 
 
 
 }
 
 