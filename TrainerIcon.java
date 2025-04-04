
import java.awt.*;
import javax.swing.*;

public class TrainerIcon
{

    private final int h = 280;

    private final ImageIcon profile, train, station;
    private int rank;

    private final JLabel bgLabel, profileLabel, nameLabel, trainLabel, stationLabel;

    public TrainerIcon(String name, int r, JPanel p)
    {
        profile = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + ".png"));
        train = new ImageIcon(getClass().getResource("/Images/Game/locomotive highlight.png"));
        station = new ImageIcon(getClass().getResource("/Images/Game/station highlight.png"));
        rank = r;

        nameLabel = new JLabel("PLAYER " + name);
        nameLabel.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 20));
        nameLabel.setForeground(Color.WHITE);

        profileLabel = new JLabel(new ImageIcon(profile.getImage().getScaledInstance((int)(300/2), (int)(300/2), Image.SCALE_SMOOTH)));

        bgLabel = new JLabel("       ");
        bgLabel.setOpaque(true);
        bgLabel.setBackground(Color.RED);

        trainLabel = new JLabel(new ImageIcon(train.getImage().getScaledInstance((int)(251/4), (int)(201/4), Image.SCALE_SMOOTH)));
        stationLabel = new JLabel(new ImageIcon(station.getImage().getScaledInstance((int)(251/4), (int)(201/4), Image.SCALE_SMOOTH)));

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
        profileLabel.setBounds(10, 190*rank- 150, (int)(h/2), (int)(h/2));
        nameLabel.setBounds(120, 190*rank - 200, (int)(h/2), (int)(h/2));
        bgLabel.setBounds(5, 190*rank - 165, (int)(h - 55), (int)(h/2 + 30));

        trainLabel.setBounds(80, 190*rank - 150, (int)(h/2), (int)(h/2));
        stationLabel.setBounds(80, 190*rank - 100, (int)(h/2), (int)(h/2));
    }

    public void addToPanel(JPanel p) {
        p.add(nameLabel);
        p.add(trainLabel);
        p.add(stationLabel);
        p.add(profileLabel);
        p.add(bgLabel);
    }

}