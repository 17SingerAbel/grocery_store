package storeGui;

import gorcery_store.Section;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * A Gui for adding new section
 */
public class AddSectionGui extends SmallGui implements ActionListener {
  /*** a ManagerGui */
  private ManagerGui mg;
  /*** a JLabel */
  private JLabel msg;
  /*** A JTextField */
  private JTextField section;
  /*** a JButton */
  private JButton ok;
  /*** a JPanel */
  private JPanel panel;
  /*** a JFrame */
  private JFrame frame;

  /**
   * The constructor of AddSectionGui
   * Create a Gui for adding new section
   * @param mg a ManagerGui
   */
  public AddSectionGui(ManagerGui mg){
    this.mg = mg;
    addObserver(mg.getSimulation());
    frame = new JFrame("Add New Section");
    panel = new JPanel();
    msg = new JLabel("Please type the section you want to add");
    section = new JTextField(14);
    section.setEditable(true);
    ok = new JButton("OK");
    ok.addActionListener(this);
    panel.add(msg);

    panel.add(new JScrollPane(section, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));   
    panel.add(ok);
    panel.setLayout(new FlowLayout());
    frame.add(panel);

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
    String sectionName = section.getText();
    Section s = new Section(sectionName);
    mg.getStore().getContainer().put(sectionName, s);
    setChanged();
    notifyObservers("New section " + sectionName + " is added to Store.");
    frame.dispose();
  }
}
