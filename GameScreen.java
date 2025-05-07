import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import javax.swing.*;




public class GameScreen extends JFrame {
   
    private List<TrainCard> selectedCards = new ArrayList<>();
private List<TrainCard> faceUpCards = new ArrayList<>();
private List<JLabel> faceUpLabels = new ArrayList<>();
private static BackgroundPanel panel;
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
    private List<JLabel> drawCardLabels = new ArrayList<>();






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
    private static List<TicketCard> selectedDest = new ArrayList<>();
    private static List<TicketCard> selectedDest1 = new ArrayList<>();
    private static List<TicketCard> selectedDest2 = new ArrayList<>();
    private static List<TicketCard> selectedDest3 = new ArrayList<>();
    private static List<TicketCard> selectedDest4 = new ArrayList<>();
    private static List<JLabel> dCardLabel = new ArrayList<>();
    private List<ImageIcon> dCardImage = new ArrayList<>();
    private List<ImageIcon> dLCardImage = new ArrayList<>();  
    private static List<TicketCard> dCardData = new ArrayList<>();
    private static boolean invenVisible;
    Stack<TicketCard> tickets;
    Stack<TicketCard> lTickets;
    //private int drawCardTwice;


   


    public GameScreen() {
        game = new Game();
        gameState = new GameState(game);
      //  drawCardTwice = 0;
        System.out.println(game.getBoardGraph().getVertices().size());
        tickets = game.getNormRoutes();
         lTickets = game.getLongRoutes();




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
        for(int i=0; i<tickets.size(); i++ ){
            dCardImage.add(new ImageIcon(getClass().getResource("/Images/Routes/"+tickets.get(i).getCityA().getName()+"- "+tickets.get(i).getCityB().getName()+".png")));
        }
        for(int i=0; i<lTickets.size(); i++ ){
            dLCardImage.add(new ImageIcon(getClass().getResource("/Images/Long Routes/"+lTickets.get(i).getCityA().getName()+"- "+lTickets.get(i).getCityB().getName()+".png")));
        }
        for(int i=0; i<tickets.size(); i++ ){
            TicketCard card = tickets.get(i);
           dCardLabel.add(new JLabel(new ImageIcon(dCardImage.get(i).getImage().getScaledInstance((int)(433), (int)(557), Image.SCALE_SMOOTH))));
           dCardData.add(card);
        }
        for(int i=0; i<lTickets.size(); i++ ){
            TicketCard Lcard = lTickets.get(i);
           dCardLabel.add(new JLabel(new ImageIcon(dLCardImage.get(i).getImage().getScaledInstance((int)(433/1.5), (int)(557/1.5), Image.SCALE_SMOOTH))));
           dCardData.add(Lcard);
        }
         //testing
     /*JButton giveAllCardsButton = new JButton("Give All Cards");
giveAllCardsButton.setBounds(20, 20, 150, 30); // Adjust position and size as needed
this.add(giveAllCardsButton);


giveAllCardsButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String[] colors = {"black", "blue", "green", "orange", "pink", "red", "white", "yellow", "wild"}; // exclude "wild" if you don't want it
        for (Player player : gameState.getPlayers()) {
           for (String color : colors) {
                for (int i = 0; i < 5; i++) {
                   player.add(new TrainCard(color),"test");
                }
            }
       }
        System.out.println("All players received 5 of each train card.");
        panel.repaint(); // update GUI if needed
    }
});*/


        drawLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (purchase) return;
       
                draw = true;
                open();
       
                // Clear any previously shown cards
                for (JLabel label : drawCardLabels) {
                    panel.remove(label);
                }
                drawCardLabels.clear();
       
                Stack<TrainCard> deck = game.getDeck();
                int maxCardsToShow = Math.min(5, deck.size());
                int baseX = getWidth() - 1000;
                int baseY = getHeight() - 500;
       
                for (int i = 0; i < maxCardsToShow; i++) {
                    TrainCard card = deck.get(deck.size() - 1 - i);
       
                    String color = card.getColor().toLowerCase();
                    ImageIcon cardIcon;
                    switch (color) {
                        case "black": cardIcon = blackImg; break;
                        case "blue": cardIcon = blueImg; break;
                        case "green": cardIcon = greenImg; break;
                        case "orange": cardIcon = orangeImg; break;
                        case "pink": cardIcon = pinkImg; break;
                        case "red": cardIcon = redImg; break;
                        case "white": cardIcon = whiteImg; break;
                        case "yellow": cardIcon = yellowImg; break;
                        case "wild": cardIcon = wildImg; break;
                        default: cardIcon = backImg; break;
                    }
       
                    JLabel cardLabel = new JLabel(new ImageIcon(
                        cardIcon.getImage().getScaledInstance((int)(cardWidth / 6), (int)(cardHeight / 6), Image.SCALE_SMOOTH)
                    ));
                    cardLabel.setBounds(baseX + i * 140, baseY, cardWidth / 6, cardHeight / 6);
       
                    drawCardLabels.add(cardLabel);
                    add(cardLabel);
                }
       
                revalidate();
                repaint();
            }
        });
       
        invenLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("open inventory");
                inven = true;
                open();
                 playerDestCards();
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
        this.panel = panel;
        panel.setLayout(null);


        back.addMouseListener(new MouseAdapter() {
         
            @Override
               public void mouseClicked(MouseEvent e) {
                int pnum = gameState.getTurn();
                Player current = gameState.getPlayers()[pnum-1];
                TrainCard card = game.getDeck().pop();
                current.add(card, "deck");
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


        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                System.out.println("Clicked at: (" + point.x + ", " + point.y + ")");
            }
        });


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
         for(JLabel i : dCardLabel){
            add(i);
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


        invenVisible = false;


        for (int i = 0; i < 9; i++)
        {
            cardNums.get(i).setBounds(95 + i*160,300,(int)(cardWidth/6),(int)(cardHeight/6));
            cardNums.get(i).setVisible(false);
        }
         for(int j = 0; j< dCardLabel.size(); j++){
            dCardLabel.get(j).setBounds(295 + j*160,300,(int)(433*1.6),(int)(577*1.2));
            dCardLabel.get(j).setVisible(false);
   
        }


   
        Collections.shuffle(tickets);
        Collections.shuffle(lTickets);


         for (int playerTurn = 1; playerTurn <= 4; playerTurn++) {
     selectedDest = null;
   
    switch (playerTurn) {
        case 1: selectedDest = selectedDest1; break;
        case 2: selectedDest = selectedDest2; break;
        case 3: selectedDest = selectedDest3; break;
        case 4: selectedDest = selectedDest4; break;
    }
       
        handleDestinationSelection(playerTurn, selectedDest, tickets, lTickets);  
}
        /*TicketCard ticket = tickets.pop();


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
                                                     
                                                    } */
               
                                    }
                             private void handleDestinationSelection(int playerTurn, List<TicketCard> selectedDest, Stack<TicketCard> tickets, Stack<TicketCard> lTickets) {
                                            List<String> optionList = new ArrayList<>();
                                            List<TicketCard> optionL = new ArrayList<>();
                                       
                                            for (int i = 0; i < 3; i++) {
                                                TicketCard ticket = tickets.pop();
                                                optionL.add(ticket);
                                                selectedDest.add(ticket);
                                                optionList.add("discard: " + ticket.getCityA().getName() + "->" + ticket.getCityB().getName() + " points: " + ticket.getWorth());
                                            }
                                       
                                            TicketCard longTicket = lTickets.pop();
                                            optionL.add(longTicket);
                                            selectedDest.add(longTicket);
                                   
                                            optionList.add("discard: " + longTicket.getCityA().getName() + "->" + longTicket.getCityB().getName() + " points: " + longTicket.getWorth());
                                       
                                            for (int discardCount = 0; discardCount < 2; discardCount++) {
                                                Object[] options = optionList.toArray();
                                                JList<Object> list = new JList<>(options);
                                                list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                                JScrollPane scrollPane = new JScrollPane(list);
                                       
                                                int choice = JOptionPane.showOptionDialog(null, scrollPane,
                                                        "Player " + playerTurn + " - Choose ticket to discard (" + (discardCount + 1) + "/2)",
                                                        JOptionPane.OK_CANCEL_OPTION,
                                                        JOptionPane.PLAIN_MESSAGE,
                                                        null, null, null);
                                       
                                                if (choice == JOptionPane.OK_OPTION) {
                                                    String selected = (String) list.getSelectedValue();
                                                    for (int i = 0; i < optionList.size(); i++) {
                                                        if (optionList.get(i).equals(selected)) {
                                                            TicketCard selectedTicket = optionL.get(i);
                                                            selectedDest.remove(selectedTicket);
                                                            optionL.remove(i);
                                                            optionList.remove(i);
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            if (playerTurn==1)
                                            {
                                            selectedDest1=selectedDest;
                                            System.out.println(selectedDest1);
                                            }
                                            else if (playerTurn==2)
                                            {
                                            selectedDest2=selectedDest;
                                            System.out.println(selectedDest2);
                                            }
                                            else if (playerTurn==3)
                                            {
                                            selectedDest3=selectedDest;
                                            System.out.println(selectedDest3);
                                            }
                                            else if (playerTurn==4)
                                            {
                                            selectedDest4=selectedDest;
                                            System.out.println(selectedDest4);
                                            }
                                        }
   


                                    public void open()
                                    {
                                        arenaLabel.setVisible(true);
                                        backLabel.setVisible(true);
                                   
                                        drawLabel.setVisible(false);
                                        invenLabel.setVisible(false);
                                        purchLabel.setVisible(false);
                                   
                                        arenaLabel.setLayout(null); // Use absolute positioning
                                   
                                        arenaLabel.revalidate();
                                        arenaLabel.repaint();
                                   
                                        if (draw) {
                                            // Only show face-up cards in draw mode
                                            displayFaceUpCards();
                                            back.setVisible(true);
                                            routeback.setVisible(true);
                                            System.out.println("DRAW mode: " + game.getDeck().size());
                                        } else {
                                            // Ensure face-up cards are cleared when NOT in draw mode
                                            arenaLabel.removeAll();
                                            faceUpCards.clear();
                                            faceUpLabels.clear();
                                        }
                                   
                                        if (inven) {
                                            // Show inventory cards only
                                            black.setVisible(true);
                                            blue.setVisible(true);
                                            green.setVisible(true);
                                            orange.setVisible(true);
                                            pink.setVisible(true);
                                            red.setVisible(true);
                                            white.setVisible(true);
                                            yellow.setVisible(true);
                                            wild.setVisible(true);
                                             invenVisible = true;
                                   
                                            for (int i = 0; i < 9; i++) {
                                                cardNums.get(i).setVisible(true);
                                                cardNums.get(i).setText(
                                                    gameState.players[gameState.getTurn()-1].getNumCards().get(Player.colorOrder.get(i)).size() + ""
                                                );
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
            back.setVisible(false);
            routeback.setVisible(false);


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


             invenVisible = false;


            for (JLabel j : cardNums)
            {
                j.setVisible(false);
            }


            inven = false;
            for(int j = 0; j< dCardLabel.size(); j++){
                dCardLabel.get(j).setVisible(false);
               
                repaint();
            }
        }
        for (JLabel label : drawCardLabels) {
            panel.remove(label);
        }
        arenaLabel.removeAll();
        faceUpCards.clear();
        faceUpLabels.clear();


        drawCardLabels.clear();
        panel.repaint();




    }
     public static void playerDestCards(){
        for(int j = 0; j< dCardLabel.size(); j++){
            dCardLabel.get(j).setBounds(295 + j*160,300,(int)(433*1.6),(int)(577*1.2));
            dCardLabel.get(j).setVisible(false);
           


        }
        if (!invenVisible) {
            return;
        }
   
        selectedDest= null;
        if (gameState.getTurn() == 1) {
            selectedDest = selectedDest1;
        }
        else if (gameState.getTurn() == 2) {
            selectedDest = selectedDest2;
        }
        else if (gameState.getTurn() == 3) {
            selectedDest = selectedDest3;
        }
        else if (gameState.getTurn() == 4) {
            selectedDest = selectedDest4;
        }


            int xOffSet = 0;
        HashSet<Integer> matchedIndexes = new HashSet<>();
        for(TicketCard selected: selectedDest){
            for(int k =0; k< dCardLabel.size(); k++){
                 if(matchedIndexes.contains(k)) continue;
                 
                TicketCard c = dCardData.get(k);
             
            String a1 = selected.getCityA().getName().trim();
            String b1 = selected.getCityB().getName().trim();
            String a2 = c.getCityA().getName().trim();
            String b2 = c.getCityB().getName().trim();


            boolean match =
                (a1.equals(a2) && b1.equals(b2)) ||
                (a1.equals(b2) && b1.equals(a2));
                System.out.println("Comparing: " + a1 + " - " + b1 + " to " + a2 + " - " + b2 + " | Match: " + match);


                if (match) {
                    JLabel label = dCardLabel.get(k);
                    label.setBounds(50 + xOffSet*250, 500, (int)(433*1.6),(int)(577*1.2));
                    label.setVisible(true);
                    matchedIndexes.add(k);
                    xOffSet++;
                    break;
                }
            }
        }
       
    }


    public static void nextTurn()
    {
      //  if(gameState.getEnd())
       // {
        //    ArrayList<TrainStation> stations = gameState.getCurrentPlayer().getUsedStations();
        //    for(int i = 0; i< stations.size();i++)
       //     {
        //       // ArrayList
               
        //    }
       // }


        trainLabel.setIcon(new ImageIcon(trainBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        stationLabel.setIcon(new ImageIcon(stationBtn.getImage().getScaledInstance((int)(251*0.6), (int)(201*0.6), Image.SCALE_SMOOTH)));
        textLabel.setText("It is now Player " + gameState.getTurn() + "'s turn!");
        purchase = false;


        a.reposition();
        b.reposition();
        c.reposition();
        d.reposition();
         for(int j = 0; j< dCardLabel.size(); j++){
            dCardLabel.get(j).setBounds(295 + j*160,300,(int)(433*1.6),(int)(577*1.2));
            dCardLabel.get(j).setVisible(false);
           


        playerDestCards();




    }
}
   
   
    public void displayFaceUpCards() {
        arenaLabel.removeAll();
        arenaLabel.setLayout(null);
   
        faceUpCards.clear();
        faceUpLabels.clear();
   
        int cardWidth = 100;
        int cardHeight = 150;
        int spacing = 20;
        int totalWidth = (5 * cardWidth) + (4 * spacing);
        int startX = (arenaLabel.getWidth() - totalWidth) / 2;
        int y = 300;
   
        for (int i = 0; i < Math.min(5, game.getDeck().size()); i++) {
            TrainCard card = game.getDeck().get(i);
            faceUpCards.add(card);
   
            JLabel label = createCardLabel(card, cardWidth, cardHeight);
            label.setBounds(startX + i * (cardWidth + spacing), y, cardWidth, cardHeight);
            final int index = i;
   
            label.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    handleCardClick(index);
                }
            });
   
            faceUpLabels.add(label);
            arenaLabel.add(label);
        }
   
        arenaLabel.revalidate();
        arenaLabel.repaint();
    }
    private JLabel createCardLabel(TrainCard card, int width, int height) {
        ImageIcon icon = card.getImage();
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }
   


    private void handleCardClick(int index) {
        if (index < 0 || index >= faceUpCards.size()) return;
   
        TrainCard picked = faceUpCards.get(index);
        if(picked.equals("wild"))
        {
            if(gameState.getCurrentPlayer().getMoves()!=0)
            {
                JOptionPane.showMessageDialog(null, "you can not take a wild card from the face up becasue you already drew a card");
                return;
            }
        }
        gameState.getCurrentPlayer().add(picked, "face");
        JOptionPane.showMessageDialog(null,"a "+picked.getColor()+" card has been added to your hand");
   
        // Remove selected from deck and faceUpCards
        game.getDeck().remove(picked);
   
        // Replace it with next card from the deck (if available)
        if (!game.getDeck().isEmpty()) {
            TrainCard replacement = game.getDeck().get(0);
            faceUpCards.set(index, replacement);
            game.getDeck().remove(0);
   
            // Update image of label
            ImageIcon icon = replacement.getImage();
            Image scaledImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
            faceUpLabels.get(index).setIcon(new ImageIcon(scaledImage));
        } else {
            // No replacement: hide the label and remove the card
            faceUpCards.remove(index);
            arenaLabel.remove(faceUpLabels.get(index));
            faceUpLabels.remove(index);
            arenaLabel.revalidate();
            arenaLabel.repaint();
        }
        if(gameState.getCurrentPlayer().getMoves()==2)
        {
            gameState.getCurrentPlayer().resetMoves();
            gameState.nextTurn();
            nextTurn();
        }
    }
   


    static class BackgroundPanel extends JPanel{
        private static final List<Railroad> claimedRoutes = new ArrayList<>();


    public void addClaimedRoute(Railroad route) {
        claimedRoutes.add(route);
        repaint();
    }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon bg = new ImageIcon(getClass().getResource("/Images/Game/game bg.png"));
            ImageIcon map = new ImageIcon(getClass().getResource("/Images/Game/map.png"));
            g.drawImage(bg.getImage(), 0, 0, 1920, 1080, this);
            int mapw = 1678;
            int maph = 1080;
            g.drawImage(map.getImage(), 235, 10, (int)(mapw*0.77), (int)(maph*0.77), this);
            for (Railroad r : claimedRoutes) {
                Image icon;
               
                icon = new ImageIcon(getClass().getResource("/Images/Board Icon/"+r.getOwner()+"pf.png")).getImage();
                System.out.println(r.getCityB().getX()+"city B");
                GameScreen.drawRoute(g, r.getCityA().getX(), r.getCityA().getY(), r.getX(), r.getY(), r.getCityB().getX(), r.getCityB().getY(), icon, r.getOwner());
            }
        }


       
    }
    public static void addClaimed (Railroad r)
    {
        panel.addClaimedRoute(r);
    }
 


    public static void main(String[] args) {


        new GameScreen();
       


        StartScreen.getFrames()[0].dispose();


       
   


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




    public static void drawRoute(Graphics g, int startX, int startY, int middleX, int middleY, int endX, int endY, Image image, int owner) {
    Graphics2D g2 = (Graphics2D) g;
    System.out.println(endX+" "+endY);


    // Set line color and stroke (customize as needed)
    if(owner ==1)
    g2.setColor(Color.GREEN);
    if(owner ==2)
    g2.setColor(Color.CYAN);
    if(owner ==3)
    g2.setColor(Color.MAGENTA);
    if(owner ==4)
    g2.setColor(Color.ORANGE);
    g2.setStroke(new BasicStroke(8)); // 3 pixels thick


    // Draw line from start to middle
    g2.drawLine(startX, startY, middleX, middleY);


    // Draw the image at the middle point (centered)
    int imageWidth = image.getWidth(null);
    int imageHeight = image.getHeight(null);
    int scaledWidth = imageWidth / 2;  
int scaledHeight = imageHeight / 2;
g2.drawLine(middleX, middleY, endX, endY);


g2.drawImage(image,
    middleX - scaledWidth / 2, middleY - scaledHeight / 2,
    scaledWidth, scaledHeight,                              
    null);


    // Draw line from middle to end
   
}






   
}

