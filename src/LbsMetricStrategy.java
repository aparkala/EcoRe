

public class LbsMetricStrategy extends MetricStrategy{

    public double GetMetricPrice(double price)
    {
        return price;
    }
    public double PutMetricPrice(double price)
    {
        return price;
    }
    public String[] MetricTableHeader()
    {
        return new String[]{"Items", "Price/lb"};
    }
    public String[] MetricItemsTableHeader()
    {
        return new String[] {"Items","Weight","Price/lb"};
    }
}