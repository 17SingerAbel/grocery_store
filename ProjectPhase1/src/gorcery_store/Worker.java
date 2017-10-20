package gorcery_store;

import java.io.Serializable;

/**
 * A worker in the store
 */
public class Worker implements Serializable {
  /*** A worker's number. */
  private String workingNum;
  /*** A worker's password. */
  private String password;
  /** * A worker's job. */
  private String occupation;

  /**
   * The constructor of a worker. Create a worker by given his working number, password and job.
   * 
   * @param num a worker's number
   * @param code a worker's password
   * @param job a worker's job
   */
  public Worker(String num, String code, String job) {
    workingNum = num;
    password = code;
    occupation = job;
  }

  /**
   * @param str The string.
   * Set worker password.
   */
  public void setPassword(String str) {
    this.password = str;
  }

  /**
   * Get the work number of the worker.
   *
   * @return str The working num.
   */
  public String getWorkingNum() {
    return workingNum;
  }
  /**
   * Get the password of the worker.
   *
   * @return str The password.
   */
  public String getPassword() {
    return password;
  }
  /**
   * Get the occupation of the worker.
   *
   * @return str The occupation.
   */
  public String getOccupation() {
    return occupation;
  }

}
