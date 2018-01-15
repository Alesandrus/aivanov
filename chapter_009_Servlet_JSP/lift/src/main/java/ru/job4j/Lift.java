package ru.job4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Лифт.
 */
public class Lift {
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
     * Флаг использования лифта. True если в данный момент времени лифт кем-то используется.
     */
    private final AtomicBoolean useLift = new AtomicBoolean(false);

    /**
     * Флаг, нажатия кнопки внутри лифта. True если кнопка внутри лифта нажата.
     */
    private final AtomicBoolean useButtonInsideLift = new AtomicBoolean(false);

    /**
     * Флаг вызова лифта снаружи. True если лифт вызвали.
     */
    private final AtomicBoolean useOutSide = new AtomicBoolean(false);

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
     * Установить массив этажей, по которым будеткурсировать лифт.
     *
     * @param floors этажи.
     */
    public void setFloors(Floor[] floors) {
        this.floors = floors;
        currentLevel.set(1);
    }

    /**
     * Оператор, котрый в отдельном потоке управляет движением лифта по этажам, учитывается,
     * то что у этажей возможна разная высота.
     */
    private class LiftMover implements Runnable {
        /**
         * Выбор и запуск метода движения в зависимости от положения лифта.
         */
        @Override
        public void run() {
            if (destinationLevel.get() > currentLevel.get()) {
                moveUp();
            } else if (destinationLevel.get() < currentLevel.get()) {
                moveDown();
            }
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
    }

    /**
     * Вызов лифта снаружи. Если лифт не используется, то вызывается метод move() и лифт приезжает.
     *
     * @param destination этаж, на котром совершается вызов лифта.
     */
    public void call(int destination) {
        if (useLift.compareAndSet(false, true)) {
            useOutSide.set(true);
            destinationLevel.set(destination);
            move(destination);
        } else {
            LOGGER.info("Лифт в данный момент используется, подождите");
        }
    }

    /**
     * Закрытие дверей производится в отдельном потоке. Поток закрытия ждет когда завершится фаза
     * открытия дверей.
     * После того как двери закрываются пауза для ожидания нажатия кнопки внутри лифта, если кто-то в него зашел.
     * Если после прошествия определеннго времени (здесь 2 секунды) кнопка внутри лифта не нажата,
     * то флаг использования лифта сбрасывется.
     *
     * @param opener поток
     */
    private void closeDoor(Thread opener) {
        Thread closer = new Thread(() -> {
            try {
                opener.join();
                Thread.sleep((long) (1000 * closeTime));
                LOGGER.info("Двери закрываются");
                useOutSide.compareAndSet(true, false);

                long actionWaiter = 1000L * 2;
                Thread.sleep(actionWaiter);
                if (!useButtonInsideLift.get()) {
                    useLift.set(false);
                }
            } catch (InterruptedException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
        closer.start();
    }

    /**
     * Приехать на этаж. Вызывается внутри лифта, если никто не вызвал лифт снаружи. Такое возможно если пользователь,
     * зашел в лифт не нажал на кнопку в течении 2 с.
     *
     * @param destination этаж назначения.
     */
    public void goToLevel(int destination) {
        if (!useOutSide.get()) {
            useButtonInsideLift.set(true);
            useLift.compareAndSet(false, true);
            destinationLevel.set(destination);
            move(destination);
            useButtonInsideLift.set(false);
        } else {
            LOGGER.info("Пока вы думали на какую кнопку нажимать, лифт кто-то вызвал!");
        }
    }

    /**
     * Движение лифта к этажу назначения.
     *
     * @param destination этаж назначения.
     */
    private void move(int destination) {
        if (currentLevel.get() != destination) {
            Thread caller = new Thread(new LiftMover());
            caller.start();

            Thread opener = new Thread(() -> {
                try {
                    caller.join();
                    LOGGER.info("Двери открываются");
                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            });
            opener.start();
            closeDoor(opener);
        } else {
            Thread opener = new Thread(() -> LOGGER.info("Двери открываются"));
            opener.start();
            closeDoor(opener);
        }
    }
}
