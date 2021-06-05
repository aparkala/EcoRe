import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBItemDao implements ItemDao{
    private List<Item> itemList;
    @Override
    public boolean insertRcmItem(String rcmId, Integer itemId, Double price) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            String query = "INSERT INTO RCM_Items_(rcmId,itemId,itemPrice) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            ps.setInt(2,itemId);
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

    @Override
    public boolean changeItemPrice(String rcmId, Integer itemId, Double newPrice) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            String query = "UPDATE RCM_Items_ SET itemPrice=? WHERE rcmId=? AND itemId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,newPrice);
            ps.setString(2,rcmId);
            ps.setInt(3,itemId);

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

    @Override
    public void removeRcmItem(String rcmId, Integer itemId) {
        boolean status=false;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            String query = "DELETE FROM RCM_Items_ WHERE rcmId=? AND itemId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            ps.setInt(2,itemId);

            status=ps.execute();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    @Override
    public List<Item> GetRCMItems(String rcmId) {
        ResultSet result=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
            String query = "SELECT RCM_Items_.itemId,Items_.itemName,RCM_Items_.itemPrice FROM RCM_Items_ inner join Items_ on RCM_Items_.itemId=Items_.itemId where RCM_Items_.rcmId=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1,rcmId);
            result=ps.executeQuery();
            //conn.close();
            itemList = new ArrayList<>();
            while(result.next()){
                itemList.add(new Item(result.getInt("itemId"), result.getString("itemName"), result.getDouble("itemPrice")));
            }

        }
        catch(Exception e)
        {
            System.out.println(e);

        }


        return itemList;
    }
}
