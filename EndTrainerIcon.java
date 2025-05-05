
import java.awt.*;
import javax.swing.*;

class EndTrainerIcon
{

    private static int h = 280;

    ImageIcon profile, bg;
    JLabel profileLabel, bgLabel, nameLabel;
    int rank;

    public EndTrainerIcon(String name, int r, JPanel p)
    {

        profile = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + ".png"));
        bg = new ImageIcon(getClass().getResource("/Images/Trainers/" + r + "end.png"));
        rank = r;

        nameLabel = new JLabel("PLAYER " + name);
        nameLabel.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 50));
        nameLabel.setForeground(Color.BLACK);

        profileLabel = new JLabel(new ImageIcon(profile.getImage().getScaledInstance((int)(300/1.75), (int)(300/1.75), Image.SCALE_SMOOTH)));

        bgLabel = new JLabel(new ImageIcon(bg.getImage().getScaledInstance((int)(588), (int)(188), Image.SCALE_SMOOTH)));

        addToPanel(p);
        reposition();
    }

    public void reposition()
    {
        profileLabel.setBounds(20, 190*rank- 145, (int)(h/1.75), (int)(h/1.75));
        nameLabel.setBounds(190, 190*rank - 210, (int)(h), (int)(h));
        bgLabel.setBounds(5, 190*rank - 165, (int)(588), (int)(188));
    }

    public void addToPanel(JPanel p) {
        p.add(profileLabel);
        p.add(nameLabel);
        p.add(bgLabel);
    }

}