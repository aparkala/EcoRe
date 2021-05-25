import java.awt.event.ActionEvent;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class viewRCM {
    private JPanel viewRcmPane;
    private JButton activateDeactivateButton;
    private JTable statusTable;
    private JTabbedPane addModifyTab;
    private JButton emptyButton;
    private JTextField newItemName;
    private JFormattedTextField newItemPrice;
    private JButton addButton;
    private JTextField modifiedItemName;
    private JComboBox itemComboBox;
    private JFormattedTextField modifiedItemPrice;
    private JButton modifyButton;
    private JPanel buttonPane;
    private JSplitPane modifySplitPane;
    private JPanel statusPane;
    private JPanel addTab;
    private JPanel modifyTab;
    private JPanel addButtonPane;
    private JPanel modifyButtonPane;
    RCM rcm;


    public viewRCM(String rcmId) throws Exception {
        initComponents();
        this.loadRCM(rcmId);
    }

    private void loadRCM(String rcmId) throws Exception{
        rcm = new RCM(rcmId);
    }

    public void initComponents() throws Exception {
        JFrame frame = new JFrame();

        frame.setContentPane(viewRcmPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        loadStatusTable();
        loadButtons();
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
        Object[][]data = {{rcm.getLocation(), rcm.getStatus(), rcm.getCapacity() - rcm.getCapacityLeft(), rcm.getMoneyLeft(), rcm.getLastEmptiedStr()}};
        statusTable = new JTable(data, columnNames);
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


}
