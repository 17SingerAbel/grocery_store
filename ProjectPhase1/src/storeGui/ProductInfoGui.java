package storeGui;


import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * a Gui for getting information of a product
 */
public class ProductInfoGui extends SmallGui implements ActionListener {
  /*** a RevenueAndCostGui */
  private RevenueAndCostGui revenueAndCostGui;
  /*** a JLabel */
  private JLabel upc;
  /*** A JTextField */
  private JTextField productUpc;
  /*** a JTextArea */
  private JTextArea result;
  /*** a JLabel */
  private JLabel start;
  /*** A JTextField */
  private JTextField startDate;
  /*** a JLabel */
  private JLabel end;
  /*** A JTextField */
  private JTextField endDate;
  /*** a JPanel */
  private JPanel panel, panel1;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of ProductInfoGui Create a Gui for getting information of a product
   * 
   * @param revenueAndCostGui a RevenueAndCostGui
   */
  public ProductInfoGui(RevenueAndCostGui revenueAndCostGui) {
    this.revenueAndCostGui = revenueAndCostGui;
    addObserver(revenueAndCostGui.getSimulation());
    frame = new JFrame("Product Info");
    panel = new JPanel();
    panel1 = new JPanel();
    upc = new JLabel("Please type the upc of the product:");
    productUpc = new JTextField(12);
    JButton cost = new JButton("Product Cost");
    cost.addActionListener(this);
    JButton revenue = new JButton("Product Revenue");
    revenue.addActionListener(this);
    start = new JLabel("start date:");
    startDate = new JTextField(12);
    end = new JLabel("end date:");
    endDate = new JTextField(12);
    result = new JTextArea(15, 30);
    panel.add(upc);
    panel.add(productUpc);
    panel.add(start);
    panel.add(startDate);
    panel.add(end);
    panel.add(endDate);
    panel1.add(revenue);
    panel1.add(cost);
    panel.setLayout(new FlowLayout(0));
    panel1.setLayout(new GridLayout(0, 1));
    frame.add(new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    frame.add("North", panel);
    frame.add("East", panel1);
    frame.setResizable(true);
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
      case "Product Revenue":
        String str1 =
            productUpc.getText() + "'s revenue is : " + productRevenue(productUpc.getText());
        result.setText(str1);
        setChanged();
        notifyObservers(str1);
        break;
      case "Product Cost":
        String str2 = productUpc.getText() + "'s cost is : " + productCost(productUpc.getText());
        result.setText(str2);
        setChanged();
        notifyObservers(str2);
        break;
    }
  }

  /**
   * Get the cost of a product
   * 
   * @param upc the upc of a product
   * @return the cost of a product
   */
  private String productCost(String upc) {
    HashMap<Integer, ArrayList> history = revenueAndCostGui.getStore().
        getProducts().get(upc).getOrder().getHistory();
    String result = "";
    if (history.isEmpty()) {
      result = "The cost is 0";
    } else {
      double total = 0;
      for (int day : history.keySet()) {
        if ((Integer.valueOf(startDate.getText()) <= day) &&
            (day <= (Integer.valueOf(endDate.getText())))) {
          result += String.valueOf(day) + "  " + history.get(day) + "\n";
          total += ((Double) history.get(day).get(0)) * ((Integer)history.get(day).get(1));
        }
      }
      result += "Total cost is " + total;
    }
    return result;
  }

  /**
   * Get the revenue of a product
   * 
   * @param upc the upc of a product
   * @return the revenue of a product
   */
  private String productRevenue(String upc) {
    HashMap<Integer, ArrayList> history = revenueAndCostGui.getStore().
        getProducts().get(upc).getSale().getHistory();
    String result = "";
    if (history.isEmpty()) {
      result = "The revenue is 0";
    } else {
      double total = 0;
      for (int day : history.keySet()) {
        if ((Integer.valueOf(startDate.getText()) <= day) &&
            (day <= (Integer.valueOf(endDate.getText())))) {
          result += String.valueOf(day) + "  " + history.get(day) + "\n";
          total += ((Double) history.get(day).get(0)) * ((Integer)history.get(day).get(1));
        }
      }
      result += "Total revenue is " + total;
    }
    return result;
  }

}
