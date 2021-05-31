import java.sql.*;


public class DBConn {

    private DBConn() {
    }

    ;

    static private DBConn instance_ = new DBConn();

    static public DBConn instance() {
        return instance_; //return Single instance
    }


    boolean InsertGroups(String groupId, String groupName) {
        boolean status = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO Groups_(groupId,groupName) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, groupId);
            ps.setString(2, groupName);
            status = ps.execute();
            conn.close();
            return status;
        } catch (Exception e) {
            System.out.println(e);
            return status;
        }
    }

    boolean InsertRCM(String rcmId, String groupId, double rcmCapacity, double money, String rcmLocation) {
        boolean status = false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO RCM_(rcmId,groupId,rcmLocation,rcmCapacity,capacityLeft,money,opStatus) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rcmId);
            ps.setString(2, groupId);
            ps.setString(3, rcmLocation);
            ps.setDouble(4, rcmCapacity);
            ps.setDouble(5, rcmCapacity);
            ps.setDouble(6, money);
            ps.setString(7, "inactive");

            status = ps.execute();
            conn.close();
            return status;
        } catch (Exception e) {
            System.out.println(e);
            return status;
        }
    }

    ResultSet GetGroups() throws SQLException {
        Connection conn = null;
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Groups_";
            PreparedStatement ps = conn.prepareStatement(query);
            result = ps.executeQuery();
            return result;

        } catch (Exception e) {
            System.out.println(e);
            return null;

        } finally {
            //conn.close();
        }

    }

    ResultSet GetRCM(String rcmId) throws Exception {
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");

            String query = "SELECT rcm_.* FROM rcm_ where rcmId=?";
            //String query = "SELECT rcm_.* FROM rcm_";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rcmId);
            result = ps.executeQuery();
            /**while (result.next()){
                RCM rcm = new RCM(result.getString(2), result.getString(3),
                        result.getDouble(4), result.getDouble(5), result.getDouble(8),
                        result.getString(6), Status.valueOf(result.getString(7)));
            }**/
            //conn.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return result;
    }

    public void setStatusActive(String rcmID){
        // change rcm status to ACTIVE
    }

    public void setStatusInactive(String rcmID){
        // change rcm status to INACTIVE
    }

    public void setStatusFull(String rcmId){
        // change rcm status to FULL
    }

    public void setLastEmptied(String rcmId, String lastEmptied){
        // change rcm last emptied
    }

    public ResultSet getRcmItems(String rcmId) {
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");

            String query = "SELECT itemid, itemname, itemprice FROM rcm_items_ natural join items_ on rcm_items_.itemid = items_.itemid where rcm_items_.rcmid=?";
            //String query = "SELECT rcm_.* FROM rcm_";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rcmId);
            result = ps.executeQuery();
            /**while (result.next()){
             RCM rcm = new RCM(result.getString(2), result.getString(3),
             result.getDouble(4), result.getDouble(5), result.getDouble(8),
             result.getString(6), Status.valueOf(result.getString(7)));
             }**/
            //conn.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return result;
    }

    public ResultSet getAllItems(){
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");

            String query = "SELECT * FROM Items_";
            PreparedStatement ps = conn.prepareStatement(query);

            result = ps.executeQuery();
            /**while (result.next()){
             RCM rcm = new RCM(result.getString(2), result.getString(3),
             result.getDouble(4), result.getDouble(5), result.getDouble(8),
             result.getString(6), Status.valueOf(result.getString(7)));
             }**/
            //conn.close();

        } catch (Exception e) {
            System.out.println(e);

        }
        return result;
    }

}


