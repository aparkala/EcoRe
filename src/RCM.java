import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 * TODO : Builder or Prototype Pattern
 */

public class RCM implements VisitableComponent {
    private String rcmId;
    private String groupId;
    private String location;
    private double capacity;
    private double capacityLeft;
    private double money;
    private String lastEmptiedStr;
    private Status opStatus;
    private HashMap<String,Item> itemMap;
    private List<HashMap<Item,Double>> insertedItems;
    private DBConn db = DBConn.instance();

    @Override
    public void accept(RmosMain.loadViewPanel buttonLoader) {
        buttonLoader.visit(this);
    }

    public RCM(String rcmId) throws Exception {
        this.rcmId = rcmId;
        ResultSet result = db.GetRCM(rcmId);
        while (result.next()) {
            this.groupId = result.getString(2);
            this.location = result.getString(3);
            this.lastEmptiedStr = result.getString(6);
            this.opStatus = Status.valueOf(result.getString(7));
            this.capacity = result.getDouble(4);
            this.capacityLeft = result.getDouble(5);
            this.money = result.getDouble(8);

        }
    }

    /**public RCM(String groupId, String location, double capacity, double capacityLeft, double moneyLeft, String lastEmptiedStr, Status status) {
        this.groupId = groupId;
        this.location = location;
        this.capacity = capacity;
        this.capacityLeft = capacityLeft;
        this.moneyLeft = moneyLeft;
        this.lastEmptiedStr = lastEmptiedStr;
        this.status = status;
     public RCM(String rcmId) throws Exception {
     this.rcmId = rcmId;
     ResultSet result = db.GetRCM(rcmId);
     while(result.next()) {
     this.groupId = result.getString(2);
     this.location = result.getString(3);
     this.lastEmptiedStr = result.getString(6);
     this.status = Status.valueOf(result.getString(7));
     this.capacity = result.getDouble(4);
     this.capacityLeft = result.getDouble(5);
     this.moneyLeft = result.getDouble(8);
     }
     System.out.println(this.toString());

     }
    }**/

    //Constructor


    private RCM(RCM.RCMBuilder rb) throws SQLException {
        this.groupId=rb.groupId;
        this.rcmId = rb.rcmId;
        this.capacity = rb.capacity;
        this.capacityLeft = rb.capacityLeft;
        this.money = rb.money;
        this.location = rb.location;
        this.opStatus = rb.opStatus;
        itemMap = new HashMap<>();
        insertedItems = new ArrayList<>();
        init();
    }

    void init() throws SQLException {
        ResultSet resultSet = db.GetRCMItems(this.rcmId);

        if(resultSet == null){
            return;
        }
        while((resultSet != null) && (resultSet.next())) {
            itemMap.put(resultSet.getString("itemName"), new Item(resultSet.getString("itemId"), resultSet.getString("itemName"), resultSet.getDouble("itemPrice")));
        }
    }

    public static class RCMBuilder {
        private String rcmId;
        private String groupId;
        private String location;
        private double capacity;
        private double capacityLeft;
        private double money;
        private String lastEmptiedStr;
        private Status opStatus;

        public RCMBuilder() {

        }

        public RCM.RCMBuilder withRCMId(String rcmId) {
            this.rcmId = rcmId;
            return this;
        }

        public RCM.RCMBuilder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public RCM.RCMBuilder withCapacity(double capacity) {
            this.capacity = capacity;
            return this;
        }

        public RCM.RCMBuilder withCapacityLeft(double capacityLeft) {
            this.capacityLeft = capacityLeft;
            return this;
        }

        public RCM.RCMBuilder withMoney(double money) {
            this.money = money;
            return this;
        }

        public RCM.RCMBuilder withLocation(String location) {
            this.location = location;
            return this;
        }

        public RCM.RCMBuilder withOpStatus(Status opStatus) {
            this.opStatus = opStatus;
            return this;
        }



        public RCM build() throws SQLException {
            return new RCM(this);
        }
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
    public String getGroupId()
    {
        return this.groupId=groupId;
    }
    public Status getOpStatus()
    {
        return this.opStatus=opStatus;
    }

    @Override
    public String toString() {
        return "RCM{" +
                "rcmId='" + rcmId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", capacityLeft=" + capacityLeft +
                ", moneyLeft=" + money +
                ", lastEmptiedStr='" + lastEmptiedStr + '\'' +
                ", status=" + opStatus +
                '}';
    }

    public double getMoneyLeft()
    {
        return this.money;

    }
    public void setMoneyAvailable(double moneyLeft)
    {
        this.money=moneyLeft;
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
        return this.opStatus;

    }
    public void setStatus(Status status)
    {
        this.opStatus=status;
    }

    public HashMap<String,Item> getAvailableItems()
    {
        return this.itemMap;

    }
    public void setAvailableItems(HashMap<String,Item> availableItems)
    {
        this.itemMap=availableItems;
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
        this.setStatus(Status.INACTIVE);
        //update transaction log ?
    }
    public void activate(){
        db.setStatusActive(rcmId);
        this.setStatus(Status.ACTIVE);
        //update transaction log?
    }
    

    public void empty() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        lastEmptiedStr = dateFormat.format(date);
        db.setLastEmptied(rcmId, lastEmptiedStr);
        this.setStatus(Status.FULL);

        //update transaction log
    }

    void print() {
        System.out.println(this.toString());
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        for (Item item : itemMap.values()) {
            item.print();
        }
    }
}
