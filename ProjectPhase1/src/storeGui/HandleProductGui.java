package storeGui;

import gorcery_store.Store;
import gorcery_store.StoreSimulation;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A Gui for handle product
 */
public class HandleProductGui extends SmallGui implements ActionListener {
  /*** a ManagerGui */
  private ManagerGui mg;
  /*** a JPanel */
  private JPanel panel;
  /*** a JFrame */
  private JFrame frame;
  /*** a JTextArea */
  private JTextArea textArea;

  /**
   * The constructor of HandleProductGui Create a Gui for handle product
   * 
   * @param mg a ManagerGui
   */
  public HandleProductGui(ManagerGui mg) {
    this.mg = mg;
    frame = new JFrame("Handle Product");
    panel = new JPanel();
    panel.setLayout(new GridLayout(0, 1));
    addObserver(mg.getSimulation());
    textArea = new JTextArea(15, 30);
    JButton addNewItem = new JButton("Add New Item");
    JButton setSellPrice = new JButton("Set Sell Price");
    JButton setDiscount = new JButton("Set Discount");
    addNewItem.addActionListener(this);
    setSellPrice.addActionListener(this);
    setDiscount.addActionListener(this);
    panel.add(addNewItem);
    panel.add(setSellPrice);
    panel.add(setDiscount);
    frame.add("West", new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    frame.add("East", panel);
    frame.setResizable(false);
    frame.setVisible(true);
    frame.pack();
  }

  /**
   * Set action to buttons which has been added ActionListener.
   * 
   * @param e an ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Set Sell Price":
        SetSellPriceGui ssp = new SetSellPriceGui(this);
        break;
      case "Set Discount":
        SetDiscountGui sdc = new SetDiscountGui(this);
        break;

      case "Add New Item":
        AddNewItemGui ang = new AddNewItemGui(this);
        break;

    }
  }

  /**
   * Get my simulation
   * 
   * @return my simulation
   */
  public StoreSimulation getSimulation() {
    return mg.getSimulation();
  }

  /**
   * Get my store
   * 
   * @return my store
   */
  public Store getStore() {
    return mg.getStore();
  }

  /**
   * Set my textArea by a given str
   * 
   * @param str a given string
   */
  public void setTextArea(String str) {
    mg.setTextArea(str);
  }
}
