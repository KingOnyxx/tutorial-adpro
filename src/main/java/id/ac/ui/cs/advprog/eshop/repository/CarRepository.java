package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepository {
    static int id = 0;

    private List<Car> carData = new ArrayList<>();

    public Car create(Car car){
        if (car.getCarId() == null) {
            car.setCarId(UUID.randomUUID().toString());
        }
        carData.add(car);
        return car;
    }

    public Iterator<Car> findAll(){
        return carData.iterator();
    }

    public int findById(String carId){
        for (Car car : carData) {
            if (car.getCarId().equals(carId)) {
                return carData.indexOf(car);
            }
        }
        return 0;
    }

    public Car findCarById(String carId){
        return carData.get(findById(carId));
    }

    public Car update(String id, Car updatedCar){
        for (int i = 0; i < carData.size(); i++) {
            Car car = carData.get(i);
            if (car.getCarId().equals(id)) {
                car.setCarName(updatedCar.getCarName());
                car.setCarColor(updatedCar.getCarColor());
                car.setCarQuantity(updatedCar.getCarQuantity());
                return car;
            }       
        }
        return null;
    }

    public void delete(String id){
        carData.removeIf(car -> car.getCarId().equals(id));
    }
}
