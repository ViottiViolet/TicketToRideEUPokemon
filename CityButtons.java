
import com.sun.jdi.event.ThreadStartEvent;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class CityButtons {

    private static ArrayList<CityButton> cityList;
   public GameState state;
    
    public CityButtons(JPanel p, GameState g, Game G)
    {
      // this.state = state;
        cityList = new ArrayList<CityButton>();
        int x = 322;
        int y = 54;

        cityList.add(new CityButton(715-x, 88-y, p, "Edinburgh", g,G));
        cityList.add(new CityButton(1263-x, 62-y, p, "Stockholm", g,G));
        cityList.add(new CityButton(1652-x, 94-y, p, "Petrograd", g,G));
        cityList.add(new CityButton(814-x, 281-y, p, "London", g,G));
        cityList.add(new CityButton(936-x, 286-y, p, "Amsterdamn", g,G));
        cityList.add(new CityButton(1140-x, 161-y, p, "Kobenhavn", g,G));
        cityList.add(new CityButton(1433-x, 99-y, p, "Riga", g,G));
        cityList.add(new CityButton(1323-x, 214-y, p, "Danzig", g,G));
        cityList.add(new CityButton(1778-x, 248-y, p, "Moskva", g,G));
        cityList.add(new CityButton(910-x, 346-y, p, "Bruxelles", g,G));
        cityList.add(new CityButton(1044-x, 295-y, p, "Essen", g,G));
        cityList.add(new CityButton(1174-x, 312-y, p, "Berlin", g,G));
        cityList.add(new CityButton(1385-x, 301-y, p, "Warszawa", g,G));
        cityList.add(new CityButton(1532-x, 276-y, p, "Wilno", g,G));
        cityList.add(new CityButton(1663-x, 276-y, p, "Smolensk", g,G));
        cityList.add(new CityButton(680-x, 430-y, p, "Brest", g,G));
        cityList.add(new CityButton(797-x, 394-y, p, "Dieppe", g,G));
        cityList.add(new CityButton(1018-x, 383-y, p, "Frankfurt", g,G));
        cityList.add(new CityButton(1582-x, 370-y, p, "Kyiv", g,G));
        cityList.add(new CityButton(1757-x, 438-y, p, "Kharkov", g,G));
        cityList.add(new CityButton(860-x, 448-y, p, "Paris", g,G));
        cityList.add(new CityButton(1099-x, 441-y, p, "Monchen", g,G));
        cityList.add(new CityButton(1247-x, 460-y, p, "Wein", g,G));
        cityList.add(new CityButton(1309-x, 493-y, p, "Budapest", g,G));
        cityList.add(new CityButton(1810-x, 506-y, p, "Rostov", g,G));
        cityList.add(new CityButton(1010-x, 522-y, p, "Zurich", g,G));
        cityList.add(new CityButton(1119-x, 562-y, p, "Venezela", g,G));
        cityList.add(new CityButton(1228-x, 578-y, p, "Zagrab", g,G));
        cityList.add(new CityButton(1508-x, 583-y, p, "Bucuresti", g,G));
        cityList.add(new CityButton(1685-x, 601-y, p, "Sevastopol", g,G));
        cityList.add(new CityButton(1802-x, 620-y, p, "Sochi", g,G));
        cityList.add(new CityButton(778-x, 645-y, p, "Pamplona", g,G));
        cityList.add(new CityButton(648-x, 749-y, p, "Madrid", g,G));
        cityList.add(new CityButton(560-x, 780-y, p, "Lisboa", g,G));
        cityList.add(new CityButton(647-x, 846-y, p, "Cadiz", g,G));
        cityList.add(new CityButton(794-x, 766-y, p, "Barcelona", g,G));
        cityList.add(new CityButton(975-x, 644-y, p, "Marseille", g,G));
        cityList.add(new CityButton(1130-x, 677-y, p, "Roma", g,G));
        cityList.add(new CityButton(1179-x, 845-y, p, "Palermo", g,G));
        cityList.add(new CityButton(1341-x, 653-y, p, "Sarajevo", g,G));
        cityList.add(new CityButton(1242-x, 711-y, p, "Brindisi", g,G));
        cityList.add(new CityButton(1427-x, 664-y, p, "Sofia", g,G));
        cityList.add(new CityButton(1403-x, 808-y, p, "Athina", g,G));
        cityList.add(new CityButton(1575-x, 743-y, p, "Constantinople", g,G));
        cityList.add(new CityButton(1674-x, 810-y, p, "Angora", g,G));
        cityList.add(new CityButton(1778-x, 780-y, p, "Erzurum", g,G));
        cityList.add(new CityButton(1515-x, 842-y, p, "Smyrna", g,G));
        
    }
    public void setState(GameState state)
    {
        this.state = state;

    }

    public void enableAll(int x)
    {
        for (CityButton c : cityList)
        {
            c.getLabel().setVisible(true);
        }
        CityButton.setCities(x);
    }

    public static void disableAll()
    {
        for (CityButton c : cityList)
        {
            if (!c.getPurchased()) c.getLabel().setVisible(false);
        }
    }

}

