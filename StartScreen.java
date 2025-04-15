import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class StartScreen extends JFrame {

    private final JLabel startLabel;
    private final JLabel rulesLabel;
    private final JLabel rulesScreenLabel;
    private final ImageIcon startBtn;
    private final ImageIcon rulesBtn;
    private final int buttonHeight, buttonWidth;
    private final ImageIcon rulesScreen;

    public void startGame()
    {
        //test 
        GameScreen.main(new String[0]);
        dispose();

    }

    public StartScreen()
    {
        setTitle("Ticket to Ride Europe: Pokemon Express START");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1550, 1080));
        setResizable(false);

        startBtn = new ImageIcon(getClass().getResource("/Images/Start/startButton.png"));
        rulesBtn = new ImageIcon(getClass().getResource("/Images/Start/rulesButton.png"));
        rulesScreen = new ImageIcon(getClass().getResource("/Images/Start/Rules.png"));
        buttonHeight = 156;
        buttonWidth = 436;

        startLabel = new JLabel(new ImageIcon(startBtn.getImage().getScaledInstance((int)(buttonWidth/1.5), (int)(buttonHeight/1.5), Image.SCALE_SMOOTH)));
        rulesLabel = new JLabel(new ImageIcon(rulesBtn.getImage().getScaledInstance((int)(buttonWidth/1.5), (int)(buttonHeight/1.5), Image.SCALE_SMOOTH)));
        rulesScreenLabel = new JLabel(new ImageIcon(rulesScreen.getImage().getScaledInstance(getWidth() - 100, getHeight() - 90, Image.SCALE_SMOOTH)));
        startLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                startGame();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                startLabel.setIcon(new ImageIcon(startBtn.getImage().getScaledInstance((int)(buttonWidth/1.4), (int)(buttonHeight/1.4), Image.SCALE_SMOOTH)));
                startLabel.setBounds(getWidth()-470, 245, (int)(buttonWidth/1.4), (int)(buttonHeight/1.4));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                startLabel.setIcon(new ImageIcon(startBtn.getImage().getScaledInstance((int)(buttonWidth/1.5), (int)(buttonHeight/1.5), Image.SCALE_SMOOTH)));
                startLabel.setBounds(getWidth()-460, 250, (int)(buttonWidth/1.5), (int)(buttonHeight/1.5));
            }
        });
        rulesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("open rules");
                rulesScreenLabel.setVisible(true);
                rulesLabel.setVisible(false);
                startLabel.setVisible(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rulesLabel.setIcon(new ImageIcon(rulesBtn.getImage().getScaledInstance((int)(buttonWidth/1.4), (int)(buttonHeight/1.4), Image.SCALE_SMOOTH)));
                rulesLabel.setBounds(getWidth()-470, 345, (int)(buttonWidth/1.4), (int)(buttonHeight/1.4));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                rulesLabel.setIcon(new ImageIcon(rulesBtn.getImage().getScaledInstance((int)(buttonWidth/1.5), (int)(buttonHeight/1.5), Image.SCALE_SMOOTH)));
                rulesLabel.setBounds(getWidth()-460, 350, (int)(buttonWidth/1.5), (int)(buttonHeight/1.5));
            }

        });
        rulesScreenLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("close rules");
                rulesScreenLabel.setVisible(false);
                rulesLabel.setVisible(true);
                startLabel.setVisible(true); 
                
            }

        });

        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        add(startLabel);
        add(rulesLabel);
        add(rulesScreenLabel);
        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        rulesScreenLabel.setBounds(0, -10, getWidth(), getHeight() - 10);
        rulesScreenLabel.setVisible(false);

        startLabel.setBounds(getWidth()-460,250,(int)(buttonWidth/1.5),(int)(buttonHeight/1.5));
        rulesLabel.setBounds(getWidth()-460,350,(int)(buttonWidth/1.5),(int)(buttonHeight/1.5));
    }

    static class BackgroundPanel extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon bg = new ImageIcon(getClass().getResource("/Images/Start/startScreen.png"));
            g.drawImage(bg.getImage(), -30, 0, getWidth() + 35, getHeight(), this);
        }
    }

    public static void main(String[] args) {
        new StartScreen();
    }

}
