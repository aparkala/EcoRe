import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.ResultSet;
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
    private JPanel lblAcceptedItems;
    private JLabel lblInsertedItems;
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
    private String[] RCMITEMSLBS = {"Items", "Price/lb"};
    private String[] RCMITEMSKGS = {"Items","Price/kg"};
    List<RCMTransaction> rcmTransactions=new ArrayList<>();
    List<Item> itemsList=new ArrayList<>();
    private String[] RCMINSERTEDITEMSLBS = {"Items","Weight","Price/lb"};
    private String[] RCMINSERTEDITEMSKGS = {"Items","Weight","Price/kg"};


    public RCMMain(String groupID,String rcmId,String metric)
    {
        this.groupID=groupID;
        this.rcmId=rcmId;
        this.metric=metric;
        initComponents();
        LoadRCMItems(rcmId);
        LoadCapacityMoney(rcmId);
        LoadTransactionId();
    }
    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(panelRCM);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();


        txtItem.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent evt) {
                super.focusLost(evt);
                Predicate<Item> validItem = e->e.getItemName().equalsIgnoreCase(txtItem.getText());
                if(!txtItem.getText().isEmpty())
                {
                if(!itemsList.stream().anyMatch(validItem))
                {
                    lblError.setText("Not accepted");
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
        if(metric=="lbs")
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
        }

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

        buttonInsert.addActionListener(evt -> InsertRCMItems(evt));
        submitButton.addActionListener(evt -> SubmitItems(evt));

        frame.setVisible(true);
    }


    private void InsertRCMItems(ActionEvent e)
    {
        if(txtItem.getText().isEmpty())
        {
            lblError.setText("Please enter item type");
        }
        else if(txtWeight.getText().isEmpty())
        {
            lblError.setText("Please enter weight");
        }
        else if(Double.parseDouble(txtWeight.getText())>capacityLeft)
        {
            lblError.setText("Sorry!Machine is full. Cannot accept the item.");


        }
        else {
            Date now = new Date();
            java.sql.Date insertedDate = new java.sql.Date(now.getTime());

            int itemId = 0;
            for (Item item : itemsList) {
                if (txtItem.getText().equalsIgnoreCase(item.getItemName())) {
                    pricePerMetric = item.getPricePerMetric();
                    itemId = item.getItemID();
                }
            }
            weight = Double.parseDouble(txtWeight.getText());
            price = pricePerMetric * weight;
            finalPrice += price; //use for cash
            capacityLeft -= weight;

            DefaultTableModel insertedTableModel = (DefaultTableModel) (tableInserted.getModel());
            insertedTableModel.addRow(new String[]{txtItem.getText(), txtWeight.getText(), String.valueOf(price)});
            //enhance
            if(metric=="kgs")
            {
                price=price/0.45359237;
            }
            rcmTransactions.add(new RCMTransaction.RCMTransactionBuilder().withTransactionId(transactionId).withRCMId(rcmId).withItemId(itemId).withItemPrice(price).withInsertedDate(insertedDate).withWeight(weight).withCash(1).withIsEmpty(0).withGroupId(groupID).build());
            submitButton.setEnabled(true);
            txtItem.setText("");
            txtWeight.setText("");
        }
    }
    private void LoadRCMItems(String rcmId)
    {
        try {
            ResultSet result = DBConn.instance().GetRCMItems(rcmId);
            while (result.next())
            {
                if(metric=="lbs")
                {
                    metricItemPrice=result.getDouble("itemPrice");
                }
                else if(metric=="kgs")
                {
                    metricItemPrice=result.getDouble("itemPrice")*0.45359237;
                }
                Item item=new Item(result.getInt(1),result.getString(2),metricItemPrice);
                itemsList.add(item);
                DefaultTableModel rcmTableModel = (DefaultTableModel)(tableRCM.getModel());
                // enhance the code here-design pattern

                rcmTableModel.addRow(new String[]{result.getString("itemName"),String.valueOf(metricItemPrice)});

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    private void LoadCapacityMoney(String rcmId)
    {
        try {
            ResultSet result = DBConn.instance().GetCapacityLeft(rcmId);
            while (result.next()) {
                capacityLeft=result.getDouble(1);
                moneyLeft=result.getDouble(2);
            }
        }
        catch(Exception e)
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
        DBConn.instance().UpdateCapacityMoney(capacityLeft,moneyLeft,rcmId);
    }



}


