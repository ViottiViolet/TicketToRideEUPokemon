import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameScreen extends JFrame {

    private final JLabel drawLabel;
    private final JLabel invenLabel;
    private final JLabel purchLabel;
    private final JLabel backLabel;
    private final JLabel arenaLabel;
    private final JLabel trainLabel;
    private final JLabel stationLabel;
    private final JLabel textLabel;
    private final JLabel textBoxLabel;

    private final JLabel black, blue, green, orange, pink, red, white, yellow, wild, back;

    private final ImageIcon drawbtn, drawhover;
    private final ImageIcon invenbtn, invenhover;
    private final ImageIcon purchbtn, purchhover;
    private final ImageIcon backbtn, backhover;
    private final ImageIcon arena;
    private final ImageIcon trainBtn, trainhighlight;
    private final ImageIcon stationBtn, stationhighlight;
    private final ImageIcon textBox;
    private final ImageIcon blackImg, blueImg, greenImg, orangeImg, pinkImg, redImg, whiteImg, yellowImg, wildImg, backImg;

    private final int buttonHeight, buttonWidth;
    private final int cardHeight, cardWidth;

    TrainerIcon a, b, c, d;
    CityButtons cityButtons;
    boolean draw, inven;

    private static boolean purchase = false;
    private static boolean trainselect, stationselect = false;

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
        backbtn = new ImageIcon(getClass().getResource("/Images/Game/back.png"));
        backhover = new ImageIcon(getClass().getResource("/Images/Game/back hover.png"));
        arena = new ImageIcon(getClass().getResource("/Images/Game/arena.png"));
        trainBtn = new ImageIcon(getClass().getResource("/Images/Game/locomotiveBtn.png"));
        trainhighlight = new ImageIcon(getClass().getResource("/Images/Game/locomotive highlight.png"));
        stationBtn = new ImageIcon(getClass().getResource("/Images/Game/station.png"));
        stationhighlight = new ImageIcon(getClass().getResource("/Images/Game/station highlight.png"));
        textBox = new ImageIcon(getClass().getResource("/Images/Game/textbox.png"));

        blackImg = new ImageIcon(getClass().getResource("/Images/Cards/black.png"));
        blueImg = new ImageIcon(getClass().getResource("/Images/Cards/blue.png"));
        greenImg = new ImageIcon(getClass().getResource("/Images/Cards/green.png"));
        orangeImg = new ImageIcon(getClass().getResource("/Images/Cards/orange.png"));
        pinkImg = new ImageIcon(getClass().getResource("/Images/Cards/pink.png"));
        redImg = new ImageIcon(getClass().getResource("/Images/Cards/red.png"));
        whiteImg = new ImageIcon(getClass().getResource("/Images/Cards/white.png"));
        wildImg = new ImageIcon(getClass().getResource("/Images/Cards/wild.png"));
        yellowImg = new ImageIcon(getClass().getResource("/Images/Cards/yellow.png"));
        backImg = new ImageIcon(getClass().getResource("/Images/Cards/back.png"));

        buttonHeight = 46;
        buttonWidth = 129;

        cardHeight = 1280;
        cardWidth = 920;

        drawLabel = new JLabel(new ImageIcon(drawbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        invenLabel = new JLabel(new ImageIcon(invenbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        purchLabel = new JLabel(new ImageIcon(purchbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        backLabel = new JLabel(new ImageIcon(backbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        textLabel = new JLabel("Click the train to purchase a route or the station to place a train station!");
        trainLabel = new JLabel(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        stationLabel = new JLabel(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        arenaLabel = new JLabel(new ImageIcon(arena.getImage().getScaledInstance((int)(1599*1.1), (int)(940*1.1), Image.SCALE_SMOOTH)));
        textBoxLabel = new JLabel(new ImageIcon(textBox.getImage().getScaledInstance((int)(1490/3.2), (int)(460/3.2), Image.SCALE_SMOOTH)));

        black = new JLabel(new ImageIcon(blackImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        blue = new JLabel(new ImageIcon(blueImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        green = new JLabel(new ImageIcon(greenImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        orange = new JLabel(new ImageIcon(orangeImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        pink = new JLabel(new ImageIcon(pinkImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        red = new JLabel(new ImageIcon(redImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        white = new JLabel(new ImageIcon(whiteImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        wild = new JLabel(new ImageIcon(wildImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        yellow = new JLabel(new ImageIcon(yellowImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        back = new JLabel(new ImageIcon(backImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));

        drawLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (purchase) return;
                System.out.println("open draw");
                draw = true;
                open();
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
                inven = true;
                open();
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
                textLabel.setVisible(true);
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
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                close();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backLabel.setIcon(new ImageIcon(backhover.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                backLabel.setIcon(new ImageIcon(backbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
            }
        });

        trainLabel.addMouseListener(new MouseAdapter() {
            
           
            @Override
               public void mouseClicked(MouseEvent e) {
                if(purchase && stationselect==false){
                    System.out.println("select train");
                    trainselect = true;
                    trainLabel.setIcon(new ImageIcon(trainhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
                    textLabel.setText("Select the cities on either side of the route you would like to purchase!");
                    textLabel.setVisible(true);
                    
                    cityButtons.enableAll(2);
                }
            }
            
        });
        stationLabel.addMouseListener(new MouseAdapter() {
            
           
            @Override
               public void mouseClicked(MouseEvent e) {
                if(purchase && trainselect == false){
                    System.out.println("select station");
                    stationselect = true;
                    stationLabel.setIcon(new ImageIcon(stationhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
                    textLabel.setText("Select the city where you would like to place your train station !");
                    textLabel.setVisible(true);
                   
                    cityButtons.enableAll(1);
                }
            }
            
        });
        
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        panel.addMouseListener(new MouseAdapter() {
            
           
            @Override
               public void mouseClicked(MouseEvent e) {
                System.out.println(MouseInfo.getPointerInfo().getLocation().x + ", " + MouseInfo.getPointerInfo().getLocation().y);
                
            }
            
        });

        a = new TrainerIcon("1", 1, panel);
        b = new TrainerIcon("2", 2, panel);
        c = new TrainerIcon("3", 3, panel);
        d = new TrainerIcon("4", 4, panel);

        cityButtons = new CityButtons(panel);
        cityButtons.disableAll();

        draw = false;
        inven = false;

        add(back);

        add(black);
        add(blue);
        add(green);
        add(orange);
        add(pink);
        add(red);
        add(white);
        add(wild);
        add(yellow);

        add(backLabel);
        add(arenaLabel);

        add(drawLabel);
        add(invenLabel);
        add(purchLabel);
        add(textLabel);
        add(textBoxLabel);
        add(trainLabel);
        add(stationLabel);
        
        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        drawLabel.setBounds(getWidth()-820,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        invenLabel.setBounds(getWidth()-300,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        purchLabel.setBounds(getWidth()-560,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        textLabel.setBounds(getWidth()-1270,getHeight()-225,(int)(1490/3.2),(int)(460/3.2));
        textLabel.setVisible(false);
        textBoxLabel.setBounds(getWidth()-1300,getHeight()-225,(int)(1490/3.2), (int)(460/3.2));

        arenaLabel.setBounds(-120,-15,(int)(1599*1.1),(int)(940*1.1));
        arenaLabel.setVisible(false);

        backLabel.setBounds(getWidth()-280,10,(int)(buttonWidth*2),(int)(buttonHeight*2));
        backLabel.setVisible(false);

        trainLabel.setBounds(getWidth()-1540,getHeight()-220,(int)(251*0.6),(int)(201*0.6));
        stationLabel.setBounds(getWidth()-1430,getHeight()-230,(int)(251*0.6),(int)(201*0.6));

        back.setBounds(getWidth()-600,getHeight()-500,(int)(cardWidth/6),(int)(cardHeight/6));
        back.setVisible(false);

        black.setBounds(40,150,(int)(cardWidth/6),(int)(cardHeight/6));
        blue.setBounds(200,150,(int)(cardWidth/6),(int)(cardHeight/6));
        green.setBounds(360,150,(int)(cardWidth/6),(int)(cardHeight/6));
        orange.setBounds(520,150,(int)(cardWidth/6),(int)(cardHeight/6));
        pink.setBounds(680,150,(int)(cardWidth/6),(int)(cardHeight/6));
        red.setBounds(840,150,(int)(cardWidth/6),(int)(cardHeight/6));
        white.setBounds(1000,150,(int)(cardWidth/6),(int)(cardHeight/6));
        yellow.setBounds(1160,150,(int)(cardWidth/6),(int)(cardHeight/6));
        wild.setBounds(1320,150,(int)(cardWidth/6),(int)(cardHeight/6));
        black.setVisible(false);
        blue.setVisible(false);
        green.setVisible(false);
        orange.setVisible(false);
        pink.setVisible(false);
        red.setVisible(false);
        white.setVisible(false);
        yellow.setVisible(false);
        wild.setVisible(false);

    }

    public void open()
    {
        arenaLabel.setVisible(true);
        backLabel.setVisible(true);

        drawLabel.setVisible(false);
        invenLabel.setVisible(false);
        purchLabel.setVisible(false);

        if (draw)
        {
             back.setVisible(true);
        }
        else if (inven)
        {
            black.setVisible(true);
            blue.setVisible(true);
            green.setVisible(true);
            orange.setVisible(true);
            pink.setVisible(true);
            red.setVisible(true);
            white.setVisible(true);
            yellow.setVisible(true);
            wild.setVisible(true);
        }

    }

    public void close()
    {

        arenaLabel.setVisible(false);
        backLabel.setVisible(false);

        drawLabel.setVisible(true);
        invenLabel.setVisible(true);
        purchLabel.setVisible(true);

        if (draw)
        {
            back.setVisible(false);
            draw = false;
        }
        else if (inven)
        {
            black.setVisible(false);
            blue.setVisible(false);
            green.setVisible(false);
            orange.setVisible(false);
            pink.setVisible(false);
            red.setVisible(false);
            white.setVisible(false);
            yellow.setVisible(false);
            wild.setVisible(false);
            inven = false;
        }


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
