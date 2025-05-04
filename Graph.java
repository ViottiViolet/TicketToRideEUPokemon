import java.util.ArrayList;

public class Graph
{
    private  ArrayList<String> connectedCityHolder = new ArrayList<String>();
    private  ArrayList<City> vertices = new ArrayList<City>();
    private  ArrayList<City> visitedVertices = new ArrayList<City>();

    public Graph ()
    {
        vertices = new ArrayList<>();
    }

    // returns the railroad(s) between 2 cities, takes in the names of the 2 cities
    public  ArrayList<Railroad> getRailroad(String city1, String city2)
    {
        ArrayList<Railroad> r = new ArrayList<Railroad>();
        for(City c: vertices)
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
        //System.out.println(vertices);
         City newVertex = new City(name);
        vertices.add(newVertex);
        return newVertex;
        
    }
//ddssf
    public void addEdge (City a, City b, int weight)
    {
        int x=0 ;
        int y =0;

        for(int i = 0; i<vertices.size();i++)
        {
            if (vertices.get(i).getName().equals(a.getName()))
            x =i;
            if (vertices.get(i).getName().equals(b.getName()))
            y =i;

        }
       
        City city1 = vertices.get(x);
        City city2 = vertices.get(y);


        city1.addTrack(b, weight);
        city2.addTrack(a, weight);


       

    }
    //public Railroad (String color, int length, boolean istunnel, int engineCount, City a, City b)
    public void addEdge(String color, int length, boolean isTunnel, int engineCount, City a, City b)
    {
        int x =-1;
        int y=-1;
        for(int i = 0; i<vertices.size();i++)
        {
            if(vertices.get(i).getName().equals(a.getName()))
            x=i;
            if(vertices.get(i).getName().equals(b.getName()))
            y=i;

        }
        
        City city1 = vertices.get(x);
        City city2 = vertices.get(y);
        city1.addTrack(new Railroad(color, length, isTunnel, engineCount, city1, b));
        city2.addTrack(new Railroad(color, length, isTunnel, engineCount, city2, a));



    }
     public void addEdge(Railroad r)
    {
        int x =-1;
        int y=-1;
        for(int i = 0; i<vertices.size();i++)
        {
          City neighbor =  r.getCityB();

          if(!visitedVertices.contains(neighbor))
          {
            visitedVertices.add(neighbor);
            depthFirstTraversal(neighbor, visitedVertices);
          }
        }
    }

    public void depthFirstTraversal(City start, ArrayList<City> visitedVertices)
    {
        if (visitedVertices.contains(start)) return;

        visitedVertices.add(start);
        connectedCityHolder.add(start.getName());

        for (Railroad r : start.getEdges())
        {
            City neighbor = (r.getCityA().equals(start)) ? r.getCityB() : r.getCityA();
            depthFirstTraversal(neighbor, visitedVertices);
        }
    }


    // the name is kinda bad, but im too lazy to change it; the method is just used to check if there is a continuous route between 2 cities ( akak ticket card completion)
    public boolean isConnectedFinal(City a, City b)
    {
        connectedCityHolder = new ArrayList<>();
        ArrayList<City> visited = new ArrayList<>();

        depthFirstTraversal(a, visited);

        return connectedCityHolder.contains(b.getName());
    }


    ////////////////////
    ///// this checks adjacency 
    public  boolean isConnected (City a, City b)
    {
        boolean connected = false;
         int i=0;
         for(City x : vertices)
         {
            if(x.getName().equals(a.getName()))
            break;
            i++;
         }

         
         City A = vertices.get(i);
         
        ArrayList <Railroad> ACity = A.getEdges();
      
      
        for(Railroad r: ACity)
        {
            if(r.getCityA().getName().equals(b.getName())|| r.getCityB().getName().equals(b.getName()))
            if(r.getCityA().getName().equals(b.getName())|| r.getCityB().getName().equals(b.getName()))
            connected = true;

        }
        return connected;
    }

// returns a list of all Cities
    public ArrayList<City> getVertices() {
        return vertices;
    }

}
