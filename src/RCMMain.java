import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class RCMMain extends FocusAdapter {
    private JPanel panelRCM;
    private JTable tableRCM;
    private JTextField txtItem;
    private JTextField txtWeight;
    private JButton buttonInsert;
    private JButton submitButton;
    private JLabel lblError;
    private JScrollPane rcmScrollPane;
    private JScrollPane itemsScrollPane;
    private JTable tableInserted;
    private JPanel lblAcceptedItemsPane;
    private JLabel lblInsertedItems;
    private JLabel lblImage;
    private JLabel lblAcceptedItems;
    private JPanel errorPanel;
    private String rcmId;
    private String groupID;
    private String metric;
    private double pricePerMetric;
    private double price;
    private double weight;
    private double metricItemPrice=0;
    private double finalPrice=0;
    private double capacityLeft=0;
    private double moneyLeft=0;
    private int transactionId=0;
    //enhance code - design pattern
    //private String[] RCMITEMSLBS = {"Items", "Price/lb"};
    //private String[] RCMITEMSKGS = {"Items","Price/kg"};
    List<RCMTransaction> rcmTransactions=new ArrayList<>();
    List<Item> itemsList=new ArrayList<>();
    //private String[] RCMINSERTEDITEMSLBS = {"Items","Weight","Price/lb"};
    //private String[] RCMINSERTEDITEMSKGS = {"Items","Weight","Price/kg"};
    private String[] RCMINSERTEDITEMSLBS = {"Items","Weight","Price/lb"};
    private String[] RCMINSERTEDITEMSKGS = {"Items","Weight","Price/kg"};
    private JFrame frame;
    DecimalFormat df=new DecimalFormat("0.00");
    RMOS rmos;
    RCM rcm;
    private MetricStrategy metricStrategy;


    public RCMMain(String groupID,String rcmId,MetricStrategy metricStrategy)
    {
        this.groupID=groupID;
        this.rcmId=rcmId;
        //this.metric=metric;
        this.metricStrategy=metricStrategy;
        initComponents();

        rmos = RMOS.get_instance();
        rcm = rmos.getRCM(groupID, rcmId);
        moneyLeft=rcm.getMoneyLeft();
        capacityLeft=rcm.getCapacityLeft();
        //itemsList = (List<Item>) rcm.getAvailableItems().values();

        LoadRCMItems();
        LoadTransactionId();
    }
    public void initComponents()
    {
        frame = new JFrame();
        frame.setContentPane(panelRCM);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1200, 725);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("Images/ecore.png");
        lblImage.setIcon(icon);

        txtItem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                super.focusLost(evt);
                Predicate<Item> validItem = e->e.getItemName().equalsIgnoreCase(txtItem.getText());
                if(!txtItem.getText().isEmpty())
                {
                if(!itemsList.stream().anyMatch(validItem))
                {
                    lblError.setText("Item not accepted.");
                    txtItem.setText("");
                }}

            }
        });

        /*txtWeight.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                super.focusLost(evt);

                if(!txtWeight.getText().isEmpty())
                {
                if(Double.parseDouble(txtWeight.getText())>capacityLeft)
                {
                    lblError.setText("Sorry!Machine is full. Cannot accept the item.");

                }}

            }
        });*/

        //enhance
        /*if(metric=="lbs")
        {
            tableRCM.setModel(new DefaultTableModel(RCMITEMSLBS, 0) {public boolean isCellEditable(int row, int column) { return false; }
            });
            tableInserted.setModel(new DefaultTableModel(RCMINSERTEDITEMSLBS, 0) {
                public boolean isCellEditable(int row, int column) { return false; }
            });
        }
        else if(metric=="kgs")
        {
            tableRCM.setModel(new DefaultTableModel(RCMITEMSKGS, 0) {public boolean isCellEditable(int row, int column) { return false; }
            });
            tableInserted.setModel(new DefaultTableModel(RCMINSERTEDITEMSKGS, 0) {
                public boolean isCellEditable(int row, int column) { return false; }
            });
        }*/

        //strategy pattern
        tableRCM.setModel(new DefaultTableModel(metricStrategy.MetricTableHeader(), 0) {public boolean isCellEditable(int row, int column) { return false; }
        });
        tableInserted.setModel(new DefaultTableModel(metricStrategy.MetricItemsTableHeader(), 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        });
        tableRCM.setRowHeight(40);
        tableRCM.getTableHeader().setReorderingAllowed(false);
        tableRCM.getTableHeader();
        tableRCM.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 20));
        //((DefaultTableCellRenderer)tableRCM.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableRCM.setFont(new Font("Montserrat", Font.PLAIN, 15));
        //tableRCM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableRCM.getColumnModel().getColumn(0);
        tableRCM.getColumnModel().getColumn(1);


        tableInserted.setRowHeight(40);
        tableInserted.getTableHeader().setReorderingAllowed(false);
        tableInserted.getTableHeader();
        tableInserted.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 20));
        //((DefaultTableCellRenderer)tableRCM.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tableInserted.setFont(new Font("Montserrat", Font.PLAIN, 15));
        //tableRCM.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableInserted.getColumnModel().getColumn(0);
        tableInserted.getColumnModel().getColumn(1);

        txtItem.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtItem.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        //txtItem.setMaximumSize(new java.awt.Dimension(300, 54));
        //txtItem.setMinimumSize(new java.awt.Dimension(300, 54));
        //txtItem.setPreferredSize(new java.awt.Dimension(300, 54));

        txtWeight.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtWeight.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Weight", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        //txtWeight.setMaximumSize(new java.awt.Dimension(300, 54));
        //txtWeight.setMinimumSize(new java.awt.Dimension(300, 54));
        //txtWeight.setPreferredSize(new java.awt.Dimension(300, 54));

        buttonInsert.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonInsert.setForeground(Color.black);
        buttonInsert.setBounds(300, 205, 200, 30);

        submitButton.setFont(new Font("Montserrat", Font.PLAIN, 30));
        submitButton.setForeground(Color.black);
        submitButton.setBounds(420, 365, 350, 90);

        lblAcceptedItems.setForeground(Color.white);
        lblAcceptedItems.setFont(new Font("Montserrat", Font.PLAIN, 30));

        lblInsertedItems.setForeground(Color.white);
        lblInsertedItems.setFont(new Font("Montserrat", Font.PLAIN, 30));

        lblError.setForeground(Color.white);
        lblError.setFont(new Font("Montserrat", Font.PLAIN, 20));

        buttonInsert.addActionListener(evt -> {
            try {
                InsertRCMItems(evt);
            } catch (ParseException e) {


            }
        });
        submitButton.addActionListener(evt -> SubmitItems(evt));


        frame.setVisible(true);
    }


    private void InsertRCMItems(ActionEvent e) throws ParseException {
        if(txtItem.getText().isEmpty())
        {
            lblError.setText("Please enter item type");
        }
        else if(txtWeight.getText().isEmpty())
        {
            lblError.setText("Please enter weight");
        }
        else if(Double.parseDouble(txtWeight.getText())>rcm.getCapacityLeft())
        {
            lblError.setText("Sorry!Weight input exceeds the capacity of the machine.");


        }
        else {
            Date now = new Date();
            java.sql.Date insertedDate = new java.sql.Date(now.getTime());

            int itemId = 0;
            for (Item item : rcm.getAvailableItems().values()) {
                if (txtItem.getText().equalsIgnoreCase(item.getItemName())) {
                    pricePerMetric = item.getPricePerMetric();
                    itemId = item.getItemID();
                }
            }
            weight = Double.parseDouble(txtWeight.getText());
            price = pricePerMetric * weight;
            finalPrice += price; //use for cash
            rcm.setCapacityLeft(rcm.getCapacityLeft() - weight);

            DefaultTableModel insertedTableModel = (DefaultTableModel) (tableInserted.getModel());
            insertedTableModel.addRow(new String[]{txtItem.getText(), txtWeight.getText(), String.valueOf(price)});

            price= metricStrategy.PutMetricPrice(price); //strategy pattern
            rcmTransactions.add(new RCMTransaction.RCMTransactionBuilder().withTransactionId(transactionId).withRCMId(rcmId).withItemId(itemId).withItemPrice(price).withInsertedDate(insertedDate).withWeight(weight).withCash(1).withIsEmpty(0).withGroupId(groupID).build());
            submitButton.setEnabled(true);
            txtItem.setText("");
            txtWeight.setText("");
        }
    }
    private void LoadRCMItems()
    {
        try {

            for(Item item:rcm.getAvailableItems().values())
            {

                itemsList.add(item);
                metricItemPrice=metricStrategy.GetMetricPrice(item.getPricePerMetric());

                DefaultTableModel rcmTableModel = (DefaultTableModel)(tableRCM.getModel());

                rcmTableModel.addRow(new String[]{item.getItemName(),String.valueOf(metricItemPrice)});

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    private void LoadTransactionId()
    {
        transactionId=DBConn.instance().GetMaxTransactionId();
        transactionId+=1;
    }
    private void SubmitItems(ActionEvent evt)
    {
        int cash=0;
        if(finalPrice<moneyLeft)
        {
            String message = "Would you like to collect money via Cash or Coupon?";
            Object[] options = {"Coupon", "Cash"};
            int n = JOptionPane.showOptionDialog(null, message, null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);
            if (n == JOptionPane.NO_OPTION)
            {
                cash=1;
                moneyLeft-=finalPrice;
                JOptionPane.showMessageDialog(null, "Please collect your cash worth: "+finalPrice+"$");
            }
            else if(n==JOptionPane.YES_OPTION)
            {
                JOptionPane.showMessageDialog(null, "Please collect your coupon worth: "+finalPrice+"$");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please collect your coupon worth: "+finalPrice+"$");
        }
        final int cashCoupon=cash;
        rcmTransactions.stream().forEach(e->e.setCash(cashCoupon));
        DBConn.instance().InsertRCMItems(rcmTransactions);
        DBConn.instance().UpdateCapacityMoney(rcm.getCapacityLeft(),moneyLeft,rcmId);
        frame.dispose();


    }

    }






