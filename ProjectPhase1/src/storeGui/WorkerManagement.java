package storeGui;

import gorcery_store.Worker;
import gorcery_store.WorkerAccount;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * A Gui for manage workers
 */
public class WorkerManagement extends SmallGui implements ActionListener {
  /*** a ManagerGui */
  private ManagerGui rg;
  /*** a JLabel */
  private JLabel input1;
  /*** a JLabel */
  private JLabel input2;
  /*** a JTextArea */
  private JTextArea textArea1 = new JTextArea(2, 30);
  /*** a JTextArea */
  private JTextArea textArea2 = new JTextArea(2, 15);
  /*** a JTextArea */
  private JTextArea outputArea = new JTextArea(2, 30);
  /*** a JButton */
  private JButton recruit;
  /*** a JButton */
  private JButton dismiss;
  /*** a JFrame */
  private JFrame frame;
  /*** my WorkerAccount */
  private WorkerAccount workers;

  /**
   * The constructor of WorkerManagement Create a Gui for manage workers
   * 
   * @param rg a ManagerGui
   */
  public WorkerManagement(ManagerGui rg) {
    this.rg = rg;
    addObserver(rg.getSimulation());
    frame = new JFrame("WorkerManagerMent");
    frame.setLayout(new FlowLayout());
    input1 = new JLabel("Please type working number here:");
    input2 = new JLabel("If recruition, please type job here:");
    dismiss = new JButton("Dismiss");
    recruit = new JButton("Recruit");

    dismiss.addActionListener(this);
    recruit.addActionListener(this);

    frame.add(input1);
    frame.add(textArea1);
    frame.add(input2);
    frame.add(textArea2);
    frame.add(dismiss);
    frame.add(recruit);
    frame.add(outputArea);

    frame.setSize(400, 480);
    frame.setResizable(true);
    frame.setVisible(true);
  }

  /**
   * Set action to buttons which has been added ActionListener.
   * 
   * @param e an ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Dismiss":
        dismissWorker();
        break;
      case "Recruit":
        recuritWorker();
    }
  }

  /**
   * Diss miss a worker
   */
  private void dismissWorker() {
    workers = rg.getWorkerAccount();
    if (!(workers.workers.containsKey(textArea1.getText()))) {
      WarningGui wg = new WarningGui();
      outputArea.append("We didn't recognize this worker account. \n");
      textArea1.setText("");
    } else {
      workers.dismiss(textArea1.getText());
      outputArea.setText("Successfully dismiss workers with working number "
          + String.valueOf(textArea1.getText()) + "\n");
      setChanged();
      notifyObservers("Successfully dismiss workers with working number "
          + String.valueOf(textArea1.getText()));
    }
  }

  /**
   * Recurit a worker
   */
  private void recuritWorker() {
    workers = rg.getWorkerAccount();
    if (workers.workers.containsKey(textArea1.getText())) {
      outputArea.append("We already had this worker account. + \n");
      textArea1.setText("");
    } else {
      if (textArea2.getText() == "") {
        workers.recruit(new Worker(textArea1.getText(), "123", "undetermined"));
      } else {
        workers.recruit(new Worker(textArea1.getText(), "123", textArea2.getText()));
      }
      outputArea.setText(
          "Successfully recruit workers with working number " + String.valueOf(textArea1.getText())
              + " as " + textArea2.getText() + "\n The default password is 123. \n");
      setChanged();
      notifyObservers(
          "Successfully recruit workers with working number " + String.valueOf(textArea1.getText())
              + " as " + textArea2.getText() + "\n The default password is 123.");
    }
  }
}
