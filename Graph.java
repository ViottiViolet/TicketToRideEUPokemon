
import java.util.ArrayList;

public class Graph 
{
    private static ArrayList<String> connectedCityHolder;
    private ArrayList<City> verticies;

    public Graph ()
    {
        verticies = new ArrayList<City>();
    }

    public City addVertex (String name)
    {
        City newVertex = new City (name);
        this.verticies.add(newVertex);
        return newVertex;

    }

    public void addEdge (City a, City b, int weight)
    {
        a.addEdgee(b, weight);
        b.addEdgee(a, weight);

    }
    //hello

    public void depthFirstTraversal( City start, ArrayList<City> visitedVerticies)
    {
        connectedCityHolder.add(start.getName());
        for (Railroad r: start.getEdges())
        {
          City neighbor =  r.getCityB();

          if(!visitedVerticies.contains(neighbor))
          {
            visitedVerticies.add(neighbor);
            depthFirstTraversal(neighbor, visitedVerticies);

          }
        }
       

    }

    public boolean isConnected(City a, City b)
    {
        ArrayList<City> visited = new ArrayList <City>();
        boolean isConnected = false;
        depthFirstTraversal(a, visited);
        for (String str : connectedCityHolder)
        {
            if ( str.equals(b.getName()))
            isConnected = true;
        }
        connectedCityHolder = new ArrayList<String>();
        return isConnected;

    }

}