
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

        cityList.add(new CityButton(715-320, 88-50, p));
        cityList.add(new CityButton(1263-320, 62-50, p));
        cityList.add(new CityButton(1652-320, 94-50, p));
        cityList.add(new CityButton(814-320, 281-50, p));
        cityList.add(new CityButton(936-320, 286-50, p));
        cityList.add(new CityButton(1140-320, 161-50, p));
        cityList.add(new CityButton(1433-320, 99-50, p));
        cityList.add(new CityButton(1323-320, 214-50, p));
        cityList.add(new CityButton(1778-320, 248-50, p));
        cityList.add(new CityButton(910-320, 346-50, p));
        cityList.add(new CityButton(1044-320, 295-50, p)); // essen
        cityList.add(new CityButton(1174-320, 312-50, p));
        cityList.add(new CityButton(1385-320, 301-50, p)); // warsaw
        cityList.add(new CityButton(1532-320, 276-50, p));
        cityList.add(new CityButton(1663-320, 276-50, p));
        cityList.add(new CityButton(680-320, 430-50, p)); // brest
        cityList.add(new CityButton(797-320, 394-50, p)); // dieppe
        cityList.add(new CityButton(1018-320, 383-50, p)); // frankfurt
        cityList.add(new CityButton(1582-320, 370-50, p)); // kviv
        cityList.add(new CityButton(1757-320, 438-50, p));
        cityList.add(new CityButton(860-320, 448-50, p));
        cityList.add(new CityButton(1099-320, 441-50, p));
        cityList.add(new CityButton(1247-320, 460-50, p));
        cityList.add(new CityButton(1309-320, 493-50, p));
        cityList.add(new CityButton(1810-320, 506-50, p));
        cityList.add(new CityButton(1010-320, 522-50, p));
        cityList.add(new CityButton(1119-320, 562-50, p));
        cityList.add(new CityButton(1228-320, 578-50, p));
        cityList.add(new CityButton(1508-320, 583-50, p));
        cityList.add(new CityButton(1685-320, 601-50, p));
        cityList.add(new CityButton(1802-320, 620-50, p));
        cityList.add(new CityButton(778-320, 645-50, p));
        cityList.add(new CityButton(648-320, 749-50, p));
        cityList.add(new CityButton(560-320, 780-50, p)); // lisboa
        cityList.add(new CityButton(647-320, 846-50, p));
        cityList.add(new CityButton(794-320, 766-50, p));
        cityList.add(new CityButton(975-320, 644-50, p));
        cityList.add(new CityButton(1130-320, 677-50, p));
        cityList.add(new CityButton(1179-320, 845-50, p));
        cityList.add(new CityButton(1341-320, 653-50, p));
        cityList.add(new CityButton(1242-320, 711-50, p));
        cityList.add(new CityButton(1427-320, 664-50, p));
        cityList.add(new CityButton(1403-320, 808-50, p));
        cityList.add(new CityButton(1575-320, 743-50, p));
        cityList.add(new CityButton(1674-320, 810-50, p));
        cityList.add(new CityButton(1778-320, 780-50, p));
        cityList.add(new CityButton(1515-320, 842-50, p));
        
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

        glowLabel.setBounds(x,y,(int)(120/3), (int)(120/3));
    }

    
        
    

}