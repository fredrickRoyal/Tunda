/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vpsposclient;

import javax.print.DocPrintJob;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

/**
 *
 * @author Royal
 */
public class PrintJobWatcher {
    boolean done = false;

  PrintJobWatcher(DocPrintJob job) {
    job.addPrintJobListener(new PrintJobAdapter() {
            @Override
      public void printJobCanceled(PrintJobEvent pje) {
        allDone();
      }
            @Override
      public void printJobCompleted(PrintJobEvent pje) {
        allDone();
      }
            @Override
      public void printJobFailed(PrintJobEvent pje) {
        allDone();
      }
            @Override
      public void printJobNoMoreEvents(PrintJobEvent pje) {
        allDone();
      }
      void allDone() {
        synchronized (PrintJobWatcher.this) {
          done = true;
          System.out.println("Printing done ...");
          PrintJobWatcher.this.notify();
        }
      }
    });
  }
  public synchronized void waitForDone() {
    try {
      while (!done) {
        wait();
      }
    } catch (InterruptedException e) {
    }
  }
    
}
