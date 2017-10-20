package storeGui;

import gorcery_store.Product;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A Gui for set the price for a selling product
 */
public class SetSellPriceGui extends SmallGui implements ActionListener {
  /*** a HandleProductGui */
  private HandleProductGui hg;
  /*** a JLabel */
  private JLabel input1;
  /*** a JLabel */
  private JLabel input2;
  /*** a JTextArea */
  private JTextArea textArea1 = new JTextArea(2, 30);
  /*** a JTextArea */
  private JTextArea textArea2 = new JTextArea(2, 30);
  /*** a JTextArea */
  private JTextArea outputArea = new JTextArea(15, 30);
  /*** a JButton */
  private JButton go;
  /*** a JFrame */
  private JFrame frame;
  /*** my product */
  private Product product;

  /**
   * The constructor of SetSellPriceGui Create a Gui for set the price for a selling product
   * 
   * @param hg a HandleProductGui
   */
  public SetSellPriceGui(HandleProductGui hg) {
    this.hg = hg;

    addObserver(hg.getSimulation());
    frame = new JFrame("Set Sell Price");
    frame.setLayout(new FlowLayout());
    input1 = new JLabel("Please type UPC of your product here:");
    input2 = new JLabel("Please set your sell price here:");
    go = new JButton("Go");

    go.addActionListener(this);

    frame.add(input1);
    frame.add(textArea1);
    frame.add(input2);
    frame.add(textArea2);
    frame.add(go);
    frame.add(new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));

    frame.setSize(400, 480);
    frame.setResizable(false);
    frame.setVisible(true);

  }

  /**
   * Set action to buttons which has been added ActionListener.
   * 
   * @param e an ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Go":
        product = hg.getStore().getProducts().get(textArea1.getText());
        if (product == null || !textArea2.getText().matches("[0-9]*")) {
          WarningGui wg = new WarningGui();
          outputArea.setText("Invalid UPC number. Please enter it again.");
          textArea1.setText("");
          textArea2.setText("");
        } else {
          product.getSale().price = Double.parseDouble(textArea2.getText());
          outputArea.append("Setting price successfully. Now the price for "
              + String.valueOf(product.getName()) + " is " + String.valueOf(textArea2.getText()));
          setChanged();
          notifyObservers(
              "Setting price successfully. Now the price for " + String.valueOf(product.getName())
                  + " is " + String.valueOf(textArea2.getText()) + ". ");
          textArea1.setText("");
          textArea2.setText("");
        }
    }
  }
}

