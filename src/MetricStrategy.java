import java.text.ParseException;

public abstract class MetricStrategy {
    public abstract double GetMetricPrice(double price) throws ParseException;
    public abstract double PutMetricPrice(double price) throws ParseException;
    public abstract String[] MetricTableHeader();
    public abstract String[] MetricItemsTableHeader();
}
