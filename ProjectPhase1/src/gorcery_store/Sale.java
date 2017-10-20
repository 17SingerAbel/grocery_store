package gorcery_store;

/**
 * The class for selling a product.
 */
public class Sale extends ProductEvent {
  /** The discount of the product. */
  private double discount = 1.0;
  /** My startDate for discounting. */
  private String startDate;
  /** My endDate for discounting. */
  private String endDate;

  /**
   * Initialize me.
   * 
   * @param p the price for selling
   * @param date the sale date
   */
  public Sale(double price, int date) {
    super(price, date);
  }

  /**
   * Initialize me.
   * 
   * @param price the price
   * @param date the local date
   * @param quantity the quantity
   * @param name the upc
   * @param sDate the starting date
   * @param eDate the end date
   * @param discount discount
   */
  public Sale(double price, int date, int quantity, String name, String sDate, String eDate,
      double discount) {
    super(price, date);
    setQuantity(quantity);
    setUpc(name);
    setStartDate(sDate);
    setEndDate(eDate);
    setDiscount(discount);
  }

  /**
   * Get my sellPrice after discounting.
   * 
   * @return my sellPrice after discounting
   */
  @Override
  public double getPrice() {
    return discount * price;
  }


  /**
   * Get my discount.
   * 
   * @return my discount
   */
  public double getDiscount() {
    return this.discount;
  }

  /**
   * set my discount as the given discount if within the period of the discount. Otherwise set my
   * discount as 1.
   * 
   * @param discount a given discount
   */
  public void setDiscount(double discount) {
    if (endDate == null || startDate == null) {
      return;
    }
    if (Integer.valueOf(endDate) >= date && date >= Integer.valueOf(startDate)) {
      this.discount = discount;
    } else {
      this.discount = 1.0;
    }
  }

  /**
   * set my startDate as the given date.
   * 
   * @param date a given startDate
   */
  public void setStartDate(String date) {
    startDate = date;
  }

  /**
   * Get my startDate.
   * 
   * @return my startDate
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * set my endDate as the given date.
   * 
   * @param date a given endDate
   */
  public void setEndDate(String date) {
    endDate = date;
  }

  /**
   * Get my endDate.
   * 
   * @return my endDate
   */
  public String getEndDate() {
    return endDate;
  }
}
