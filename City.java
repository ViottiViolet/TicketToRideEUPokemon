 import java.util.ArrayList;
import java.util.List;
public class City
{
    public String name;
    public ArrayList<Railroad> edges;

    public City (String x) {
       name = x;
        edges = new ArrayList<Railroad>();
    }

    public ArrayList<Railroad> getEdges() {
        return this.edges;
    }

    public String getName() {
        return name;
    }

    public void addEdge(City b, int weight) {
        edges.add(new Railroad(this, b, weight));
    }

}