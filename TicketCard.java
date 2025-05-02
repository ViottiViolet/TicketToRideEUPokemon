import java.awt.image.*;

public class TicketCard{
    
    private int worth;
    private BufferedImage card;
    private City cityA;
    private City cityB;

    public TicketCard(BufferedImage i, City a, City b, int w) {
        card = i;
        cityA = a;
        cityB = b;
        worth = w;
    }

    public BufferedImage getImage() {
        return card;
    }

    public City getCityA() {
        return cityA;
    }

    public City getCityB() {
        return cityB;
    }

    public int getWorth() {
        return worth;
    }

}

