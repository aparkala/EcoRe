import java.util.List;

/**
 * Implemented Singleton Pattern
 **/

public class RMOS {
    private static final RMOS singleton = new RMOS();
    private List<RCMGroup> groupList;

    private RMOS(){}

    public RMOS get_instance(){
        return this.singleton;
    }
}
