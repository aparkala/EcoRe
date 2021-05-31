import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;


public class Menu{
    private JTabbedPane RMOS;
    private JPanel panel1;
    private JPanel RCM;
    private JPanel RmosLogin;
    private JPanel headerPanel;
    private JPanel panelCard;
    Login login1;

    private JLabel lblImage;
    private JComboBox comboBoxRCM;
    private JRadioButton lbsRadioButton;
    private JRadioButton kgsRadioButton;
    private JButton buttonSubmit;
    private JLabel lblMetric;
    private HashMap<String,String> groupRCM=new LinkedHashMap<>();
    private String metric;

    public Menu()
    {
        initComponents();
        LoadActiveRCM();
    }
    public void initComponents()

    {

        JFrame frame = new JFrame("ECORE");
        frame.setContentPane(panel1);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 700, 400);
        frame.setLocationRelativeTo(null);

        comboBoxRCM.setFont(new Font("Montserrat", Font.PLAIN, 15));
        comboBoxRCM.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RCM", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));

        buttonSubmit.setFont(new Font("Montserrat", Font.PLAIN, 20));
        buttonSubmit.setForeground(Color.black);
        buttonSubmit.setBounds(300, 205, 200, 30);

        ImageIcon icon = new ImageIcon("Images/ecore.png");
        Image img = icon.getImage();
        lblImage.setIcon(icon);

        ButtonGroup group=new ButtonGroup();
        group.add(lbsRadioButton);
        group.add(kgsRadioButton);
        lbsRadioButton.setSelected(true);

        buttonSubmit.addActionListener(evt -> SubmitRCM(evt));

        frame.setVisible(true);


    }

    public void LoadActiveRCM()
    {
        try {
            ResultSet result = DBConn.instance().GetActiveRCM();
            comboBoxRCM.removeAllItems();
            while (result.next()) {
                groupRCM.put(result.getString(1),result.getString(2));
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
        for(Entry<String,String> group:groupRCM.entrySet())
        {
            if(group.getValue().equals(comboBoxRCM.getSelectedItem().toString()))
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
    public static void main(String[] args) {
       Menu m = new Menu();

    }


}
