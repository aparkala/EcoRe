import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dummy extends JFrame {

    private static final long serialVersionUID = 1L;

    public dummy(String appTitle) throws ParseException, SQLException {
        super(appTitle);

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "chartTitle",
                "RCM",
                "Weight",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel=new ChartPanel(barChart);


        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() throws ParseException, SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateFrom = dateFormat.parse("05/26/2021");
        Date dateTo=dateFormat.parse("05/27/2021");
        ResultSet resultSet = DBConn.instance().GetWeightValueByRCM(new java.sql.Date(dateFrom.getTime()),new java.sql.Date(dateTo.getTime()),"group1");



        while (resultSet.next()) {

            dataset.addValue(resultSet.getDouble(2),"RCM",resultSet.getString(1));
        }


        return dataset;
    }

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(()->{
            dummy example= null;
            try {
                example = new dummy("Bar Chart Window");
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            example.setSize(800, 400);
            example.setLocationRelativeTo(null);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }
}