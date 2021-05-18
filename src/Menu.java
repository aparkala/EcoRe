import javax.swing.*;
import java.awt.*;

public class Menu {
    private JTabbedPane RMOS;
    private JPanel panel1;
    private JPanel RCM;
    private JPanel RmosLogin;
    private JPanel headerPanel;

    public Menu()
    {
        initComponents();
    }
    public void initComponents()

    {
        JFrame frame = new JFrame("ECORE");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("Images/ecore.png");
        Image img = icon.getImage();

        /*headerPanel = new JPanel(){
                @Override
                public void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    Graphics2D 	g2 = (Graphics2D) g;
                    g2.drawImage(img, 0, 0, this.getWidth(),this.getHeight(), this);
                }
            };*/

        frame.setVisible(true);


    }
    public static void main(String[] args)
    {
        Menu m=new Menu();
    }
}
