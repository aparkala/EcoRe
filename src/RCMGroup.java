import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RCMGroup {
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
    public void setRcmList(List<RCM> rcmList)
    {
        this.rcmMap=rcmMap;
    }

    private void init() throws SQLException {
        ResultSet result = db.getRCMsFromGroup(this.groupId);

        while(result.next()) {
            rcmMap.put(result.getString("rcmId"), new RCM.RCMBuilder().withRCMId(result.getString("rcmId"))
                                .withGroupId(this.groupId)
                                .withLocation(result.getString("rcmLocation"))
                                .withOpStatus(Status.valueOf(result.getString("opStatus")))
                                .withCapacity(result.getDouble("rcmCapacity"))
                                .withCapacityLeft(result.getDouble("rcmCapacity"))
                                .withMoney(result.getDouble("money"))
                                .build());
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

    void makeAccept(RcmCounter counter){
        for (RCM rcm : rcmMap.values()){
            rcm.accept(counter);
        }
    }

    void makeAccept(RmosMain.loadViewPanel loadViewPanel){
        for (RCM rcm : rcmMap.values()){
            rcm.accept(loadViewPanel);
        }
    }
}
