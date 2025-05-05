import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import javax.swing.*;


public class GameScreen extends JFrame {

    private  Game game;
    private static JLabel drawLabel;
    private static JLabel invenLabel;
    private static JLabel purchLabel;
    private static JLabel backLabel;
    private static JLabel arenaLabel;
    private static JLabel trainLabel;
    private static JLabel stationLabel;
    private static JLabel textLabel;
    private static JLabel textBoxLabel;

    private static JLabel black, blue, green, orange, pink, red, white, yellow, wild, back;
    private static JLabel routeback;
    private static ArrayList<JLabel> cardNums;

    private static ImageIcon drawbtn, drawhover;
    private static ImageIcon invenbtn, invenhover;
    private static ImageIcon purchbtn, purchhover;
    private static ImageIcon backbtn, backhover;
    private static ImageIcon arena;
    private static ImageIcon trainBtn, trainhighlight;
    private static ImageIcon stationBtn, stationhighlight;
    private static ImageIcon textBox;
    private static ImageIcon blackImg, blueImg, greenImg, orangeImg, pinkImg, redImg, whiteImg, yellowImg, wildImg, backImg;
    private static ImageIcon routebackImg;

    private static int buttonHeight, buttonWidth;
    private static int cardHeight, cardWidth;

    private static TrainerIcon a, b, c, d;
    CityButtons cityButtons;
    static boolean draw;
    static boolean inven;

    private static boolean purchase = false;
    private static boolean trainselect, stationselect = false;
    private static GameState gameState;
    private static int choice = 1; 
    private static ArrayList<Object> selectedDest;
    private static JList<Object> destinationJList = new JList<>();
    //private int drawCardTwice;

    private static JLabel deckLabel;  // For face-down deck
    private static JLabel discardLabel;  // For discard pile
    private static JLabel[] faceUpLabels;  // For face up cards
    private static int cardsDrawn = 0;  // Track number of cards drawn this turn
    private static boolean drewWild = false;  // Track if a wild card was drawn
    private static ImageIcon deckBackImg;  // For face-down deck

