import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Game {
    private Stack <TrainCard> deck;
    public static ArrayList<TicketCard> longRoutes = new ArrayList<>();
    public static ArrayList<TicketCard> normRoutes = new ArrayList<>();

    public Game() {
    
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
               card = ImageIO.read(GameState.class.getResource("/Images/Cards/" + cardInfo[3]));
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
               card = ImageIO.read(GameState.class.getResource("/Images/Cards/" + cardInfo[3]));
            } catch(IOException e){
               System.out.println("game card error");
            }
            normRoutes.add(new TicketCard(card, cityA, cityB, worth));
        }

    }
}