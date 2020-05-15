package TowerDefenseVisual;

import java.util.concurrent.atomic.AtomicBoolean;


/*THIS CLASS IS HERE TO HELP SAFELY MANAGE SUB AND MULTI THREADING*/


public abstract class ControlSubThread implements Runnable {
    private Thread worker;

    /*INTERVAL FOR WHICH THE THREAD MUST SLEEP BEFORE RUNNING*/

    protected int interval;

    /*ATOMIC BOOLEAN CAN BE ACCESSED ONLY FROM ONE SPECIFIC THREAD AND ALLOWS TO SAFELY MANAGE MULTI THREADING */

    protected final AtomicBoolean running = new AtomicBoolean(false);

    public ControlSubThread(int sleepInterval) {
        interval = sleepInterval;
    }

    /*STARTING THE THREAD*/

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    /*INTERRUPTING THE THREAD WHEN WE ARE DONE*/

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    /*CHECKING IF THE THREAD IS RUNNING*/

    boolean isRunning() {
        return running.get();
    }

}