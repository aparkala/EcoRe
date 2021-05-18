import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

public class Login {
    private JPanel panelLogin;
    private JTextField txtUsername;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel panelUsername;
    private JPanel panelPassword;
    private JPanel panelButton;
    private JLabel lblError;


    public Login() {
        initComponents();

    }
    public void initComponents()
    {
        JFrame frame = new JFrame();
        frame.setContentPane(panelLogin);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
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
