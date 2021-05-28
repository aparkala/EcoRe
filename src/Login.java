import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class Login extends JPanel{
    private JPanel panelLogin;
    private JTextField txtUsername;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel lblUsername;
    private JLabel lblError;
    Menu m;



    public Login() {
        initComponents();

    }
    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        loginButton.setFont(new Font("Montserrat", Font.PLAIN, 30));
        loginButton.setForeground(Color.black);
        loginButton.setBounds(420, 365, 350, 90);



        txtUsername.setFont(new Font("Montserrat", Font.PLAIN, 15));

        txtUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        txtUsername.setMaximumSize(new java.awt.Dimension(300, 54));
        txtUsername.setMinimumSize(new java.awt.Dimension(300, 54));
        txtUsername.setPreferredSize(new java.awt.Dimension(300, 54));

        passwordField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        passwordField.setMaximumSize(new java.awt.Dimension(300, 54));
        passwordField.setMinimumSize(new java.awt.Dimension(300, 54));
        passwordField.setPreferredSize(new java.awt.Dimension(300, 54));

        loginButton.addActionListener(evt -> validateLogin(evt));
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


        }
        else
        {
            lblError.setText("Please enter correct Username/Password.");
        }

    }
    public static void main(String[] args)
    {
        Login l=new Login();
    }

}
