
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Player implements Comparable
{
    private BufferedImage image;
    private int score;
    private HashMap<String, Stack<TrainCard>> trainCards;
    private ArrayList <TicketCard> Tickets;
    private ArrayList<TicketCard> completedTickets;
    private int longestPath;// for european express 
    private Graph graph;
    private int playerNum;
    private Stack <TrainStation> trainStations; 
    private ArrayList <TrainStation> usedStations; 
    private Stack <Train> trains;

    public Player(int x)
    { 
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
       System.out.println(x);
       System.out.println("HELLO");
       // Using ClassLoader (safe and works in JARs)
  
        try {
            InputStream is = Player.class.getResourceAsStream("/Images/Trainers/"+x+".png");
            if(is == null)
            System.out.println("FAILED");
            image = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Fail2.0");
            e.printStackTrace();
        }
        // image = new ImageIcon(getClass().getResource.getFile("/Images/Trainers/"+x+".png"));
         score = 0;
        trainCards = new HashMap<String, Stack<TrainCard>>();
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
     public void buyStation(String color)
     {
        Stack stack = trainCards.get(color);
     
        int cardsNeeded = 3-trainStations.size()+1;
        for(int i = 0; i<cardsNeeded; i++)
        {
            stack.pop();
        }

    }

    public boolean canAfforStation(String color)
    {
        int cardsNeeded = 3-trainStations.size()+1;
        if(trainCards.get(color).size()>=cardsNeeded)
        return true;
        return false;
    }

    public void placeTrainStation(City a)
    {
        TrainStation station;
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


    public Graph getGraph()
    {
        return graph;
    }

    public int getScore()
    {
        return score;
    }

    public ArrayList<TrainCard> buy(Railroad r, int numWilds)
    {
        ArrayList<TrainCard> usedCards = new ArrayList<TrainCard>();
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


    public int getNumStations()
    {
        return 3 - usedStations.size();
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

}