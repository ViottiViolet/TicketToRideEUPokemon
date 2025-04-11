public class Railroad
{

    private String color;
    private int length;
    private boolean isTunnel;
    private int engineCount;
    private City a;
    private City b; 
    public Railroad (String color, int length, boolean istunnel, int engineCount, City a, City b)
    {
        this.color = color;
        this.length = length;
        this.isTunnel = istunnel;
        this.engineCount = engineCount;
        this.a = a;
        this.b = b;

        
    }
    public Railroad(City a, City b, int weight)
    {
        this.a = a; 
        this.b = b;
        length = weight;
    }
    public String getColor()
    {
        return color;
    }

    public int getLength()
    {
        return length;
    }
    public boolean isTunnel()
    {
        return isTunnel;
    }
    public int getEngineCount()
    {
        return engineCount;
    }

    public City getEnd ()
    {
        return b;
    }

}