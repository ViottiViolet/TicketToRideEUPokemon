import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack <TrainCard> deck;
    private static Stack <TrainCard> discardPile;
    private static Stack<TicketCard> longRoutes = new Stack<>();
    private static Stack<TicketCard> normRoutes = new Stack<>();
     private Stack <TrainCard> trainDeck = new Stack<>();


    private ArrayList<TrainCard> faceUpCards = new ArrayList<>();
    private static final String[] CARD_COLORS = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow", };
    private Graph boardGraph;

    public Game() {
        discardPile = new Stack<>();
        deck = new Stack<>();
        boardGraph = new Graph();
        int x = 322;
        int y = 54;
        boardGraph.addVertex("Lisboa",257, 749);
        boardGraph.addVertex("Cadiz",344, 814);
        boardGraph.addVertex("Madrid",344, 719);
        boardGraph.addVertex("Barcelona",489, 730);
        boardGraph.addVertex("Pamplona",473, 615);
        boardGraph.addVertex("Marseille",670, 608);
        boardGraph.addVertex("Paris",560, 421);
        boardGraph.addVertex("Brest",379, 398);
        boardGraph.addVertex("Zurich",709, 489);
        boardGraph.addVertex("Dieppe",493, 360);
        boardGraph.addVertex("London",509, 249);
        boardGraph.addVertex("Bruxelles",601, 314);
        boardGraph.addVertex("Amsterdam",635, 255);
        boardGraph.addVertex("Essen",741, 263);
        boardGraph.addVertex("Frankfurt",722, 353);
        boardGraph.addVertex("Monchen",800, 411);
        boardGraph.addVertex("Venezela",818, 533);
        boardGraph.addVertex("Roma",821, 648);
        boardGraph.addVertex("Palermo",874, 814);
        boardGraph.addVertex("Brindisi",937, 680);
        boardGraph.addVertex("Berlin",871, 285);
        boardGraph.addVertex("Zagrab",928, 545);
        boardGraph.addVertex("Sarajevo",1035, 620);
        boardGraph.addVertex("Wein",944, 431);
        boardGraph.addVertex("Kobenhavn",831, 130);
        boardGraph.addVertex("Budapest",1004, 464);
        boardGraph.addVertex("Danzig",1017, 183);
        boardGraph.addVertex("Athina",1101, 780);
        boardGraph.addVertex("Warszawa",1084, 272);
        boardGraph.addVertex("Bucuresti",1204, 553);
        boardGraph.addVertex("Sevastopol",1385, 573);
        boardGraph.addVertex("Constantinople",1269, 710);
        boardGraph.addVertex("Angora",1369, 782);
        boardGraph.addVertex("Smyrna",1211, 814);
        boardGraph.addVertex("Edinburgh",421, 55);
        boardGraph.addVertex("Stockholm",968, 31);
        boardGraph.addVertex("Sofia",1122, 635);
        boardGraph.addVertex("Kyiv",1275, 341);
        boardGraph.addVertex("Wilno",1228, 241);
        boardGraph.addVertex("Riga",1123, 66);
        boardGraph.addVertex("Petrograd",1345, 63);
        boardGraph.addVertex("Smolensk",1362, 246);
        boardGraph.addVertex("Kharkov",1454, 407);
        boardGraph.addVertex("Moskva",1472, 216);
        boardGraph.addVertex("Rostov",1506, 476);
        boardGraph.addVertex("Sochi",1499, 591);
        boardGraph.addVertex("Erzurum",1479, 750);
       // boardGraph.addVertex("Snyrna",1515-x, 842-y);
        System.out.println("helloo");

      //  boardGraph = new Graph();
        trainDeck = new Stack<>();
        faceUpCards = new ArrayList<>();
        initializeTrainDeck();
        drawFaceUpCards();

        //scanners
        Scanner longRoutesReader = null;
        Scanner routesReader = null;
        Scanner railroadReader = null;

        InputStream one = getClass().getResourceAsStream("/railroads.tsv");
        if (one == null) {
            System.out.println("Could not load railroads.csv");
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
            City a = findCityByName(info1[4]);
            City b = findCityByName(info1[5]);
            int X = Integer.parseInt(info1[6]);
            int Y = Integer.parseInt(info1[7]);
            boardGraph.addEdge(color, length, isTunnel, engineCount, a, b,X,Y);

        }    
        // longRoutesReader.nextLine();
        while(longRoutesReader.hasNextLine()){
            
            String[] cardInfo = longRoutesReader.nextLine().split("\t");
            System.out.println("long Routes read");//yolo
            City cityA = findCityByName(cardInfo[0]);
            City cityB = findCityByName(cardInfo[1]);
            int worth = Integer.parseInt(cardInfo[2]);
            BufferedImage card = null;
            
            try{
               card = ImageIO.read(GameState.class.getResource("/Images/Long Routes/" + cityA.getName()+ "- " + cityB.getName() + ".png"));
            } catch(IOException e){
               System.out.println("game card error");
            }
            longRoutes.add(new TicketCard(card, cityA, cityB, worth));
        }

        for (int i = 0; i <14; i++)
            {
                if (i<12)
                {
                deck.push(new TrainCard("blue"));
                deck.push(new TrainCard("black"));
                deck.push(new TrainCard("green"));
                deck.push(new TrainCard("orange"));
                deck.push(new TrainCard("pink"));
                deck.push(new TrainCard("red"));
                deck.push(new TrainCard("white"));
                deck.push(new TrainCard("yellow"));
                }
                deck.push(new TrainCard("yellow"));

            }
        Collections.shuffle(deck);

           // System.out.println("line 166");

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
                BufferedImage cardImage = null;
                try {
                    cardImage = ImageIO.read(getClass().getResource("/Images/Cards/" + color + ".png"));
                } catch (IOException e) {
                    System.out.println("error loading train card image: " + color);
                }
                trainDeck.add(new TrainCard(color));
            }
            
        }
       
        //add wild cards
        for (int i = 0; i < 14; i++) {
            BufferedImage cardImage = null;
            try {
                cardImage = ImageIO.read(getClass().getResource("/Images/Cards/wild.png"));
            } catch (IOException e) {
                System.out.println("error loading wild card image");
            }
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
    public Stack <TrainCard> getDeck ()
    {

        return deck;

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
private City findCityByName(String name) {
    for (City city : boardGraph.getVertices()) {
        if (city.getName().equalsIgnoreCase(name.trim())) {
            return city;
        }
    }
    System.out.println("City not found: " + name);
    return null;
}
}



