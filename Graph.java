import java.util.ArrayList;

public class Graph
{
    private static ArrayList<String> connectedCityHolder;
    private static ArrayList <City> verticies;
   

    public Graph ()
    {
        verticies = new ArrayList<>();
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

    public boolean isConnectedFinal(City a, City b)
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
    public static boolean isConnected (City a, City b)
    {
        boolean connected = false;
         int i=0;
         for(City x : verticies)
         {
            if(x.getName().equals(a.getName()))
            break;
            i++;
         }

         
         City A = verticies.get(i);
         
        ArrayList <Railroad> ACity = A.getEdges();
      
        for(Railroad r: ACity)
        {
            if(r.getCityA().getName().equals(b.getName())|| r.getCityB().getName().equals(b.getName()))
            connected = true;

        }
        return connected;
    }
//dfdfdfs
    public ArrayList<City> getVertices() {
        return verticies;
    }

}