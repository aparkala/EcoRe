import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
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
    private JLabel lblPassword;
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




       // txtUsername.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Username", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        //txtUsername.setMaximumSize(new java.awt.Dimension(300, 54));
        //txtUsername.setMinimumSize(new java.awt.Dimension(300, 54));
        //txtUsername.setPreferredSize(new java.awt.Dimension(300, 54));

        txtUsername.setFont(new Font("Montserrat", Font.PLAIN, 15));
        txtUsername.setBackground(Color.white);
        txtUsername.setForeground(Color.black);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        //passwordField.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", Font.PLAIN, 15), Color.black));
        //passwordField.setMaximumSize(new java.awt.Dimension(300, 54));
        //passwordField.setMinimumSize(new java.awt.Dimension(300, 54));
        //passwordField.setPreferredSize(new java.awt.Dimension(300, 54));

        passwordField.setFont(new Font("Montserrat", Font.PLAIN, 15));
        passwordField.setBackground(Color.white);
        passwordField.setForeground(Color.black);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                new CustomeBorder(),
                new EmptyBorder(new Insets(15, 25, 15, 25))));

        lblUsername.setForeground(Color.black);
        lblUsername.setFont(new Font("Montserrat", Font.PLAIN, 20));

        lblPassword.setForeground(Color.black);
        lblPassword.setFont(new Font("Montserrat", Font.PLAIN, 20));

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
    @SuppressWarnings("serial")
    class CustomeBorder extends AbstractBorder {
        @Override
        public void paintBorder(java.awt.Component c, Graphics g, int x, int y,
                                int width, int height) {
            // TODO Auto-generated method stubs
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(Color.gray);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }
    public static void main(String[] args)
    {
        Login l=new Login();
    }

}
