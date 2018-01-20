package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Лифт.
 */
public class Lift implements Runnable {
    /**
     * Логгер.
     */
    private static final Logger LOGGER = LogManager.getLogger(Logger.class.getName());

    /**
     * Этажи, по которым курсирует лифт.
     */
    private Floor[] floors;

    /**
     * Скорость лифта м/с.
     */
    private double speedMPS;

    /**
     * Время между открытием и закрытием дверей.
     */
    private double closeTime;

    /**
     * Номер этажа, на которым в данный момент находится лифт.
     */
    private final AtomicInteger currentLevel = new AtomicInteger();

    /**
     * Номер этажа, к которому необходимо приехать.
     */
    private final AtomicInteger destinationLevel = new AtomicInteger();

    /**
     * Флаг использования лифта. True если была нажата кнопка внутри лифта.
     */
    private final AtomicBoolean useLift = new AtomicBoolean(false);

    /**
     * Приоритетная очередь нажатий кнопок внутри лифта. В приоритете нижние этажи.
     */
    private final Queue<Integer> buttonQueue = new PriorityBlockingQueue<>();

    /**
     * Очередь нажатий кнопок из подъезда.
     */
    private final Queue<Integer> levelQueue = new LinkedBlockingDeque<>();

    /**
     * Флаг остановки лифта. True - остановка.
     */
    private final AtomicBoolean stopLift = new AtomicBoolean(false);

    /**
     * Конструктор лифта.
     *
     * @param speedMPS  скорость лифта.
     * @param closeTime время между открытием и закрытием дверей.
     */
    public Lift(double speedMPS, double closeTime) {
        this.speedMPS = speedMPS;
        this.closeTime = closeTime;
    }

    /**
     * Установить массив этажей, по которым будет курсировать лифт.
     *
     * @param floors этажи.
     */
    public void setFloors(Floor[] floors) {
        this.floors = floors;
        currentLevel.set(1);
    }

    /**
     * Метод для запуск лифта в отдельном потоке.
     * Лифт работает пока не установлен флаг остановки лифта в True. Если флаг использования лифта изнутри установлен
     * в True, то лифт курсирует по всем этажам установленным в приоритетной очереди вызовов, совершенных внутри лифта.
     * Как только эта очередь окажется пустой, выставится флаг исаользования внутри лифта в False и лифт будет
     * просматривать очередь вызовов совершенных снаружи лифта.
     */
    @Override
    public void run() {
        while (!stopLift.get()) {
            if (useLift.get()) {
                while (!buttonQueue.isEmpty()) {
                    destinationLevel.set(buttonQueue.poll());
                    move();
                }
                useLift.set(false);
            } else {
                if (!levelQueue.isEmpty()) {
                    destinationLevel.set(levelQueue.poll());
                    move();
                }
            }
        }
    }

    /**
     * Выбоор направления движения с открытием и закрытием дверей.
     */
    private void move() {
        if (destinationLevel.get() > currentLevel.get()) {
            moveUp();
        } else if (destinationLevel.get() < currentLevel.get()) {
            moveDown();
        }
        openDoor();
        closeDoor();
    }

    /**
     * Движение лифта вверх к назначенному этажу.
     */
    private void moveUp() {
        while (currentLevel.get() != destinationLevel.get()) {
            long floorTime = (long) (1000 * speedMPS * floors[currentLevel.get() - 1].getHeight());
            try {
                Thread.sleep(floorTime);
                LOGGER.info("Лифт поднялся на " + currentLevel.incrementAndGet() + " этаж");
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Движение лифта вниз к назначенному этажу.
     */
    private void moveDown() {
        while (currentLevel.get() != destinationLevel.get()) {
            long floorTime = (long) (1000 * speedMPS * floors[currentLevel.get() - 1].getHeight());
            try {
                Thread.sleep(floorTime);
                LOGGER.info("Лифт спустился на " + currentLevel.decrementAndGet() + " этаж");
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Открыть двери.
     */
    private void openDoor() {
        LOGGER.info("Двери открываются");
    }

    /**
     * Закрыть двери через время CloseTime.
     */
    private void closeDoor() {
        try {
            Thread.sleep((long) (1000 * closeTime));
            LOGGER.info("Двери закрываются");
            long actionWaiter = 1000L * 2;
            Thread.sleep(actionWaiter);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    /**
     * Вызов лифта из подъезда, т.е. постановка в очередь levelQueue номера этажа при условии,
     * что такой номер в ней отсутствует.
     *
     * @param destination этаж, на котром совершается вызов лифта.
     */
    public void call(int destination) {
        if (!levelQueue.contains(destination)) {
            levelQueue.offer(destination);
        }
    }

    /**
     * Установка в приоритетную очередь номеров кнопок нажатых внутри лифта. При занесении в очередь кнопки
     * производится проверка на наличие данной кнопки в очереди.
     *
     * @param level номер кнокпки этажа.
     */
    public void goTo(int level) {
        if (!buttonQueue.contains(level)) {
            useLift.set(true);
            buttonQueue.offer(level);

        }
    }

    /**
     * Запуск лифта.
     */
    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    /**
     * Остановка лифта.
     */
    public void stop() {
        stopLift.set(true);
    }
}
