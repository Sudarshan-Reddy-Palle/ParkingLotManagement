import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int id;
    public int getId() {
        return id;
    }
    public int getParkingSlotsAvailable() {
        return parkingSlotsAvailable;
    }

    int parkingSlotsAvailable;
    List<Vehicle> parkingSlots = new ArrayList<>();
    private List<ParkingLotObserver> parkingLotObserversList = new ArrayList<ParkingLotObserver>();

    ParkingLot(int totalSlots)
    {
        this.parkingSlotsAvailable = totalSlots;
    }
    ParkingLot(int totalSlots,int id)
    {
        this.parkingSlotsAvailable = totalSlots;
        this.id=id;
    }
    public void subscribeToParkingLotObserversList(ParkingLotObserver parkingLotObserver){
        parkingLotObserversList.add(parkingLotObserver);
    }
    public Boolean allocateParkingSlot(Vehicle vehicle){
        if(parkingSlotsAvailable >0) {
            parkingSlotsAvailable -= 1;
            parkingSlots.add(vehicle);
            if(parkingSlotsAvailable==0){
                notifyAllObserversLotIsFull();
            }
            return true;
        }
        return false;
    }

    public Boolean deallocateParkingSlot(Vehicle vehicle){
        if(parkingSlots.contains(vehicle)) {
            if (parkingSlotsAvailable == 0) {
                notifyAllObserversSlotsAreBackAvailable();
            }
            parkingSlotsAvailable += 1;
            parkingSlots.remove(vehicle);
            return true;
        }
        return false;
    }

    private void notifyAllObserversSlotsAreBackAvailable() {
        for (ParkingLotObserver parkingLotObserver : parkingLotObserversList) {
            parkingLotObserver.notifyParkingLotIsBackAvailable();
        }
    }

    public void notifyAllObserversLotIsFull(){
        for (ParkingLotObserver parkingLotObserver : parkingLotObserversList) {
            parkingLotObserver.notifyParkingLotIsFull();
        }
    }

    public boolean checkIfParkingLotIsFull() {
        if(parkingSlotsAvailable==0)
            return true;
        return false;
    }
}
