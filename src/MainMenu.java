import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MainMenu extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JLabel lblImage;
    private JComboBox comboBoxRCM;
    private JRadioButton lbsRadioButton;
    private JRadioButton kgsRadioButton;
    private JButton buttonSubmit;
    private JTextField txtUsername;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel lblError;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JLabel lblMetric;
    private Map<String,ArrayList<String>> groupRCM=new HashMap<>();


    private String metric;

    public MainMenu()
    {
        initComponents();
        LoadActiveRCM();
    }
    public void initComponents() {

        JFrame frame = new JFrame("ECORE");
        frame.setContentPane(panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 500);
        frame.setLocationRelativeTo(null);

        comboBoxRCM.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxRCM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select RCM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        buttonSubmit.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonSubmit.setForeground(Color.black);
        buttonSubmit.setBounds(300, 205, 200, 30);

        txtUsername.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtUsername.setBackground(Color.white);
        txtUsername.setForeground(Color.black);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                new CustomBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 15));
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.black);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new CustomBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        lblUsername.setForeground(Color.black);
        lblUsername.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblPassword.setForeground(Color.black);
        lblPassword.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblMetric.setForeground(Color.black);
        lblMetric.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lbsRadioButton.setForeground(Color.black);
        lbsRadioButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        kgsRadioButton.getUI();
        kgsRadioButton.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblError.setForeground(Color.white);
        lblError.setFont(new Font("Montserrat", Font.PLAIN, 20));

        loginButton.setFont(new Font("Montserrat", Font.PLAIN, 20));
        loginButton.setForeground(Color.black);
        loginButton.setBounds(300, 205, 200, 30);


        loginButton.addActionListener(evt -> validateLogin(evt));

        ImageIcon icon = new ImageIcon("Images/ecore.png");
        lblImage.setIcon(icon);

        ButtonGroup group=new ButtonGroup();
        group.add(lbsRadioButton);
        group.add(kgsRadioButton);
        lbsRadioButton.setSelected(true);

        buttonSubmit.addActionListener(evt -> SubmitRCM(evt));

        frame.setVisible(true);
    }
    private void validateLogin(ActionEvent e)
    {
        lblError.setVisible(true);
        if(txtUsername.getText().length()==0)
        {

            lblError.setText("Please enter username.");
        }
        else if(passwordField.getPassword().length==0)
        {

            lblError.setText("Please enter Password.");
        }
        else if (txtUsername.getText().equalsIgnoreCase("admin") && String.copyValueOf(passwordField.getPassword()).equals("admin123"))
        {
            lblError.setText("Login Successful");
            RmosMain rmosMain=new RmosMain();



        }
        else
        {
            lblError.setText("Please enter correct Username/Password.");
        }

    }

    public void LoadActiveRCM()
    {
        try {
            ResultSet result = DBConn.instance().GetActiveRCM();
            comboBoxRCM.removeAllItems();
            while (result.next()) {
               // groupRCM.put(result.getString(1),result.getString(2)); //bug-not working']
                addValues(result.getString(1),result.getString(2));
                comboBoxRCM.addItem(result.getString(2));


            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void SubmitRCM(ActionEvent evt)
    {
        String groupID=null;
        for(Map.Entry<String,ArrayList<String>> group:groupRCM.entrySet())
        {
            if(group.getValue().contains(comboBoxRCM.getSelectedItem().toString()))
            {
                groupID=group.getKey();
            }
        }
        if(lbsRadioButton.isSelected())
        {
            metric="lbs";
        }
        else if(kgsRadioButton.isSelected())
        {
            metric="kgs";
        }
        RCMMain rcmMain=new RCMMain(groupID,comboBoxRCM.getSelectedItem().toString(),metric); //send this via class object
    }
    private void addValues(String key, String value) {
        ArrayList tempList = null;
        if (groupRCM.containsKey(key)) {
            tempList = groupRCM.get(key);
            if(tempList == null)
                tempList = new ArrayList();
            tempList.add(value);
        } else {
            tempList = new ArrayList();
            tempList.add(value);
        }
        groupRCM.put(key,tempList);
    }

    public static void main(String[] args)
    {
        MainMenu m=new MainMenu();
    }
}
