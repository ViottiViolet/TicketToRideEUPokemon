
import java.util.ArrayList;

public class Graph 
{
    private static ArrayList<String> connectedCityHolder;
    private ArrayList<City> vertecies;

    public Graph ()
    {
        vertecies = new ArrayList<City>();
    }

    public City addVertex (String name)
    {
        City newVertex = new City (name);
        this.vertecies.add(newVertex);
        return newVertex;

    }

    public void addEdge (City a, City b, int weight)
    {
       a.addEdge(b, weight);
        b.addEdge(a, weight);

    }

    public void depthFirstTraversal( City start, ArrayList<City> visitedVertecies)
    {
        connectedCityHolder.add(start.getName());
        for (Railroad r: start.getEdges())
        {
          City neighbor =  r.getEnd();

          if(!visitedVertecies.contains(neighbor))
          {
            visitedVertecies.add(neighbor);
            depthFirstTraversal(neighbor, visitedVertecies);

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