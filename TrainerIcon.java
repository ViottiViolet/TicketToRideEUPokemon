
import java.awt.*;
import javax.swing.*;

public class TrainerIcon
{

    private final ImageIcon profile;
    private int rank;

    private JPanel icon;
    private final JLabel profileLabel, otherLabel;

    public TrainerIcon(String name, int r)
    {
        profile = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + ".png"));
        rank = r;

        profileLabel = new JLabel(new ImageIcon(profile.getImage().getScaledInstance((int)(300/2), (int)(300/2), Image.SCALE_SMOOTH)));
        otherLabel = new JLabel("lelalaala");

        createPanel();
        reposition();
    }

    public void setRank(int r)
    {
        rank = r;
        reposition();
    }

    public void reposition()
    {
        icon.setBounds(0, 150*rank, (int)(300/2), (int)(300/2));
        otherLabel.setBounds(0, 0, 0, 0);
    }

    public JPanel getLabel()
    {
        return icon;
    }

    public void createPanel() {
        icon = new JPanel();
        icon.add(otherLabel);
        icon.add(profileLabel);
        

        otherLabel.setBounds(0, 0, 100, 100);
    }

}