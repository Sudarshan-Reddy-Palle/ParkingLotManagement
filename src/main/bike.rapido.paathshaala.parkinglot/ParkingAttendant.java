import java.util.*;

public class ParkingAttendant {
    List<ParkingLot> parkingLots;
    List<Vehicle> parkedCars;
    ParkingStrategy parkingStrategy;
    ParkingAttendant(List<ParkingLot> parkingLots)
    {
        this.parkingLots = parkingLots;
        this.parkedCars = new ArrayList<>();
    }

    public ParkingStrategy getParkingStrategy() {
        return parkingStrategy;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public Optional<ParkingLot> park(Vehicle vehicle) {
        if(!parkedCars.contains(vehicle)) {
            parkedCars.add(vehicle);
            ParkingLot parkingLot = parkingStrategy.getParkingLot();
            if(parkingLot != null)
            {
                parkingLot.allocateParkingSlot(vehicle);
                return Optional.of(parkingLot);
            }
        }
        return Optional.empty();
    }


    public Optional<ParkingLot> unpark(Vehicle vehicle) {
        if(parkedCars.contains(vehicle)) {
            parkedCars.remove(vehicle);
            for (ParkingLot parkingLot : parkingLots) {
                if (parkingLot.parkingSlots.contains(vehicle)) {
                    parkingLot.deallocateParkingSlot(vehicle);
                    return Optional.of(parkingLot);
                }
            }
        }
        return Optional.empty();
    }
}

