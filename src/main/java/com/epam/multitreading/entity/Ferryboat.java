package com.epam.multitreading.entity;

import com.epam.multitreading.state.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Ferryboat {
    private static final Logger logger = LogManager.getLogger(Ferryboat.class);
    private static Ferryboat instance;
    private static final ReentrantLock lock = new ReentrantLock(true);
    private static final AtomicBoolean instanceExist = new AtomicBoolean(false);
    private final Condition condition = lock.newCondition();
    private State state;
    private int carryingCapacity;
    private int square;

    private final List<Car> cars = new ArrayList<>();

    private Ferryboat() {
    }

    public static Ferryboat getInstance(){
        if (instanceExist.compareAndSet(false, true)) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new Ferryboat();
                    instanceExist.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public void transfer(Car car){
        addCar(car);
        sailing(car);
        discharging(car);
    }

    private void addCar(Car car) {
        try {
            lock.lock();
            while (state == State.CHARGED) {
                logger.info("{}: all seats are occupied, waiting", Thread.currentThread().getName());
                condition.await();
            }
            if (!checkFreePlaceForCar(car)) {
                logger.info("Ready to go!");
                state = State.CHARGED;
                condition.signalAll();
                condition.await();
            }
            cars.add(car);
            logger.info("{}: boarded the ferry waiting for start.", Thread.currentThread().getName());
            condition.await(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private void discharging(Car car) {
        lock.lock();
        try {
            cars.remove(car);
            logger.info("{} : discharged!", Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(1000);
            if (cars.isEmpty()) {
                state = State.EMPTY;
                condition.signalAll();
            }
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    private void sailing(Car car){
        logger.info("{} : cross the river!", Thread.currentThread().getName());
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException: {}", e.getMessage());
        }
    }

    private boolean checkFreePlaceForCar(Car car) {
        int takenSize = getTakenSize() + car.getSize();
        int takenCapacity = getTakenCapacity() + car.getWeight();
        boolean result = (square >= takenSize && carryingCapacity >= takenCapacity);
        logger.info("{} : ReadyToGo: {}", Thread.currentThread().getName(), result);
        return result;
    }

    private int getTakenSize() {
        return cars.stream()
                .mapToInt(Car::getSize)
                .sum();
    }

    private int getTakenCapacity() {
        return cars.stream()
                .mapToInt(Car::getWeight)
                .sum();
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
