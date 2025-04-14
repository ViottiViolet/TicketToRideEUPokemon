import java.util.*;

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
        boolean isRoutePurchased = false;
        Railroad routePurchased = null;
        //HashMap<TrainCard,Integer> playerCards; 

        for(Railroad r : game.getRailroads()) { //check each route
            if(!isRoutePurchased) {
                TrainCard[] playerCards = currentPlayer.getTrainCards(); //need to add getTrainCards() method that gets player's cards
                boolean canAfford = true;
                //HashMap<TrainCard, Integer> routeCost;

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
        }

        if(routePuchased != null) {
            //need method that checks and sets whether or not a route is already taken
            //add route to player's inventory
        }

        if(lastTurn == false) 
        {
            checkGameEnd();
        }
        
        currentPlayer = (currentPlayer + 1) % 4;
        
        if(lastTurn && (currentPlayer == playerLastTurnIndex)) 
        {
            gameEnd();
        }
    }
    
    public void gameEnd() {
        for (int i = 0; i < 4; i++) 
        {
            score[i] = players[i].getScore();
        }
        //set screen to endscreen
    }

}