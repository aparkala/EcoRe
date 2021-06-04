import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCMGroup implements VisitableComponent{
    private String groupId;
    private String groupName;
    private HashMap<String, RCM> rcmMap;
    DBConn db = DBConn.instance();

    public RCMGroup(String groupId, String groupName) throws SQLException {
        this.groupId = groupId;
        this.groupName = groupName;
        rcmMap = new HashMap<>();
        init();
    }

    public String getGroupId()
    {
        return this.groupId;
    }

    public String getGroupName()
    {
        return this.groupName;

    }
    public void setGroupName(String groupName)
    {
        this.groupName=groupName;
    }

    public HashMap<String, RCM> getRcmMap()
    {
        return this.rcmMap;

    }
    public void setRcmMap(List<RCM> rcmList)
    {
        this.rcmMap=rcmMap;
    }

    private void init() throws SQLException {
        ResultSet result = db.getRCMsFromGroup(this.groupId);
        if (result != null) {
            while (result.next()) {
                rcmMap.put(result.getString("rcmId"), new RCM.RCMBuilder().withRCMId(result.getString("rcmId"))
                        .withGroupId(this.groupId)
                        .withLocation(result.getString("rcmLocation"))
                        .withOpStatus(Status.valueOf(result.getString("opStatus")))
                        .withCapacity(result.getDouble("rcmCapacity"))
                        .withCapacityLeft(result.getDouble("capacityLeft"))
                        .withMoney(result.getDouble("money"))
                        .build());
                rcmMap.get(result.getString("rcmId")).setLastEmptied(result.getDate("lastEmptied"));
            }
        }
    }

    void print(){
        System.out.println("GroupId: " + groupId
                            + "GroupName: " + groupName);
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
        for (RCM rcm : rcmMap.values()){
            rcm.print();
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");
    }


    @Override
    public void accept(RmosMain.loadViewPanel buttonLoader) {
        for (RCM rcm : rcmMap.values()){
            rcm.accept(buttonLoader);
        }
    }

}
