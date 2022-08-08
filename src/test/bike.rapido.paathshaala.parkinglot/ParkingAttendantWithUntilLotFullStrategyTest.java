import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingAttendantWithUntilLotFullStrategyTest {
    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = new Owner();
    }

    @Test
    void shouldParkCarsInSlotOneWithUntilLotFullStrategy() {
        int[] lotCapacities = {2,2};
        owner.createParkingLots(lotCapacities);
        ParkingAttendant parkingAttendant = owner.assignParkingStrategyToParkingAttendant("UntilLotFull");
        Vehicle firstCar = new Vehicle();
        Vehicle secondCar = new Vehicle();
        ParkingLot parkedParkingLotOfFirstCar = parkingAttendant.park(firstCar);
        ParkingLot parkedParkingLotOfSecondCar = parkingAttendant.park(secondCar);

        assertEquals(1,parkedParkingLotOfFirstCar.getId());
        assertEquals(1,parkedParkingLotOfSecondCar.getId());

    }


}
