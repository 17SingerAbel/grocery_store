package storeGui;



import gorcery_store.Product;
import gorcery_store.Store;
import gorcery_store.StoreSimulation;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Gui for reshelvers
 */
public class ReshelverGui extends SmallGui implements ActionListener {
  /*** a JFrame */
  private JFrame frame;
  /*** a JTextArea */
  private JTextArea textArea;
  /*** A JTextField */
  private JTextField upc;
  /*** my store */
  private Store store;
  /*** a JLabel */
  private JLabel lbinput;
  /*** my product */
  private Product product;
  /*** my oldLocation */
  private String oldLocation = "";
  /*** my simulation */
  private StoreSimulation simulation;

  /**
   * The constructor of ReshelverGui Create a Gui for reshelvers
   * 
   * @param simulation a StoreSimulation
   */
  public ReshelverGui(StoreSimulation simulation) {
    this.simulation = simulation;
    this.store = simulation.getStore();
    addObserver(simulation);
    frame = new JFrame("Reshelver");
    lbinput = new JLabel("please type information here");
    textArea = new JTextArea(10, 30);
    upc = new JTextField(16);
    textArea.setEditable(false);

    textArea.append("Please type the UPC of the product");
    JButton Ok = new JButton("OK");
    JButton Quantity = new JButton("Quantity");
    JButton OrderHistory = new JButton("Order History");
    JButton ChangeLocation = new JButton("Change Location");
    Panel panel = new Panel();
    Panel panel1 = new Panel();
    panel.setLayout(new FlowLayout(0));
    panel1.setLayout(new GridLayout(0, 1));

    // button add listener
    Quantity.addActionListener(this);
    OrderHistory.addActionListener(this);
    ChangeLocation.addActionListener(this);
    Ok.addActionListener(this);


    panel.add(lbinput);
    panel.add(upc);
    panel.add(Ok);

    panel1.add(Quantity);
    panel1.add(OrderHistory);
    panel1.add(ChangeLocation);
    frame.add("North", panel);
    frame.add("East", panel1);
    frame.add("Center", new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));

    frame.setResizable(false);
    frame.setVisible(true);
    frame.pack();
  }

  /**
   * Check if a upc is valid
   * 
   * @param command a given input
   */
  public void checkAvaliablity(String command) {
    if (store.getProducts().get(command) == null) {
      textArea.setText("Invalid UPC number. Please enter it again.");
      upc.setText("");
      WarningGui gui = new WarningGui();
    } else if (!(store.getProducts().get(command) == null)) {
      product = store.getProducts().get(command);
      textArea.setText("Valid UPC, please press button");
    }
  }

  /**
   * Set action to buttons which has been added ActionListener.
   * 
   * @param e an ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    textArea.setText("");

    switch (e.getActionCommand()) {
      case "OK":
        go();
        upc.setText("");
        break;
      case "Change Location":
        oldLocation = product.getLocation();
        textArea.append("Type the new location you want to move to : \n");
        upc.setText("");

        break;
      case "Quantity":
        upc.setText("");
        textArea.append(product.getName() + " UPC: " + product.getUpc() + " remains quantity of "
            + product.getQuantity() + "\n");
        setChanged();
        notifyObservers(product.getName() + " UPC: " + product.getUpc() + " OrderHistory: " + "\n"
            + product.getOrder().getHistory());
        break;
      case "Order History":
        upc.setText("");
        textArea.append(product.getName() + " UPC: " + product.getUpc() + " OrderHistory: " + "\n"
            + product.getOrder().getHistory() + "\n");
        setChanged();
        notifyObservers(product.getName() + " UPC: " + product.getUpc() + " remains quantity of "
            + product.getQuantity());
        break;
    }


  }

  /**
   * The action for ok button.
   */
  private void go() {
    if (oldLocation.equals("")) {
      checkAvaliablity(upc.getText());
    } else {
      product.setAisle(upc.getText());
      textArea.append("Now the product " + product.getName() + " is moved to the location "
          + product.getLocation() + " from the old location " + oldLocation + "\n");

      setChanged();
      notifyObservers("Now the product " + product.getName() + " is moved to the location "
          + product.getLocation() + " from the old location " + oldLocation);

      oldLocation = "";
    }
  }
}
