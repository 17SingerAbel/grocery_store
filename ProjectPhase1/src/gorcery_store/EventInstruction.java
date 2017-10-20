package gorcery_store;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * This class contains some helpful methods for an event.
 */
public class EventInstruction {
  /*** my StoreSimulation */
  private StoreSimulation simulation;
  /*** Command SetUp. */
  private static final String SETUP = "SetUp";
  /*** Command SetUp. */
  private static final String SETUPWORKER = "SetUpWorker";

  /**
   * The constructor of EventInstruction Create an EventInstruction by a given store simulation
   * 
   * @param simul a given StoreSimulation
   */
  public EventInstruction(StoreSimulation simul) {
    simulation = simul;
  }

  /**
   * Set up an order based on the information in the given list.
   * 
   * @param record a given list of String
   */
  public void setUpOrOrder(String[] record) {
    Double o3 = Double.valueOf(record[3]);
    int o4 = Integer.valueOf(record[4]);
    String[] secString = record[5].split("-");
    Section section = simulation.store.getSection(secString[0]);
    SubSection subSection;
    Product product;
    if (!section.getContainer().containsKey(secString[1])) {
      subSection = new SubSection(secString[1]);
      simulation.store.addSubSection(subSection, section);
    } else {
      subSection = section.getContainer().get(secString[1]);
    }
    product = new Product(record[1], record[2], o4, Integer.valueOf(record[7]), subSection,
        record[6], o3, Double.valueOf(record[8]), simulation.getDATE());
    product.getOrder().setUpc(product.getName());
    product.getOrder().setQuantity(product.getQuantity());
    //product.getOrder().addHistory(o3, o4);
    subSection.addProduct(product);
    simulation.store.getProducts().put(product.getUpc(), product);
    if (record[0].equals("Order")) {
      simulation.store.getOrderInOneDay().add(product.getOrder());
      product.setStatus("Order");
    } else {
      product.setStatus("SetUp");
      product.getOrder().companyName = record[9];
    }
    simulation.getLogger().log(Level.INFO, product.toString());

  }

  /**
   * Set up a worker based on the information in the given list.
   * 
   * @param record a given list of String
   */
  private void setUpWorker(String[] record) {
    Worker a = new Worker(record[1], record[2], record[3]);
    simulation.store.getAcc().recruit(a);
    simulation.getLogger().log(Level.INFO, "Set up worker " + a.getWorkingNum()
        + " with the occupation of " + a.getOccupation());
  }

  /**
   * Read events from event.txt and run the program.
   * 
   * @param filePath the path of the file event.txt.
   * @throws IOException may throws IOException
   */
  void readEvent(String filePath) throws IOException {
    Scanner scanner = new Scanner(new FileInputStream(filePath));
    while (scanner.hasNext()) {
      String line = scanner.nextLine();
      String[] record = line.split(", ");
      Event event = new Event(record[0], record);
      simulation.events.add(event);
    }
    scanner.close();
    simulate(simulation.events);
  }

  /**
   * Handle the event in events list.
   * 
   * @param event an given event
   */
  private void handleEvent(Event event) throws IOException {
    switch (event.getType()) {
      case SETUP:
        setUpOrOrder(event.getContent());
        break;
      case SETUPWORKER:
        setUpWorker(event.getContent());
        break;
    }
  }

  /**
   * Simulate the event in events
   * 
   * @param events a list of event
   * @throws IOException IOException
   */
  private void simulate(List<Event> events) throws IOException {
    for (Event e : events) {
      handleEvent(e);
    }
  }

}
