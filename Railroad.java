public class Railroad
{

    private String color;
    private int length;
    private boolean isTunnel;
    private int engineCount;
    private City a;
    private City b; 
    private boolean isOwned;
    private int x,y;
    private int owner;

    public Railroad (String color, int length, boolean istunnel, int engineCount, City a, City b, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.color = color;
        this.length = length;
        this.isTunnel = istunnel;
        this.engineCount = engineCount;
        this.a = a;
        this.b = b;
        isOwned = false;
    }

    public Railroad(City a, City b, int weight)
    {
        this.a = a; 
        this.b = b;
        length = weight;
        isOwned = false;
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

    public City getCityA()
    {
        return a;
    }

    public City getCityB()
    {
        return b;
    }

    public boolean getIsOwned()
    {
        return isOwned;
    }

    public void claim()
    {
        isOwned = true;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void setOwner(int o)
    {
        owner = o;
    }
    public int getOwner()
    {return owner;}
}