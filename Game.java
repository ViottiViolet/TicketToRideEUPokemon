import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack <TrainCard> deck;
    public static ArrayList<TicketCard> longRoutes = new ArrayList<>();
    public static ArrayList<TicketCard> normRoutes = new ArrayList<>();
     private Stack <TrainCard> trainDeck = new Stack<>();
    private ArrayList<TrainCard> faceUpCards = new ArrayList<>();
    private static final String[] CARD_COLORS = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow", };
    private static Graph boardGraph;


    


    public Game() {
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
    boardGraph.addVertex("Brindist");
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
    boardGraph.addVertex("Smyma");
    System.out.println("helloo");



        boardGraph = new Graph();
        trainDeck = new Stack<>();
        faceUpCards = new ArrayList<>();
        initializeTrainDeck();
        drawFaceUpCards();
       

        //scanners
        Scanner longRoutesReader = null;
        Scanner routesReader = null;
        Scanner railroadReader = null;
        try {

            railroadReader = new Scanner (new File("railroads.tsv"));
            longRoutesReader = new Scanner(new File("long routes.tsv"));
            routesReader = new Scanner(new File("routes.tsv"));
        } catch(IOException e){
           System.out.println("game reader error");
        }
       
      railroadReader.nextLine();
        while(railroadReader.hasNextLine())
        {
            String str = railroadReader.nextLine();
            System.out.println(str);
            String []info1 = str.split("    ");
            for(String rts: info1)
            {
                System.out.println(rts+"hello");
            }
            String color = info1[0];
            
            int length = Integer.parseInt(info1[1]);
            System.out.println(length);
            System.out.println("HHHHSJKDNKJDJSJ");
            boolean isTunnel = info1[2].equals("yes");
            int engineCount = Integer.parseInt(info1[3]);
            City a = new City (info1[5]);
            City b = new City(info1[6]);
            boardGraph.addEdge(color, length, isTunnel, engineCount, a, b);

        }    
            longRoutesReader.nextLine();
        while(longRoutesReader.hasNextLine()){
            String[] cardInfo = longRoutesReader.nextLine().split("\t");
            City cityA = new City(cardInfo[0]);
            City cityB = new City(cardInfo[1]);
            int worth = Integer.parseInt(cardInfo[2]);
            BufferedImage card = null;
            try{
               card = ImageIO.read(GameState.class.getResource("/Images/Long Routes/" + cityA + "- " + cityB + ".png"));
            } catch(IOException e){
               System.out.println("game card error");
            }
            longRoutes.add(new TicketCard(card, cityA, cityB, worth));

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
        }

        //reading in normal routes
        routesReader.nextLine();
        while(routesReader.hasNextLine()){
            String[] cardInfo = routesReader.nextLine().split("\t");
            City cityA = new City(cardInfo[0]);
            City cityB = new City(cardInfo[1]);
            int worth = Integer.parseInt(cardInfo[2]);
            BufferedImage card = null;
            try{
               card = ImageIO.read(GameState.class.getResource("/Images/Routes/" + cityA + "- " + cityB + ".png"));
            } catch(IOException e){
               System.out.println("game card error");
            }
            normRoutes.add(new TicketCard(card, cityA, cityB, worth));
        }
    }

    private void initializeTrainDeck() {
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

    public static Graph getBoardGraph() 
 
 {
    return boardGraph;
    
 }
}
