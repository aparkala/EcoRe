import java.sql.ResultSet;
import java.util.List;

public interface ItemDao {
    public boolean insertRcmItem(String rcmId, Integer itemId, Double price);
    public boolean changeItemPrice(String rcmId, Integer itemId, Double newPrice);
    public void removeRcmItem(String rcmId, Integer itemId);
    public List<Item> GetRCMItems(String rcmId);
}
