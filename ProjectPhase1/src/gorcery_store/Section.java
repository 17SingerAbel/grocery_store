package gorcery_store;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * The section in the store. Section contains several subsection and has lots of different products.
 */
public class Section implements Serializable {
  /** The upc of the section. */
  private String name;
  /** The container has several subsection. */
  private Map<String, SubSection> container;

  /**
   * The section has a upc.
   * 
   * @param name The upc.
   */
  public Section(String name) {
    this.name = name;
    container = new HashMap<>();
  }

  /**
   * Get the container.
   * 
   * @return The container.
   */
  public Map<String, SubSection> getContainer() {
    return container;
  }

  /**
   * Return the section of the upc.
   * 
   * @return The upc of the section.
   */
  public String getName() {
    return name;
  }

}
