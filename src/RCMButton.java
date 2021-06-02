import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RCMButton extends JButton implements ActionListener{
    RCM rcm;
    public RCMButton(RCM rcm){
        this.rcm = rcm;
        this.setText(rcm.getRcmId() + "  AT  " + rcm.getLocation());
        this.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        try {
            viewRCM viewRCM = new viewRCM(rcm);
            System.out.println(this);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
