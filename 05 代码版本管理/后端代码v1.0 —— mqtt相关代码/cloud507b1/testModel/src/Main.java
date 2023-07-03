import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static boolean flag = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("Thread 1 is waiting for work to be done...");
                lock.lock();
                while (!flag) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Thread 1 starts working...");
                // 执行任务1
                System.out.println("Task 1 is completed");
            } finally {
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(3000);  // 模拟任务执行时间
                System.out.println("Thread 2 has completed the work");
                lock.lock();
                flag = true;
                condition.signal();  // 发送条件满足的信号
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}

class Work {
    public void doWork() {
        System.out.println("Work is done");
    }
}
