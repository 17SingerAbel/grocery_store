package gorcery_store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create event for a product, which can be sale or order.
 */
public class ProductEvent implements Serializable {
  /** My upc. */
  public String upc;
  /** My price for the product. */
  public double price;
  /** My quantity for the product. */
  public int quantity;
  /** My date of ordering or selling the product. */
  public int date;
  /** My history for the product. */
  HashMap<Integer, ArrayList> history;

  /**
   * The Constructor of ProductEvent. Create an event for a product which can be sale or order.
   * 
   * @param price the price of the product
   * @param date the date of buying or selling the product
   */
  public ProductEvent(double price, int date) {
    this.price = price;
    this.date = date;
    history = new HashMap<>();
  }

  /**
   * Get my price.
   * 
   * @return my price
   */
  public double getPrice() {
    return price;
  }

  /**
   * set my price as the given price. Also put it into the history.
   * 
   * @param price a given price
   */
  public void setPriceAndQuantity(double price, int quantity) {
    this.price = price;
    this.quantity = quantity;
    addHistory(price, quantity);
  }

  /**
   * Get my quantity.
   * 
   * @return my quantity
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * set my quantity as the given quantity.
   * 
   * @param quantity my given quantity
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * set my date as the given date.
   * 
   * @param date a given date
   */
  public void setDate(int date) {
    this.date = date;
  }

  /**
   * Set the upc of the product.
   * 
   * @param upc the new upc.
   */
  public void setUpc(String upc) {
    this.upc = upc;
  }

  /**
   * Return my upc.
   * 
   * @return my upc.
   */
  public String getUpc() {
    return upc;
  }

  /**
   * Return my history.
   * 
   * @return My history.
   */
  public HashMap<Integer, ArrayList> getHistory() {
    return history;
  }

  /**
   * Set my history to history.
   * 
   * @param history the target history.
   */
  public void setHistory(HashMap<Integer, ArrayList> history) {
    this.history = history;
  }

  /**
   * Add the [price, quantity] into My history.
   * 
   * @param p the new price.
   */
  public void addHistory(double p, int q) {
    ArrayList data = new ArrayList();
    data.add(p);
    data.add(q);
    history.put(date, data);
  }

}
