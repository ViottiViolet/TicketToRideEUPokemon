import java.util.ArrayList;

public class Graph
{
    private static ArrayList<String> connectedCityHolder = new ArrayList<String>();
    private static ArrayList<City> verticies = new ArrayList<City>();

    public Graph ()
    {
        verticies = new ArrayList<>();
    }

    // returns the railroad(s) between 2 cities, takes in the names of the 2 cities
    public ArrayList<Railroad> getRailroad(String city1, String city2)
    {
        ArrayList<Railroad> r = new ArrayList<Railroad>();
        for(City c: verticies)
        {
            if(c.getName().equals(city1)||c.getName().equals(city2))
            {
                ArrayList <Railroad> list = c.getEdges();
                for(Railroad R:list)
                {
                    if(((R.getCityA().getName().equals(city1)) && (R.getCityB().getName().equals(city2))))
                    r.add(R);

                }
                
            }
            
        }
       return r;
    }

   
    public City addVertex(String name)
    {
        //System.out.println(verticies);
         City newVertex = new City(name);
        verticies.add(newVertex);
        return newVertex;
        
    }
//ddssf
    public void addEdge (City a, City b, int weight)
    {
        int x = verticies.indexOf(a);
        int y = verticies.indexOf(b);
        City city1 = verticies.get(x);
        City city2 = verticies.get(y);


        city1.addTrack(b, weight);
        city2.addTrack(a, weight);


       

    }
    //public Railroad (String color, int length, boolean istunnel, int engineCount, City a, City b)
    public void addEdge(String color, int length, boolean isTunnel, int engineCount, City a, City b)
    {
        int x =-1;
        int y=-1;
        for(int i = 0; i<verticies.size();i++)
        {
            if(verticies.get(i).getName().equals(a.getName()))
            x=i;
            if(verticies.get(i).getName().equals(b.getName()))
            y=i;

        }
        
        City city1 = verticies.get(x);
        City city2 = verticies.get(y);
        city1.addTrack(new Railroad(color, length, isTunnel, engineCount, city1, b));
        city2.addTrack(new Railroad(color, length, isTunnel, engineCount, city2, a));



    }
     public void addEdge(Railroad r)
    {
        int x =-1;
        int y=-1;
        for(int i = 0; i<verticies.size();i++)
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
// returns a list of all Cities
    public ArrayList<City> getVertices() {
        return verticies;
    }

}