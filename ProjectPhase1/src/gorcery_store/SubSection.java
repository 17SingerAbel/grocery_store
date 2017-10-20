package gorcery_store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A subsection has a upc and lots of products.
 */
public class SubSection implements Serializable {
  /** The upc of the subsection. */
  private String name;
  /** The container has lots of different products. key is upc */
  private HashMap<String, Product> container = new HashMap();
  /*** The parent section of my subsection */
  private Section parent;

  /**
   * A subsection with upc and parent section.
   */
  public SubSection(String name) {
    this.name = name;
  }

  /**
   * Return the upc of the section.
   * 
   * @return the section upc/
   */
  public String getName() {
    return this.name;
  }

  /**
   * Add the given product to the container.
   * 
   * @param product the given product
   */
  public void addProduct(Product product) {
    this.container.put(product.getUpc(), product);
  }

  /**
   * Set my parent to the given parent.
   * 
   * @param parent a given parent
   */
  public void setParent(Section parent) {
    this.parent = parent;
  }

  /**
   * Get my parent.
   * 
   * @return my parent
   */
  public Section getParent() {
    return this.parent;
  }
}
