package storeGui;

import gorcery_store.Product;
import gorcery_store.Store;
import gorcery_store.StoreSimulation;
import gorcery_store.WorkerAccount;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * a Gui for managers
 */
public class ManagerGui extends SmallGui implements ActionListener {
  /*** a JTextArea */
  private JTextArea textArea;
  /*** my store */
  private Store store;
  /*** my StoreSimulation */
  private StoreSimulation simulation;
  /*** my WorkerAccount */
  private WorkerAccount workers;
  /*** a JButton */
  private JButton pendingOrder;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of ManagerGui Create a Gui for managers
   * 
   * @param acc a WorkerAccount
   * @param simulation a StoreSimulation
   */
  public ManagerGui(WorkerAccount acc, StoreSimulation simulation) {
    this.simulation = simulation;
    this.store = simulation.getStore();
    this.workers = acc;
    addObserver(simulation);
    frame = new JFrame("Manager");
    textArea = new JTextArea(15, 30);
    pendingOrder = new JButton("Pending Order");
    JButton workerManagement = new JButton("Worker Management");
    JButton addSection = new JButton("Add New Section");
    JButton revAndCost = new JButton("Revenue and Cost");
    JButton handle = new JButton("Handle Product");
    Panel panel = new Panel();
    panel.setLayout(new GridLayout(0, 1));
    // button add listener
    pendingOrder.addActionListener(this);
    workerManagement.addActionListener(this);
    addSection.addActionListener(this);
    revAndCost.addActionListener(this);
    handle.addActionListener(this);
    frame.add("West", new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
    panel.add(pendingOrder);
    panel.add(workerManagement);
    panel.add(addSection);
    panel.add(revAndCost);
    panel.add(handle);
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
      case "Worker Management":
        WorkerManagement wmg = new WorkerManagement(this);
        break;
      case "Pending Order":
        textArea.setText(store.getOrderListString());
        setChanged();
        notifyObservers(store.getOrderListString());
        pendingOrder.setText("Purchase");
        break;
      case "Purchase":
        if (!textArea.getText()
            .equals("This is no pending order. \n" + "All pending orders have been purchased.")) {
          purchase();
        }
        pendingOrder.setText("Pending Order");
        break;
      case "Add New Section":
        AddSectionGui ad = new AddSectionGui(this);
        break;
      case "Revenue and Cost":
        RevenueAndCostGui revenueAndCostGui = new RevenueAndCostGui(this);
        break;
      case "Handle Product":
        HandleProductGui handleProductGui = new HandleProductGui(this);
    }
  }

  /**
   * Helper method for case Purchase.
   */
  private void purchase() {
    String[] orders = textArea.getText().split("\n");
    ArrayList<ArrayList<String>> newOrders = new ArrayList<>();
    for (String order : orders) {
      String[] array_ = order.split("[:;]");
      ArrayList<String> newOrder = new ArrayList<>();
      newOrder.add(array_[1].substring(1));
      newOrder.add(array_[3].substring(1));
      newOrder.add(array_[5].substring(1));
      newOrders.add(newOrder);
    }
    for (ArrayList<String> order : newOrders) {
      int upcLength = order.get(0).length();
      String upc = order.get(0).substring(upcLength - 13, upcLength - 1);
      Product p = store.getProducts().get(upc);
      p.getOrder().setUpc(upc);
      p.getOrder().setDate(simulation.getDATE());

      p.getOrder().setPriceAndQuantity(Double.valueOf(order.get(1)), Integer.valueOf(order.get(2)));
      store.getPendingReceived().add(order);

      // in order to remove order from the pending order, we need change order's quantity
      // into the default value.
      ArrayList<String> oldOrder = new ArrayList<>(order);
      oldOrder.remove(2);
      oldOrder.add(String.valueOf(p.getThreshold() * 3));
      store.getPendingOrder().remove(oldOrder);

      p.setStatus("Reorder");
      store.getOrderInOneDay().add(p.getOrder());
      store.addOneDayCost(simulation.getDATE(), store.cost());
      textArea.setText(store.getOrderListString());
      setChanged();
      notifyObservers(
          p.toString() + " by " + p.getOrder().getQuantity() + " from " + p.getOrder().companyName);
    }
  }

  /**
   * Get my simulation
   * 
   * @return my simulation
   */
  public StoreSimulation getSimulation() {
    return this.simulation;
  }

  /**
   * Set my textArea by a given str
   * 
   * @param str a given string
   */
  public void setTextArea(String str) {
    this.textArea.setText(str);
  }

  /**
   * Get my store
   * 
   * @return my store
   */
  public Store getStore() {
    return store;
  }

  /**
   * Get my workers
   * 
   * @return my workers
   */
  public WorkerAccount getWorkerAccount() {
    return workers;
  }
}
