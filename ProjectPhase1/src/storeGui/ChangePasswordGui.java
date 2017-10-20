package storeGui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A Gui for changing password
 */
public class ChangePasswordGui extends SmallGui implements ActionListener {
  /*** a JLabel */
  private JLabel ins;
  /*** A JTextField */
  private JTextField password;
  /*** a InitialFrame Gui */
  private InitialFrame ini;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of ChangePasswordGui Create a Gui for changing password
   * 
   * @param i an InitialFrame Gui
   */
  public ChangePasswordGui(InitialFrame i) {
    ini = i;
    frame = new JFrame("Change Password");
    JPanel p = new JPanel();
    ins = new JLabel("Please enter your new password: ");
    password = new JTextField(6);
    JButton ok = new JButton("Confirm");
    ok.addActionListener(this);
    p.add(ins);
    p.add(password);
    p.add(ok);
    p.setLayout(new FlowLayout(0));
    frame.add(p);
    // setSize(500, 100);
    frame.setVisible(true);
    frame.pack();
  }

  /**
   * Set action to buttons which has been added ActionListener.
   * 
   * @param e an ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    ini.getWorkers().workers.get(ini.getL1()).setPassword(password.getText());

    ini.setL1("");
    ini.setL2("");
    frame.dispose();

  }


}
