
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    private ParkingLot parkingLot;


    @Test
    void shouldParkTheCarWhenSlotIsAvailable() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);

        Boolean canBeParked = parkingLot.checkIfSlotAvailable();

        assertTrue(canBeParked);
    }

    @Test
    void shouldNotParkTheCarWhenSlotIsUnavailable() {
        int availableSlots = 0;
        parkingLot = new ParkingLot(availableSlots);

        Boolean canBeParked = parkingLot.checkIfSlotAvailable();

        assertFalse(canBeParked);
    }

    @Test
    void shouldAllowToUnParkTheCarIfItIsAlreadyParked() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();

        if(parkingLot.checkIfSlotAvailable()){
            parkingLot.park(myCar);
        }

        Boolean unParkStatus = parkingLot.unPark(myCar);
        assertTrue(unParkStatus);
    }

    @Test
    void shouldNotAllowToUnParkTheCarBeforeParkingIt() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();

        Boolean unParkStatus = parkingLot.unPark(myCar);
        assertFalse(unParkStatus);
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
