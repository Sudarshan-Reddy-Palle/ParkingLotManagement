import java.util.*;

public class ParkingAttendant {
    List<ParkingLot> parkingLots;
    List<Vehicle> parkedCars;
    String parkingStrategy;
    ParkingAttendant(List<ParkingLot> parkingLots, String parkingStrategy)
    {
        this.parkingLots = parkingLots;
        this.parkedCars = new ArrayList<>();
        this.parkingStrategy = parkingStrategy;
    }
    public ParkingLot park(Vehicle vehicle) {
        if(!parkedCars.contains(vehicle)) {
            parkedCars.add(vehicle);
            ParkingLot parkingLot = null;
            if(Objects.equals(parkingStrategy, "Evenly")){
                parkingLot = parkVehicleEvenlyInParkingLots(vehicle);
            }
            else if(Objects.equals(parkingStrategy, "UntilLotFull")){
                parkingLot = parkVehicleUntilLotFullInParkingLots(vehicle);
            }
            return parkingLot;
        }
        return null;
    }

    private ParkingLot parkVehicleUntilLotFullInParkingLots(Vehicle vehicle) {
        for(ParkingLot parkingLot : parkingLots){
            if(parkingLot.getParkingSlotsAvailable()>0){
                parkingLot.allocateParkingSlot(vehicle);
                return parkingLot;
            }
        }
        return null;
    }

    private ParkingLot parkVehicleEvenlyInParkingLots(Vehicle vehicle)
    {
        ParkingLot parkingLot = identifyLotWithMaximumAvailableSlots();
        if(parkingLot != null)
        {
            parkingLot.allocateParkingSlot(vehicle);
            return parkingLot;
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

