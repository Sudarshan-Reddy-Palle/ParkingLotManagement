import java.util.ArrayList;
import java.util.List;

public class ParkingAttendant {
    List<ParkingLot> parkingLots = new ArrayList<>();
    ParkingAttendant(List<ParkingLot> parkingLots)
    {
        this.parkingLots = parkingLots;
    }
    public boolean park(Vehicle vehicle) {
        for(ParkingLot parkingLot:parkingLots)
        {
            if(!parkingLot.checkIfParkingLotIsFull()) {
                parkingLot.allocateParkingSlot(vehicle);
                return true;
            }
        }
        return false;
    }

    public boolean unpark(Vehicle vehicle) {
        for(ParkingLot parkingLot:parkingLots)
        {
            if(parkingLot.parkingSlots.contains(vehicle)) {
                return parkingLot.deallocateParkingSlot(vehicle);
//                return true;
            }
        }
        return false;
    }
}
