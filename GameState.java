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
        boolean isRoutePurchased = false;
        Railroad routePurchased = null;

        //check if player can purchase any routes
        for(City city : game.getGameGraph().getVertices()) {
            for(Railroad r : city.getEdges()) {
                if(!isRoutePurchased && !r.getIsOwned()) {
                    ArrayList<TrainCard> playerCards = players[currentPlayer].getTrainCards();
                    boolean canAfford = true;
                    int routeLength = r.getLength();
                    String routeColor = r.getColor();

                    //check if player has enough trains
                    if(players[currentPlayer].getNumTrains() < routeLength) {
                        canAfford = false;
                    } else {
                        //count matching cards player has of route color
                        int matchingCards = 0;
                        for(TrainCard card : playerCards) {
                            if(card.getColor().equals(routeColor) || card.isWild()) {
                                matchingCards++;
                            }
                        }
                        if(matchingCards < routeLength) {
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
        }

        if(routePurchased != null) {
            routePurchased.setIsOwned();
            players[currentPlayer].addCompletedRoute(routePurchased); //add route to player's completed routes
            players[currentPlayer].addScore(routePurchased.getLength()); //update player's score
            players[currentPlayer].removeTrains(routePurchased.getLength()); //remove used trains
        }

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