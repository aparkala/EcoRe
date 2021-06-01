import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class ViewForm extends JPanel{
    private JPanel viewFormPanel;
    private JButton viewButton;
    private JComboBox comboBoxGroup;
    private JComboBox comboBoxRCM;
    private JLabel lblGroup;

    public ViewForm()
    {
        initComponents();
    }

    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(viewFormPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        loadGroup();
        comboBoxGroup.addActionListener(evt -> loadRCM(evt));
        viewButton.addActionListener(evt->viewRCM(evt));
        frame.setVisible(true);

    }
    private void loadGroup()
    {
        try {
            ResultSet result = DBConn.instance().GetGroups();

            while (result.next())
            {
                comboBoxGroup.addItem(result.getString("groupId"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void loadRCM(ActionEvent e)
    {
        try {
            ResultSet result = DBConn.instance().GetRCM(comboBoxGroup.getSelectedItem().toString());

            while (result.next())
            {
                comboBoxRCM.addItem(result.getString("rcmId"));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
    private void viewRCM(ActionEvent e)
    {

    }
    public static void main(String[] args)
    {
        ViewForm v=new ViewForm();
    }
}

