import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack<TrainCard> trainDeck;
    private static Stack<TrainCard> discardPile;
    private static Stack<TicketCard> longRoutes = new Stack<>();
    private static Stack<TicketCard> normRoutes = new Stack<>();
    private ArrayList<TrainCard> faceUpCards = new ArrayList<>();
    private static final String[] CARD_COLORS = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow"};
    private Graph boardGraph;

    public Game() {
        discardPile = new Stack<>();
        trainDeck = new Stack<>();
        boardGraph = new Graph();
        boardGraph.addVertex("Lisboa");
        boardGraph.addVertex("Cadiz");
        boardGraph.addVertex("Madrid");
        boardGraph.addVertex("Barcelona");
        boardGraph.addVertex("Pamplona");
        boardGraph.addVertex("Marseille");
        boardGraph.addVertex("Paris");
        boardGraph.addVertex("Brest");
        boardGraph.addVertex("Zurich");
        boardGraph.addVertex("Dieppe");
        boardGraph.addVertex("London");
        boardGraph.addVertex("Bruxelles");
        boardGraph.addVertex("Amsterdam");
        boardGraph.addVertex("Essen");
        boardGraph.addVertex("Frankfurt");
        boardGraph.addVertex("Monchen");
        boardGraph.addVertex("Venezela");
        boardGraph.addVertex("Roma");
        boardGraph.addVertex("Palermo");
        boardGraph.addVertex("Brindisi");
        boardGraph.addVertex("Berlin");
        boardGraph.addVertex("Zagrab");
        boardGraph.addVertex("Sarajevo");
        boardGraph.addVertex("Wein");
        boardGraph.addVertex("Kobenhavn");
        boardGraph.addVertex("Budapest");
        boardGraph.addVertex("Danzig");
        boardGraph.addVertex("Athina");
        boardGraph.addVertex("Warszawa");
        boardGraph.addVertex("Bucuresti");
        boardGraph.addVertex("Sevastopol");
        boardGraph.addVertex("Constantinople");
        boardGraph.addVertex("Angora");
        boardGraph.addVertex("Smyrna");
        boardGraph.addVertex("Edinburgh");
        boardGraph.addVertex("Stockholm");
        boardGraph.addVertex("Sofia");
        boardGraph.addVertex("Kyiv");
        boardGraph.addVertex("Wilno");
        boardGraph.addVertex("Riga");
        boardGraph.addVertex("Petrograd");
        boardGraph.addVertex("Smolensk");
        boardGraph.addVertex("Kharkov");
        boardGraph.addVertex("Moskva");
        boardGraph.addVertex("Rostov");
        boardGraph.addVertex("Sochi");
        boardGraph.addVertex("Erzurum");
        boardGraph.addVertex("Snyrna");
        System.out.println("helloo");

        initializeTrainDeck();
        drawFaceUpCards();

        //scanners
        Scanner longRoutesReader = null;
        Scanner routesReader = null;
        Scanner railroadReader = null;

        InputStream one = getClass().getResourceAsStream("/railroads.tsv");
        if (one == null) {
            System.out.println("Could not load railroads.tsv");
        } else {
            railroadReader = new Scanner(one);

        }
        
        InputStream two = getClass().getResourceAsStream("/long routes.tsv");
        if (two == null) {
            System.out.println("Could not load long routes.tsv");
        } else {
            longRoutesReader = new Scanner(two);
        }
        
        InputStream three = getClass().getResourceAsStream("/routes.tsv");
        if (three == null) {
            System.out.println("Could not load routes.tsv");
        } else {
            routesReader = new Scanner(three);
        }

        
       
        //railroadReader.nextLine();
        while(railroadReader.hasNextLine())
        {
            String str = railroadReader.nextLine();
            //System.out.println(str);
            String []info1 = str.split("\t");
           
            String color = info1[0];
            
            int length = Integer.parseInt(info1[1]);
            //
           
            //
            boolean isTunnel = info1[2].equals("yes");
            int engineCount = Integer.parseInt(info1[3]);
            City a = new City (info1[4]);
            City b = new City(info1[5]);
            boardGraph.addEdge(color, length, isTunnel, engineCount, a, b);

        }    
        // longRoutesReader.nextLine();
        while(longRoutesReader.hasNextLine()){
            
            String[] cardInfo = longRoutesReader.nextLine().split("\t");
            System.out.println("long Routes read");//yolo
            City cityA = new City(cardInfo[0]);
            City cityB = new City(cardInfo[1]);
            int worth = Integer.parseInt(cardInfo[2]);
            BufferedImage card = null;
            
            try{
               card = ImageIO.read(GameState.class.getResource("/Images/Long Routes/" + cityA.getName()+ "- " + cityB.getName() + ".png"));
            } catch(IOException e){
               System.out.println("game card error");
            }
            longRoutes.add(new TicketCard(card, cityA, cityB, worth));
        }

        //reading in normal routes
       // routesReader.nextLine();
        while(routesReader.hasNextLine()){
            String[] cardInfo = routesReader.nextLine().split("\t");
            City cityA = new City(cardInfo[0]);
            City cityB = new City(cardInfo[1]);
            int worth = Integer.parseInt(cardInfo[2]);
            
            BufferedImage card = null;
            try{
               card = ImageIO.read(GameState.class.getResource("/Images/Routes/" + cityA.getName() + "- " + cityB.getName() + ".png"));
            } catch(IOException e){
               System.out.println("game card error");
            }
             
            normRoutes.add(new TicketCard(card, cityA, cityB, worth));
        }
        System.out.println("185");
    }
   

    private void initializeTrainDeck() {
        System.out.println("190");
        //add colored cards
        for (String color : CARD_COLORS) {
            for (int i = 0; i < 12; i++) {
                trainDeck.add(new TrainCard(color));
            }
        }
       
        //add wild cards
        for (int i = 0; i < 14; i++) {
            trainDeck.add(new TrainCard("wild"));
        }
        System.out.println("214");
        //shuffle deck
        Collections.shuffle(trainDeck);
    }

    private void drawFaceUpCards() {
        for (int i = 0; i < 5; i++) {
            if (!trainDeck.isEmpty()) {
                faceUpCards.add(trainDeck.remove(0));
            }
        }
    }
    public Stack<TrainCard> getDeck() {
        return trainDeck;
    }
    

    public ArrayList<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public TrainCard drawFromDeck() {
        if (trainDeck.isEmpty()) {
            return null;
        }
        return trainDeck.remove(0);
    }

    public void replaceFaceUpCard(int index) {
        if (!trainDeck.isEmpty()) {
            faceUpCards.set(index, trainDeck.remove(0));
        }
    }

    public  Graph getBoardGraph() 
    {
      //  System.out.println(boardGraph.getVertices().size());
    return boardGraph;

    
 }
    public Stack<TicketCard> getNormRoutes()
    {
        return normRoutes;
    }

    public Stack<TicketCard> getLongRoutes()
{
    return longRoutes;
}
public static void addToDiscard (ArrayList<TrainCard> cards)
{
    for (int i = 0; i< cards.size();i++)
    {
        discardPile.push(cards.get(i));
    }
}

public Stack<TrainCard> getDiscardPile() {
    return discardPile;
}
}


