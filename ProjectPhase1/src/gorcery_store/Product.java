package gorcery_store;


import java.io.Serializable;

/**
 * A product in the store
 */
public class Product implements Serializable {
  /** The upc of the product. */
  private String upc;
  /** The upc of the product. */
  private String name;
  /** The status of the product. Received, Order, Reorder, sale, and SetUp. */
  private String status;
  /** The order of the product. */
  private Order order;
  /** The sale of the product. */
  private Sale sale;
  /** The quantity of the product in store. */
  private int quantity;
  /** The floor limit of the product. */
  private int threshold;
  /** The subsection of the product. */
  private SubSection section;
  /** My number of aisle. */
  private String aisle;

  /**
   * Initialize me with empty information..
   */
  public Product() {}

  /**
   * The constructor of Product Create a product with given upc, upc, quantity, threshold, section,
   * aisle, orderPrice, sellPrice and date.
   * 
   * @param upc The upc of the product
   * @param name The upc of the product
   * @param quantity The quantity of the product
   * @param threshold The threshold of the product
   * @param section The section of the product
   * @param aisle The aisle of the product
   * @param orderPrice The orderPrice of the product
   * @param sellPrice The sellPrice of the product
   * @param date The date of the product
   */
  public Product(String upc, String name, int quantity, int threshold, SubSection section,
      String aisle, double orderPrice, double sellPrice, int date) {
    this.upc = upc;
    this.name = name;
    this.section = section;
    this.quantity = quantity;
    this.threshold = threshold;
    this.aisle = aisle;
    order = new Order(orderPrice, date);

    sale = new Sale(sellPrice, date);

  }

  /**
   * Set the new threshold.
   * 
   * @param threshold The threshold of the product.
   */
  public void setThreshold(int threshold) {
    this.threshold = threshold;
  }

  /**
   * Set the section of the product.
   * 
   * @param section The section of the product.
   */
  public void setSection(SubSection section) {
    this.section = section;
  }


  /**
   * Get my UPC.
   * 
   * @return my upc
   */
  public String getUpc() {
    return this.upc;
  }

  /**
   * Get my upc.
   * 
   * @return my upc
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get a string for a product.
   * 
   * @return return the string of a product
   */
  @Override
  public String toString() {
    switch (status) {
      case "Received":
        return "Received " + this.getName() + " (" + upc + ") amount " + order.getQuantity()
            + " at price " + order.getPrice() + ", product remains " + quantity + " in store";
      case "Sale":
        return "Sold " + this.getName() + " (" + upc + ") amount " + sale.getQuantity()
            + " at price " + sale.getPrice() + ", product remains " + quantity + " in store";
      case "SetUp":
        return "Set up the starting state of our inventory: " + "Received " + this.getName() + " ("
            + upc + ") amount " + order.getQuantity() + " at price " + order.getPrice()
            + ", product remains " + quantity + " in store";
      case "Reorder":
        return "Reordered " + name;
      case "Order":
        return "Order new product " + this.getName() + " (" + upc + ") amount "
            + order.getQuantity();
      default:
        return "Wrong status!";
    }
  }

  /**
   * Get my quantity.
   * 
   * @return my quantity
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Get my location.
   * 
   * @return my aisle
   */
  public String getLocation() {
    return aisle;
  }

  /**
   * set my aisle as the given aisle.
   * 
   * @param aisle a given aisle
   */
  public void setAisle(String aisle) {
    this.aisle = aisle;
  }

  /**
   * Add the given amount to my quantity.
   * 
   * @param amount the quantity needed to add to my quantity
   */
  public void addQuantity(int amount) {
    this.quantity += amount;
  }

  /**
   * set my status as the given status.
   * 
   * @param status a given status
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * Get my order.
   * 
   * @return my order
   */
  public Order getOrder() {
    return order;
  }

  /**
   * Get my sale.
   * 
   * @return my sale
   */
  public Sale getSale() {
    return sale;
  }

  /**
   * Set the Sale.
   * 
   * @param newSale the new order.
   */
  public void setSale(Sale newSale) {
    sale = newSale;
  }

  /**
   * set my quantity as the given quantity.
   * 
   * @param quantity a given quantity
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Get my threshold.
   * 
   * @return my threshold
   */
  public int getThreshold() {
    return threshold;
  }

  /**
   * Get my subsection
   * 
   * @return my subSection
   */
  public SubSection getSection() {
    return section;
  }

}
