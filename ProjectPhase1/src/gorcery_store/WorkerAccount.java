package gorcery_store;

import java.io.Serializable;
import java.util.HashMap;

/**
 * An WorkerAccount which has a list of workers and actions to a certain worker.
 */
public class WorkerAccount implements Serializable {
  /*** A list of workers */
  public HashMap<String, Worker> workers;

  /**
   * The constructor for a WorkerAccount. Create an empty WorkerAccount.
   */
  public WorkerAccount() {
    workers = new HashMap<>();
  }

  /**
   * Check if the worker type correct information, if true return his job, if not return wrong
   * message.
   * 
   * @param acoountNum the account number of a worker
   * @param code the password of a worker
   * @return The job of the worker, wrong message otherwise
   */
  public String logIn(String acoountNum, String code) {
    if (!workers.containsKey(acoountNum)) {
      return "We didn't recognize this worker account.";
    } else {
      Worker worker = workers.get(acoountNum);
      if (!code.equals(worker.getPassword())) {
        return "Wrong password.";
      } else {
        return worker.getOccupation();
      }
    }

  }

  /**
   * Recruit the given worker
   * 
   * @param newMan a given worker
   */
  public void recruit(Worker newMan) {
    if (!workers.containsKey(newMan.getWorkingNum())) {
      workers.put(newMan.getWorkingNum(), newMan);
    } else {
      // do something
    }
  }

  /**
   * Dismiss the given worker
   * 
   * @param workerNum the worker's number
   */
  public void dismiss(String workerNum) {
    if (workers.containsKey(workerNum)) {
      workers.remove(workerNum);
    }
  }


}
