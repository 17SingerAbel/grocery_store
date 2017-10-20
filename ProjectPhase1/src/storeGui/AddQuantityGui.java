package storeGui;

import gorcery_store.Product;

import gorcery_store.Store;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A Gui for adding quantity
 */
public class AddQuantityGui extends SmallGui implements ActionListener {
  /*** a ReceiverGui */
  private ReceiverGui rg;
  /*** a JLabel */
  private JLabel msg;
  /*** a JTextArea */
  private JTextArea pendingOrder;
  /*** a JButton */
  private JButton ok;
  /*** a JPanel */
  private JPanel panel;
  /*** a JFrame */
  private JFrame frame;
  /*** my store */
  private Store store;

  /**
   * The constructor of AddQuantityGui Create a Gui for adding quantity
   * 
   * @param rg a ReceiverGui
   */
  public AddQuantityGui(ReceiverGui rg) {
    this.rg = rg;
    store = rg.getStore();
    addObserver(rg.getSimulation());
    frame = new JFrame("Add Quantity");
    panel = new JPanel();
    msg = new JLabel("Please select the product UPC received.");
    pendingOrder = new JTextArea(15, 30);
    pendingOrder.setEditable(false);
    pendingOrder.setText(rg.getStore().getPendingReceivedItems());
    ok = new JButton("OK");
    ok.addActionListener(this);
    panel.add(msg);
    panel.add(pendingOrder);
    panel.add(ok);
    panel.setLayout(new FlowLayout(0));
    frame.add(new JScrollPane(pendingOrder, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    frame.add("North", panel);
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
    if (!pendingOrder.getText().equals(
        "This are no pending received items. \n" + "All pending items have been received.")) {

      if (!checkUpc(pendingOrder.getSelectedText())) {
        WarningGui warning = new WarningGui();
      } else {
        Product product = store.getProducts().get(pendingOrder.getSelectedText());
        product.addQuantity(product.getOrder().getQuantity());
        ArrayList<String> list = new ArrayList<>();
        list.add(product.getName() + " (" + product.getUpc() + ")");
        list.add(String.valueOf(product.getOrder().price));
        list.add(String.valueOf(product.getOrder().getQuantity()));
        store.getPendingReceived().remove(list);
        pendingOrder.setText(rg.getStore().getPendingReceivedItems());
        rg.setTextArea("Now the quantity of " + product.getName() + " is " + product.getQuantity());
        setChanged();
        notifyObservers(
            "Now the quantity of " + product.getName() + " is " + product.getQuantity());
      }
    } else {
      frame.dispose();
    }
  }
}
