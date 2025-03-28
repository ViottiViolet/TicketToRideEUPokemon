import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen() {

        setTitle("Ticket to Ride Europe: Pokemon Express GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1550, 1080));
        setResizable(false);
        
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }

    static class BackgroundPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon bg = new ImageIcon(getClass().getResource("/Images/Game/game bg.png"));
            ImageIcon map = new ImageIcon(getClass().getResource("/Images/Game/map.png"));
            g.drawImage(bg.getImage(), 0, 0, 1920, 1080, this);
            int mapw = 1678;
            int maph = 1080;
            g.drawImage(map.getImage(), 235, 10, (int)(mapw*0.77), (int)(maph*0.77), this);

            /*ImageIcon wild = new ImageIcon(getClass().getResource("/Images/Cards/wild.png"));
            ImageIcon white = new ImageIcon(getClass().getResource("/Images/Cards/white.png"));
            ImageIcon red = new ImageIcon(getClass().getResource("/Images/Cards/red.png"));
            ImageIcon orange = new ImageIcon(getClass().getResource("/Images/Cards/orange.png"));
            ImageIcon black = new ImageIcon(getClass().getResource("/Images/Cards/black.png"));
            ImageIcon pink = new ImageIcon(getClass().getResource("/Images/Cards/pink.png"));
            ImageIcon green = new ImageIcon(getClass().getResource("/Images/Cards/green.png"));
            ImageIcon blue = new ImageIcon(getClass().getResource("/Images/Cards/blue.png"));

            g.drawImage(wild.getImage(), 200, 615, 92, 128, this);
            g.drawImage(white.getImage(), 310, 615, 92, 128, this);
            g.drawImage(red.getImage(), 420, 615, 92, 128, this);
            g.drawImage(orange.getImage(), 530, 615, 92, 128, this);
            g.drawImage(black.getImage(), 640, 615, 92, 128, this);
            g.drawImage(pink.getImage(), 750, 615, 92, 128, this);
            g.drawImage(green.getImage(), 860, 615, 92, 128, this);
            g.drawImage(blue.getImage(), 970, 615, 92, 128, this);*/
        }
    }

    public static void main(String[] args) {
        new GameScreen();
    }

}
