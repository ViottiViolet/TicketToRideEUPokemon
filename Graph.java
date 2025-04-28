import java.util.ArrayList;

public class Graph
{
    private static ArrayList<String> connectedCityHolder = new ArrayList<String>();
    private static ArrayList<City> vertices = new ArrayList<City>();

    public Graph ()
    {
        connectedCityHolder = new ArrayList<String>();
        vertices = new ArrayList<City>();
    }

    public City addVertex (String name)
    {
        City newVertex = new City (name);
        this.vertices.add(newVertex);
        return newVertex;
    }

    public void addEdge (City a, City b, int weight)
    {

        a.addEdgee(b, weight);
        b.addEdgee(a, weight);
    }
    //hello

    public static void depthFirstTraversal(City start, ArrayList<City> visitedVertices)
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

    public static boolean isConnected (City a, City b)
    {
        boolean connected = false;
        int i = vertices.indexOf(a);
        City A = vertices.get(i);
        ArrayList <Railroad> ACity = A.getEdges();
        for(Railroad r: ACity)
        {
            if(r.getCityA().equals(b)|| r.getCityB().equals(b))
            connected = true;

        }
        return connected;
    }

    public ArrayList<City> getVertices() {
        return vertices;
    }

}