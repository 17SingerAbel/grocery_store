package storeGui;

import gorcery_store.Order;
import gorcery_store.Product;
import gorcery_store.Sale;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A Gui for getting information of a section
 */
public class SectionInfoGui extends SmallGui implements ActionListener {
  /*** a RevenueAndCostGui */
  private RevenueAndCostGui revenueAndCostGui;
  /*** a JLabel */
  private JLabel msg;
  /*** A JTextField */
  private JTextField section;
  /*** a JTextArea */
  private JTextArea result;
  /*** a JButton */
  private JButton cost;
  /*** a JButton */
  private JButton revenue;
  /*** a JPanel */
  private JPanel panel, panel1;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of SectionInfoGui Create a Gui for getting information of a section
   * 
   * @param revenueAndCostGui a RevenueAndCostGui
   */
  public SectionInfoGui(RevenueAndCostGui revenueAndCostGui) {
    this.revenueAndCostGui = revenueAndCostGui;
    addObserver(revenueAndCostGui.getSimulation());
    frame = new JFrame();
    panel = new JPanel();
    panel1 = new JPanel();
    msg = new JLabel("Please type the section :");
    section = new JTextField(10);
    cost = new JButton("Section Cost");
    cost.addActionListener(this);
    revenue = new JButton("Section Revenue");
    revenue.addActionListener(this);
    result = new JTextArea(15, 30);
    panel.add(msg);
    panel.add(section);
    panel1.add(revenue);
    panel1.add(cost);
    frame.add(new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    panel.setLayout(new FlowLayout(0));
    panel1.setLayout(new GridLayout(0, 1));
    frame.add("East", panel1);
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
    if (!revenueAndCostGui.getStore().getContainer().containsKey(section.getText())) {
      WarningGui wg = new WarningGui();
    } else {
      switch (e.getActionCommand()) {
        case "Section Cost":
          String str1 = section.getText() + "'s cost is : " + sectionCost(section.getText());
          result.setText(str1);
          setChanged();
          notifyObservers(str1);
          break;
        case "Section Revenue":
          String str2 = section.getText() + "'s revenue is : " + sectionRevenue(section.getText());
          result.setText(str2);
          setChanged();
          notifyObservers(str2);
          break;
      }
    }
  }

  /**
   * Get the cost for a certain section
   * 
   * @param section a given section
   * @return the cost for a certain section
   */
  public double sectionCost(String section) {
    ArrayList<Order> orderInOneDay = revenueAndCostGui.getStore().getOrderInOneDay();
    Map<String, Product> products = revenueAndCostGui.getStore().getProducts();
    double cost = 0;
    for (Order order : orderInOneDay) {
      Product product = products.get(order.getUpc());
      if (product.getSection().getParent().getName().equals(section)) {
        cost = cost + order.getPrice() * order.getQuantity();
      }
    }
    return cost;
  }

  /**
   * Get the revenue for a certain section
   * 
   * @param section a given section
   * @return the revenue for a certain section
   */
  public double sectionRevenue(String section) {
    ArrayList<Sale> saleInOneDay = revenueAndCostGui.getStore().getSaleInOneDay();
    Map<String, Product> products = revenueAndCostGui.getStore().getProducts();
    double revenue = 0;
    for (Sale sale : saleInOneDay) {
      Product product = products.get(sale.getUpc());
      if (product.getSection().getParent().getName().equals(section)) {
        revenue = revenue + sale.getPrice() * sale.getQuantity();
      }
    }
    return revenue;
  }
}
