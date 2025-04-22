
import java.awt.*;
import javax.swing.*;

public class EndScreen extends JFrame
{

    public EndScreen()
    {
        setTitle("Ticket to Ride Europe: Pokemon Express END");
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
            ImageIcon bg = new ImageIcon(getClass().getResource("/Images/End/Endscreen_Backgound.png"));
            g.drawImage(bg.getImage(), -100, 0, 1920, 1080, this);
        }
    }

    public static void main(String[] args) {
        new EndScreen();
    }

}