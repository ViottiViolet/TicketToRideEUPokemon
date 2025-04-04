import java.awt.image.*;

public class TicketCard{
    private int worth;
    private BufferedImage card;
    private String cityA;
    private String cityB;

    public TicketCard(BufferedImage i, String a, String b, int w) {
        card = i;
        cityA = a;
        cityB = b;
        worth = w;
    }

    public BufferedImage getImage() {
        return card;
    }

    public String getCityA() {
        return cityA;
    }

    public String getCityB() {
        return cityB;
    }

    public int getWorth() {
        return worth;
    }

}