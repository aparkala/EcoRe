import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

public class viewRCM {
    private JFrame frame = new JFrame();
    private JPanel viewRcmPane;
    private JButton activateDeactivateButton;
    private JTable statusTable;
    private JTabbedPane addModifyTab;
    private JButton emptyButton;
    private JFormattedTextField newItemPrice;
    private JButton addButton;
    private JComboBox modifyItemCB;
    private JFormattedTextField modifiedItemPrice;
    private JButton modifyButton;
    private JPanel buttonPane;
    private JSplitPane modifySplitPane;
    private JPanel statusPane;
    private JPanel addTab;
    private JPanel modifyTab;
    private JPanel addButtonPane;
    private JPanel modifyButtonPane;
    private JComboBox addItemCB;
    private JLabel modifyLblError;
    private JLabel addLblError;
    private JPanel addLblErrorPanel;
    private JPanel modifyLblErrorPanel;
    RCM rcm;
    DBConn db;
    HashMap<String, String> allItems;
    HashMap<String, Double> rcmItems;



    public viewRCM(String rcmId) throws Exception {
        db = DBConn.instance();
        this.loadRCM(rcmId);
        initComponents();
    }

    private void loadRCM(String rcmId) throws Exception{
        rcm = new RCM(rcmId);
        System.out.println(rcm.toString());
    }

    public void initComponents() throws Exception {


        frame.setContentPane(viewRcmPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //loadStatusTable();
        loadItems();
        loadRcmItems(rcm.getRcmId());
        loadAddItems();
        loadModifyItems();
        loadButtons();
        frame.setVisible(true);
    }

    private void loadButtons(){
        if ((rcm.getStatus() == Status.valueOf("ACTIVE")) || (rcm.getStatus() == Status.valueOf("FULL"))){
            activateDeactivateButton.setText("DEACTIVATE");
        }
        else {
            activateDeactivateButton.setText("ACTIVATE");
        }

        if (rcm.getCapacityLeft() == 0){
            emptyButton.setVisible(true);
        }
        else {
            emptyButton.setVisible(false);
        }

        activateDeactivateButton.addActionListener(evt -> switchPower(evt));
        emptyButton.addActionListener(evt -> emptyRCM(evt));
    }


    private void loadStatusTable(){

        String[] columnNames = {"LOCATION", "OP_STATUS", "WEIGHT OF ITEMS", "MONEY LEFT", "LAST EMPTIED"};
        String[][] data = {{rcm.getLocation(),
                String.valueOf(rcm.getStatus()),
                String.valueOf(rcm.getCapacity() - rcm.getCapacityLeft()),
                String.valueOf(rcm.getMoneyLeft()),
                rcm.getLastEmptiedStr()}};
        statusTable = new JTable(data, columnNames);
        statusTable.setRowHeight(40);
        statusTable.getTableHeader().setReorderingAllowed(false);
        statusTable.getTableHeader().setPreferredSize(new Dimension(600, 75));
        statusTable.getTableHeader().setFont(new Font("Serif", Font.BOLD, 30));
        //((DefaultTableCellRenderer)statusTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        statusTable.setFont(new Font("Serif", Font.PLAIN, 15));
        statusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        statusTable.getColumnModel().getColumn(0);
        statusTable.getColumnModel().getColumn(1);
        // Frame Size
        statusPane = new JPanel();
        statusPane.add(statusTable, BorderLayout.CENTER);
        // Frame Visible = true
        frame.add(statusPane);

    }

    private void switchPower(ActionEvent e){
        if ((rcm.getStatus() == Status.valueOf("ACTIVE")) || (rcm.getStatus() == Status.valueOf("FULL"))){
            rcm.deactivate();
        }
        else {
            rcm.activate();
        }
        
    }

    private void emptyRCM(ActionEvent e){
        rcm.empty();
        this.loadStatusTable();
        emptyButton.setVisible(false);
    }

    private void loadAddItems() {
        Set<String> rcmItemIds = rcmItems.keySet();
        Set<String> allItemIds = allItems.keySet();

        allItemIds.removeAll(rcmItemIds);

        for (String id : allItemIds) {
            addItemCB.addItem(allItems.get(id));
        }

    }

    private void loadModifyItems() {
        for (String id : rcmItems.keySet()) {
            modifyItemCB.addItem(allItems.get(id));
        }
    }

    private void loadItems() throws SQLException {
        allItems = new HashMap<>();
        ResultSet items = db.getAllItems();
        while (items.next()){
            allItems.put(items.getString("itemid"), items.getString("itemname"));
        }
    }

    private void loadRcmItems(String rcmId) throws SQLException {
        rcmItems = new HashMap<>();
        ResultSet items = db.getRcmItems(rcmId);
        while(items.next()) {
            rcmItems.put(items.getString("itemid"), items.getDouble("itemprice"));
        }
    }

    public static void main(String args[]) throws Exception{
        viewRCM v = new viewRCM("SCU-001");
    }

}
