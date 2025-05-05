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
      //  drawCardTwice = 0;
        System.out.println(game.getBoardGraph().getVertices().size());
       


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
        routebackImg =  new ImageIcon(getClass().getResource("/Images/Game/RouteCardBack.png"));

        buttonHeight = 46;
        buttonWidth = 129;

        cardHeight = 1280;
        cardWidth = 920;

        drawLabel = new JLabel(new ImageIcon(drawbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        invenLabel = new JLabel(new ImageIcon(invenbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        purchLabel = new JLabel(new ImageIcon(purchbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        backLabel = new JLabel(new ImageIcon(backbtn.getImage().getScaledInstance((int)(buttonWidth*2), (int)(buttonHeight*2), Image.SCALE_SMOOTH)));
        textLabel = new JLabel("It is now Player " + gameState.getTurn() + "'s turn!");
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
        routeback = new JLabel(new ImageIcon(routebackImg.getImage().getScaledInstance((int)(433), (int)(577), Image.SCALE_SMOOTH)));

        System.out.println(game.getBoardGraph().getVertices().size()+"124");
        cardNums = new ArrayList<JLabel>();
        for (int i = 0; i < 9; i++)
        {
            cardNums.add(new JLabel("wiwi"));
            cardNums.get(i).setFont(new Font("Dialog", Font.BOLD, 30));
            cardNums.get(i).setForeground(Color.BLACK);
        }
        

        drawLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (purchase) return;
                //System.out.println("open draw");
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
                //System.out.println("open inventory");
                inven = true;
                open();
            }
          //  System.out.println(game.getBoardGraph().getVertices().size()+"124");
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
                //System.out.println("open purchase");
                
                if (purchase) return;

                purchase = true;
                stationselect = false;
                trainselect = false;
                textLabel.setText("Click the train to purchase a route or the station to place a train station!");
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
                System.out.println("back");
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
                //System.out.println(game.getBoardGraph().getVertices().size()+"213");
                if(purchase && stationselect==false){
                    
                    //System.out.println("select train");
                    trainselect = true;
                    trainLabel.setIcon(new ImageIcon(trainhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
                    textLabel.setText("Select the cities on either side of the route you would like to purchase!");
                    
                    cityButtons.enableAll(2);
                    
                   
                }
            }
            
        });
        stationLabel.addMouseListener(new MouseAdapter() {
          
            @Override
               public void mouseClicked(MouseEvent e) {
                if(purchase && trainselect == false){
                    //System.out.println("select station");
                    stationselect = true;
                    stationLabel.setIcon(new ImageIcon(stationhighlight.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
                    textLabel.setText("Select the city where you would like to place your train station !");
                   
                    cityButtons.enableAll(1);
                }
            }
            
        });

        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);

        back.addMouseListener(new MouseAdapter() {
          
            @Override
               public void mouseClicked(MouseEvent e) {
                int pnum = gameState.getTurn();
                Player current = gameState.getPlayers()[pnum-1];
                TrainCard card = game.getDeck().pop();
                current.add(card);
                JOptionPane.showMessageDialog(panel,
                                "a "+card.getColor()+" card was added to your hand ",
                                "cardn drawn ",
                                JOptionPane.WARNING_MESSAGE);
                                if(current.getMoves()==2)
                                {
                                    current.resetMoves();
                                    gameState.nextTurn();
                                    nextTurn();
                                    
                                }
            }
           
            
        });
        
        

        /*panel.addMouseListener(new MouseAdapter() {
            
           
            @Override
               public void mouseClicked(MouseEvent e) {
                System.out.println(MouseInfo.getPointerInfo().getLocation().x + ", " + MouseInfo.getPointerInfo().getLocation().y);
                
            }
            
        });*/

        //GameState.makePlayers();
        a = new TrainerIcon("1", 1, panel, GameState.players[0],gameState);
        b = new TrainerIcon("2", 2, panel, GameState.players[1],gameState);
        c = new TrainerIcon("3", 3, panel, GameState.players[2],gameState);
        d = new TrainerIcon("4", 4, panel, GameState.players[3], gameState);
      //  System.out.println(game.getBoardGraph().getVertices().size());

        cityButtons = new CityButtons(panel, gameState,game);
       
        cityButtons.disableAll();

        draw = false;
        inven = false;

        add(back);
        add(routeback);

        add(black);
        add(blue);
        add(green);
        add(orange);
        add(pink);
        add(red);
        add(white);
        add(wild);
        add(yellow);

        for (JLabel j : cardNums)
        {
            add(j);
        }

        add(backLabel);
        add(arenaLabel);

        add(drawLabel);
        add(invenLabel);
        add(purchLabel);
        add(textLabel);
        add(textBoxLabel);
        add(trainLabel);
        add(stationLabel);
        
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
                                }
                            }
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
                    
                    if (cardsDrawn == 2) {
                        gameState.nextTurn();
                        nextTurn();
                    }
                }
            }
        });

        add(deckLabel);
        add(discardLabel);
        for (JLabel label : faceUpLabels) {
            panel.add(label);
        }

        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        drawLabel.setBounds(getWidth()-820,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        invenLabel.setBounds(getWidth()-300,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        purchLabel.setBounds(getWidth()-560,getHeight()-200,(int)(buttonWidth*2),(int)(buttonHeight*2));
        textLabel.setBounds(getWidth()-1270,getHeight()-225,(int)(1490/3.2),(int)(460/3.2));
        textBoxLabel.setBounds(getWidth()-1300,getHeight()-225,(int)(1490/3.2), (int)(460/3.2));

        arenaLabel.setBounds(-120,-15,(int)(1599*1.1),(int)(940*1.1));
        arenaLabel.setVisible(false);

        backLabel.setBounds(getWidth()-280,10,(int)(buttonWidth*2),(int)(buttonHeight*2));
        backLabel.setVisible(false);

        trainLabel.setBounds(getWidth()-1540,getHeight()-220,(int)(251*0.6),(int)(201*0.6));
        stationLabel.setBounds(getWidth()-1430,getHeight()-230,(int)(251*0.6),(int)(201*0.6));

        back.setBounds(getWidth()-600,getHeight()-500,(int)(cardWidth/6),(int)(cardHeight/6));
        back.setVisible(false);
        routeback.setBounds(getWidth()-1370,getHeight()-700,(int)(433*1.6),(int)(577*1.2));
        routeback.setVisible(false);

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

        for (int i = 0; i < 9; i++)
        {
            cardNums.get(i).setBounds(95 + i*160,300,(int)(cardWidth/6),(int)(cardHeight/6));
            cardNums.get(i).setVisible(false);
        }

        List<String> optionList = new ArrayList<String>();
        Stack<TicketCard> tickets = game.getNormRoutes();
        Stack<TicketCard> lTickets = game.getLongRoutes();
        Collections.shuffle(tickets);
        Collections.shuffle(lTickets);
        
        TicketCard ticket = tickets.pop();

        optionList.add("discard: "+ticket.getCityA().getName()+"->"+ticket.getCityB().getName()+" points: "+ticket.getWorth());
        ticket = tickets.pop();
        optionList.add("discard: "+ticket.getCityA().getName()+"->"+ticket.getCityB().getName()+" points: "+ticket.getWorth());
        ticket = tickets.pop();
        optionList.add("discard: "+ticket.getCityA().getName()+"->"+ticket.getCityB().getName()+" points: "+ticket.getWorth());
        ticket = lTickets.pop();
        optionList.add("discard: "+ticket.getCityA().getName()+"->"+ticket.getCityB().getName()+" points: "+ticket.getWorth());
        Object[] options =  optionList.toArray();
         JList<Object> list = new JList<>(options);
         selectedDest = new ArrayList<>();
         selectedDest.add(list);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);
                
                                    choice = JOptionPane.showOptionDialog(null, scrollPane,
                                    "Destination cards",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null, null, null);
                                    ;
                                    
                                    if (choice == JOptionPane.OK_OPTION) {
                                        Object selected = list.getSelectedValue();
                                       selectedDest.remove(selected);
                                       optionList.remove(optionList.indexOf(selected));
                                       options =  optionList.toArray();
                                       list = new JList<>(options);
                                       list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

                                        scrollPane = new JScrollPane(list);
                                         JOptionPane.showOptionDialog(null, scrollPane,
                                                    "Destination Cards",
                                                    JOptionPane.OK_CANCEL_OPTION,
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    null, null, null);
                                                     selected = list.getSelectedValue();
                                                     selectedDest.remove(selected);
                                                     
                                                    } 
                
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
            // Reset drawing state
            cardsDrawn = 0;
            drewWild = false;
            
            // Show draw screen elements
            deckLabel.setVisible(true);
            discardLabel.setVisible(true);
            
            // Update face up cards
            ArrayList<TrainCard> faceUpCards = game.getFaceUpCards();
            for (int i = 0; i < 5; i++) {
                if (i < faceUpCards.size()) {
                    TrainCard card = faceUpCards.get(i);
                    ImageIcon cardImg = new ImageIcon(getClass().getResource("/Images/Cards/" + card.getColor() + ".png"));
                    faceUpLabels[i].setIcon(new ImageIcon(cardImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
                    faceUpLabels[i].setVisible(true);
                } else {
                    faceUpLabels[i].setVisible(false);
                }
            }
            
            // Update discard pile if there are discarded cards
            if (!game.getDiscardPile().isEmpty()) {
                TrainCard topCard = game.getDiscardPile().peek();
                ImageIcon cardImg = new ImageIcon(getClass().getResource("/Images/Cards/" + topCard.getColor() + ".png"));
                discardLabel.setIcon(new ImageIcon(cardImg.getImage().getScaledInstance((int)(cardWidth/6), (int)(cardHeight/6), Image.SCALE_SMOOTH)));
            }
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

            for (int i = 0; i < 9; i++)
            {
                cardNums.get(i).setVisible(true);
                cardNums.get(i).setText(gameState.players[gameState.getTurn()-1].getNumCards().get(Player.colorOrder.get(i)).size() + "");
            }
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
            deckLabel.setVisible(false);
            discardLabel.setVisible(false);
            for (JLabel label : faceUpLabels) {
                label.setVisible(false);
            }
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

            for (JLabel j : cardNums)
            {
                j.setVisible(false);
            }

            inven = false;
        }


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
    

    
}