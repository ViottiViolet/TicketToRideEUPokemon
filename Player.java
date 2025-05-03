
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import javax.swing.ImageIcon;


public class Player implements Comparable
{
    private ImageIcon image;
    private int score;
    private HashMap<String, Stack<TrainCard>> trainCards;
    public static ArrayList<String> colorOrder;
    private ArrayList <TicketCard> Tickets;
    private ArrayList<TicketCard> completedTickets;
    private int longestPath;// for european express 
    private Graph graph;
    private int playerNum;
    private Stack <TrainStation> trainStations; 
    private ArrayList <TrainStation> usedStations; 
    private Stack <Train> trains;
    private int moves ; 

    public Player(int x)
    { 
        moves = 0;
        playerNum = x;
        trainStations = new Stack<TrainStation>();
        trains = new Stack<Train>();
        for(int i = 0; i<3; i++)
        {
            trainStations.push(new TrainStation(playerNum));
        }
        for(int i = 0; i<45; i++)
        {
            trains.push(new Train(playerNum));
        }
        
        playerNum = x;
        image = new ImageIcon(getClass().getResource("/Images/Trainers/"+x+".png"));
        score = 0;
        trainCards = new HashMap<String, Stack<TrainCard>>();
        colorOrder = new ArrayList<String>(Arrays.asList("black", "blue", "green", "orange", "pink", "red", "white", "yellow", "wild"));
        for (String color : colorOrder)
        {
            trainCards.put(color, new Stack<TrainCard>());
        }
        Tickets = new ArrayList<TicketCard>();
        completedTickets = new ArrayList<>();
        longestPath = 0;
        usedStations = new ArrayList <TrainStation>();

    }
  

    public int compareTo(Object o) {
       int difference = score-((Player)o).getScore();
       if ( difference !=0)
       return difference;
       difference = completedTickets.size()-((Player)o).numTicketsComplete();
       if ( difference !=0)
       return difference;
       return calculateLongestPath()-((Player)o).calculateLongestPath();



    }

    public void placeTrainStation(City a)
    {
        TrainStation station = trainStations.pop();
        station = new TrainStation(playerNum);
        station.setCity(a);
       
        usedStations.add(station);
        //System.out.println(playerNum + " has " + (4 - usedStations.size()));
    }

    public int calculateLongestPath()//TODO
    {
        return 1;// temporary

    }

    public boolean isCompleted (TicketCard ticket)
    {
        boolean check = false;
        City A = ticket.getCityA();
        City B = ticket.getCityB();
        return graph.isConnectedFinal(A, B);



    }


    // checks all tickets and adds the completed tickets into the completedTickets list
    public void completedTickets ()
    {
        for(TicketCard t: Tickets)
        {
            if(isCompleted(t))
            completedTickets.add(t);
        }
    }

      public int numTicketsComplete()
    {
        return completedTickets.size();
    }

    public ArrayList<TicketCard> getCompletedTickets()
    {
        return completedTickets;

    }

    public int getCardTypeNum (String color)
    {
        return trainCards.get(color).size(); 
    }


    public Graph getGraph()
    {
        return graph;
    }

    public int getScore()
    {
        return score;
    }

    public ArrayList<TrainCard> buy(Railroad r, int numWilds, int pric, String color)
    {


        ArrayList<TrainCard> usedCards = new ArrayList<TrainCard>();
        
        int price = pric;
        ArrayList <TrainCard> list;

        //TEMPORARY
        for(int i = 0; i<price; i++)
        {
            trains.pop();
        }
        //return usedCards;

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
            trains.pop();
        }
        r.claim();
        City A = r.getCityA();
        City B = r.getCityB();
        graph.addVertex(A.getName());//adds to graph
        graph.addVertex(B.getName());
        graph.addEdge(A,B,r.getLength());
        addScore(r.getLength());// updates score
        return usedCards;
    }

    public String canAfford(Railroad r)
    {
        String canAfford = "no";
        String color = r.getColor();
        int wilds = r.getEngineCount();
        boolean mountain = r.isTunnel();
        int length = r.getLength();
        // case 1: no color, no wilds
        if(color.equals("none")&& wilds==0)
        {
            Set set = trainCards.keySet();
            Iterator iter = set.iterator();
            while(iter.hasNext())
            {
                if(trainCards.get(iter.next()).size()>=length)
                return "yes";
            }
        }
        // case 2: no color,  wilds needed
        if(color.equals("none")&& wilds!=0)
        {
            boolean x = false;
            Set set = trainCards.keySet();
            Iterator iter = set.iterator();
            while(iter.hasNext())
            {
                x =((trainCards.get(iter.next())).size()>=(length-wilds));
                if(x == true)
                break;
                
            }
            if(x==false)
            return "no";
            if(trainCards.get("wild").size()>=wilds)
            return "yes";
        }
        // case 3: color, no mountain 
        if(!mountain)
        {
            if(trainCards.get(color).size()>=length)
            return "yes";
        }
        // case 4: color, mountain
        if(trainCards.get(color).size()>=length)
        return "mountain";
        
        return "no";



    }

    public void add(TrainCard card)
    {
        moves++;
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
    public boolean canAffordM(Railroad r, int price)
    {
        String color = r.getColor();
        if(trainCards.get(color).size()<price)
        {
            return true;
        }
        if(trainCards.containsKey("wilds"))
        {
            if(trainCards.get(color).size()<price)
            return true;
        }
        return false;
        
    }
    
    public int getNumTrains()
    {
        return trains.size();
    }


    public int getNumStations()
    {
        return 3 - usedStations.size();
    }

    public HashMap<String, Stack<TrainCard>> getNumCards()
    {
        return trainCards;
    }

   public void numRoutesCompleted()
   {

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

    public int getWilds()
    {
        Set  set = trainCards.keySet();
        Iterator <String> iter = set.iterator();
        Stack stack = new Stack();
        while(iter.hasNext())
        {
            String key = iter.next();
            Stack s = trainCards.get(key);
            if(key.equals("wild"));
            {
            stack = s;
            break;
            }
        }
        System.out.println(stack.size());
        return stack.size();
    }
    public int getMoves()
    {
        return moves;
    }
    public void resetMoves ()
    {moves = 0;}

}