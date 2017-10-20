package storeGui;

import gorcery_store.DataManager;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import gorcery_store.Store;
import gorcery_store.StoreSimulation;
import gorcery_store.WorkerAccount;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

/**
 * A Gui for creating initial frame for all gui, asking for log in.
 */
public class InitialFrame extends SmallGui implements ActionListener {
  /*** a JPanel */
  private JPanel panel, panel1, panel2, panel3;
  /*** a JLabel */
  private JLabel account;
  /*** a JLabel */
  private JLabel password;
  /*** A JTextField */
  private JTextField l1;
  /*** A JPasswordField */
  private JPasswordField l2;
  /*** my workerAccount */
  private WorkerAccount workers;
  /*** my simulation */
  private StoreSimulation simulation;
  private DataManager dataManager;
  /*** my store */
  private Store store;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of InitialFrame Create a Gui for creating initial frame for all gui, asking for
   * log in.
   * 
   * @param acc a WorkerAccount
   * @param dataManager a StoreSimulation
   */
  public InitialFrame(WorkerAccount acc, DataManager dataManager) {
    frame = new JFrame("Log in");
    this.simulation = dataManager.simulation;
    this.store = simulation.getStore();
    this.dataManager = dataManager;
    panel = new JPanel();
    panel1 = new JPanel();
    panel2 = new JPanel();
    panel3 = new JPanel();
    account = new JLabel("Account:    ");
    password = new JLabel("Password: ");
    JButton button = new JButton("Log in");
    JButton change = new JButton("Change the password");
    JButton exit = new JButton("Exit");
    exit.addActionListener(this);
    l1 = new JTextField(18);
    l2 = new JPasswordField(18);

    button.addActionListener(this);
    change.addActionListener(this);
    workers = acc;

    panel.add(account);
    panel.add(l1);
    panel1.add(password);
    panel1.add(l2);
    panel2.add(button);
    panel2.add(change);
    panel2.add(exit);
    panel3.setLayout(new GridLayout(0, 1));
    panel3.add(panel);
    panel3.add(panel1);
    panel3.add(panel2);
    panel.setLayout(new FlowLayout(1));
    panel1.setLayout(new FlowLayout(1));
    panel2.setLayout(new FlowLayout(1));
    frame.add(panel3);

    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    // setSize(500, 250);
    frame.setResizable(false);
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

    String info = workers.logIn(l1.getText(), l2.getText());
    if (e.getActionCommand().equals("Exit")){
      dataManager.storeData();
      // Comment out to activate storing.
//      dataManager.storeReadableData();
      System.exit(0);
    } else if (!(info.equals("We didn't recognize this worker account.")
        || info.equals("Wrong password."))) {
      switch (e.getActionCommand()) {
        case "Log in":
          switch (info) {
            case "Manager":
              ManagerGui managerGui = new ManagerGui(workers, simulation);
              break;
            case "Receiver":
              ReceiverGui receiverGui = new ReceiverGui(simulation);
              break;
            case "Reshelver":
              ReshelverGui reshelverGui = new ReshelverGui(simulation);
              break;
            case "Cashier":
              CashierGui cashierGui = new CashierGui(simulation);
              break;
          }
          simulation.getLogger().log(Level.INFO,
              "The " + info + " (" + l1.getText() + ") logged in.");
          l2.setText("");
          l1.setText("");

          break;
        case "Change the password":
          ChangePasswordGui cha = new ChangePasswordGui(this);
          simulation.getLogger().log(Level.INFO,
              "The " + info + " (" + l1.getText() + ") has changed the password.");

      }
    } else {
      WarningGui warning = new WarningGui();
      l1.setText("");
      l2.setText("");
    }
  }

  /**
   * Get my workers
   * 
   * @return my workers
   */
  public WorkerAccount getWorkers() {
    return workers;
  }

  /**
   * Get my l1 text
   * 
   * @return the text in l1
   */
  public String getL1() {
    return l1.getText();
  }

  /**
   * @param str The string.
   *
   * Set l1 text/
   */
  public void setL1(String str) {
    l1.setText(str);
  }
  /**
   * @param str The string.
   *
   * Set l2 text/
   */
  public void setL2(String str) {
    l2.setText(str);
  }
}