    public GameScreen() {
        game = new Game();
        gameState = new GameState(game);
        GameState.makePlayers();

        setTitle("Ticket to Ride Europe: Pokemon Express GAME");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1550, 1080));
        setResizable(false);

        // Load button images
        drawbtn = new ImageIcon(getClass().getResource("/Images/Game/draw.png"));
        drawhover = new ImageIcon(getClass().getResource("/Images/Game/draw hover.png"));
        invenbtn = new ImageIcon(getClass().getResource("/Images/Game/inventory.png"));
        invenhover = new ImageIcon(getClass().getResource("/Images/Game/inventory hover.png"));
        purchbtn = new ImageIcon(getClass().getResource("/Images/Game/purchase.png"));
        purchhover = new ImageIcon(getClass().getResource("/Images/Game/purchase hover.png"));
        backbtn = new ImageIcon(getClass().getResource("/Images/Game/back.png"));
        backhover = new ImageIcon(getClass().getResource("/Images/Game/back hover.png"));
        arena = new ImageIcon(getClass().getResource("/Images/Game/arena.png"));
        trainBtn = new ImageIcon(getClass().getResource("/Images/Game/locomotive.png"));
        trainhighlight = new ImageIcon(getClass().getResource("/Images/Game/locomotive highlight.png"));
        stationBtn = new ImageIcon(getClass().getResource("/Images/Game/station.png"));
        stationhighlight = new ImageIcon(getClass().getResource("/Images/Game/station highlight.png"));
        textBox = new ImageIcon(getClass().getResource("/Images/Game/textbox.png"));
        
        // Load card images
        blackImg = new ImageIcon(getClass().getResource("/Images/Cards/black.png"));
        blueImg = new ImageIcon(getClass().getResource("/Images/Cards/blue.png"));
        greenImg = new ImageIcon(getClass().getResource("/Images/Cards/green.png"));
        orangeImg = new ImageIcon(getClass().getResource("/Images/Cards/orange.png"));
        pinkImg = new ImageIcon(getClass().getResource("/Images/Cards/pink.png"));
        redImg = new ImageIcon(getClass().getResource("/Images/Cards/red.png"));
        whiteImg = new ImageIcon(getClass().getResource("/Images/Cards/white.png"));
        yellowImg = new ImageIcon(getClass().getResource("/Images/Cards/yellow.png"));
        wildImg = new ImageIcon(getClass().getResource("/Images/Cards/wild.png"));
        backImg = new ImageIcon(getClass().getResource("/Images/Cards/back.png"));
        routebackImg = new ImageIcon(getClass().getResource("/Images/Game/RouteCardBack.png"));
        
        // Set dimensions
        buttonHeight = drawbtn.getIconHeight();
        buttonWidth = drawbtn.getIconWidth();
        cardHeight = blackImg.getIconHeight();
        cardWidth = blackImg.getIconWidth();

        // Initialize main buttons
        drawLabel = new JLabel(new ImageIcon(drawbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        invenLabel = new JLabel(new ImageIcon(invenbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        purchLabel = new JLabel(new ImageIcon(purchbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        backLabel = new JLabel(new ImageIcon(backbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        textLabel = new JLabel("It is now Player " + gameState.getTurn() + "'s turn!");
        trainLabel = new JLabel(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        stationLabel = new JLabel(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        arenaLabel = new JLabel(new ImageIcon(arena.getImage().getScaledInstance((int)(1599*1.1), (int)(940*1.1), Image.SCALE_SMOOTH)));
        textBoxLabel = new JLabel(new ImageIcon(textBox.getImage().getScaledInstance((int)(1490/3.2), (int)(460/3.2), Image.SCALE_SMOOTH)));

        // Initialize card labels
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
        routeback = new JLabel(new ImageIcon(routebackImg.getImage().getScaledInstance((int)(433), (int)(577), Image.SCALE_SMOOTH)));

        // Initialize card numbers
        cardNums = new ArrayList<JLabel>();
        for (int i = 0; i < 9; i++) {
            cardNums.add(new JLabel("0"));
            cardNums.get(i).setFont(new Font("Dialog", Font.BOLD, 30));
            cardNums.get(i).setForeground(Color.BLACK);
        }

        // Initialize draw functionality components
        deckBackImg = new ImageIcon(getClass().getResource("/Images/Cards/back.png"));
        deckLabel = new JLabel(new ImageIcon(deckBackImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
        discardLabel = new JLabel();
        
        // Initialize face up cards
        faceUpLabels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            faceUpLabels[i] = new JLabel();
            faceUpLabels[i].setSize((int)(cardWidth/6), (int)(cardHeight/6));
            final int cardIndex = i;
            faceUpLabels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!draw || cardsDrawn >= 2) return;
                    
                    ArrayList<TrainCard> faceUpCards = game.getFaceUpCards();
                    if (cardIndex < faceUpCards.size()) {
                        TrainCard card = faceUpCards.get(cardIndex);
                        
                        // Check if we can draw this card based on rules
                        if (card.getColor().equals("wild")) {
                            if (cardsDrawn == 0 && !drewWild) {
                                // Can only draw wild as first card
                                Player current = gameState.getPlayers()[gameState.getTurn()-1];
                                current.add(card);
                                game.replaceFaceUpCard(cardIndex);
                                cardsDrawn = 2; // Wild card ends turn
                                drewWild = true;
                                JOptionPane.showMessageDialog(null, "Wild card drawn - turn ends");
                                gameState.nextTurn();
                                nextTurn();
                                close();
                            }
                        } else {
                            // Non-wild card
                            if (cardsDrawn == 0 || (cardsDrawn == 1 && !drewWild)) {
                                Player current = gameState.getPlayers()[gameState.getTurn()-1];
                                current.add(card);
                                game.replaceFaceUpCard(cardIndex);
                                cardsDrawn++;
                                
                                if (cardsDrawn == 2) {
                                    gameState.nextTurn();
                                    nextTurn();
                                    close();
                                }
                            }
                        }
                        
                        // Update the face-up cards display
                        ArrayList<TrainCard> updatedFaceUpCards = game.getFaceUpCards();
                        if (cardIndex < updatedFaceUpCards.size()) {
                            TrainCard newCard = updatedFaceUpCards.get(cardIndex);
                            ImageIcon cardImage = getCardImage(newCard.getColor());
                            faceUpLabels[cardIndex].setIcon(new ImageIcon(cardImage.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
                        }
                    }
                }
            });
        }

        // Add mouse listener to deck for face-down draws
        deckLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!draw || cardsDrawn >= 2) return;
                
                TrainCard card = game.drawFromDeck();
                if (card != null) {
                    Player current = gameState.getPlayers()[gameState.getTurn()-1];
                    current.add(card);
                    cardsDrawn++;
                    
                    String message = "You drew a " + card.getColor() + " card.";
                    if (cardsDrawn < 2) {
                        message += " You may draw one more card.";
                    }
                    JOptionPane.showMessageDialog(null, message);
                    
                    if (cardsDrawn == 2) {
                        gameState.nextTurn();
                        nextTurn();
                        close();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "The deck is empty!");
                }
            }
        });

        // Set up the background panel
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);
        
        // Add all components to the panel
        addComponentsToPanel(panel);
        
        // Set component positions
        setComponentPositions();
        
        // Add mouse listeners
        addMouseListeners();
        
        // Initialize trainer icons
        a = new TrainerIcon("1", 1, panel, GameState.players[0], gameState);
        b = new TrainerIcon("2", 2, panel, GameState.players[1], gameState);
        c = new TrainerIcon("3", 3, panel, GameState.players[2], gameState);
        d = new TrainerIcon("4", 4, panel, GameState.players[3], gameState);
        
        // Initialize city buttons
        cityButtons = new CityButtons(panel, gameState, game);
        cityButtons.disableAll();
        
        // Set initial states
        draw = false;
        inven = false;
        
        // Set the panel as the content pane
        setContentPane(panel);
        
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void addComponentsToPanel(BackgroundPanel panel) {
        panel.add(back);
        panel.add(routeback);
        panel.add(black);
        panel.add(blue);
        panel.add(green);
        panel.add(orange);
        panel.add(pink);
        panel.add(red);
        panel.add(white);
        panel.add(wild);
        panel.add(yellow);
        
        for (JLabel j : cardNums) {
            panel.add(j);
        }

        panel.add(backLabel);
        panel.add(arenaLabel);
        panel.add(drawLabel);
        panel.add(invenLabel);
        panel.add(purchLabel);
        panel.add(textLabel);
        panel.add(textBoxLabel);
        panel.add(trainLabel);
        panel.add(stationLabel);
        
        // Add draw functionality components
        panel.add(deckLabel);
        panel.add(discardLabel);
        for (JLabel label : faceUpLabels) {
            panel.add(label);
        }
    }

    private void setComponentPositions() {
        // Set positions for main buttons
        drawLabel.setBounds(getWidth()-820, getHeight()-200, (int)(buttonWidth*2), (int)(buttonHeight*2));
        invenLabel.setBounds(getWidth()-300, getHeight()-200, (int)(buttonWidth*2), (int)(buttonHeight*2));
        purchLabel.setBounds(getWidth()-560, getHeight()-200, (int)(buttonWidth*2), (int)(buttonHeight*2));
        
        // Set positions for text elements
        textLabel.setBounds(getWidth()-1270, getHeight()-225, (int)(1490/3.2), (int)(460/3.2));
        textBoxLabel.setBounds(getWidth()-1300, getHeight()-225, (int)(1490/3.2), (int)(460/3.2));
        
        // Set positions for arena and back button
        arenaLabel.setBounds(-120, -15, (int)(1599*1.1), (int)(940*1.1));
        backLabel.setBounds(getWidth()-280, 10, (int)(buttonWidth*2), (int)(buttonHeight*2));
        
        // Set positions for train and station buttons
        trainLabel.setBounds(getWidth()-1540, getHeight()-220, (int)(251*0.6), (int)(201*0.6));
        stationLabel.setBounds(getWidth()-1430, getHeight()-230, (int)(251*0.6), (int)(201*0.6));
        
        // Calculate center positions for cards
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int cardSpacing = (int)(cardWidth/5);
        
        // Set positions for deck and discard pile
        deckLabel.setBounds(centerX - (int)(cardWidth/3) - cardSpacing, centerY - (int)(cardHeight/12), (int)(cardWidth/6), (int)(cardHeight/6));
        discardLabel.setBounds(centerX - (int)(cardWidth/3) + cardSpacing, centerY - (int)(cardHeight/12), (int)(cardWidth/6), (int)(cardHeight/6));
        
        // Set positions for face up cards
        int totalWidth = 5 * ((int)(cardWidth/6) + cardSpacing);
        int startX = centerX - totalWidth/2;
        for (int i = 0; i < 5; i++) {
            faceUpLabels[i].setBounds(startX + i * ((int)(cardWidth/6) + cardSpacing), centerY + (int)(cardHeight/6), (int)(cardWidth/6), (int)(cardHeight/6));
        }
        
        // Initially hide certain elements
        arenaLabel.setVisible(false);
        backLabel.setVisible(false);
        deckLabel.setVisible(false);
        discardLabel.setVisible(false);
        for (JLabel label : faceUpLabels) {
            label.setVisible(false);
        }
    }

    public void open() {
        // Hide main screen elements
        drawLabel.setVisible(false);
        invenLabel.setVisible(false);
        purchLabel.setVisible(false);
        
        // Show back button and arena
        backLabel.setVisible(true);
        arenaLabel.setVisible(true);

        if (draw) {
            // Reset draw state
            cardsDrawn = 0;
            drewWild = false;
            
            // Show deck and discard pile
            deckLabel.setVisible(true);
            discardLabel.setVisible(true);
            
            // Update and show face-up cards
            ArrayList<TrainCard> faceUpCards = game.getFaceUpCards();
            for (int i = 0; i < 5; i++) {
                if (i < faceUpCards.size()) {
                    TrainCard card = faceUpCards.get(i);
                    ImageIcon cardImage = getCardImage(card.getColor());
                    faceUpLabels[i].setIcon(new ImageIcon(cardImage.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
                    faceUpLabels[i].setVisible(true);
                } else {
                    faceUpLabels[i].setVisible(false);
                }
            }
            
            // Update discard pile if not empty
            Stack<TrainCard> discardPile = game.getDiscardPile();
            if (!discardPile.isEmpty()) {
                TrainCard topCard = discardPile.peek();
                ImageIcon cardImage = getCardImage(topCard.getColor());
                discardLabel.setIcon(new ImageIcon(cardImage.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
            }
            discardLabel.setVisible(!discardPile.isEmpty());
            
            // Update text
            textLabel.setText("Draw up to 2 cards. Wild cards can only be drawn as the first card.");
        } else if (inven) {
            // Show inventory screen
            back.setVisible(true);
            routeback.setVisible(true);
            black.setVisible(true);
            blue.setVisible(true);
            green.setVisible(true);
            orange.setVisible(true);
            pink.setVisible(true);
            red.setVisible(true);
            white.setVisible(true);
            wild.setVisible(true);
            yellow.setVisible(true);
            
            for (JLabel j : cardNums) {
                j.setVisible(true);
                j.setText(gameState.players[gameState.getTurn()-1].getNumCards().get(Player.colorOrder.get(cardNums.indexOf(j))).size() + "");
            }
            
            textLabel.setText("Your inventory:");
        }
    }

    public void close() {
        // Reset states
        draw = false;
        inven = false;
        purchase = false;
        trainselect = false;
        stationselect = false;
        
        // Hide draw-related components
        deckLabel.setVisible(false);
        discardLabel.setVisible(false);
        for (JLabel label : faceUpLabels) {
            label.setVisible(false);
        }
        
        // Hide inventory screen elements
        back.setVisible(false);
        routeback.setVisible(false);
        black.setVisible(false);
        blue.setVisible(false);
        green.setVisible(false);
        orange.setVisible(false);
        pink.setVisible(false);
        red.setVisible(false);
        white.setVisible(false);
        wild.setVisible(false);
        yellow.setVisible(false);
        
        for (JLabel j : cardNums) {
            j.setVisible(false);
        }
        
        // Hide back button and arena
        backLabel.setVisible(false);
        arenaLabel.setVisible(false);
        
        // Show main screen elements
        drawLabel.setVisible(true);
        invenLabel.setVisible(true);
        purchLabel.setVisible(true);
        
        // Reset text
        textLabel.setText("It is now Player " + gameState.getTurn() + "'s turn!");
    }

    public static void nextTurn()
    {

        trainLabel.setIcon(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        stationLabel.setIcon(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        textLabel.setText("It is now Player " + gameState.getTurn() + "'s turn!");
        purchase = false;

        a.reposition();
        b.reposition();
        c.reposition();
        d.reposition();

    }
    /*public static void addDestCards(ArrayList<Object> cards){
        for(int i=0; i<selectedDest.size(); i++){
            destinationJList.add(i, selectedDest.)
        }
    }*/
    

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
       

        //StartScreen.getFrames()[0].dispose();

       
    

    }



    public static void setTextLabel(String string) {
        // TODO Auto-generated method stub
        textLabel.setText(string);
    }

    public static void reset() {
        // Reset the text label only if appropriate
        if(textLabel.getText().equals("choose another play")) {
            trainselect = false;
            trainLabel.setIcon(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        }
    
        // Reset all mode flags
        purchase = false;
        draw = false;
        inven = false;
        trainselect = false;
        stationselect = false;
    
        // Reset icons just to be safe
        trainLabel.setIcon(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        stationLabel.setIcon(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
    
        // Update label
        textLabel.setText("choose another play");
    }
    

    
    private void addMouseListeners() {
        drawLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
                purchase = true;
                textLabel.setText("Click on a route to purchase it or click on a city to place a train station!");
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
                if (purchase) {
                    stationselect = false;
                    textLabel.setText("Click on a route to purchase it!");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                trainLabel.setIcon(new ImageIcon(trainhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                trainLabel.setIcon(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
            }
        });

        stationLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (purchase) {
                    stationselect = true;
                    textLabel.setText("Click on a city to place a train station!");
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                stationLabel.setIcon(new ImageIcon(stationhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                stationLabel.setIcon(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
            }
        });
    }

    private ImageIcon getCardImage(String color) {
        switch (color.toLowerCase()) {
            case "black": return blackImg;
            case "blue": return blueImg;
            case "green": return greenImg;
            case "orange": return orangeImg;
            case "pink": return pinkImg;
            case "red": return redImg;
            case "white": return whiteImg;
            case "yellow": return yellowImg;
            case "wild": return wildImg;
            default: return backImg;
        }
    }
}