import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 * Implemented Singleton Pattern
 **/

public class RMOS implements Visitor {
    private static final RMOS singleton = new RMOS();
    private HashMap<String,RCMGroup> groupMap = new HashMap<>();
    DBConn db = DBConn.instance();
    RcmCounter counter = new RcmCounter();
    int numberOfRCMs;
    @Override
    public void visit(RCM rcm) {
        numberOfRCMs++;
    }

    private RMOS(){}

    public static RMOS get_instance(){
        return singleton;
    }

    void loadGroups() throws SQLException {
        ResultSet result = db.getGroupIds();
        while(result.next()) {
            this.groupMap.put(result.getString("groupId"),new RCMGroup(result.getString("groupId"), result.getString("groupName")));
        }
        for (RCMGroup rcmGroup : groupMap.values()) {
            rcmGroup.print();
            rcmGroup.makeAccept(counter);
        }
    }

    void makeAccept(RmosMain.loadViewPanel loadViewPanel){
        for (RCMGroup rcmGroup : groupMap.values()) {
            rcmGroup.print();
            rcmGroup.makeAccept(loadViewPanel);
        }
    }

    public static void main(String args[]) throws SQLException {
        RMOS rmos = new RMOS().get_instance();
        rmos.loadGroups();
        System.out.println(rmos.counter.counter);
    }



}

