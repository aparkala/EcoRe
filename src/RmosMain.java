import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.util.List;
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
    private JPanel visualPane;
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
    private JLabel lblImage;
    private JLabel lblGroup;
    private JLabel lblRCM;
    private JPanel ErrorPanel;
    private JLabel lblNoOfItemsHeader;
    private JLabel lblMostUsedHeader;
    private JLabel lblNoOfDays;
    private JTabbedPane statsTabbedPane;
    private JPanel visualPanel;
    private JScrollPane textScrollPane;
    private JLabel lblStatsHeader;
    private JLabel lblGroupId;
    private JLabel lblGroupName;
    private JLabel lblRCMId;
    private JLabel lblCapacity;
    private JLabel lblMoney;
    private JLabel lblLocation;
    private JLabel lblFrom;
    private JLabel lblTo;
    private JLabel lblCategory;
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
       // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1200, 725);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("Images/ecore.png");
        lblImage.setIcon(icon);

        CVMS.setFont(new Font("Montserrat", Font.PLAIN, 15));
        statsPane.setFont(new Font("Montserrat", Font.PLAIN, 15));
        ItemsPane.setFont(new Font("Montserrat", Font.PLAIN, 15));

        lblGroup.setForeground(Color.white);
        lblGroup.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblGroupId.setForeground(Color.black);
        lblGroupId.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblGroupName.setForeground(Color.black);
        lblGroupName.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblRCMId.setForeground(Color.black);
        lblRCMId.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblCapacity.setForeground(Color.black);
        lblCapacity.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblMoney.setForeground(Color.black);
        lblMoney.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblLocation.setForeground(Color.black);
        lblLocation.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblGroup.setForeground(Color.white);
        lblGroup.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblRCM.setForeground(Color.white);
        lblRCM.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblNoOfItemsHeader.setForeground(Color.white);
        lblNoOfItemsHeader.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblMostUsedHeader.setForeground(Color.white);
        lblMostUsedHeader.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblStatsHeader.setForeground(Color.white);
        lblStatsHeader.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblNoOfDays.setFont(new Font("Montserrat", Font.PLAIN, 15));

        lblError.setForeground(Color.white);
        lblError.setFont(new Font("Montserrat", Font.PLAIN, 25));

        lblMessage.setForeground(Color.white);
        lblMessage.setFont(new Font("Montserrat", Font.PLAIN, 25));

        lblFrequentlyUsed.setForeground(Color.white);
        lblFrequentlyUsed.setFont(new Font("Montserrat", Font.PLAIN, 25));

        txtGroupID.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtGroupID.setBackground(Color.white);
        txtGroupID.setForeground(Color.black);
        txtGroupID.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        //txtGroupID.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        txtGroupName.setFont(new Font("Montserrat", Font.PLAIN, 15));
        //txtGroupName.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        txtGroupName.setBackground(Color.white);
        txtGroupName.setForeground(Color.black);
        txtGroupName.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        groupIds.setFont(new Font("Montserrat", Font.PLAIN, 15));
        groupIds.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));


        rcmId.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmId.setBackground(Color.white);
        rcmId.setForeground(Color.black);
        rcmId.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
       // rcmId.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        rcmCapacity.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmCapacity.setBackground(Color.white);
        rcmCapacity.setForeground(Color.black);
        rcmCapacity.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        //rcmCapacity.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capacity", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        money.setFont(new Font("Montserrat", Font.PLAIN, 15));
        money.setBackground(Color.white);
        money.setForeground(Color.black);
        money.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
        //money.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Money", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));


        rcmLocation.setFont(new Font("Montserrat", Font.PLAIN, 15));
        rcmLocation.setBackground(Color.white);
        rcmLocation.setForeground(Color.black);
        rcmLocation.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));
       //rcmLocation.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        comboBoxGroup.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxGroup.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        comboBoxGroup.setModel(new DefaultComboBoxModel());

        comboBoxRCM.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxRCM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RCM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        comboBoxMonth.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxMonth.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Month", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));


        comboBoxYear.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxYear.setBorder(BorderFactory.createTitledBorder(null, "Year", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));


        groupComboBox.setFont(new Font("Montserrat", Font.PLAIN, 15));
        groupComboBox.setBorder(BorderFactory.createTitledBorder(null, "Group", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        RCMComboBox.setFont(new Font("Montserrat", Font.PLAIN, 15));
        RCMComboBox.setBorder(BorderFactory.createTitledBorder(null, "RCM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        lblCategory.setForeground(Color.black);
        lblCategory.setFont(new Font("Montserrat", Font.PLAIN, 20));

        weightRadioButton.setForeground(Color.black);
        weightRadioButton.setFont(new Font("Montserrat", Font.PLAIN, 20));

        valueRadioButton.setForeground(Color.black);
        valueRadioButton.setFont(new Font("Montserrat", Font.PLAIN, 20));

        emptyTimesRadioButton.setForeground(Color.black);
        emptyTimesRadioButton.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblFrom.setForeground(Color.black);
        lblFrom.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblTo.setForeground(Color.black);
        lblTo.setFont(new Font("Montserrat", Font.PLAIN, 20));

        txtFromDate.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtFromDate.setBackground(Color.white);
        txtFromDate.setForeground(Color.black);
        txtFromDate.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        txtToDate.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtToDate.setBackground(Color.white);
        txtToDate.setForeground(Color.black);
        txtToDate.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));


        txtNoOfDays.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtNoOfDays.setBackground(Color.white);
        txtNoOfDays.setForeground(Color.black);
        txtNoOfDays.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        submitGroup.setFont(new Font("Montserrat", Font.PLAIN, 20));
        submitGroup.setForeground(Color.black);
        submitGroup.setBounds(420, 365, 350, 90);

        rcmSubmit.setFont(new Font("Montserrat", Font.PLAIN, 20));
        rcmSubmit.setForeground(Color.black);
        rcmSubmit.setBounds(420, 365, 350, 90);

        buttonSubmit.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonSubmit.setForeground(Color.black);
        buttonSubmit.setBounds(420, 365, 350, 90);

        buttonFrequentlyUsed.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonFrequentlyUsed.setForeground(Color.black);
        buttonFrequentlyUsed.setBounds(420, 365, 350, 90);


        buttonWeightValue.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonWeightValue.setForeground(Color.black);
        buttonWeightValue.setBounds(420, 365, 350, 90);

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
        comboBoxGroup.addActionListener(evt -> SelectRCM(evt));//enhance
        groupComboBox.addActionListener(evt->SelectRCMStats(evt));
        buttonSubmit.addActionListener(evt -> SubmitTotalItems(evt));
        buttonFrequentlyUsed.addActionListener(evt -> SubmitFrequentlyUsed(evt));
        buttonWeightValue.addActionListener(evt->SubmitWeightValue(evt));


        loadGroup();
        LoadMonth();
        LoadYear();



        frame.setVisible(true);
    }
    //Pattern (Mediator may be) enhance
    @SuppressWarnings("serial")
    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y,
                                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.gray);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
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

                lblError.setText("Group created");


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
            //Builder pattern applied
            //boolean status=DBConn.instance().InsertRCM(rcmId.getText(),groupIds.getSelectedItem().toString(), Double.parseDouble(rcmCapacity.getText()), Double.parseDouble(money.getText()), rcmLocation.getText());
            DBConn.instance().InsertRCM(new RCMCreate.RCMCreateBuilder().withGroupId(groupIds.getSelectedItem().toString()).withRCMId(rcmId.getText()).withCapacity(Double.parseDouble(rcmCapacity.getText())).withCapacityLeft(Double.parseDouble(rcmCapacity.getText())).withMoney(Double.parseDouble(money.getText())).withLocation(rcmLocation.getText()).withOpStatus("inactive").build());
            lblError.setText("RCM created");

        }

    }
    private void loadGroup()
    {
        try {
            ResultSet result = DBConn.instance().GetGroups();
            groupIds.removeAllItems();
            comboBoxGroup.removeAllItems();
            groupComboBox.removeAllItems();

            groupIds.addItem("--Select--");
            comboBoxGroup.addItem("--Select--");

            groupComboBox.addItem("--Select--");
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
            RCMComboBox.removeAllItems();
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
    private void SelectRCMStats(ActionEvent evt)
    {
        LoadRCM(groupComboBox.getSelectedItem().toString());
    }
    private void LoadMonth()
    {
        comboBoxMonth.removeAllItems();
        comboBoxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Month--", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));

    }
    private void LoadYear()
    {
        comboBoxYear.removeAllItems();

        comboBoxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"--Year--"}));
        for (int year = 2015; year <= Calendar.getInstance().get(Calendar.YEAR); year++) {
            comboBoxYear.addItem(year + "");
        }
    }
    private void SubmitTotalItems(ActionEvent evt)
    {
       /* if(comboBoxYear.getSelectedIndex()==0)
        {
            lblMessage.setText("Please select group.");
        }
        else if(comboBoxRCM.getSelectedIndex()==0)
        {
            lblMessage.setText("Please select RCM.");
        }
        else if(comboBoxMonth.getSelectedIndex()==0)
        {
            lblMessage.setText("Please select month.");
        }
        else if(comboBoxYear.getSelectedIndex()==0)
        {
            lblMessage.setText("Please select year.");
        }
        else {*/
            int count = DBConn.instance().GetTotalRecycledItemsByMonth(comboBoxRCM.getSelectedItem().toString(), comboBoxMonth.getSelectedItem().toString(), comboBoxYear.getSelectedItem().toString());
            lblMessage.setText("The total number of recycled items for the month " + comboBoxMonth.getSelectedItem().toString() + " is " + count);
        //}
    }
    private void SubmitFrequentlyUsed(ActionEvent evt)
    {
        try {
            ResultSet resultSet = DBConn.instance().GetMostFrequentlyUsedRCM(Integer.parseInt(txtNoOfDays.getText()));
            while (resultSet.next()) {
                lblFrequentlyUsed.setText("The most frequently used RCM is "+resultSet.getString(1)+" located at "+resultSet.getString(2));
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
                BarChart_AWT("Weight Stats", dataset);
            }
            else if(valueRadioButton.isSelected())
            {
                while (resultSet.next()) {
                    DefaultTableModel valueTableModel = (DefaultTableModel) (tableWeightValue.getModel());
                    valueTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(3)});
                    dataset.addValue(resultSet.getDouble(3), "RCM", resultSet.getString(1));
                }
                BarChart_AWT("Value Stats", dataset);

            }
            else if(emptyTimesRadioButton.isSelected())
            {

                while (resultSet.next()) {
                    DefaultTableModel emptyTableModel = (DefaultTableModel) (tableWeightValue.getModel());
                    emptyTableModel.addRow(new String[]{resultSet.getString(1), resultSet.getString(2)});
                    dataset.addValue(resultSet.getInt(2), "RCM", resultSet.getString(1));
                }
                BarChart_AWT("Empty times Stats", dataset);
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

        CategoryPlot plot = barChart.getCategoryPlot();

        plot.getRenderer().setSeriesPaint(0, new Color(29, 57, 65));

        ChartPanel chartPanel=new ChartPanel(barChart);
        //chartPanel.setPreferredSize(new java.awt.Dimension( visualPane.getWidth(),visualPane.getHeight()) );




        JPanel panel = new JPanel();
        panel.add(chartPanel);
        visualPanel=new JPanel();
        visualPanel.add(panel);

        JOptionPane.showMessageDialog(null,panel,"Information",JOptionPane.INFORMATION_MESSAGE);



    }


    public static void main(String[] args)
    {
        RmosMain r=new RmosMain();
    }

}


