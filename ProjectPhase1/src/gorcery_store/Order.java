package gorcery_store;

/**
 * Create an order for the product.
 */
public class Order extends ProductEvent {
  public String companyName;

  /**
   * The constructor of Order. Create an order for the product.
   * 
   * @param price the price of ordering the product
   * @param date the date when ordering the product
   */
  public Order(double price, int date) {
    super(price, date);
  }
}
