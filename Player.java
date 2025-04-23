import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;
import javax.swing.ImageIcon;


public class Player implements Comparable
{
    private ImageIcon image;
    private int score;
    private HashMap<String, Stack<TrainCard>> trainCards;
    private ArrayList <TicketCard> routeCards;
    private ArrayList<Railroad> completedRoutes;
    private Boolean express; 
    private Graph graph;
    private int playerNum;
    private Stack <TrainStation> trainStations; 
    private ArrayList <TrainStation> usedStations; 
    private Stack <Train> trains;
    
    

    public Player(int x)
    { 
        playerNum = x;
        trainStations = new Stack <TrainStation>();
        for(int i = 0; i<3; i++)
        {
            trainStations.push(new TrainStation(playerNum));
        }
        for(int i = 0; i<45; i++)
        {
            trains.push(new Train (playerNum));
        }
        
       playerNum = x;
         image = new ImageIcon(getClass().getResource("/Images/Trainers/"+x+".png"));
         score = 0;
        trainCards = new HashMap<String, Stack<TrainCard>>();
        routeCards = new ArrayList();
         completedRoutes = new ArrayList();
         express = false;
         usedStations = new ArrayList <TrainStation>();

    }
    public int numRoutesComplete()
    {
        return completedRoutes.size();
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

    public void placeTrainStation(City a)
    {
        TrainStation station = trainStations.pop();
        station = new TrainStation(playerNum,a);
       
        usedStations.add(station);

    



    }

    public int calculateLongestPath()//TODO
    {
        return 1;// temporary

    }

    public ArrayList<TicketCard> completedTickets (TicketCard ticket)
    {
        ArrayList <TicketCard> complete = new ArrayList <TicketCard>();
        for(int i =0; i<routeCards.size(); i++)
        {
            TicketCard card = routeCards.get(i);
            if(graph.isConnected(card.getCityA(), card.getCityB()))
            complete.add(card);
         
        }

        return complete;


    }

    public Graph getGraph()
    {
        return graph;
    }

    public int getScore()
    {
        return score;
    }

    public void express()
    {
        express = true; 
    }

    public ArrayList<TrainCard> buy(Railroad r, int numWilds)
    {
        ArrayList usedCards = new ArrayList<TrainCard>();
        String color = r.getColor();
        int price = r.getLength();
        ArrayList <TrainCard> list;
        for(int i = 0; i<numWilds; i++)
        {
            usedCards.add(trainCards.get("wild").pop());

        }
        price-=numWilds;
        if(price==0)
        return usedCards;

         for(int i = 0; i<price; i++)
        {
            usedCards.add(trainCards.get(color).pop());

        }

        return usedCards;



    }

    public boolean canAfford(Railroad r)
    {
        boolean afford = false;
        String color = r.getColor();
        int length = r.getLength();

   
        if(!( trainCards.containsKey(color)||trainCards.containsKey("wild")))
        return afford;
        if(trainCards.get(color).size()>=length)
        return !afford;
        if(trainCards.get(color).size()+trainCards.get("wild").size()>=length)
        return !afford;
        System.out.println ("error in canAfford method in Player class");
        return afford;


    }

    public void add(TrainCard card)
    {
        String color = card.getColor();
        Set set = trainCards.keySet();

        if(!(trainCards.containsKey(card.getColor())))
        {
            Stack <TrainCard> list = new Stack <TrainCard>();
            list.push(new TrainCard(card.getColor()));
            trainCards.put(card.getColor(),list);

        }
        else
        {
              Stack <TrainCard> stack = trainCards.get(card.getColor());
              stack.push(new TrainCard(color));
            trainCards.put(color, stack);
        }

        
    }
    
    public int getNumTrains()
        {
            return trains.size();
        }

    public void addCompletedRoute(Railroad r) 
    {
        completedRoutes.add(r);
    }

    public void addScore(int length) 
    {
        if(length == 1 || length == 2)
            score += length;
        if (length == 3)
            score += 4;
        if (length == 4)
            score += 7;
        if (length== 6)
            score += 15;
        if (length == 8)
            score += 21;
    }

    public void removeTrains(int length) 
    {
        for (int i = 0; i < length; i++)
            trains.pop();
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