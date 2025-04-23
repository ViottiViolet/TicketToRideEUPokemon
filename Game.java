import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack <TrainCard> deck;
    public static ArrayList<TicketCard> longRoutes = new ArrayList<>();
    public static ArrayList<TicketCard> normRoutes = new ArrayList<>();
    public static Graph boardGraph = new Graph();


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



        //scanners
        Scanner longRoutesReader = null;
        Scanner routesReader = null;
        try{
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
}