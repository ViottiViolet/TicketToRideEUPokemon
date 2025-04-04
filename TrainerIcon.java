
import java.awt.*;
import javax.swing.*;

public class TrainerIcon
{

    private final ImageIcon profile;
    private int rank;

    private final JLabel profileLabel, otherLabel;

    public TrainerIcon(String name, int r, JPanel p)
    {
        profile = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + ".png"));
        rank = r;

        profileLabel = new JLabel(new ImageIcon(profile.getImage().getScaledInstance((int)(300/2), (int)(300/2), Image.SCALE_SMOOTH)));
        otherLabel = new JLabel("lelalaala");

        addToPanel(p);
        reposition();
    }

    public void setRank(int r)
    {
        rank = r;
        reposition();
    }

    public void reposition()
    {
        profileLabel.setBounds(0, 150*rank, (int)(300/2), (int)(300/2));
        otherLabel.setBounds(0, 0, 0, 0);
    }

    public JPanel getLabel()
    {
        return null;
    }

    public void addToPanel(JPanel p) {
        p.add(otherLabel);
        p.add(profileLabel);
        

        otherLabel.setBounds(0, 0, 100, 100);
    }

}