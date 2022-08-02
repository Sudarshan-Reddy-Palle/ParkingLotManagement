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

    @Test
    void notifyOwnerWhenParkingLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Owner owner = new Owner();
        owner.setParkingLot(parkingLot);
        owner.subscribe();

        boolean isParked = parkingLot.park(new Vehicle());

        assertTrue(isParked);
        assertTrue(owner.checkIfParkingLotIsFull());

    }

    @Test
    void notNotifyOwnerWhenParkingLotIsFree() {
        ParkingLot parkingLot = new ParkingLot(2);
        Owner owner = new Owner();
        owner.setParkingLot(parkingLot);
        owner.subscribe();

        boolean isParked = parkingLot.park(new Vehicle());

        assertTrue(isParked);
        assertFalse(owner.checkIfParkingLotIsFull());
    }

    @Test
    void notifySecurityPersonnalWhenLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        SecurityPersonnal securityPersonnal = new SecurityPersonnal();
        securityPersonnal.setParkingLot(parkingLot);
        securityPersonnal.subscribe();

        boolean isParked = parkingLot.park(new Vehicle());

        assertTrue(isParked);
        assertTrue(securityPersonnal.checkIfParkingLotIsFull());
    }

    @Test
    void notNotifySecurityPersonnalWhenLotIsFree() {
        ParkingLot parkingLot = new ParkingLot(2);
        SecurityPersonnal securityPersonnal = new SecurityPersonnal();
        securityPersonnal.setParkingLot(parkingLot);
        securityPersonnal.subscribe();

        boolean isParked = parkingLot.park(new Vehicle());

        assertTrue(isParked);
        assertFalse(securityPersonnal.checkIfParkingLotIsFull());
    }

    @Test
    void notifyOwnerWhenSlotsAreBackAvailable() {
        ParkingLot parkingLot = new ParkingLot(1);
        Owner owner = new Owner();
        owner.setParkingLot(parkingLot);
        owner.subscribe();
        Vehicle myCar = new Vehicle();

        boolean isParked = parkingLot.park(myCar);
        boolean notifiedOwnerWhenLotIsFull = owner.checkIfParkingLotIsFull();
        boolean isUnParked = parkingLot.unPark(myCar);
        boolean notifiedOwnerWhenLotIsBackAvailable = owner.checkIfParkingLotIsAvailable();

        assertTrue(isParked);
        assertTrue(notifiedOwnerWhenLotIsFull);
        assertTrue(isUnParked);
        assertTrue(notifiedOwnerWhenLotIsBackAvailable);
    }
}
