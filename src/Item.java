public class Item {
    private String itemName;
    private int itemID;
    private double pricePerMetric;
    private String itemIdStr;

    public Item(int itemID,String itemName,double pricePerMetric)
    {
        this.itemID=itemID;
        this.itemName=itemName;
        this.pricePerMetric=pricePerMetric;
    }

    public Item(String itemID,String itemName,double pricePerMetric)
    {
        this.itemIdStr=itemID;
        this.itemName=itemName;
        this.pricePerMetric=pricePerMetric;
    }
    public  int getItemID()
    {
        return this.itemID;
    }
    public void setItemID(int ItemID)
    {
        this.itemID=itemID;
    }
    public String getItemName()
    {
        return this.itemName;
    }
    public void setItemName(String itemName)
    {
        this.itemName=itemName;
    }
    public double getPricePerMetric()
    {
        return this.pricePerMetric;
    }
    public void setPricePerMetric(double pricePerMetric)
    {
        this.pricePerMetric=pricePerMetric;
    }

    void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemID=" + itemID +
                ", pricePerMetric=" + pricePerMetric +
                ", itemIdStr='" + itemIdStr + '\'' +
                '}';
    }
}
