import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class RCM {
    private int rcmId;
    private String rcmName;
    private String location;
    private double capacity;
    private double capacityAvailable;
    private double moneyAvailable;
    private Date lastEmptied;
    private Status status;
    private HashMap<String,Item> availableItems;
    private List<HashMap<Item,Double>> insertedItems;

    public int getRcmId()
    {
        return this.rcmId;

    }
    public void setRcmId(int rcmId)
    {
        this.rcmId=rcmId;
    }
    public String getRcmName()
    {
        return this.rcmName;

    }
    public void setRcmName(String rcmName)
    {
        this.rcmName=rcmName;
    }
    public String getLocation()
    {
        return this.location;

    }
    public void setLocation(String location)
    {
        this.location=location;
    }
    public double getCapacity()
    {
        return this.capacity;

    }
    public void setCapacity(double capacity)
    {
        this.capacity=capacity;
    }
    public double getCapacityAvailable()
    {
        return this.capacityAvailable;

    }
    public void setCapacityAvailable(double capacityAvailable)
    {
        this.capacityAvailable=capacityAvailable;
    }
    public double getMoneyAvailable()
    {
        return this.moneyAvailable;

    }
    public void setMoneyAvailable(double moneyAvailable)
    {
        this.moneyAvailable=moneyAvailable;
    }
    public Date getLastEmptied()
    {
        return this.lastEmptied;

    }
    public void setLastEmptied(Date lastEmptied)
    {
        this.lastEmptied=lastEmptied;
    }
    public Status getStatus()
    {
        return this.status;

    }
    public void setStatus(Status status)
    {
        this.status=status;
    }
    public HashMap<String,Item> getAvailableItems()
    {
        return this.availableItems;

    }
    public void setAvailableItems(HashMap<String,Item> availableItems)
    {
        this.availableItems=availableItems;
    }
    public List<HashMap<Item,Double>> getInsertedItems()
    {
        return this.insertedItems;

    }
    public void setInsertedItems(List<HashMap<Item,Double>> insertedItems)
    {
        this.insertedItems=insertedItems;
    }



}
