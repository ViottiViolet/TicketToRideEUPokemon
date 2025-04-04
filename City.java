import java.util.ArrayList;

public class City
{
    public String name;
    public ArrayList<City> neighbors;

    public City(String x) {
        name = x;
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(City node) {
        this.neighbors.add(node);
    }

    public ArrayList<City> getNeighbors() {
        return this.neighbors;
    }

    public String getName() {
        return name;
    }

}