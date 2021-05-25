import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 * TODO : Builder or Prototype Pattern
 */

public class RCM {
    private String rcmId;
    private String groupId;
    private String rcmName;
    private String location;
    private double capacity;
    private double capacityLeft;
    private double moneyLeft;
    private String lastEmptiedStr;
    private Status status;
    private HashMap<String,Item> availableItems;
    private List<HashMap<Item,Double>> insertedItems;
    private DBConn db = DBConn.instance();

    //Constructor
    public RCM(String rcmID) throws Exception {
        ResultSet result = db.GetRCM(rcmId);
        ResultSetMetaData md = result.getMetaData();

        groupId = result.getString(2);
        location = result.getString(3);
        lastEmptiedStr = result.getString(6);
        status = Status.valueOf(result.getString(7));
        capacity = result.getDouble(4);
        capacityLeft = result.getDouble(5);
        moneyLeft = result.getDouble(8);
    }

    //Getters and Setters
    public String getRcmId()
    {
        return this.rcmId;

    }
    public void setRcmId(String rcmId)
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

    public double getCapacityLeft()
    {
        return this.capacityLeft;

    }
    public void setCapacityLeft(double capacityAvailable)
    {
        this.capacityLeft=capacityAvailable;
    }

    public double getMoneyLeft()
    {
        return this.moneyLeft;

    }
    public void setMoneyAvailable(double moneyLeft)
    {
        this.moneyLeft=moneyLeft;
    }

    public String getLastEmptiedStr()
    {
        return this.lastEmptiedStr;

    }
    public void setLastEmptied(String lastEmptiedStr)
    {
        this.lastEmptiedStr=lastEmptiedStr;
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

    //Other methods
    public void deactivate(){
        db.setStatusInactive(rcmId);
        //update transaction log ?
    }
    public void activate(){
        db.setStatusActive(rcmId);
        //update transaction log?
    }

    public void empty() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        lastEmptiedStr = dateFormat.format(date);
        db.setLastEmptied(rcmId, lastEmptiedStr);

        //update transaction log
    }

/****
 * TODO :   Builder or Prototype Pattern
 *          Implement activate, deactivate and empty methods

****/
}
