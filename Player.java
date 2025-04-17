import java.util.ArrayList;
import javax.swing.ImageIcon;


public class Player implements Comparable
{
    private ImageIcon image;
    private int score;
    private ArrayList<TrainCard> trainCards;
    private ArrayList<TicketCard> routeCards;
    private ArrayList<Railroad> completedRoutes;
    private boolean express; 
    private int numTrains;
    private int playerNum;

    
    public Player(int x)
    {
       
        image = new ImageIcon(getClass().getResource("/Images/Trainers/"+x+".png"));
        score = 0;
        trainCards = new ArrayList<>();
        routeCards = new ArrayList<>();
        completedRoutes = new ArrayList<>();
        express = false;
        numTrains = 45;
        playerNum = x;
    }

    public int compareTo(Object o) {
        int difference = score-((Player)o).getScore();
        if ( difference !=0)
            return difference;
        difference = completedRoutes.size()-((Player)o).numRoutesComplete();
        if ( difference !=0)
            return difference;
        return calculateLongestPath()-((Player)o).calculateLongestPath();

    }

    public int calculateLongestPath()//TODO
    {
        return 1;// temporary
    }

    public int numRoutesComplete()
    {
        return completedRoutes.size();
    }

    public int getScore()
    {
        return score;
    }

    public void express()
    {
        express = true; 
    }

    public int getNumTrains()
    {
        return numTrains;
    }

    public ArrayList<TrainCard> getTrainCards() {
        return trainCards;
    }

    public void addCompletedRoute(Railroad route) {
        completedRoutes.add(route);
    }

    public void addScore(int points) {
        score += points;
    }

    public void removeTrains(int count) {
        numTrains -= count;
    }
}