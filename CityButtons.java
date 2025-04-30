
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class CityButtons {

    private static ArrayList<CityButton> cityList;
    
    public CityButtons(JPanel p)
    {

        cityList = new ArrayList<CityButton>();
        int x = 322;
        int y = 54;

        cityList.add(new CityButton(715-x, 88-y, p, "Edinburgh"));
        cityList.add(new CityButton(1263-x, 62-y, p, "Stockholm"));
        cityList.add(new CityButton(1652-x, 94-y, p, "Petrograd"));
        cityList.add(new CityButton(814-x, 281-y, p, "London"));
        cityList.add(new CityButton(936-x, 286-y, p, "Amsterdamn"));
        cityList.add(new CityButton(1140-x, 161-y, p, "Kobenhavn"));
        cityList.add(new CityButton(1433-x, 99-y, p, "Riga"));
        cityList.add(new CityButton(1323-x, 214-y, p, "Danzig"));
        cityList.add(new CityButton(1778-x, 248-y, p, "Moskva"));
        cityList.add(new CityButton(910-x, 346-y, p, "Bruxelles"));
        cityList.add(new CityButton(1044-x, 295-y, p, "Essen"));
        cityList.add(new CityButton(1174-x, 312-y, p, "Berlin"));
        cityList.add(new CityButton(1385-x, 301-y, p, "Warszawa"));
        cityList.add(new CityButton(1532-x, 276-y, p, "Wilno"));
        cityList.add(new CityButton(1663-x, 276-y, p, "Smolensk"));
        cityList.add(new CityButton(680-x, 430-y, p, "Brest"));
        cityList.add(new CityButton(797-x, 394-y, p, "Dieppe"));
        cityList.add(new CityButton(1018-x, 383-y, p, "Frankfurt"));
        cityList.add(new CityButton(1582-x, 370-y, p, "Kyiv"));
        cityList.add(new CityButton(1757-x, 438-y, p, "Kharkov"));
        cityList.add(new CityButton(860-x, 448-y, p, "Paris"));
        cityList.add(new CityButton(1099-x, 441-y, p, "Monchen"));
        cityList.add(new CityButton(1247-x, 460-y, p, "Wein"));
        cityList.add(new CityButton(1309-x, 493-y, p, "Budapest"));
        cityList.add(new CityButton(1810-x, 506-y, p, "Rostov"));
        cityList.add(new CityButton(1010-x, 522-y, p, "Zurich"));
        cityList.add(new CityButton(1119-x, 562-y, p, "Venezela"));
        cityList.add(new CityButton(1228-x, 578-y, p, "Zagrab"));
        cityList.add(new CityButton(1508-x, 583-y, p, "Bucuresti"));
        cityList.add(new CityButton(1685-x, 601-y, p, "Sevastopol"));
        cityList.add(new CityButton(1802-x, 620-y, p, "Sochi"));
        cityList.add(new CityButton(778-x, 645-y, p, "Pamplona"));
        cityList.add(new CityButton(648-x, 749-y, p, "Madrid"));
        cityList.add(new CityButton(560-x, 780-y, p, "Lisboa"));
        cityList.add(new CityButton(647-x, 846-y, p, "Cadiz"));
        cityList.add(new CityButton(794-x, 766-y, p, "Barcelona"));
        cityList.add(new CityButton(975-x, 644-y, p, "Marseille"));
        cityList.add(new CityButton(1130-x, 677-y, p, "Roma"));
        cityList.add(new CityButton(1179-x, 845-y, p, "Palermo"));
        cityList.add(new CityButton(1341-x, 653-y, p, "Sarajevo"));
        cityList.add(new CityButton(1242-x, 711-y, p, "Brindisi"));
        cityList.add(new CityButton(1427-x, 664-y, p, "Sofia"));
        cityList.add(new CityButton(1403-x, 808-y, p, "Athina"));
        cityList.add(new CityButton(1575-x, 743-y, p, "Constantinople"));
        cityList.add(new CityButton(1674-x, 810-y, p, "Angora"));
        cityList.add(new CityButton(1778-x, 780-y, p, "Erzurum"));
        cityList.add(new CityButton(1515-x, 842-y, p, "Smyrna"));
        
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

    public CityButton(int x, int y, JPanel p, String n)
    {
        glow = new ImageIcon(getClass().getResource("/Images/Game/city glow.png"));
        station = new ImageIcon(getClass().getResource("/Images/Stations/1.png"));
        glowLabel = new JLabel(new ImageIcon(glow.getImage().getScaledInstance((int)(120/3), (int)(120/3), Image.SCALE_SMOOTH)));

        citiesSelected = new ArrayList<CityButton>();
        choice = 1;
        isPurchased = false;
        city = new City(n);

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
                        railroads = Graph.getRailroad(citiesSelected.get(0).getName(), citiesSelected.get(1).getName());
                        if (!Game.getBoardGraph().isConnected(citiesSelected.get(0).city(), citiesSelected.get(1).city()))
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
                            citiesToSelect+=2;
                            
                            if (!citiesSelected.get(0).getPurchased()) citiesSelected.get(0).getLabel().setIcon(null);
                            if (!citiesSelected.get(1).getPurchased()) citiesSelected.get(1).getLabel().setIcon(null);
                            
                        }
                        else
                        {
                            if (!railroads.isEmpty() && (railroads.get(choice-1).getIsOwned() == true || Graph.getRailroad(railroads.get(choice-1).getCityB().getName(), railroads.get(choice-1).getCityA().getName()).get(choice-1).getIsOwned() == true))
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
                                return;
                            }
                            System.out.println("route purchased");
                            if (!citiesSelected.get(0).getPurchased()) citiesSelected.get(0).getLabel().setIcon(null);
                            if (!citiesSelected.get(1).getPurchased()) citiesSelected.get(1).getLabel().setIcon(null);
                            if (!railroads.isEmpty()) railroads.get(choice-1).claim();
                            CityButtons.disableAll();
                            GameState.players[GameState.getTurn()-1].buy(railroads.get(choice-1), 0);
                            GameState.nextTurn();
                            GameScreen.nextTurn();
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
                            station = new ImageIcon(getClass().getResource("/Images/Stations/" + (GameState.getTurn()) + ".png"));
                            citiesSelected.get(0).getLabel().setIcon(new ImageIcon(station.getImage().getScaledInstance((int)(1720/25), (int)(2300/25), Image.SCALE_SMOOTH)));
                            citiesSelected.get(0).getLabel().setBounds(x,y+5,(int)(1720/25), (int)(2300/25));
                            isPurchased = true;
                            CityButtons.disableAll();
                            GameState.players[GameState.getTurn()-1].placeTrainStation(new City(citiesSelected.get(0).getName()));
                            GameState.nextTurn();
                            GameScreen.nextTurn();
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
