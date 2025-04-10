
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import javax.swing.*;

public class CityButtons {

    private ArrayList<CityButton> cityList;
    
    public CityButtons(JPanel p)
    {

        cityList = new ArrayList<CityButton>();
        int x = 320;
        int y = 55;

        cityList.add(new CityButton(715-x, 88-y, p));
        cityList.add(new CityButton(1263-x, 62-y, p));
        cityList.add(new CityButton(1652-x, 94-y, p));
        cityList.add(new CityButton(814-x, 281-y, p));
        cityList.add(new CityButton(936-x, 286-y, p));
        cityList.add(new CityButton(1140-x, 161-y, p));
        cityList.add(new CityButton(1433-x, 99-y, p));
        cityList.add(new CityButton(1323-x, 214-y, p));
        cityList.add(new CityButton(1778-x, 248-y, p));
        cityList.add(new CityButton(910-x, 346-y, p));
        cityList.add(new CityButton(1044-x, 295-y, p)); // essen
        cityList.add(new CityButton(1174-x, 312-y, p));
        cityList.add(new CityButton(1385-x, 301-y, p)); // warsaw
        cityList.add(new CityButton(1532-x, 276-y, p));
        cityList.add(new CityButton(1663-x, 276-y, p));
        cityList.add(new CityButton(680-x, 430-y, p)); // brest
        cityList.add(new CityButton(797-x, 394-y, p)); // dieppe
        cityList.add(new CityButton(1018-x, 383-y, p)); // frankfurt
        cityList.add(new CityButton(1582-x, 370-y, p)); // kviv
        cityList.add(new CityButton(1757-x, 438-y, p));
        cityList.add(new CityButton(860-x, 448-y, p));
        cityList.add(new CityButton(1099-x, 441-y, p));
        cityList.add(new CityButton(1247-x, 460-y, p));
        cityList.add(new CityButton(1309-x, 493-y, p));
        cityList.add(new CityButton(1810-x, 506-y, p));
        cityList.add(new CityButton(1010-x, 522-y, p));
        cityList.add(new CityButton(1119-x, 562-y, p));
        cityList.add(new CityButton(1228-x, 578-y, p));
        cityList.add(new CityButton(1508-x, 583-y, p));
        cityList.add(new CityButton(1685-x, 601-y, p));
        cityList.add(new CityButton(1802-x, 620-y, p));
        cityList.add(new CityButton(778-x, 645-y, p));
        cityList.add(new CityButton(648-x, 749-y, p));
        cityList.add(new CityButton(560-x, 780-y, p)); // lisboa
        cityList.add(new CityButton(647-x, 846-y, p));
        cityList.add(new CityButton(794-x, 766-y, p));
        cityList.add(new CityButton(975-x, 644-y, p));
        cityList.add(new CityButton(1130-x, 677-y, p));
        cityList.add(new CityButton(1179-x, 845-y, p));
        cityList.add(new CityButton(1341-x, 653-y, p));
        cityList.add(new CityButton(1242-x, 711-y, p));
        cityList.add(new CityButton(1427-x, 664-y, p));
        cityList.add(new CityButton(1403-x, 808-y, p));
        cityList.add(new CityButton(1575-x, 743-y, p));
        cityList.add(new CityButton(1674-x, 810-y, p));
        cityList.add(new CityButton(1778-x, 780-y, p));
        cityList.add(new CityButton(1515-x, 842-y, p));
        
    }

}

class CityButton {

    private final ImageIcon glow;

    private final JLabel glowLabel;

    public CityButton(int x, int y, JPanel p)
    {
        glow = new ImageIcon(getClass().getResource("/Images/Game/city glow.png"));

        glowLabel = new JLabel(new ImageIcon(glow.getImage().getScaledInstance((int)(120/3), (int)(120/3), Image.SCALE_SMOOTH)));

        glowLabel.addMouseListener(new MouseAdapter() {
           
            @Override
               public void mouseClicked(MouseEvent e) {
                //glowLabel.setVisible(true);
                glowLabel.setIcon(new ImageIcon(glow.getImage().getScaledInstance((int)(120/3), (int)(120/3), Image.SCALE_SMOOTH)));
            }
            
        });

        p.add(glowLabel);
        //glowLabel.setVisible(false);
        glowLabel.setIcon(null);

        glowLabel.setBounds(x,y,(int)(130/3), (int)(130/3));
    }

    
        
    

}