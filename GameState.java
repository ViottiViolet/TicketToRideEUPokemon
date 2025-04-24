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
        boolean isRoutePurchased = false;
        Railroad routePurchased = null;
        //HashMap<TrainCard,Integer> playerCards; 

        /*for(Railroad r : game.getRailroads()) { //check each route
            if(!isRoutePurchased) {
                TrainCard[] playerCards = players[currentPlayer].getTrainCards(); //need to add getTrainCards() method that gets player's cards
                boolean canAfford = true;
                //HashMap<TrainCard, Integer> routeCost;
                //int routeCost = r.length();

                for(TrainCard t : routeCost.keySet()) {
                    if(players[currentPlayer].getNumTrains() < routeCost.get(t) || routeCost.get(t) > playerCards.get(t)) { 
                        canAfford = false;
                    }
                }

                if(canAfford) {
                    System.out.println("route " + r + " can afford");
                    isRoutePurchased = true;
                    routePurchased = r;
                } else {
                    System.out.println("route " + r + " can't afford");
                }
            }
        }*/

        if(routePurchased != null) {
            if(!routePurchased.getIsOwned()) {}
                //add route to player's inventory
        }

        /*if(lastTurn == false) 
        {
            checkGameEnd();
        }*/
        
        currentPlayer = (currentPlayer + 1) % 4;
        if (currentPlayer == 0) currentPlayer = 4;
        
        /*if(lastTurn && (currentPlayer == playerLastTurnIndex)) 
        {
            gameEnd();
        }*/
    }
    
    public void gameEnd() {
        for (int i = 0; i < 4; i++) 
        {
            score[i] = players[i].getScore();
        }
        //set screen to endscreen
    }

    public static int getTurn()
    {
        return currentPlayer;
    }

}