import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @Test
    void shouldParkTheCarWhenSlotIsAvailable() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);

        Boolean isParked = parkingLot.allocateParkingSlot(new Vehicle());

        assertTrue(isParked);
    }

    @Test
    void shouldNotParkTheCarWhenSlotIsUnavailable() {
        int availableSlots = 0;
        parkingLot = new ParkingLot(availableSlots);

        Boolean isParked = parkingLot.allocateParkingSlot(new Vehicle());

        assertFalse(isParked);
    }

    @Test
    void shouldAllowToUnParkTheCarIfItIsAlreadyParked() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();
        parkingLot.allocateParkingSlot(myCar);

        Boolean isUnParked = parkingLot.deallocateParkingSlot(myCar);

        assertTrue(isUnParked);
    }

    @Test
    void shouldNotAllowToUnParkTheCarBeforeParkingIt() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);
        Vehicle myCar = new Vehicle();

        Boolean isUnParked = parkingLot.deallocateParkingSlot(myCar);

        assertFalse(isUnParked);
    }

    @Test
    void shouldReturnTrueIfParkingLotIsFull() {
        int availableSlots = 2;
        parkingLot = new ParkingLot(availableSlots);

        parkingLot.allocateParkingSlot(new Vehicle());
        parkingLot.allocateParkingSlot(new Vehicle());

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertTrue(isParkingLotFull);
    }

    @Test
    void shouldReturnFalseIfParkingLotIsNotFull() {
        int availableSlots = 3;
        parkingLot = new ParkingLot(availableSlots);

        parkingLot.allocateParkingSlot(new Vehicle());
        parkingLot.allocateParkingSlot(new Vehicle());

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertFalse(isParkingLotFull);
    }

    @Test
    void shouldNotifyOwnerWhenParkingLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(2);
        Owner ownerSpy = Mockito.spy(new Owner());
        ownerSpy.setParkingLot(parkingLot);
        ownerSpy.subscribeToParkingLotSpace();
        boolean isParked = parkingLot.allocateParkingSlot(new Vehicle());
        parkingLot.allocateParkingSlot(new Vehicle());

        assertTrue(isParked);
        System.out.println("before verify");
        verify(ownerSpy,times(1)).notifyParkingLotIsFull();
    }

    @Test
    void shouldNotNotifyOwnerWhenParkingLotIsNotFull() {
        ParkingLot parkingLot = new ParkingLot(2);
        Owner ownerSpy = Mockito.spy(new Owner());
        ownerSpy.setParkingLot(parkingLot);
        ownerSpy.subscribeToParkingLotSpace();
        boolean isParked = parkingLot.allocateParkingSlot(new Vehicle());

        assertTrue(isParked);
        verify(ownerSpy,times(0)).notifyParkingLotIsFull();
    }

    @Test
    void shouldNotifyOwnerWhenSlotsAreBackAvailable() {
        ParkingLot parkingLot = new ParkingLot(2);
        Owner ownerSpy = Mockito.spy(new Owner());
        ownerSpy.setParkingLot(parkingLot);
        ownerSpy.subscribeToParkingLotSpace();
        Vehicle vehicle1 = new Vehicle();
        boolean isParked = parkingLot.allocateParkingSlot(vehicle1);
        Vehicle vehicle2 = new Vehicle();
        boolean isSecondParked = parkingLot.allocateParkingSlot(vehicle2);
        boolean isSecondUnparked = parkingLot.deallocateParkingSlot(vehicle1);

        assertTrue(isParked);
        assertTrue(isSecondParked);
        assertTrue(isSecondUnparked);
        verify(ownerSpy,times(1)).notifyParkingLotIsFull();
        verify(ownerSpy,times(1)).notifyParkingLotIsBackAvailable();
    }

    @Test
    void shouldNotifySecurityPersonalWhenLotIsFull() {
        ParkingLot parkingLot = new ParkingLot(1);
        Owner owner = new Owner();
        owner.setParkingLot(parkingLot);
        SecurityPersonnel securityPersonnelSpy = Mockito.spy(owner.associateToASecurityPersonnel());
        securityPersonnelSpy.subscribeToParkingLotSpace();

        Vehicle vehicle = new Vehicle();
        boolean isParked = parkingLot.allocateParkingSlot(vehicle);

        assertTrue(isParked);
        verify(securityPersonnelSpy,times(1)).notifyParkingLotIsFull();

    }

    @Test
    void shouldNotNotifySecurityPersonalWhenLotIsFree() {
        ParkingLot parkingLot = new ParkingLot(2);
        Owner owner = new Owner();
        owner.setParkingLot(parkingLot);
        SecurityPersonnel securityPersonnelSpy = Mockito.spy(owner.associateToASecurityPersonnel());
        securityPersonnelSpy.subscribeToParkingLotSpace();

        Vehicle vehicle = new Vehicle();
        boolean isParked = parkingLot.allocateParkingSlot(vehicle);

        assertTrue(isParked);
        verify(securityPersonnelSpy,times(0)).notifyParkingLotIsFull();
    }


}

