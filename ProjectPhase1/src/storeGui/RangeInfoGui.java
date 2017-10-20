package storeGui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A Gui for getting information of a range of time
 */
public class RangeInfoGui extends SmallGui implements ActionListener {
  /*** a RevenueAndCostGui */
  private RevenueAndCostGui revenueAndCostGui;
  /*** a JLabel */
  private JLabel startDate;
  /*** A JTextField */
  private JTextField startInput;
  /*** a JLabel */
  private JLabel endDate;
  /*** A JTextField */
  private JTextField endInput;
  /*** a JTextArea */
  private JTextArea result;
  /*** a JButton */
  private JButton cost;
  /*** a JButton */
  private JButton revenue;
  /*** a JPanel */
  private JPanel panel, panel1, panel2, panel3;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of RangeInfoGui Create a Gui for getting information of a range of time
   * 
   * @param revenueAndCostGui a RevenueAndCostGui.
   */
  public RangeInfoGui(RevenueAndCostGui revenueAndCostGui) {
    this.revenueAndCostGui = revenueAndCostGui;
    addObserver(revenueAndCostGui.getSimulation());
    frame = new JFrame("Range Info");
    panel = new JPanel();
    panel1 = new JPanel();
    panel2 = new JPanel();
    panel3 = new JPanel();
    startDate = new JLabel("Please type the start date as format yyyyMMdd.");
    endDate = new JLabel("Please type the end date as format yyyyMMdd");
    startInput = new JTextField(10);
    endInput = new JTextField(10);
    cost = new JButton("Range Cost");
    cost.addActionListener(this);
    revenue = new JButton("Range Revenue");
    revenue.addActionListener(this);
    result = new JTextArea(15, 30);
    panel.add(startDate);
    panel.add(startInput);
    panel1.add(endDate);
    panel1.add(endInput);
    panel2.add(cost);
    panel2.add(revenue);
    panel.setLayout(new FlowLayout(0));
    panel1.setLayout(new FlowLayout(0));
    panel2.setLayout(new GridLayout(0, 1));
    frame.add("Center", new JScrollPane(result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
    panel3.add("North", panel);
    panel3.add("South", panel1);
    panel3.setLayout(new GridLayout(0, 1));
    frame.add("North", panel3);
    frame.add("East", panel2);
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
    int start = Integer.valueOf(startInput.getText());
    int end = Integer.valueOf(endInput.getText());
    if ((start > 99999999) || (end < start)) {
      WarningGui wg = new WarningGui();
    } else {
      switch (e.getActionCommand()) {
        case "Range Cost":
          String str1 = rangeCosts(start, end);
          result.setText(str1);
          setChanged();
          notifyObservers(str1);
          break;
        case "Range Revenue":
          String str2 = rangeRevenues(start, end);
          result.setText(str2);
          setChanged();
          notifyObservers(str2);
          break;
      }
    }
  }

  /**
   * Get the revenue for a certain range of time
   * 
   * @param startingDate a given start date
   * @param endingDate a given end date
   * @return the revenue for a certain range of time
   */
  private String rangeRevenues(int startingDate, int endingDate) {
    int date = revenueAndCostGui.getStore().getDate();
    Map<Integer, Double> oneDayRevenue = revenueAndCostGui.getStore().getOneDayRevenue();
    String result = "";
    if (endingDate > date) {
      endingDate = date;
    }
    if (oneDayRevenue.isEmpty()) {
      result = "The revenue is 0";
    } else {
      double total = 0;
      for (int day : oneDayRevenue.keySet()) {
        if ((startingDate <= day) && (day <= endingDate)) {
          result += String.valueOf(day) + "  " + oneDayRevenue.get(day) + "\n";
          total += oneDayRevenue.get(day);
        }
      }
      result += "Total revenue is " + total;
    }
    return result;
  }

  /**
   * Get the cost for a certain range of time
   * 
   * @param startingDate a given start date
   * @param endingDate a given end date
   * @return the cost for a certain range of time
   */
  private String rangeCosts(int startingDate, int endingDate) {
    int date = revenueAndCostGui.getStore().getDate();
    Map<Integer, Double> oneDayCost = revenueAndCostGui.getStore().getOneDayCost();
    String result = "";
    if (endingDate > date) {
      endingDate = date;
    }
    if (oneDayCost.keySet().isEmpty()) {
      result = "The cost is 0";
    } else {
      double total = 0;
      for (int day : oneDayCost.keySet()) {
        if ((startingDate <= day) && (day <= endingDate)) {
          result += String.valueOf(day) + "  " + oneDayCost.get(day) + "\n";
          total += oneDayCost.get(day);
        }
      }
      result += "Total cost is " + total;
    }
    return result;

  }

}
