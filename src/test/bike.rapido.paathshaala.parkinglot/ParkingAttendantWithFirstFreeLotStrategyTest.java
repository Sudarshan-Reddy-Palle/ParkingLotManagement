import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingAttendantWithFirstFreeLotStrategyTest {
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
    }

    @Test
    void shouldParkCarsInSlotOneWithUntilLotFullStrategy() {
        int[] lotCapacities = {3,3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new FirstFreeLotParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle firstCar = new Vehicle();
        Vehicle secondCar = new Vehicle();
        Optional<ParkingLot> parkedParkingLotOfFirstCar = parkingAttendant.park(firstCar);
        Optional<ParkingLot> parkedParkingLotOfSecondCar = parkingAttendant.park(secondCar);

        assertEquals(1,parkedParkingLotOfFirstCar.get().getId());
        assertEquals(1,parkedParkingLotOfSecondCar.get().getId());

    }


}
