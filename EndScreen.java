
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class EndScreen extends JFrame
{

    ImageIcon replayBtn, quitBtn, quitHover, replayHover;
    JLabel replay, quit;
    int buttonHeight, buttonWidth;
    EndTrainerIcon a, b, c, d;

    public EndScreen()
    {
        setTitle("Ticket to Ride Europe: Pokemon Express END");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1550, 1080));
        setResizable(false);

        quitBtn = new ImageIcon(getClass().getResource("/Images/End/quit.png"));
        quitHover = new ImageIcon(getClass().getResource("/Images/End/quit hover.png"));
        replayBtn = new ImageIcon(getClass().getResource("/Images/End/replay.png"));
        replayHover = new ImageIcon(getClass().getResource("/Images/End/replay hovered.png"));

        buttonHeight = 46;
        buttonWidth = 129;

        quit = new JLabel(new ImageIcon(quitBtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        replay = new JLabel(new ImageIcon(replayBtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));

        quit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quit.setIcon(new ImageIcon(quitHover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                quit.setIcon(new ImageIcon(quitBtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });

        replay.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StartScreen start = new StartScreen();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                replay.setIcon(new ImageIcon(replayHover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                replay.setIcon(new ImageIcon(replayBtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });

        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        a = new EndTrainerIcon("1", 1, panel);
        b = new EndTrainerIcon("2", 2, panel);
        c = new EndTrainerIcon("3", 3, panel);
        d = new EndTrainerIcon("4", 4, panel);

        panel.add(quit);
        panel.add(replay);

        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        quit.setBounds(30,getHeight()-250,(int)(buttonWidth*2),(int)(buttonHeight*2));
        replay.setBounds(300,getHeight()-250,(int)(buttonWidth*2),(int)(buttonHeight*2));

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