import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.util.Map;

public class RmosMain extends ApplicationFrame{
    private JTabbedPane CVMS;
    private JPanel rmosMain;
    private JPanel createPane;
    private JPanel ViewPane;
    private JPanel statsPane;
    private JComboBox groupIds;
    private JTextField rcmId;
    private JTextField rcmCapacity;
    private JTextField money;
    private JTextField rcmLocation;
    private JButton newGroupSubmit;
    private JButton rcmSubmit;
    private JPanel CreateRCMPane;
    private JPanel createGroupPane;
    private JTextField txtGroupID;
    private JTextField txtGroupName;
    private JButton submitGroup;
    private JTabbedPane ItemsPane;
    private JComboBox comboBoxGroup;
    private JComboBox comboBoxRCM;
    private JComboBox comboBoxMonth;
    private JComboBox comboBoxYear;
    private JButton buttonSubmit;
    private JLabel lblMessage;
    private JTextField txtNoOfDays;
    private JButton buttonFrequentlyUsed;
    private JLabel lblFrequentlyUsed;
    private JTabbedPane tabbedPane1;
    private JPanel visualPane;
    private JPanel textPane;
    private JComboBox groupComboBox;
    private JTextField txtFromDate;
    private JTextField txtToDate;
    private JButton buttonWeightValue;
    private JRadioButton weightRadioButton;
    private JRadioButton valueRadioButton;
    private JTable tableWeightValue;
    private JComboBox RCMComboBox;
    private JRadioButton emptyTimesRadioButton;
    private JPanel Vpane;
    private JPanel Visual;
    private JLabel lblError;
    private JLabel lblRCMError;
    private String[] RCMWEIGHT = {"RCM","WEIGHT"};
    private String[] RCMVALUE = {"RCM","VALUE"};
    private String[] RCMEMPTY = {"RCM","EMPTY"};

