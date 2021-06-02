import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Implemented Singleton Pattern
 **/

public class RMOS {
    private static final RMOS singleton = new RMOS();
    private HashMap<String,RCMGroup> groupMap = new HashMap<>();
    DBConn db = DBConn.instance();
    RcmCounter counter = new RcmCounter();
    int numberOfRCMs;
    private HashMap<String, String> itemMapToId = new HashMap<>();
    private HashMap<String, String> itemMapToName = new HashMap<>();
    private HashMap<String, String> groupMapToId = new HashMap<>();
    private HashMap<String, String> groupMapToName = new HashMap<>();

    private RMOS(){}

    public HashMap<String, RCMGroup> getGroupMap() { return this.groupMap; }
    public HashMap<String, String> getItemMapToId() { return this.itemMapToId; }

    public static RMOS get_instance(){
        return singleton;
    }

    void init() throws SQLException {
        ResultSet result = db.getGroupIds();
        while(result.next()) {
            this.groupMap.put(result.getString("groupId"),new RCMGroup(result.getString("groupId"), result.getString("groupName")));
        }
        for (RCMGroup rcmGroup : groupMap.values()) {
            rcmGroup.print();
            rcmGroup.accept(counter);
        }

        ResultSet resultSet = db.getAllItems();
        while(resultSet.next()){
            this.itemMapToId.put(resultSet.getString("itemId"), resultSet.getString("itemName"));
            this.itemMapToName.put(resultSet.getString("itemName"), resultSet.getString("itemId"));
        }
    }

    void makeAccept(RmosMain.loadViewPanel loadViewPanel){
        for (RCMGroup rcmGroup : groupMap.values()) {
            rcmGroup.print();
            rcmGroup.accept(loadViewPanel);
        }
    }

    String createItem(String itemId, String itemName){
        if (itemMapToId.containsKey(itemId.toLowerCase())) {
            return "Item already exists with name '" + itemMapToId.get(itemId) + "' in the database";
        }
        else if (itemMapToName.containsKey(itemName.toLowerCase())) {
            return "Item already exists with ID '" + itemMapToName.get(itemName) + "' in the database";
        }
        else {
            itemMapToName.put(itemName.toLowerCase(), itemId.toLowerCase());
            itemMapToName.put(itemId.toLowerCase(), itemName.toLowerCase());
            db.insertItem(itemId.toLowerCase(), itemName.toLowerCase());
            return "Item successfully created";
        }
    }

    String createGroup(String groupId, String groupName) throws SQLException {
        if (groupMap.containsKey(groupId.toLowerCase())) {
            return "Group already exists with the ID in the database";
        }
        else {
            groupMap.put(groupId, new RCMGroup(groupId, groupName)); //handle new groups in RCM Group constructor
            db.InsertGroups(groupId.toLowerCase(), groupName.toLowerCase());
            return "Group successfully created";
        }
    }

    String createRCM(String id, String location, double capacity, double money, String groupId) {
        //Composite needs to be figured out

        return "";
    }

    String removeItemFromRCM(RCM rcm, String itemName){
        db.removeRcmItem(rcm.getRcmId(),itemMapToName.get(itemName));
        rcm.getAvailableItems().remove(itemMapToName.get(itemName));
        return "Item successfully removed";
    }

    String addItemToRCM(RCM rcm, String itemName, double itemPrice){
        db.insertRcmItem(rcm.getRcmId(),itemMapToName.get(itemName.toLowerCase()), itemPrice);
        rcm.getAvailableItems().put(itemMapToName.get(itemName), new Item(itemMapToName.get(itemName), itemName.toLowerCase(), itemPrice));
        return "Item successfully added";
    }

    String modifyItemOfRCM(RCM rcm, String itemName, double newItemPrice){
        db.changeItemPrice(rcm.getRcmId(),itemMapToName.get(itemName), newItemPrice);
        rcm.getAvailableItems().replace(itemMapToName.get(itemName), new Item(itemMapToName.get(itemName), itemName, newItemPrice));
        return "Item successfully added";
    }

    public void deactivate(RCM rcm){

    }

    public void activate(RCM rcm){

    }

    public void empty(RCM rcm){

    }
    public static void main(String args[]) throws SQLException {
        RMOS rmos = new RMOS().get_instance();
        rmos.init();
        System.out.println(rmos.counter.counter);
    }



}

