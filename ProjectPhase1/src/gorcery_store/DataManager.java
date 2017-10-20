package gorcery_store;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DataManager contains methods to get data from file and store data to the file.
 */
public class DataManager {

  public StoreSimulation simulation;

  public DataManager(StoreSimulation s) throws IOException, ClassNotFoundException {
    simulation = s;
    simulation.addDate(getLocalDate());
    File file = new File("./ProjectPhase1/Store.ser");
    if (file.exists()) {
      simulation.store = readData();
    } else {
      EventInstruction e = new EventInstruction(simulation);
      e.readEvent("./ProjectPhase1/Setup.txt");
    }
  }

  /**
   * Store the given object in the file which has the given file path.
   */
  public void storeReadableData() throws FileNotFoundException {
    PrintWriter writer = new PrintWriter("./ProjectPhase1/Store.csv");
    for (String key : simulation.store.getProducts().keySet()) {
      Product product = simulation.store.getProducts().get(key);
      String section;
      section = product.getSection().getParent().getName() + "-" + product.getSection().getName();
      String line = product.getUpc() + "," + product.getName() + "," + product.getSale().getPrice()
          + "," + product.getQuantity() + "," + section + "," + product.getLocation() + ","
          + product.getThreshold();
      writer.println(line);
    }
    writer.close();
  }

  /**
   * 
   */
  public void storeData() {
    try {
      FileOutputStream fileOut = new FileOutputStream("./ProjectPhase1/Store.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(simulation.store);
      out.close();
      fileOut.close();
    } catch (IOException i) {
      i.printStackTrace();
    }
  }

  /**
   * Read the Data in the file.
   *
   * @return the object read, if cannot return "NotFound".
   */
  private Store readData() throws IOException, ClassNotFoundException {
    FileInputStream fileIn = new FileInputStream("./ProjectPhase1/Store.ser");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    Object e = in.readObject();
    in.close();
    fileIn.close();
    return (Store) e;

  }

  /**
   * Return an integer which represents the local date in the format of "yyyymmdd".
   * 
   * @return the local date.
   */
  public int getLocalDate() {
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    int date = Integer.valueOf(dateFormat.format(new Date()));
    return date;
  }
}
