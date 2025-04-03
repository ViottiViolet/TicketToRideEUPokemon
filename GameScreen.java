import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameScreen extends JFrame {

    private final JLabel drawLabel;
    private final JLabel invenLabel;
    private final JLabel purchLabel;
    private final JLabel arenaLabel;
    private final JLabel trainLabel;
    //private final JLabel trainStationLabel;

    private final ImageIcon drawbtn, drawhover;
    private final ImageIcon invenbtn, invenhover;
    private final ImageIcon purchbtn, purchhover;
    private final ImageIcon arena;
    private final ImageIcon trainBtn;

    private final int buttonHeight, buttonWidth;

    private static boolean purchase = false;

    public GameScreen() {

        setTitle("Ticket to Ride Europe: Pokemon Express GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1550, 1080));
        setResizable(false);

        drawbtn = new ImageIcon(getClass().getResource("/Images/Game/draw.png"));
        drawhover = new ImageIcon(getClass().getResource("/Images/Game/draw hover.png"));
        invenbtn = new ImageIcon(getClass().getResource("/Images/Game/inventory.png"));
        invenhover = new ImageIcon(getClass().getResource("/Images/Game/inventory hover.png"));
        purchbtn = new ImageIcon(getClass().getResource("/Images/Game/purchase.png"));
        purchhover = new ImageIcon(getClass().getResource("/Images/Game/purchase hover.png"));
        arena = new ImageIcon(getClass().getResource("/Images/Game/arena.png"));
        trainBtn = new ImageIcon(getClass().getResource("/Images/Game/locomotiveBtn.png"));

        buttonHeight = 46;
        buttonWidth = 129;

        drawLabel = new JLabel(new ImageIcon(drawbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        invenLabel = new JLabel(new ImageIcon(invenbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        purchLabel = new JLabel(new ImageIcon(purchbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        trainLabel = new JLabel(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        arenaLabel = new JLabel(new ImageIcon(arena.getImage().getScaledInstance((int)(1599*1.1), (int)(940*1.1), Image.SCALE_SMOOTH)));

        drawLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("open draw");
                openDraw();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                drawLabel.setIcon(new ImageIcon(drawhover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                drawLabel.setIcon(new ImageIcon(drawbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });

        invenLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("open inventory");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                invenLabel.setIcon(new ImageIcon(invenhover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                invenLabel.setIcon(new ImageIcon(invenbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });

        purchLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("open purchase");
                purchase = true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                purchLabel.setIcon(new ImageIcon(purchhover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                purchLabel.setIcon(new ImageIcon(purchbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });
        trainLabel.addMouseListener(new MouseAdapter() {
            
           
            @Override
               public void mouseClicked(MouseEvent e) {
                if(purchase){
                    System.out.println("select train");
                }
            }
            
        });
        
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        add(arenaLabel);
        add(drawLabel);
        add(invenLabel);
        add(purchLabel);
        add(trainLabel);
        
        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        drawLabel.setBounds(getWidth()-820,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        invenLabel.setBounds(getWidth()-300,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        purchLabel.setBounds(getWidth()-560,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));

        arenaLabel.setBounds(-120,-15,(int)(1599*1.1),(int)(940*1.1));
        arenaLabel.setVisible(false);

        trainLabel.setBounds(getWidth()-1540,getHeight()-250,(int)(251*0.6),(int)(201*0.6));

    }

    public void openDraw()
    {

        arenaLabel.setVisible(true);

        drawLabel.setVisible(false);
        invenLabel.setVisible(false);
        purchLabel.setVisible(false);
        trainLabel.setVisible(false);
    }

    public void closeDraw()
    {

        arenaLabel.setVisible(true);

        drawLabel.setVisible(true);
        invenLabel.setVisible(true);
        purchLabel.setVisible(true);
        trainLabel.setVisible(true);

    }

    public void openInven()
    {

        arenaLabel.setVisible(true);

        drawLabel.setVisible(false);
        invenLabel.setVisible(false);
        purchLabel.setVisible(false);
        trainLabel.setVisible(false);
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
        }
    }

    public static void main(String[] args) {
        new GameScreen();
    }

}
