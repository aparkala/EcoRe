import java.text.DecimalFormat;
import java.text.ParseException;

public class KgsMetricStrategy extends MetricStrategy{
    DecimalFormat df=new DecimalFormat("0.00");
    public double GetMetricPrice(double price) throws ParseException {
        String format = df.format(price * 0.45359237);
        return  (Double) df.parse(format);

    }
    public double PutMetricPrice(double price) throws ParseException {
        String format = df.format(price / 0.45359237);
        return  (Double) df.parse(format);

    }
    public String[] MetricTableHeader()
    {
        return new String[]{"Items", "Price/kg"};
    }
    public String[] MetricItemsTableHeader()
    {
       return new String[] {"Items","Weight","Price/kg"};
    }
}
