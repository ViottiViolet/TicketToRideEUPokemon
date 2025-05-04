
import java.awt.*;
import javax.swing.*;

public class TrainerIcon
{

    private static int h = 280;

    private ImageIcon profile, bg, train, station;
    private int rank;

    private JLabel bgLabel, profileLabel, nameLabel, trainLabel, trainNumLabel, stationLabel, stationNumLabel, pointsLabel;
    private Player player;
    private GameState state;

    public TrainerIcon(String name, int r, JPanel p, Player play, GameState state)
    {
        this.state = state;
        profile = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + ".png"));
        bg = new ImageIcon(getClass().getResource("/Images/Trainers/" + name + "bg.png"));
        train = new ImageIcon(getClass().getResource("/Images/Game/locomotive highlight.png"));
        station = new ImageIcon(getClass().getResource("/Images/Game/station highlight.png"));
        rank = r;
        player = play;

        nameLabel = new JLabel("PLAYER " + name);
        nameLabel.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 20));
        nameLabel.setForeground(Color.BLACK);

        pointsLabel = new JLabel("points: "+player.getScore());
        pointsLabel.setFont(new Font("Dialog", Font.BOLD + Font.ITALIC, 15));
        pointsLabel.setForeground(Color.BLUE);

        profileLabel = new JLabel(new ImageIcon(profile.getImage().getScaledInstance((int)(300/3), (int)(300/3), Image.SCALE_SMOOTH)));
       

        bgLabel = new JLabel(new ImageIcon(bg.getImage().getScaledInstance((int)(h - 55), (int)(h/2 + 30), Image.SCALE_SMOOTH)));

        trainLabel = new JLabel(new ImageIcon(train.getImage().getScaledInstance((int)(251/4), (int)(201/4), Image.SCALE_SMOOTH)));
        trainNumLabel = new JLabel(player.getNumTrains() + "");
        trainNumLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        trainNumLabel.setForeground(Color.BLACK);

        stationLabel = new JLabel(new ImageIcon(station.getImage().getScaledInstance((int)(251/4), (int)(201/4), Image.SCALE_SMOOTH)));
        stationNumLabel = new JLabel(player.getNumStations() + "");
        stationNumLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        stationNumLabel.setForeground(Color.BLACK);

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
        update();
        profileLabel.setBounds(10, 190*rank- 150, (int)(h/3), (int)(h/3));
        pointsLabel.setBounds(10, 190*rank- 200, (int)(h/3), (int)(h/3));
        nameLabel.setBounds(120, 190*rank - 210, (int)(h/2), (int)(h/2));
        bgLabel.setBounds(5, 190*rank - 165, (int)(h - 55), (int)(h/2 + 30));

        trainLabel.setBounds(-30, 190*rank - 95, (int)(h/2), (int)(h/2));
        trainNumLabel.setText(player.getNumTrains() + "");
        trainNumLabel.setBounds(65, 190*rank - 95, (int)(h/2), (int)(h/2));
        stationLabel.setBounds(80, 190*rank - 100, (int)(h/2), (int)(h/2));
        stationNumLabel.setText(player.getNumStations() + ""); 
        stationNumLabel.setBounds(175, 190*rank - 95, (int)(h/2), (int)(h/2));
        if(state.getTurn()==player.getNumber())
        {
            profileLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 10));

        }
        else
        {
            profileLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 0));

        }
    }

    public void addToPanel(JPanel p) {
        p.add(nameLabel);
        p.add(pointsLabel);
        p.add(trainLabel);
        p.add(trainNumLabel);
        p.add(stationLabel);
        p.add(stationNumLabel);
        p.add(profileLabel);
        p.add(bgLabel);
        
    }

    public void update() {
        pointsLabel.setText("points: " + player.getScore());
        trainNumLabel.setText(player.getNumTrains() + "");
        stationNumLabel.setText(player.getNumStations() + "");
    }
    

}