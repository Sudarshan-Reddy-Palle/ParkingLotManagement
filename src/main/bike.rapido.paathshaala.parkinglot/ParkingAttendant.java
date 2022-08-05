import java.util.*;

public class ParkingAttendant {
    List<ParkingLot> parkingLots;
    List<Vehicle> parkedCars;
    ParkingAttendant(List<ParkingLot> parkingLots)
    {
        this.parkingLots = parkingLots;
        this.parkedCars = new ArrayList<>();
    }
    public ParkingLot park(Vehicle vehicle) {
        if(!parkedCars.contains(vehicle)) {
            parkedCars.add(vehicle);
            ParkingLot parkingLot = identifyLotWithMaximumAvailableSlots();
            if(parkingLot != null)
            {
                parkingLot.allocateParkingSlot(vehicle);
                return parkingLot;
            }

        }
        return null;
    }

    private ParkingLot identifyLotWithMaximumAvailableSlots() {
        ParkingLot maxAvailableSlotedLot = null;
        int maxAvailableSlotSize = 0;
        for (ParkingLot parkingLot:parkingLots)
        {
            if(maxAvailableSlotSize<parkingLot.getParkingSlotsAvailable())
            {
                maxAvailableSlotSize = parkingLot.getParkingSlotsAvailable();
                maxAvailableSlotedLot = parkingLot;
            }
        }
        return maxAvailableSlotedLot;
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

