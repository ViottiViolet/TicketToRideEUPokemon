import java.util.ArrayList;

public class Graph
{
    private static ArrayList<String> connectedCityHolder;
    private ArrayList<City> vertices;

    public Graph ()
    {
        vertices = new ArrayList<>();
    }

    public City addVertex (String name)
    {
        City newVertex = new City (name);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge (City a, City b, int weight)
    {
 chandanaIt11
        a.addEdgee(b, weight);
        b.addEdgee(a, weight);


        a.addEdge(b, weight);
        b.addEdge(a, weight);
 main
    }
    //hello

    public void depthFirstTraversal(City start, ArrayList<City> visitedVertices)
    {
        connectedCityHolder.add(start.getName());
        for (Railroad r: start.getEdges())
        {
          City neighbor =  r.getCityB();

          if(!visitedVertices.contains(neighbor))
          {
            visitedVertices.add(neighbor);
            depthFirstTraversal(neighbor, visitedVertices);
          }
        }
    }

    public boolean isConnected(City a, City b)
    {
        ArrayList<City> visited = new ArrayList<City>();
        boolean isConnected = false;
        depthFirstTraversal(a, visited);
        for (String str : connectedCityHolder)
        {
            if (str.equals(b.getName()))
                isConnected = true;
        }
        connectedCityHolder = new ArrayList<String>();
        return isConnected;
    }

    public ArrayList<City> getVertices() {
        return vertices;
    }

}