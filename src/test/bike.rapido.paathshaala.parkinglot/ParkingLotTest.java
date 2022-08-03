import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
        for (int i = 0; i < 2; i++) {
            parkingLot.allocateParkingSlot(new Vehicle());
        }

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertTrue(isParkingLotFull);
    }

    @Test
    void shouldReturnFalseIfParkingLotIsNotFull() {
        int availableSlots = 3;
        parkingLot = new ParkingLot(availableSlots);
        for (int i = 0; i < 2; i++) {
            parkingLot.allocateParkingSlot(new Vehicle());
        }

        Boolean isParkingLotFull = parkingLot.checkIfParkingLotIsFull();

        assertFalse(isParkingLotFull);
    }

//    @Test
//    void shouldNotifyOwnerWhenParkingLotIsFull() {
//        ParkingLot parkingLot = new ParkingLot(1);
//        Owner owner = new Owner();
//        owner.setParkingLot(parkingLot);
//        owner.subscribeToAParkingLot();
//
//        boolean isParked = parkingLot.park(new Vehicle());
//
//        assertTrue(isParked);
//        assertTrue(owner.checkIfParkingLotIsFull());
//
//    }

//    @Test
//    void shouldNotNotifyOwnerWhenParkingLotIsFree() {
//        ParkingLot parkingLot = new ParkingLot(2);
//        Owner owner = new Owner();
//        owner.setParkingLot(parkingLot);
//        owner.subscribeToAParkingLot();
//
//        boolean isParked = parkingLot.park(new Vehicle());
//
//        assertTrue(isParked);
//        assertFalse(owner.checkIfParkingLotIsFull());
//    }

//    @Test
//    void notifySecurityPersonnalWhenLotIsFull() {
//        ParkingLot parkingLot = new ParkingLot(1);
//        SecurityPersonnel securityPersonnel = new SecurityPersonnel();
//        securityPersonnel.setParkingLot(parkingLot);
//        securityPersonnel.subscribeToAParkingLot();
//
//        boolean isParked = parkingLot.park(new Vehicle());
//
//        assertTrue(isParked);
//        assertTrue(securityPersonnel.checkIfParkingLotIsFull());
//    }

//    @Test
//    void notNotifySecurityPersonnalWhenLotIsFree() {
//        ParkingLot parkingLot = new ParkingLot(2);
//        SecurityPersonnel securityPersonnel = new SecurityPersonnel();
//        securityPersonnel.setParkingLot(parkingLot);
//        securityPersonnel.subscribeToAParkingLot();
//
//        boolean isParked = parkingLot.park(new Vehicle());
//
//        assertTrue(isParked);
//        assertFalse(securityPersonnel.checkIfParkingLotIsFull());
//    }

//    @Test
//    void notifyOwnerWhenSlotsAreBackAvailable() {
//        ParkingLot parkingLot = new ParkingLot(1);
//        Owner owner = new Owner();
//        owner.setParkingLot(parkingLot);
//        owner.subscribeToAParkingLot();
//        Vehicle myCar = new Vehicle();
//
//        boolean isParked = parkingLot.park(myCar);
//        boolean notifiedOwnerWhenLotIsFull = owner.checkIfParkingLotIsFull();
//        boolean isUnParked = parkingLot.unPark(myCar);
//        boolean notifiedOwnerWhenLotIsBackAvailable = owner.checkIfParkingLotIsAvailable();
//
//        assertTrue(isParked);
//        assertTrue(notifiedOwnerWhenLotIsFull);
//        assertTrue(isUnParked);
//        assertTrue(notifiedOwnerWhenLotIsBackAvailable);
//    }

//    @Test
//    void shouldParkTheCarInSecondLotWhenFirstLotIsFull() {
//        int[] lotCapacities = {0,2};
//        ParkingAttendant parkingAssistant = new ParkingAttendant(lotCapacities);
//        Vehicle car = new Vehicle();
//
//        boolean isParked = parkingAssistant.parkByParkingAttendant(car);
//
//        assertTrue(isParked);
//    }

    @Test
    void shouldParkTheCarInFirstLotWhenFirstLotIsAvailable() {
        int[] lotCapacities = {1,2};

//        ParkingAttendant parkingAttendant = new ParkingAttendant(lotCapacities);
        Owner owner = new Owner();
        owner.initializeParkingLots(lotCapacities);
        owner.subscribeToParkingLotSpace();
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();

        boolean isParked = parkingAttendant.park(car);
        boolean isSecondParked = parkingAttendant.park(car);
        boolean isThirdParked = parkingAttendant.park(car);

        assertTrue(isParked);
        assertTrue(isSecondParked);
        assertTrue(isThirdParked);
    }

    @Test
    void shouldParkTheCarInFirstLotWhenFirstLotIsAvailableWithSecurity() {
        int[] lotCapacities = {1,2};
        Owner owner = new Owner();
        SecurityPersonnel securityPersonnel = owner.associateToASecurityPersonnel();
        owner.initializeParkingLots(lotCapacities);
        owner.subscribeToParkingLotSpace();
        securityPersonnel.subscribeToParkingLotSpace();
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();

        boolean isParked = parkingAttendant.park(car);
        boolean isSecondParked = parkingAttendant.park(car);
        boolean isThirdParked = parkingAttendant.park(car);

        assertTrue(isParked);
        assertTrue(isSecondParked);
        assertTrue(isThirdParked);
    }

    @Test
    void shouldUnParkTheCarAfterItIsParked() {
        int[] lotCapacities = {1,2};
        Owner owner = new Owner();
        SecurityPersonnel securityPersonnel = owner.associateToASecurityPersonnel();
        owner.initializeParkingLots(lotCapacities);
        owner.subscribeToParkingLotSpace();
        securityPersonnel.subscribeToParkingLotSpace();
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();

        boolean isParked = parkingAttendant.park(car);
        boolean isUnParked = parkingAttendant.unpark(car);

        assertTrue(isParked);
        assertTrue(isUnParked);
    }

    @Test
    void shouldNotUnparkTheCarAfterItIsUnParked() {
        int[] lotCapacities = {1,2};
        Owner owner = new Owner();
        SecurityPersonnel securityPersonnel = owner.associateToASecurityPersonnel();
        owner.initializeParkingLots(lotCapacities);
        owner.subscribeToParkingLotSpace();
        securityPersonnel.subscribeToParkingLotSpace();
        ParkingAttendant parkingAttendant = owner.employNewParkingAttendant();
        Vehicle car = new Vehicle();

        boolean isParked = parkingAttendant.park(car);
        boolean isUnParked = parkingAttendant.unpark(car);
        boolean isUnParkedTwice = parkingAttendant.unpark(car);

        assertTrue(isParked);
        assertTrue(isUnParked);
        assertFalse(isUnParkedTwice);
    }
}

