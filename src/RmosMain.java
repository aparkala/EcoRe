import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class RmosMain {
    private JTabbedPane CVMS;
    private JPanel rmosMain;
    private JPanel createPane;
    private JPanel ViewPane;
    private JPanel statsPane;
    private JComboBox groupIds;
    private JTextField rcmId;
    private JFormattedTextField rcmCapacity;
    private JFormattedTextField money;
    private JTextField rcmLocation;
    private JButton newGroupSubmit;
    private JButton rcmSubmit;
    private JPanel CreateRCMPane;
    private JPanel newRcmForm;
    private JPanel createGroupPane;
    private JTextField txtGroupID;
    private JTextField txtGroupName;
    private JLabel lblGroupID;
    private JLabel lblGroupName;
    private JButton submitGroup;
    private JLabel lblError;
    private JLabel lblRCMError;


    public RmosMain() {
        initComponents();
    }
    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(rmosMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        submitGroup.addActionListener(evt -> SubmitGroup(evt));
        rcmSubmit.addActionListener(evt -> SubmitRCM(evt));
        loadGroup();
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
        lblRCMError.setVisible(true);
        if(rcmId.getText().length()<1)
        {
            lblRCMError.setText("Please enter ID");
        }
        else if(rcmCapacity.getText().length()<1)
        {
            lblRCMError.setText("Please enter capacity");
        }
        else if(money.getText().length()<1)
        {
            lblRCMError.setText("Please enter money");
        }
        else if(rcmLocation.getText().length()<1)
        {
            lblRCMError.setText("Please enter location");
        }
        else
        {
            boolean status=DBConn.instance().InsertRCM(rcmId.getText(),groupIds.getSelectedItem().toString(), Double.parseDouble(rcmCapacity.getText()), Double.parseDouble(money.getText()), rcmLocation.getText());
            if(status)
            {
                lblRCMError.setText("Success");
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
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public static void main(String[] args)
    {
        RmosMain r=new RmosMain();
    }
}


