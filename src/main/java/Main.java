import com.epam.multitreading.entity.Autotruck;
import com.epam.multitreading.entity.Ferryboat;
import com.epam.multitreading.entity.PassengerCar;

public class Main {
    public static void main(String[] args) {
        PassengerCar passengerCar = new PassengerCar(2, 5);
        Autotruck autotruck = new Autotruck(5, 15);
        Ferryboat ferryboat = Ferryboat.getInstance();
    }
}
