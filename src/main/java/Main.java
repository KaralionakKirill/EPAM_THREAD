import com.epam.multitreading.entity.Car;
import com.epam.multitreading.entity.Ferryboat;
import com.epam.multitreading.factory.CarFactory;
import com.epam.multitreading.parser.CarParser;
import com.epam.multitreading.reader.DataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        List<Car> cars = carsFromFile();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Ferryboat ferry = Ferryboat.getInstance();
        ferry.setSquare(30);
        ferry.setCarryingCapacity(50);
        for (Car car : cars) {
            executorService.submit(car);
        }
    }

    private static List<Car> carsFromFile() {
        DataReader dataReader = DataReader.getInstance();
        List<String> data = dataReader.readLines("data.txt");
        CarParser parser = CarParser.getInstance();
        CarFactory factory = CarFactory.getInstance();
        List<Car> cars = new ArrayList<>();
        for (String line : data) {
            List<Integer> parseLine = parser.parse(line);
            Car car = factory.createVehicle(parseLine.get(0), parseLine.get(1));
            cars.add(car);
        }
        return cars;
    }
}
