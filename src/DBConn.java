import java.sql.*;
import java.util.List;

public class DBConn {

    private DBConn(){};

    static private DBConn instance_  = new DBConn();

    static public DBConn instance()
    {
        return instance_; //return Single instance
    }


    boolean InsertGroups(String groupId,String groupName) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();
            String query = "INSERT INTO Groups_(groupId,groupName) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,groupId);
            ps.setString(2,groupName);
            status=ps.execute();
            conn.close();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return status;
        }
    }
    //boolean InsertRCM(String rcmId,String groupId,double rcmCapacity,double money,String rcmLocation) {
    boolean InsertRCM(RCMCreate rcmCreate) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO RCM_(rcmId,groupId,rcmLocation,rcmCapacity,capacityLeft,money,opStatus) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmCreate.getRcmId());
            ps.setString(2,rcmCreate.getGroupId());
            ps.setString(3,rcmCreate.getLocation());
            ps.setDouble(4,rcmCreate.getCapacity());
            ps.setDouble(5,rcmCreate.getCapacityLeft());
            ps.setDouble(6,rcmCreate.getMoney());
            ps.setString(7,rcmCreate.getOpStatus());

            status=ps.execute();
            conn.close();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return status;
        }
    }
    ResultSet GetGroups() throws SQLException {
        Connection conn=null;
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM Groups_";
            PreparedStatement ps = conn.prepareStatement(query);
            result=ps.executeQuery();
            return result;

        }
        catch(Exception e)
        {
            System.out.println(e);
            return null;

        }
        finally {
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

    ResultSet GetRCMs(String rcmId) throws Exception {
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

    boolean insertRcmItem(String rcmId, String itemId, Double price) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO RCM_Items_(rcmId,itemId,itemPrice) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            ps.setString(2,itemId);
            ps.setDouble(3,price);

            status=ps.execute();
            conn.close();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return status;
        }
    }

    boolean insertItem(String itemId, String itemName) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO Items_(itemId,itemName) VALUES (?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,itemId);
            ps.setString(2,itemName);

            status=ps.execute();
            conn.close();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return status;
        }
    }

    boolean changeItemPrice(String rcmId, String itemId, Double newPrice) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "UPDATE RCM_Items_ SET itemPrice=? WHERE rcmId=? AND itemId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,newPrice);
            ps.setString(2,rcmId);
            ps.setString(3,itemId);

            status=ps.execute();
            conn.close();
            return status;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return status;
        }
    }

    void removeRcmItem(String rcmId, String itemId) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "DELETE FROM RCM_Items_ WHERE rcmId=? AND itemId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            ps.setString(2,itemId);

            status=ps.execute();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    ResultSet getGroupIds() {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT * from Groups_";
            PreparedStatement ps = conn.prepareStatement(query);
            result=ps.executeQuery();
            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }

    ResultSet GetRCMItems(String rcmId)
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT RCM_Items_.itemId,Items_.itemName,RCM_Items_.itemPrice FROM RCM_Items_ inner join Items_ on RCM_Items_.itemId=Items_.itemId where RCM_Items_.rcmId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            result=ps.executeQuery();
            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }

    ResultSet getRCMsFromGroup(String groupId)
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT * from RCM_ where groupId=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,groupId);
            result=ps.executeQuery();
            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }

    ResultSet getAllItems()
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT * from Items_";
            PreparedStatement ps = conn.prepareStatement(query);

            result=ps.executeQuery();
            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }
    ResultSet GetCapacityLeft(String rcmId)
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "SELECT capacityLeft,money FROM RCM_ where rcmId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            result=ps.executeQuery();

            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }
    int GetMaxTransactionId()
    {
        int transactionId=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt = conn.createStatement();
            String query = "select max(transactionId) from transactions_";
            PreparedStatement ps = conn.prepareStatement(query);

            ResultSet result=ps.executeQuery();
            while (result.next())
            {
                transactionId=result.getInt(1);
            }
            //conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return transactionId;
    }
    void InsertRCMItems(List<RCMTransaction> rcmTransactions) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();
            for(RCMTransaction transaction:rcmTransactions) {
                String query = "INSERT INTO Transactions_(transactionId,rcmId,itemId,weight,price,insertedDate,cash,isEmpty,groupId) VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setInt(1, transaction.getTransactionId());
                ps.setString(2, transaction.getRcmId());
                ps.setInt(3, transaction.getItemId());
                ps.setDouble(4, transaction.getWeight());
                ps.setDouble(5, transaction.getPrice());
                ps.setDate(6, transaction.getInsertedDate());
                ps.setDouble(7, transaction.getCash());
                ps.setInt(8,transaction.getIsEmpty());
                ps.setString(9,transaction.getGroupId());



                ps.execute();
            }
            conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }
    void UpdateCapacityMoney(double capacityLeft,double moneyLeft,String rcmId)
    {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();

            String query = "UPDATE RCM_ set capacityLeft = ?, money = ? where rcmId=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, capacityLeft);
            ps.setDouble(2, moneyLeft);
            ps.setString(3,rcmId);
            ps.execute();
            conn.close();

        }
        catch(Exception e)
        {
            System.out.println(e);

        }
    }
    ResultSet GetActiveRCM()
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();

            String query = "select groupId,rcmId from RCM_ where opStatus='active'";

            PreparedStatement ps = conn.prepareStatement(query);

            result=ps.executeQuery();


        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }
    int GetTotalRecycledItemsByMonth(String rcmId,String month,String year)
    {
        int count=0;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();

            String query = "select count(*) from Transactions_ where rcmId=? AND to_char(inserteddate,'fmMonth')=? AND to_char(inserteddate,'YYYY')=?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, rcmId);
            ps.setString(2, month);
            ps.setString(3,year);
            ResultSet result=ps.executeQuery();
            while (result.next())
            {
                count=result.getInt(1);
            }


        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return count;
    }
    ResultSet GetMostFrequentlyUsedRCM(int days)
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();

            String query = "SELECT a.rcmId,RCM_.rcmLocation FROM (SELECT rcmId,transactionid,COUNT(rcmId) rcmCount FROM Transactions_ where insertedDate > trunc(sysdate-?) GROUP BY rcmId,transactionid ORDER BY transactionid DESC ) a inner join RCM_ on a.rcmId=RCM_.rcmId WHERE  ROWNUM <= 1";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, days);
            result=ps.executeQuery();



        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }
    ResultSet GetWeightValueByRCM(Date fromDate,Date toDate,String groupId)
    {
    {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            Statement stmt=conn.createStatement();

            String query = "SELECT rcmId,sum(weight),sum(price) FROM  Transactions_ where to_char(inserteddate,'DD/MM/YYYY') between to_char(?,'DD/MM/YYYY') and to_char(?,'DD/MM/YYYY')  and  groupId=? and isEmpty=0 GROUP BY rcmId";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDate(1, fromDate);
            ps.setDate(2, toDate);
            ps.setString(3, groupId);
            result=ps.executeQuery();



        }
        catch(Exception e)
        {
            System.out.println(e);

        }
        return result;
    }
}
    ResultSet GetEmptyItemsByRCM(Date fromDate,Date toDate,String rcmID) {
        {
            ResultSet result = null;
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection conn = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
                Statement stmt = conn.createStatement();

                String query = "SELECT rcmId,count(*) FROM  Transactions_ where to_char(inserteddate,'DD/MM/YYYY') between to_char(?,'DD/MM/YYYY') and to_char(?,'DD/MM/YYYY')  and  rcmID=? and isEmpty=1 GROUP BY rcmId";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setDate(1, fromDate);
                ps.setDate(2, toDate);
                ps.setString(3, rcmID);
                result = ps.executeQuery();


            } catch (Exception e) {
                System.out.println(e);

            }
            return result;
        }
    }

}
