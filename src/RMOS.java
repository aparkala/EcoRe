import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Implemented Singleton Pattern
 **/

public class RMOS {
    DBConn db = DBConn.instance();
    private static final RMOS singleton = new RMOS();
    private HashMap<String,RCMGroup> groupMap = new HashMap<>();

    private HashMap<Integer, String> itemMapToId = new HashMap<>();
    private HashMap<String, Integer> itemMapToName = new HashMap<>();

    private RMOS(){}

    public HashMap<String, RCMGroup> getGroupMap() { return this.groupMap; }


    public static RMOS get_instance(){
        return singleton;
    }

    void init() throws SQLException {
        ResultSet result = db.getGroupIds();
        while(result.next()) {
            this.groupMap.put(result.getString("groupId"),new RCMGroup(result.getString("groupId"), result.getString("groupName")));
        }


        ResultSet resultSet = db.getAllItems();
        while(resultSet.next()){
            this.itemMapToId.put(resultSet.getInt("itemId"), resultSet.getString("itemName"));
            this.itemMapToName.put(resultSet.getString("itemName"), resultSet.getInt("itemId"));
        }

    }


    //RCM Hook Methods
    RCM getRCM(String groupId, String rcmID){
        return groupMap.get(groupId).getRcmMap().get(rcmID);
    }

    String createItem(String itemId, String itemName) throws SQLException {
        if (itemMapToId.containsKey(Integer.parseInt(itemId))) {
            return "Item already exists with name '" + itemMapToId.get(itemId) + "' in the database";
        }
        else if (itemMapToName.containsKey(itemName.toLowerCase())) {
            return "Item already exists with ID '" + itemMapToName.get(itemName) + "' in the database";
        }
        else {

            db.insertItem(itemName.toLowerCase());
            LoadItems();
            return "Item successfully created";
        }
    }

    String createGroup(String groupId, String groupName) throws SQLException {
        if (groupMap.containsKey(groupId.toLowerCase())) {
            return "Group already exists with the ID in the database";
        }
        else {
            groupMap.put(groupId.toLowerCase(), new RCMGroup(groupId.toLowerCase(), groupName)); //handle new groups in RCM Group constructor
            db.InsertGroups(groupId.toLowerCase(), groupName.toLowerCase());
            return "Group successfully created";
        }
    }

    String createRCM(String rcmId, String location, double capacity, double money, String groupId) throws SQLException {
        //Composite needs to be figured out

        RCM rcm = new RCM.RCMBuilder().withRCMId(rcmId)
                .withGroupId(groupId)
                .withLocation(location)
                .withOpStatus(Status.valueOf("INACTIVE"))
                .withCapacity(capacity)
                .withCapacityLeft(capacity)
                .withMoney(money)
                .build();

        groupMap.get(groupId).getRcmMap().put(rcmId, rcm);

        db.insertRCM(rcm);

        return "RCM Successfully Created";
    }

    void makeAccept(RmosMain.loadViewPanel loadViewPanel){
        for (RCMGroup rcmGroup : groupMap.values()) {
            rcmGroup.print();
            rcmGroup.accept(loadViewPanel);
        }
    }



    String removeItemFromRCM(RCM rcm, String itemName){
        db.removeRcmItem(rcm.getRcmId(),itemMapToName.get(itemName));
        rcm.getAvailableItems().remove(itemName);
        return "Item successfully removed";
    }

    String addItemToRCM(RCM rcm, String itemName, double itemPrice){
        db.insertRcmItem(rcm.getRcmId(),itemMapToName.get(itemName.toLowerCase()), itemPrice);
        rcm.getAvailableItems().put(itemName.toLowerCase(), new Item(itemMapToName.get(itemName), itemName.toLowerCase(), itemPrice));
        return "Item successfully added";
    }

    String modifyItemOfRCM(RCM rcm, String itemName, double newItemPrice){
        db.changeItemPrice(rcm.getRcmId(),itemMapToName.get(itemName), newItemPrice);
        rcm.getAvailableItems().replace(itemName.toLowerCase(), new Item(itemMapToName.get(itemName), itemName, newItemPrice));
        return "Item successfully modified";
    }

    public void deactivate(RCM rcm){
        rcm.deactivate();
    }

    public void activate(RCM rcm){

        rcm.activate();
    }

    public void empty(RCM rcm){
        int transactionId= db.GetMaxTransactionId();
       int itemId=db.GetItem(rcm.getRcmId());
        db.setStatusFULL(rcm.getRcmId());
        rcm.empty();
        db.InsertEmptyTransaction(new RCMTransaction.RCMTransactionBuilder().withTransactionId(transactionId+1).withRCMId(rcm.getRcmId()).withGroupId(rcm.getGroupId()).withInsertedDate(rcm.getLastEmptied()).withIsEmpty(1).build(),itemId);


    }
    public void delete(RCM rcm){
        db.setStatusDelete(rcm.getRcmId());
        rcm.setStatus(Status.DELETED);

    }
    public static void main(String args[]) throws SQLException {
        RMOS rmos = new RMOS().get_instance();
        rmos.init();
    }


    public HashMap<String, Integer> getItemMapToName() {
        return this.itemMapToName;
    }
    private void LoadItems() throws SQLException {
        ResultSet resultSet = db.getAllItems();
        itemMapToId=new HashMap<>();
        itemMapToName=new HashMap<>();
        while(resultSet.next()){
            this.itemMapToId.put(resultSet.getInt("itemId"), resultSet.getString("itemName"));
            this.itemMapToName.put(resultSet.getString("itemName"), resultSet.getInt("itemId"));
        }

    }
}

