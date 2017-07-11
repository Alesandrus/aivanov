package ru.job4j.monitore_synchronizy;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Created by Ivanov_ab on 11.07.2017.
 */
@ThreadSafe
public class Count {

    @GuardedBy("this")
    private int count;

    public synchronized int increment() {
        return ++count;
    }


//Delete this -------------------------------------------------------->
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();
        Actor actor1 = new Actor(count);
        Actor actor2 = new Actor(count);
        Actor actor3 = new Actor(count);

        Thread t1 = new Thread(actor1);
        Thread t2 = new Thread(actor2);
        Thread t3 = new Thread(actor3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        //System.out.println(count.count);
    }
}

class Actor implements Runnable {
    Count count;
    Actor(Count count) {
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            count.increment();
        }
    }
}