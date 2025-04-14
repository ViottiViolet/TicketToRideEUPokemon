public class Railroad
{

    private String color;
    private int length;
    private boolean isTunnel;
    private int engineCount;
    private City a;
    private City b; 
    private boolean isOwned;

    public Railroad (String color, int length, boolean istunnel, int engineCount, City a, City b)
    {
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

    public void setIsOwned()
    {
        isOwned = true;
    }
}
