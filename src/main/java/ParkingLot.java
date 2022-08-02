import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    int parkingSlotsAvailable;
    List<Vehicle> parkingSlots = new ArrayList<>();

    private List<Observer> observers = new ArrayList<Observer>();

    ParkingLot(int totalSlots)
    {
        this.parkingSlotsAvailable = totalSlots;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public Boolean park(Vehicle vehicle){
        if(parkingSlotsAvailable >0) {
            parkingSlotsAvailable -= 1;
            parkingSlots.add(vehicle);
            if(parkingSlotsAvailable==0){
                notifyAllObservers();
            }
            return true;
        }

        return false;
    }

    public Boolean unPark(Vehicle vehicle){
        parkingSlotsAvailable += 1;
        if(parkingSlots.contains(vehicle)){
            parkingSlots.remove(vehicle);
            return true;
        }
        return false;
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.notifyParkingLotIsFull();
        }
    }

    public Boolean checkIfParkingLotIsFull(){
        if(parkingSlotsAvailable==0){
            notifyAllObservers();
            return true;
        }
        return false;
    }

//    public static void main(String[] args)
//    {
//        ParkingLot parkingLot = new ParkingLot(1);
//        Vehicle myCar = new Vehicle();
//        Owner owner = new Owner(parkingLot);
//        SecurityPersonnal securityPersonnal = new SecurityPersonnal(parkingLot);
//        parkingLot.park(myCar);
//    }
}
