import java.awt.*;
import javax.swing.*;

public class GameScreen extends JFrame {

    public GameScreen() {

        setTitle("Ticket to Ride Europe: Thomas the Train Edition GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1300, 800));
        
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
        }
    }

    public static void main(String[] args) {
        new GameScreen();
    }

}