import java.util.ArrayList;
import java.util.List;

public class City
{
    private int x;
    private int y ;
    public String name;
    public ArrayList<Railroad> edges;

    public City (String x) {
        name = x;
        edges = new ArrayList<Railroad>();
    }
    public City (String x, int xc, int yc) {
        this.x=xc;
        y = yc;
        name = x;
        edges = new ArrayList<Railroad>();
    }

    public ArrayList<Railroad> getEdges() {
        return this.edges;
    }

    public String getName() {
        return name;
    }

    public void addTrack(City b, int weight) {
        edges.add(new Railroad(this, b, weight));
    }
    public void addTrack(Railroad r )
    {
        edges.add(r);
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

}