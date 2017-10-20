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
 * A Gui for receivers
 */
public class ReceiverGui extends SmallGui implements ActionListener {
  /*** a JTextArea */
  private JTextArea textArea = new JTextArea(15, 30);
  /*** my simulation*/
  private StoreSimulation simulation;
  /*** my store */
  private Store store;
  /*** a JLabel*/
  private JLabel upc;
  /*** A JTextField */
  private JTextField productUpc;
  /*** a JFrame*/
  private JFrame frame;
  /*** my product*/
  private Product product;
  /**
   * The constructor of ReceiverGui
   * Create a Gui for receivers
   * @param simulation a StoreSimulation
   */
  public ReceiverGui(StoreSimulation simulation){
    this.simulation = simulation;
    this.store = simulation.getStore();
    addObserver(simulation);
    textArea.setEditable(false);
    upc = new JLabel("The product's UPC:");
    productUpc = new JTextField(12);
    frame = new JFrame("Receiver");
    Panel panel = new Panel();
    Panel panel1 = new Panel();
    panel1.setLayout(new FlowLayout(0));
    panel.setLayout(new GridLayout(0, 1));
    JButton addQuantity = new JButton("Received");
    JButton priceHistory = new JButton("PriceHistory");
    JButton location = new JButton("Location");
    JButton price = new JButton("Price");
    JButton cost = new JButton("Cost");
    addQuantity.addActionListener(this);
    priceHistory.addActionListener(this);
    location.addActionListener(this);
    price.addActionListener(this);
    cost.addActionListener(this);
    panel1.add(upc);
    panel1.add(productUpc);
    panel.add(addQuantity);
    panel.add(priceHistory);
    panel.add(location);
    panel.add(cost);
    panel.add(price);
    frame.add("North",panel1);
    frame.add("East",panel);
    frame.add(new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    frame.setResizable(false);
    frame.setVisible(true);
    frame.pack();
  }
  /**
   * Set action to buttons which has been added ActionListener.
   * @param e an ActionEvent
   */
  public void actionPerformed(ActionEvent e){
    if(e.getActionCommand().equals("Received")){
      AddQuantityGui ag = new AddQuantityGui(this);
    }else if (store.getProducts().containsKey(productUpc.getText())) {
      product = store.getProducts().get(productUpc.getText());
      switchCommand(e);
    }else {
      WarningGui warning = new WarningGui();
      productUpc.setText("");
    }
  }

  private void switchCommand(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "PriceHistory":
        String result = "The price history of " + product.getName() + ": " + "\n";
        for (Integer key : product.getSale().getHistory().keySet()) {
          result += String.valueOf(key) + ": " + product.getSale().getHistory().get(key) + "\n";
        }
        textArea.setText(result);
        setChanged();
        notifyObservers(result);
        break;
      case "Location":
        textArea.setText(product.getName() + " UPC: " + product.getUpc()
            + " is at location " + product.getLocation());
        setChanged();
        notifyObservers(product.getName() + " UPC: " + product.getUpc()
            + " is at location " + product.getLocation());
        break;
      case "Price":
        textArea.setText(product.getName() + " UPC: " + product.getUpc()
            + " now the current price is " + product.getSale().price);
        setChanged();
        notifyObservers(product.getName() + " UPC: " + product.getUpc()
            + " now the current price is " + product.getSale().price);
        break;
      case "Cost":
        textArea.setText(product.getName() + " UPC: " + product.getUpc()
            + " cost " + product.getOrder().price + " now per unit.");
        setChanged();
        notifyObservers(product.getName() + " UPC: " + product.getUpc()
            + " cost " + product.getOrder().price + " now per unit.");
        break;
    }
  }
/**
 * Get my product
 * @return my product
 */
  public Product getProduct() {
    return this.product;
  }

  /**
   * Get my simulation
   * @return my simulation
   */
  public StoreSimulation getSimulation() {
    return this.simulation;
  }
  /**
   * Set my textArea by a given str
   * @param str a given string
   */
  public void setTextArea(String str) {
    this.textArea.setText(str);
  }
  /**
   * Get my store
   * @return my store
   */
  public Store getStore() {
    return store;
  }
}
