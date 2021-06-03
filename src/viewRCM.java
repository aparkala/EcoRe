import java.awt.*;
import java.awt.event.ActionEvent;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class viewRCM extends FocusAdapter{
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
    RMOS rmos;



    public viewRCM(RCM rcm) throws Exception {
        this.rcm = rcm;
        rmos = RMOS.get_instance();

        initComponents();
    }

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

        activateDeactivateButton.addActionListener(evt -> switchPower(evt));
        emptyButton.addActionListener(evt -> emptyRCM(evt));
        deleteButton.addActionListener(evt -> deleteRCM(evt));
        addButton.addActionListener(evt -> addItem(evt));
        modifyButton.addActionListener(evt -> modifyItem(evt));
        removeButton.addActionListener(evt -> removeItem(evt));

        addItemCB.addFocusListener(this);


       // frame.pack();
        loadStatusTable();
        loadAddItems();
        loadModifyItems();
        loadRemoveItems();
        loadButtons();
        frame.setVisible(true);
    }

    public void focusGained(FocusEvent evt) {
        loadAddItems();
    }

    private void loadButtons(){
        if ((rcm.getStatus() == Status.valueOf("ACTIVE")) || (rcm.getStatus() == Status.valueOf("FULL")) || (rcm.getStatus() == Status.valueOf("active")) || (rcm.getStatus() == Status.valueOf("full"))){
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
        loadStatusTable();

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

        removeLbl.setText(rmos.removeItemFromRCM(rcm, itemToRemove));
        removeLbl.setVisible(true);
        removeItemCB.setSelectedIndex(0);

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

        if ((newItemPrice.getText() == "") || (newItemPrice.getText().matches("[A-Za-z]"))){
            addLbl.setText("Please set a valid price for the selected item");
            addLbl.setVisible(true);
            return;
        }
        else {
            price = new Double(newItemPrice.getText());
        }

        addLbl.setText(rmos.addItemToRCM(rcm, itemToAdd, price));
        addLbl.setVisible(true);
        addItemCB.setSelectedIndex(0);
        newItemPrice.setText("");

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

        if ((modifiedItemPrice.getText() == "") || (modifiedItemPrice.getText().matches("[A-Za-z]"))){
            modifyLbl.setText("Please set a valid new price for the selected item");
            modifyLbl.setVisible(true);
            return;
        }
        else {
            newPrice = Double.parseDouble(modifiedItemPrice.getText());
            modifyLbl.setText(rmos.modifyItemOfRCM(rcm, itemToModify, newPrice));
            modifyLbl.setVisible(true);
            modifyItemCB.setSelectedIndex(0);
            modifiedItemPrice.setText("");
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
        if ((rcm.getStatus() == Status.valueOf("ACTIVE")) || (rcm.getStatus() == Status.valueOf("FULL")) || (rcm.getStatus() == Status.valueOf("active")) || (rcm.getStatus() == Status.valueOf("full"))){
            rmos.deactivate(rcm);
        }
        else {
           rmos.activate(rcm);
        }
        rcm.toString();
        loadButtons();
    }

    private void emptyRCM(ActionEvent e){
        rmos.empty(rcm);
        this.loadStatusTable();
        emptyButton.setVisible(false);
    }

    private void loadAddItems() {
        addItemCB.removeAllItems();
        ArrayList<String> rcmItems = new ArrayList<>(rcm.getAvailableItems().keySet());
        ArrayList<String> allItems = new ArrayList<>(rmos.getItemMapToName().keySet());
        System.out.println(rcmItems);
        System.out.println(allItems);

        //System.out.println(allItemIds);
        //System.out.println(rcmItemIds);

        if (allItems.size() == rcmItems.size()){
            addItemCB.addItem(" -- RCM already services all available items -- ");
            addButton.setEnabled(false);
            return;
        }

        addButton.setEnabled(true);

        allItems.removeAll(rcmItems);
        //System.out.println(allItemIds);
        //System.out.println(rcmItemIds);

        addItemCB.addItem(" -- Select Item -- ");
        for (String name : allItems) {
            if (!rcmItems.contains(name)) {
                addItemCB.addItem(name);
            }
        }
        //System.out.println(allItems);

    }

    private void loadModifyItems() {
        modifyItemCB.removeAllItems();
        //System.out.println(rcmItems);
        //System.out.println(allItems);
        modifyItemCB.addItem(" -- Select Item --");
        for (String name : rcm.getAvailableItems().keySet()) {
            modifyItemCB.addItem(name);
        }

        //modifyItemCB.addItemListener(evt -> addCbChanged(evt));
    }

    private void loadRemoveItems() {
        removeItemCB.removeAllItems();
        //System.out.println(rcmItems);
        //System.out.println(allItems);
        removeItemCB.addItem(" -- Select Item --");
        for (String name : rcm.getAvailableItems().keySet()) {
            removeItemCB.addItem(name);
        }

        //modifyItemCB.addItemListener(evt -> addCbChanged(evt));
    }

    public static void main(String args[]) throws Exception{
        //viewRCM v = new viewRCM("SCU-001");
    }

}
