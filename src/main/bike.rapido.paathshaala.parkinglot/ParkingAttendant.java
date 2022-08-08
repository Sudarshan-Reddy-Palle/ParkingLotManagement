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

    public ParkingLot park(Vehicle vehicle) {
        if(!parkedCars.contains(vehicle)) {
            parkedCars.add(vehicle);
            ParkingLot parkingLot = parkingStrategy.getParkingLot();
            if(parkingLot != null)
            {
                parkingLot.allocateParkingSlot(vehicle);
                return parkingLot;
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

