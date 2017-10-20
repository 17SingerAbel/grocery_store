package storeGui;

import java.util.Observable;

/**
 * A class used for check if an input is valid or not.
 */
public class SmallGui extends Observable {
  /**
   * Check if the given upc is valid
   * 
   * @param upc a given upc
   * @return true if valid, otherwise false.
   */
  public boolean checkUpc(String upc) {
    return (!upc.equals(null)) && upc.length() == 12 && upc.matches("[1234567890]*");
  }

  /**
   * Check if the given input is not empty
   * 
   * @param lines a given input
   * @return true is not empty, otherwise false
   */
  public boolean checkEmpty(String lines) {
    // Check if it is space
    return !lines.equals("");
  }

  /**
   * Check if the given date range is valid
   * 
   * @param date1 a given start date
   * @param date2 a given end date
   * @return true if the date range is valid, otherwise false
   */
  public boolean checkDates(String date1, String date2) {
    return date1.length() == 8 && date1.matches("[0-9]*") && date2.length() == 8
        && date2.matches("[0-9]*") && Double.valueOf(date1) <= Double.valueOf(date2);
  }


}
