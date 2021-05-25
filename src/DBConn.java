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
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM RCM_ where rcmId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rcmId);
            result = ps.executeQuery();
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

}


