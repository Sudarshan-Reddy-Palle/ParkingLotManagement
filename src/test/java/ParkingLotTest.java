
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ParkingLot parkingLot;


    @Test
    void shouldParkTheCarWhenSlotIsAvailable() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);

        Boolean isParked = parkingLot.park(new Vehicle());

        assertTrue(isParked);
    }

    @Test
    void shouldNotParkTheCarWhenSlotIsUnavailable() {
        int availableSlots = 0;
        parkingLot = new ParkingLot(availableSlots);

        Boolean isParked = parkingLot.park(new Vehicle());

        assertFalse(isParked);
    }

    @Test
    void shouldAllowToUnParkTheCarIfItIsAlreadyParked() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();
        parkingLot.park(myCar);

        Boolean isUnParked = parkingLot.unPark(myCar);

        assertTrue(isUnParked);
    }

    @Test
    void shouldNotAllowToUnParkTheCarBeforeParkingIt() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();

        Boolean isUnParked = parkingLot.unPark(myCar);

        assertFalse(isUnParked);
    }

    @Test
    void shouldReturnTrueIfParkingLotIsFull() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        for (int i = 0; i < 2; i++) {
            parkingLot.park(new Vehicle());
        }

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertTrue(isParkingLotFull);
    }

    @Test
    void shouldReturnFalseIfParkingLotIsNotFull() {
        int availableSlots = 3;
        parkingLot = new ParkingLot(availableSlots);
        for (int i = 0; i < 2; i++) {
            parkingLot.park(new Vehicle());
        }

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertFalse(isParkingLotFull);
    }
}
