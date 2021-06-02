import java.awt.*;
import java.awt.event.ActionEvent;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class viewRCM {
    private JPanel viewRcmPane;
    private JButton activateDeactivateButton;
    private JTable statusTable;
    private JButton emptyButton;
    private JFormattedTextField newItemPrice;
    private JButton addButton;
    private JComboBox modifyItemCB;
    private JFormattedTextField modifiedItemPrice;
    private JButton modifyButton;
    private JComboBox addItemCB;
    private JLabel addLbl;
    private JLabel modifyLbl;
    private JComboBox removeItemCB;
    private JButton removeButton;
    private JLabel removeLbl;
    private JButton deleteButton;
    private JLabel lblStatus;
    private JTabbedPane tabbedPane1;
    private JLabel lblHeader;
    private JLabel lblItemName;
    private JLabel lblItemPrice;
    private JLabel lblSelectItem;
    private JLabel lblNewPrice;
    private JLabel lblRemoveSelectItem;
    RCM rcm;
    DBConn db;
    HashMap<String, String> allItems;
    HashMap<String, Double> rcmItems;



    public viewRCM(RCM rcm) throws Exception {
        this.rcm = rcm;
        db = DBConn.instance();

        initComponents();
    }

    /*private viewRCM(String rcmId) throws Exception {
        db = DBConn.instance();
        loadRCM(rcmId);
    }

    private void loadRCM(String rcmId) throws Exception{
        rcm = new RCM(rcmId);
        System.out.println(rcm.toString());
    }*/

    public void initComponents() throws Exception {
        JFrame frame = new JFrame();
        frame.setContentPane(viewRcmPane);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 1200, 725);
        frame.setLocationRelativeTo(null);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lblStatus.setForeground(Color.white);
        lblStatus.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblHeader.setForeground(Color.white);
        lblHeader.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblItemName.setForeground(Color.black);
        lblItemName.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblItemPrice.setForeground(Color.black);
        lblItemPrice.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblNewPrice.setForeground(Color.black);
        lblNewPrice.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblSelectItem.setForeground(Color.black);
        lblSelectItem.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblRemoveSelectItem.setForeground(Color.black);
        lblRemoveSelectItem.setFont(new Font("Montserrat", Font.PLAIN, 20));

        modifyItemCB.setFont(new Font("Montserrat", Font.PLAIN, 20));

        addItemCB.setFont(new Font("Montserrat", Font.PLAIN, 20));

        removeItemCB.setFont(new Font("Montserrat", Font.PLAIN, 20));

        modifiedItemPrice.setFont(new Font("Montserrat", Font.PLAIN, 15));
        modifiedItemPrice.setBackground(Color.white);
        modifiedItemPrice.setForeground(Color.black);
        modifiedItemPrice.setBorder(BorderFactory.createCompoundBorder(
                new CustomBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        newItemPrice.setFont(new Font("Montserrat", Font.PLAIN, 15));
        newItemPrice.setBackground(Color.white);
        newItemPrice.setForeground(Color.black);
        newItemPrice.setBorder(BorderFactory.createCompoundBorder(
                new CustomBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));



        addButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        addButton.setForeground(Color.black);
        addButton.setBounds(420, 365, 350, 90);

        removeButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        removeButton.setForeground(Color.black);
        removeButton.setBounds(420, 365, 350, 90);

        modifyButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        modifyButton.setForeground(Color.black);
        modifyButton.setBounds(420, 365, 350, 90);

        activateDeactivateButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        activateDeactivateButton.setForeground(Color.black);
        activateDeactivateButton.setBounds(420, 365, 350, 90);

        deleteButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        deleteButton.setForeground(Color.black);
        deleteButton.setBounds(420, 365, 350, 90);

        emptyButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        emptyButton.setForeground(Color.black);
        emptyButton.setBounds(420, 365, 350, 90);



       // frame.pack();
        loadStatusTable();
        loadItems();
        loadRcmItems();
        loadAddItems();
        loadModifyItems();
        loadRemoveItems();
        loadButtons();
        frame.setVisible(true);
    }

    private void loadButtons(){
        if ((rcm.getStatus() == Status.active) || (rcm.getStatus() == Status.full)){
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
        deleteButton.addActionListener(evt -> deleteRCM(evt));
        addButton.addActionListener(evt -> addItem(evt));
        modifyButton.addActionListener(evt -> modifyItem(evt));
        removeButton.addActionListener(evt -> removeItem(evt));
    }

    private void deleteRCM(ActionEvent actionEvent){

    }

    void removeItem(ActionEvent actionEvent){
        String itemToRemove, itemId;
        if (removeItemCB.getSelectedItem().toString() == " -- Select Item --"){
            removeLbl.setText("Please select an item to remove from the list of available items");
            removeLbl.setVisible(true);
            return;
        }
        else{
            itemToRemove = removeItemCB.getSelectedItem().toString();
        }

        for (Map.Entry entry : allItems.entrySet()) {
            if(entry.getValue().toString() == itemToRemove) {
                itemId = entry.getKey().toString();
                db.removeRcmItem(rcm.getRcmId(),itemId);
                removeLbl.setText("Item successfully removed");
                removeLbl.setVisible(true);
                rcmItems.remove(itemId);
                removeItemCB.setSelectedIndex(0);
            }
        }
        loadAddItems();
        loadModifyItems();
    }

    void addItem(ActionEvent event) {
        String itemToAdd, itemId;
        Double price;
        if (addItemCB.getSelectedItem().toString() == " -- Select Item --"){
            addLbl.setText("Please select an item to add from the list of available items");
            addLbl.setVisible(true);
            return;
        }
        else{
            itemToAdd = addItemCB.getSelectedItem().toString();
        }

        if (newItemPrice.getText() == ""){
            addLbl.setText("Please set a price for the selected item");
            addLbl.setVisible(true);
            return;
        }
        else {
            price = new Double(newItemPrice.getText());
        }

        for (Map.Entry entry : allItems.entrySet()) {
            if(entry.getValue().toString() == itemToAdd) {
                itemId = entry.getKey().toString();
                db.insertRcmItem(rcm.getRcmId(),itemId, price);
                addLbl.setText("Item successfully added");
                addLbl.setVisible(true);
                rcmItems.put(itemId, price);
                addItemCB.setSelectedIndex(0);
                newItemPrice.setText("");
            }
        }
        loadAddItems();
        loadModifyItems();
        loadRemoveItems();
    }

    void modifyItem(ActionEvent event) {

        String itemToModify, itemId;
        double newPrice;
        if (modifyItemCB.getSelectedItem().toString() == " -- Select Item --"){
            modifyLbl.setText("Please select an item to modify from the list of items serviced by the RCM");
            modifyLbl.setVisible(true);
            return;
        }
        else{
            itemToModify = modifyItemCB.getSelectedItem().toString();
        }

        if (modifiedItemPrice.getText() == ""){
            modifyLbl.setText("Please set a new price for the selected item");
            modifyLbl.setVisible(true);
            return;
        }
        else {
            newPrice = Double.parseDouble(modifiedItemPrice.getText());
            for (Map.Entry entry : allItems.entrySet()) {
                if(entry.getValue().toString() == itemToModify) {
                    itemId = entry.getKey().toString();
                    db.changeItemPrice(rcm.getRcmId(),itemId, newPrice);
                    modifyLbl.setText("Price successfully changed");
                    modifyLbl.setVisible(true);
                    rcmItems.replace(itemId, newPrice);
                    modifyItemCB.setSelectedIndex(0);
                    modifiedItemPrice.setText("");
                }
            }
        }

    }


    private void loadStatusTable(){

        String[] rowNames = {"LOCATION", "OP_STATUS", "WEIGHT OF ITEMS", "MONEY LEFT", "LAST EMPTIED"};
        String[] columnNames = {"Attribute", "Value"};
        String[] data = {rcm.getLocation(),
                String.valueOf(rcm.getStatus()),
                String.valueOf(rcm.getCapacity() - rcm.getCapacityLeft()),
                String.valueOf(rcm.getMoneyLeft()),
                rcm.getLastEmptiedStr()};
        statusTable.setModel(new DefaultTableModel(columnNames, 0) {public boolean isCellEditable(int row, int column) { return false; }});
        statusTable.setRowHeight(40);
        statusTable.getTableHeader().setReorderingAllowed(false);
        statusTable.getTableHeader();
        statusTable.getTableHeader().setFont(new Font("Montserrat", Font.BOLD, 20));
        //((DefaultTableCellRenderer)statusTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        statusTable.setFont(new Font("Montserrat", Font.PLAIN, 15));
        //statusTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        statusTable.getColumnModel().getColumn(0);
        statusTable.getColumnModel().getColumn(1);
        DefaultTableModel rcmTableModel = (DefaultTableModel)(statusTable.getModel());

        for (int i = 0; i < rowNames.length; i++) {
            rcmTableModel.addRow(new String[]{rowNames[i], data[i]});
        }
    }

    private void switchPower(ActionEvent e){
        if ((rcm.getStatus() == Status.active || (rcm.getStatus() == Status.full))){
            rcm.deactivate();
        }
        else {
            rcm.activate();
        }
        loadButtons();
    }

    private void emptyRCM(ActionEvent e){
        rcm.empty();
        this.loadStatusTable();
        emptyButton.setVisible(false);
    }

    private void loadAddItems() {
        addItemCB.removeAllItems();
        ArrayList<String> rcmItemIds = new ArrayList<>(rcmItems.keySet());
        ArrayList<String> allItemIds = new ArrayList<>(allItems.keySet());

        System.out.println(allItemIds);
        System.out.println(rcmItemIds);

        if (allItemIds.size() == rcmItemIds.size()){
            addItemCB.addItem(" -- RCM already services all available items -- ");
            addButton.setEnabled(false);
            return;
        }

        addButton.setEnabled(true);

        allItemIds.removeAll(rcmItemIds);
        //System.out.println(allItemIds);
        //System.out.println(rcmItemIds);

        addItemCB.addItem(" -- Select Item -- ");
        for (String id : allItemIds) {
            if (!rcmItemIds.contains(id)) {
                addItemCB.addItem(allItems.get(id));
            }
        }
        System.out.println(allItems);

    }

    private void loadModifyItems() {
        modifyItemCB.removeAllItems();
        //System.out.println(rcmItems);
        //System.out.println(allItems);
        modifyItemCB.addItem(" -- Select Item --");
        for (String id : rcmItems.keySet()) {
            modifyItemCB.addItem(allItems.get(id));
        }

        //modifyItemCB.addItemListener(evt -> addCbChanged(evt));
    }

    private void loadRemoveItems() {
        removeItemCB.removeAllItems();
        //System.out.println(rcmItems);
        //System.out.println(allItems);
        removeItemCB.addItem(" -- Select Item --");
        for (String id : rcmItems.keySet()) {
            removeItemCB.addItem(allItems.get(id));
        }

        //modifyItemCB.addItemListener(evt -> addCbChanged(evt));
    }


    private void loadItems() throws SQLException {
        allItems = new HashMap<>();
        ResultSet items = db.getAllItems();
        while (items.next()){
            allItems.put(items.getString("itemid"), items.getString("itemname"));
        }
        System.out.println(allItems);
    }

    private void loadRcmItems() throws SQLException {
        rcmItems = new HashMap<>();
        ResultSet items = db.GetRCMItems(rcm.getRcmId());
        while(items.next()) {
            rcmItems.put(items.getString("itemid"), items.getDouble("itemprice"));
        }
    }

    public static void main(String args[]) throws Exception{
        viewRCM v = new viewRCM(new RCM("RCM-1"));
    }

}