class CityButton {

    private final ImageIcon glow;
    private ImageIcon station;

    private final JLabel glowLabel;

    private final String name;
    private boolean isPurchased;

    static private int citiesToSelect;
    static private ArrayList<CityButton> citiesSelected;
    static private boolean chooseRoute;

    private int choice;
    private ArrayList<Railroad> railroads;
    private City city;
   private GameState state;
   private Game game;

    public CityButton(int x, int y, JPanel p, String n, GameState g, Game G)
    {
        game = G;
        glow = new ImageIcon(getClass().getResource("/Images/Game/city glow.png" ));
        station = new ImageIcon(getClass().getResource("/Images/Stations/1.png"));
        glowLabel = new JLabel(new ImageIcon(glow.getImage().getScaledInstance((int)(120/3), (int)(120/3), Image.SCALE_SMOOTH)));

        citiesSelected = new ArrayList<CityButton>();
        choice = 1;
        isPurchased = false;
        city = new City(n);
        state = g;

        glowLabel.addMouseListener(new MouseAdapter() {
           
            @Override
               public void mouseClicked(MouseEvent e) {
                    //glowLabel.setVisible(true);
                   
                    choice = 0;
                    if (!isPurchased)
                    {
                        glowLabel.setIcon(new ImageIcon(glow.getImage().getScaledInstance((int)(120/3), (int)(120/3), Image.SCALE_SMOOTH)));
                        citiesSelected.add(getCity());
                    }
                    else if (isPurchased && chooseRoute) citiesSelected.add(getCity());
                    else return;
                    
                    if (--citiesToSelect != 0)
                    {
                        return;
                    }

                    
                    if (chooseRoute)
                    {
                        
                        if (citiesSelected.get(0).equals(citiesSelected.get(1))) 
                        {
                            citiesSelected.remove(1);
                            citiesToSelect++;
                            return;
                        }
                        railroads = game.getBoardGraph().getRailroad(citiesSelected.get(0).getName(), citiesSelected.get(1).getName());
                        
                       System.out.println(game.getBoardGraph().getVertices().size());
                        if (!game.getBoardGraph().isConnected(citiesSelected.get(0).city(), citiesSelected.get(1).city()))
                        {
                            JOptionPane.showMessageDialog(p,
                                "You've selected two cities which aren't connected to each other. Please try again.",
                                "Nonadjacent Cities",
                                JOptionPane.WARNING_MESSAGE);
                                choice = 0;
                        }
                        else 
                        {
                            

                            String[] options;
                            if (railroads.size()>1) {
                               
                                
                                options = new String[]{"Cancel", "Confirm: " + railroads.get(0).getColor(), "Confirm: " + railroads.get(1).getColor()};
                            }
                            else
                            {
                                options = new String[]{"Cancel", "Confirm"};
                            }
                            choice = JOptionPane.showOptionDialog(p,
                           "Do you want to purchase the route between " + citiesSelected.get(0).getName() + " and " + citiesSelected.get(1).getName() + "?",
                                  "Route Selected",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.WARNING_MESSAGE,
                                    null, options, null);
                        }
                        if (choice == 0)
                        {
                            GameScreen.reset();
                           
                            citiesToSelect+=2;
                            
                            if (!citiesSelected.get(0).getPurchased()) citiesSelected.get(0).getLabel().setIcon(null);
                            if (!citiesSelected.get(1).getPurchased()) citiesSelected.get(1).getLabel().setIcon(null);
                            
                        }
                        else
                        {
                            if (!railroads.isEmpty() && (railroads.get(choice-1).getIsOwned() == true || game.getBoardGraph().getRailroad(railroads.get(choice-1).getCityB().getName(), railroads.get(choice-1).getCityA().getName()).get(choice-1).getIsOwned() == true))
                            {
                                JOptionPane.showMessageDialog(p,
                                "You cannot buy a route which has already been purchased. Please try again.",
                                "Invalid Route Purchase",
                                JOptionPane.WARNING_MESSAGE);
                                citiesToSelect+=2;

                                if (!citiesSelected.get(0).getPurchased()) citiesSelected.get(0).getLabel().setIcon(null);
                                if (!citiesSelected.get(1).getPurchased()) citiesSelected.get(1).getLabel().setIcon(null);
                                
                                citiesSelected.clear();
                                railroads.clear();
                                GameScreen.reset();
                                return;
                            }
                            System.out.println("route purchased");
                            if (!citiesSelected.get(0).getPurchased()) citiesSelected.get(0).getLabel().setIcon(null);
                            if (!citiesSelected.get(1).getPurchased()) citiesSelected.get(1).getLabel().setIcon(null);
                            if (!railroads.isEmpty()) railroads.get(choice-1).claim();
                            CityButtons.disableAll();
                            int current = state.getTurn();
                            purchaser( railroads.get(choice-1));

                            if(current == state.getTurn())
                            {
                                CityButtons.disableAll();
                                GameScreen.setTextLabel("choose another play");
                                GameScreen.reset();
                            }
                            //GameState.players[GameState.getTurn()-1].buy(railroads.get(choice-1), 0);
                           
                        }
                        citiesSelected.clear();
                        railroads.clear();
                    }
                    else
                    {
                        
                        if (isPurchased) return;
                        String[] options = {"Cancel", "Confirm"};
                        choice = JOptionPane.showOptionDialog(p,
                                    "Do you want to place a station on " + citiesSelected.get(0).getName() + "?",
                                    "City Selected",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.WARNING_MESSAGE,
                                    null, options, null);
                        if (choice == 0)
                        {
                            citiesToSelect++;
                            citiesSelected.get(0).getLabel().setIcon(null);
                        }
                        else
                        {
                            // TO CODE: based on player turn, append different int from 1 to 4
                            String str = JOptionPane.showInputDialog(null, "what color card do you want to use to purchase a train station, you need at least "+(3-(state.getCurrentPlayer().getNumStations())+1)+" of that card").toLowerCase().trim();
                            String colors = "blackbluegreenorangepinkredwhiteyellow";
                            while (!colors.contains(str))
                            {
                                 str = JOptionPane.showInputDialog(null, "inavlid color").toLowerCase().trim();
                            }
                            if(!state.getCurrentPlayer().canAffordStation(str))
                            {
                                CityButtons.disableAll();
                                GameScreen.reset();
                                return;
                            }
                            station = new ImageIcon(getClass().getResource("/Images/Stations/" + (state.getTurn()) + ".png"));
                            citiesSelected.get(0).getLabel().setIcon(new ImageIcon(station.getImage().getScaledInstance((int)(1720/25), (int)(2300/25), Image.SCALE_SMOOTH)));
                            citiesSelected.get(0).getLabel().setBounds(x,y+5,(int)(1720/25), (int)(2300/25));
                            isPurchased = true;
                            CityButtons.disableAll();
                            state.players[state.getTurn()-1].placeTrainStation(new City(citiesSelected.get(0).getName()),str);
                            
                            GameScreen.nextTurn();
                            state.nextTurn();
                        }
                        citiesSelected.clear();
                    }
                }
            
        });

        p.add(glowLabel);
        //glowLabel.setVisible(false);
        glowLabel.setIcon(null);

        glowLabel.setBounds(x,y,(int)(130/3), (int)(130/3));

        name = n;
        isPurchased = false;
    }

