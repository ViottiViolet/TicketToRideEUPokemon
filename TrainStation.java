public class TrainStation
{
    private int worth = 4;
    private City location;
    private Railroad assimilated;
 
    private int playerNum;
    public TrainStation( int x)
    {
       
        playerNum = x;
        

    }

    public void setCity(City a)
    {
        location = a;
    }

    public void setAssimilatedTrack(Railroad r)
    {
        assimilated = r;
    }

    public Railroad getAssimilatedTrack()
    {
        return assimilated;
    }

}