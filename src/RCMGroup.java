import java.util.List;

public class RCMGroup {
    private int groupId;
    private String groupName;
    private int zipCode;
    private List<RCM> rcmList;

    public int getGroupId()
    {
        return this.groupId;
    }
    public void setGroupId(int groupId)
    {
        this.groupId=groupId;
    }
    public String getGroupName()
    {
        return this.groupName;

    }
    public void setGroupName(String groupName)
    {
        this.groupName=groupName;
    }
    public int getZipCode()
    {
        return this.zipCode;

    }
    public void setZipCode(int zipCode)
    {
        this.zipCode=zipCode;
    }
    public List<RCM> getRcmList()
    {
        return this.rcmList;

    }
    public void setRcmList(List<RCM> rcmList)
    {
        this.rcmList=rcmList;
    }
}