    public RmosMain() {
        super( "applicationTitle" );
        initComponents();
    }
    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(rmosMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 700, 400);
        frame.setLocationRelativeTo(null);
        txtGroupID.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtGroupID.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        txtGroupName.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtGroupName.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        groupIds.setFont(new Font("Montserrat", Font.PLAIN, 15));
        groupIds.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));


        rcmId.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmId.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        rcmCapacity.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmCapacity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        money.setFont(new Font("Montserrat", Font.PLAIN, 15));
        money.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Money", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        rcmLocation.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmLocation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        weightRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == weightRadioButton){
                    tableWeightValue.setModel(new DefaultTableModel(RCMWEIGHT, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                }
            }
        });

        valueRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == valueRadioButton){
                    tableWeightValue.setModel(new DefaultTableModel(RCMVALUE, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                }
            }
        });

        emptyTimesRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == emptyTimesRadioButton){
                    tableWeightValue.setModel(new DefaultTableModel(RCMEMPTY, 0) {
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                }
            }
        });

        tableWeightValue.setModel(new DefaultTableModel(RCMWEIGHT, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tableWeightValue.setRowHeight(40);
        tableWeightValue.getTableHeader().setReorderingAllowed(false);
        tableWeightValue.getTableHeader();
        tableWeightValue.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 20));
        //((DefaultTableCellRenderer)tableRCM.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableWeightValue.setFont(new Font("Montserrat", Font.PLAIN, 15));
        //tableRCM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableWeightValue.getColumnModel().getColumn(0);
        tableWeightValue.getColumnModel().getColumn(1);


        ButtonGroup group=new ButtonGroup();
        group.add(weightRadioButton);
        group.add(valueRadioButton);
        group.add(emptyTimesRadioButton);
        weightRadioButton.setSelected(true);




        submitGroup.addActionListener(evt -> SubmitGroup(evt));
        rcmSubmit.addActionListener(evt -> SubmitRCM(evt));
        comboBoxGroup.addActionListener(evt -> SelectRCM(evt));
        buttonSubmit.addActionListener(evt -> SubmitTotalItems(evt));
        buttonFrequentlyUsed.addActionListener(evt -> SubmitFrequentlyUsed(evt));
        buttonWeightValue.addActionListener(evt->SubmitWeightValue(evt));

        loadGroup();
        LoadMonth();
        LoadYear();



        frame.setVisible(true);
    }
    private void SubmitGroup(ActionEvent e)
    {
        lblError.setVisible(true);
        if(txtGroupID.getText().length()<1)
        {

            lblError.setText("Please enter ID");
        }
        else if(txtGroupName.getText().length()<1)
        {

            lblError.setText("Please enter Name");
        }
        else
        {

            boolean status=DBConn.instance().InsertGroups(txtGroupID.getText(),txtGroupName.getText());
            loadGroup();
            if(status)
            {
                lblError.setText("Success");
            }

        }
    }
    private void SubmitRCM(ActionEvent e)
    {
        lblError.setVisible(true);
        if(rcmId.getText().length()<1)
        {
            lblError.setText("Please enter ID");
        }
        else if(rcmCapacity.getText().length()<1)
        {
            lblError.setText("Please enter capacity");
        }
        else if(money.getText().length()<1)
        {
            lblError.setText("Please enter money");
        }
        else if(rcmLocation.getText().length()<1)
        {
            lblError.setText("Please enter location");
        }
        else
        {
            boolean status=DBConn.instance().InsertRCM(rcmId.getText(),groupIds.getSelectedItem().toString(), Double.parseDouble(rcmCapacity.getText()), Double.parseDouble(money.getText()), rcmLocation.getText());
            if(status)
            {
                lblError.setText("Success");
            }
        }

    }
    private void loadGroup()
    {
        try {
            ResultSet result = DBConn.instance().GetGroups();
            groupIds.removeAllItems();
            while (result.next())
            {

                groupIds.addItem(result.getString("groupId"));
                comboBoxGroup.addItem(result.getString("groupId"));
                groupComboBox.addItem(result.getString("groupId"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    //apply pattern
    private void LoadRCM(String groupId)
    {
        try {
            ResultSet result = DBConn.instance().GetRCM(groupId);
            comboBoxRCM.removeAllItems();
            comboBoxRCM.addItem("--Select--");
            RCMComboBox.addItem("--Select--");
            while (result.next())
            {

                comboBoxRCM.addItem(result.getString(1));
                RCMComboBox.addItem(result.getString(1));

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void SelectRCM(ActionEvent evt)
    {
        LoadRCM(comboBoxGroup.getSelectedItem().toString());
    }
    private void LoadMonth()
    {
        comboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Month--", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));

    }
    private void LoadYear()
    {
        comboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Year--"}));
        for (int year = 2015; year <= Calendar.getInstance().get(Calendar.YEAR); year++) {
            comboBoxYear.addItem(year + "");
        }
    }
    private void SubmitTotalItems(ActionEvent evt)
    {
        int count=DBConn.instance().GetTotalRecycledItemsByMonth(comboBoxRCM.getSelectedItem().toString(),comboBoxMonth.getSelectedItem().toString(),comboBoxYear.getSelectedItem().toString());
        lblMessage.setText("The total number for recycled items for the month "+comboBoxMonth.getSelectedItem().toString()+" is "+count);
    }
    private void SubmitFrequentlyUsed(ActionEvent evt)
    {
        try {
            ResultSet resultSet = DBConn.instance().GetMostFrequentlyUsedRCM(Integer.parseInt(txtNoOfDays.getText()));
            while (resultSet.next()) {
                lblFrequentlyUsed.setText("The most frequently used RCM is "+resultSet.getString(1)+" at location "+resultSet.getString(2));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void SubmitWeightValue(ActionEvent evt)
    {
        try {

            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date dateFrom = dateFormat.parse(txtFromDate.getText());
            Date dateTo=dateFormat.parse(txtToDate.getText());
            ResultSet resultSet=null;
            DefaultCategoryDataset dataset =new DefaultCategoryDataset( );
            if(emptyTimesRadioButton.isSelected()) {
                resultSet = DBConn.instance().GetEmptyItemsByRCM(new java.sql.Date(dateFrom.getTime()), new java.sql.Date(dateTo.getTime()), RCMComboBox.getSelectedItem().toString());
            }
            else {
                resultSet = DBConn.instance().GetWeightValueByRCM(new java.sql.Date(dateFrom.getTime()), new java.sql.Date(dateTo.getTime()), groupComboBox.getSelectedItem().toString());
            }

            if(weightRadioButton.isSelected()) {
                while (resultSet.next()) {
                    DefaultTableModel weightTableModel = (DefaultTableModel) (tableWeightValue.getModel());
                    weightTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2)});
                    dataset.addValue(resultSet.getDouble(2), "RCM", resultSet.getString(1));
                }
                BarChart_AWT("Hello", dataset);
            }
            else if(valueRadioButton.isSelected())
            {
                while (resultSet.next()) {
                    DefaultTableModel valueTableModel = (DefaultTableModel) (tableWeightValue.getModel());
                    valueTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(3)});
                    dataset.addValue(resultSet.getDouble(3), "RCM", resultSet.getString(1));
                }
                BarChart_AWT("Hello", dataset);

            }
            else if(emptyTimesRadioButton.isSelected())
            {

                while (resultSet.next()) {
                    DefaultTableModel emptyTableModel = (DefaultTableModel) (tableWeightValue.getModel());
                    emptyTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2)});
                    dataset.addValue(resultSet.getInt(2), "RCM", resultSet.getString(1));
                }
                BarChart_AWT("Hello", dataset);
            }

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void BarChart_AWT(  String chartTitle,DefaultCategoryDataset dataset ) {

        JFreeChart barChart=null;
        if(weightRadioButton.isSelected()) {
            barChart = ChartFactory.createBarChart(
                    chartTitle,
                    "RCM",
                    "Weight",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }
        else if(valueRadioButton.isSelected())
        {
          barChart = ChartFactory.createBarChart(
                    chartTitle,
                    "RCM",
                    "Value",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }
        else if(emptyTimesRadioButton.isSelected())
        {
            barChart = ChartFactory.createBarChart(
                    chartTitle,
                    "RCM",
                    "Empty times",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, true, false);
        }


        ChartPanel chartPanel=new ChartPanel(barChart);
        //chartPanel.setPreferredSize(new java.awt.Dimension( visualPane.getWidth(),visualPane.getHeight()) );



        visualPane=new JPanel();
        JPanel panel = new JPanel();
        panel.add(chartPanel);
        visualPane.add(panel);
        JOptionPane.showMessageDialog(null,panel,"Information",JOptionPane.INFORMATION_MESSAGE);



    }


    public static void main(String[] args)
    {
        RmosMain r=new RmosMain();
    }

}


