import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    int parkingSlotsAvailable;
    List<Vehicle> parkingSlots = new ArrayList<>();

    ParkingLot(int totalSlots)
    {
        this.parkingSlotsAvailable = totalSlots;
    }

    public Boolean park(Vehicle vehicle){
        if(parkingSlotsAvailable >0) {
            parkingSlotsAvailable -= 1;
            parkingSlots.add(vehicle);
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

    public Boolean checkIfParkingLotIsFull(){
        if(parkingSlotsAvailable==0){
            return true;
        }
        return false;
    }
}
