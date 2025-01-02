
public class Threading {

    public static class StoppableRunnable implements Runnable {
        private boolean stopRunnable = false;

        public synchronized void requestStop() {
            this.stopRunnable = true;
        }

        public synchronized boolean isStopRequested() {
            return this.stopRunnable;
        }

        private void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override 
        public void run() {
            System.out.println("StoppableRannable Running...");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println(".....");
            }
            System.out.println("StoppableRunnable Stopped...");
        }
    }
    public static void main(String[] args) throws Exception{
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                // get the current thread
                Thread currentThread = Thread.currentThread();

                // Print the details of the current thread
                System.out.println("Currently running thread: " + currentThread);
                System.out.println("Thread name: " + currentThread.getName());
                // System.out.println("Thread ID: " + currentThread.getId());
                System.out.println("Thread state: " + currentThread.getState());
                System.out.println("Thread priority: " + currentThread.getPriority());

                System.out.println("Thread is running...");
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread 2 is Running....");
                try{
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2 Finished....");
            }
        };
        
        // Thread thread = new Thread(runnable, "My Presonal Thread name");
        // Thread thread2 = new Thread(runnable2, "My Presonal Thread name 2");
        // thread.start();
        // thread2.start();
        // StoppableRunnable str = new StoppableRunnable();
        // Thread thread = new Thread(str, "Thread_name");
        // thread.start();
        // try {
        //     Thread.sleep(5000);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        // System.out.println("Requesting Stop");
        // str.requestStop();
        // System.out.println("Stop Request");
        Runnable runnable3 = () -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("User Thread Running");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Thread userT = new Thread(runnable3);
        userT.setDaemon(true); // make it false , to run non-demon thread , keeping jvm thread alive
        // the JVM checks for any remaining non-daemon threads and main thread. Since there are none, the JVM exits, and the daemonThread is stopped abruptly. 
        userT.start();
        Thread.sleep(3100);
        System.out.println("Main thread ends");
    }
}
