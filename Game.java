import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    public static ArrayList<TicketCard> longRoutes = new ArrayList<>();
    public static ArrayList<TicketCard> normRoutes = new ArrayList<>();
    private ArrayList<TrainCard> trainDeck = new ArrayList<>();
    private ArrayList<TrainCard> faceUpCards = new ArrayList<>();
    private static final String[] CARD_COLORS = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow"};
    private Graph gameGraph;

    public Game() {
        gameGraph = new Graph();
        trainDeck = new ArrayList<>();
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

        //reading in long routes
        longRoutesReader.nextLine();
        while(longRoutesReader.hasNextLine()){
            String[] cardInfo = longRoutesReader.nextLine().split("\t");
            String cityA = cardInfo[0];
            String cityB = cardInfo[1];
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
            String cityA = cardInfo[0];
            String cityB = cardInfo[1];
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
                    System.out.println("Error loading train card image: " + color);
                }
                trainDeck.add(new TrainCard(color, cardImage));
            }
        }
       
        //add wild cards
        for (int i = 0; i < 14; i++) {
            BufferedImage cardImage = null;
            try {
                cardImage = ImageIO.read(getClass().getResource("/Images/Cards/wild.png"));
            } catch (IOException e) {
                System.out.println("Error loading wild card image");
            }
            trainDeck.add(new TrainCard("wild", cardImage));
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

    public Graph getGameGraph() {
        return gameGraph;
    }
}