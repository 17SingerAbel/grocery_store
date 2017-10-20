package gorcery_store;

/**
 * An event
 */
public class Event {
  /**
   * my type
   */
  private String type;
  /**
   * my content
   */
  private String[] content;

  /***
   * The constructor of Event. Create an event based on its type and content.
   * 
   * @param type the type of the event
   * @param content the content of the event
   */
  public Event(String type, String[] content) {
    this.type = type;
    this.content = content;
  }

  /**
   * Get my type
   * 
   * @return my type
   */
  public String getType() {
    return type;
  }

  /**
   * Get my content
   * 
   * @return my content
   */
  public String[] getContent() {
    return this.content;
  }

}
