import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack <TrainCard> deck;
    public static ArrayList<TicketCard> longRoutes = new ArrayList<>();
    public static ArrayList<TicketCard> normRoutes = new ArrayList<>();

    public static Graph boardGraph = new Graph();

    private ArrayList<TrainCard> faceUpCards = new ArrayList<>();
    private static final String[] CARD_COLORS = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow"};
    private Graph gameGraph;

    public Game() {
    
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

        gameGraph = new Graph();
        faceUpCards = new ArrayList<>();
        initializeTrainDeck();
        drawFaceUpCards();
       

        //scanners
        Scanner longRoutesReader = null;
        Scanner routesReader = null;
        try {
            longRoutesReader = new Scanner(new File("long routes.tsv"));
            routesReader = new Scanner(new File("routes.tsv"));
        } catch(IOException e){
           System.out.println("game reader error");
        }
//dddd
        //reading in long routes
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
                deck.push(new TrainCard(color));
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
            deck.push(new TrainCard("wild"));
        }
       
        //shuffle deck
        Collections.shuffle(deck);
    }

    private void drawFaceUpCards() {
        for (int i = 0; i < 5; i++) {
            if (!deck.isEmpty()) {
                faceUpCards.add(deck.pop());
            }
        }
    }

    public ArrayList<TrainCard> getFaceUpCards() {
        return faceUpCards;
    }

    public TrainCard drawFromDeck() {
        if (deck.isEmpty()) {
            return null;
        }
        return deck.pop();
    }

    public void replaceFaceUpCard(int index) {
        if (!deck.isEmpty()) {
            faceUpCards.set(index, deck.pop());
        }
    }

    public Graph getGameGraph() {
        return gameGraph;
    }
}