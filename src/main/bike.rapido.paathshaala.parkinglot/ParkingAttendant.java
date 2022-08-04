import java.sql.SQLOutput;
import java.util.*;

public class ParkingAttendant {
    List<ParkingLot> parkingLots = new ArrayList<>();
    List<Vehicle> parkedCars = new ArrayList<>();
    ParkingLotsCompare parkingLotsCompare = new ParkingLotsCompare();
    ParkingAttendant(List<ParkingLot> parkingLots)
    {
        this.parkingLots = parkingLots;
    }
    public ParkingLot park(Vehicle vehicle) {
        if(!parkedCars.contains(vehicle)) {
            parkedCars.add(vehicle);
            Collections.sort(parkingLots, parkingLotsCompare);

            for (ParkingLot parkingLot : parkingLots) {
                if (!parkingLot.checkIfParkingLotIsFull()) {
                    parkingLot.allocateParkingSlot(vehicle);
                    return parkingLot;
                }
            }
        }
        return null;
    }

    public ParkingLot unpark(Vehicle vehicle) {
        if(parkedCars.contains(vehicle)) {
            parkedCars.remove(vehicle);
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot.parkingSlots.contains(vehicle)) {
                    parkingLot.deallocateParkingSlot(vehicle);
                    return parkingLot;
                }
            }
        }
        return null;
    }
}
class ParkingLotsCompare implements Comparator<ParkingLot>
{
    public int compare(ParkingLot parkingLot1, ParkingLot parkingLot2)
    {
        if (parkingLot1.getParkingSlotsAvailable() < parkingLot2.getParkingSlotsAvailable()) return 1;
        if (parkingLot1.getParkingSlotsAvailable() > parkingLot2.getParkingSlotsAvailable()) return -1;
        else return 0;
    }
}