    public void purchaser ( Railroad r)
    {
        int index = state.getTurn()-1;

        Player p = state.getPlayers()[index];
        
        String check;
        check = p.canAfford(r);
        if(check.equals("no"))
        {
            JOptionPane.showMessageDialog(null, "you do not have enough cards to buy this railroad");
        return;
        }
        String color = r.getColor();
       String str = null;
       if(r.getEngineCount()!=0)
       str = "ferry";
       else if(r.isTunnel())
       str = "mountain";
       else 
       str = "normal";
        int numWilds;
        if(str.equals("normal"))
        {
            numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use?"));
            while(p.getWilds()<numWilds)
           {
               numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use? previous number was invalid"));
           }
            String recolor = "";
            if(color.equals("none"))
            {
                String colors = "blackbluegreenorangepinkredwhiteyellow";
                String rcolor = javax.swing.JOptionPane.showInputDialog("what color card do you want to use? (correct splling)");
                while (!colors.contains(rcolor))
                {
                     rcolor = javax.swing.JOptionPane.showInputDialog("what color card do you want to use ? - last input was invalid( type all lower case with correct splling)").toLowerCase().trim();

                }
                if(!p.canAffordNC(r, rcolor, numWilds))
                {
                    JOptionPane.showMessageDialog(null,"you do not have enough of "+color+" cards to purchase this railroad");
                    //GameScreen.reset();
                    return;
                }
                else 
                p.buy(r,numWilds,r.getLength(),rcolor);
                
            }
            else 
            {
            color = r.getColor();
            p.buy(r, numWilds, r.getLength(), r.getColor());
            }
           
        }
        else if(str=="ferry")
        {

            JOptionPane.showMessageDialog(null, "because this railroad requires "+r.getEngineCount()+" wilds cards");
            numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use?"+" must use at least "+r.getEngineCount()));
            while(p.getWilds()<numWilds||numWilds<r.getEngineCount())
            numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use? previous number was invalid"));
            p.buy(r,numWilds,r.getLength(), r.getColor());
            game.getBoardGraph();

        }
        
       else if(str.equals("mountain"))
        {
            String rcolor = null;
            numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use?"));
            while(p.getWilds()<numWilds)
           {
               numWilds = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("how many wilds do you want to use? previous number was invalid"));
           }
           if(color.equals("none"))
           {
            String colors = "blackbluegreenorangepinkredwhiteyellow";
                 rcolor = javax.swing.JOptionPane.showInputDialog("what color card do you want to use? (correct splling)");
                while (!colors.contains(rcolor))
                {
                     rcolor = javax.swing.JOptionPane.showInputDialog("what color card do you want to use ? - last input was invalid( type all lower case with correct splling)").toLowerCase().trim();

                }

           }
           else
           rcolor = r.getColor();
            ArrayList <TrainCard> three = new ArrayList<>();
            int count =0;
            for(int i =0; i<3;i++)
            {
                TrainCard card = game.getDeck().pop();
                if(card.getColor().equals(rcolor))
                count++;
                three.add(card);
            }
            JOptionPane.showMessageDialog(null, "the three cards drawn were: "+three.get(0).getColor()+", "+three.get(1).getColor()+", "+three.get(2).getColor());
            if(count!=0)
            {
                if(p.canAffordM(r,(r.getLength()+count),rcolor))
                {
                    int result = JOptionPane.showConfirmDialog(null,"purchasing this route requies "+count+"more cards (if needed we will have to take wild cards), do you wish to confirm purchase?","Confirmation",JOptionPane.YES_NO_OPTION);
                    if(result == JOptionPane.YES_OPTION)
                    {
                        if(p.getCardTypeNum(color)+numWilds>=r.getLength()+count)
                        p.buy(r,numWilds,r.getLength()+count, rcolor);
                        else{
                            int nWilds = r.getLength()+count-p.getCardTypeNum(color);
                        p.buy(r,nWilds, r.getLength()+count,rcolor);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "you chose not to buy this ailroad");
                        return;
                    }

                }
                else
                {
                    JOptionPane.showMessageDialog(null, "you could not buy this raiload");
                    return;
                }
            }
            else
            p.buy(r,numWilds,r.getLength(),rcolor);
          
            


        }
        JOptionPane.showMessageDialog(null,"you have purchased the railroad between "+r.getCityA().getName()+" and "+r.getCityB().getName());
        state.nextTurn();
        GameScreen.nextTurn();

      

    }

    public CityButton getCity()
    {
        return this;
    }

    public City city()
    {
        return city;
    }
  
    
    public JLabel getLabel()
    {
        return glowLabel;
    }

    public String getName()
    {
        return name;
    }
    
    public static void setCities(int x)
    {
        citiesToSelect = x;
        if (x == 2) chooseRoute = true;
        else chooseRoute = false;
    }


    public boolean getPurchased() {
        return isPurchased;
    }

}