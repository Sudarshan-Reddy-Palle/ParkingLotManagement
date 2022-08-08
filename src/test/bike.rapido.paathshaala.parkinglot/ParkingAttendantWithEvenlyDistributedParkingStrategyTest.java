import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ParkingAttendantWithEvenlyDistributedParkingStrategyTest {

    private Owner owner;
    @BeforeEach
    void setUp() {
        owner = new Owner();
    }
    @Test
    void shouldParkTheCarInFirstAndSecondLotUniformly() {
        int[] lotCapacities = {2,2};
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);

        Vehicle firstCar = new Vehicle();
        Vehicle secondCar = new Vehicle();
        Optional<ParkingLot> parkedParkingLotOfFirstCar = parkingAttendant.park(firstCar);
        Optional<ParkingLot> parkedParkingLotOfSecondCar = parkingAttendant.park(secondCar);

        assertEquals(1,parkedParkingLotOfFirstCar.get().getId());
        assertEquals(2,parkedParkingLotOfSecondCar.get().getId());
    }

    @Test
    void shouldUnParkTheCarOnlyIfItIsParked() {
        int[] lotCapacities = {2,2};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle car = new Vehicle();
        Optional<ParkingLot> parkedParkingLot = parkingAttendant.park(car);

        Optional<ParkingLot> unParkedParkingLot = parkingAttendant.unpark(car);

        assertFalse(unParkedParkingLot.isEmpty());
    }

    @Test
    void shouldNotUnparkTheCarIfItIsAlreadyUnParked() {
        int[] lotCapacities = {2,2};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle car = new Vehicle();
        Optional<ParkingLot> parkedParkingLot = parkingAttendant.park(car);
        Optional<ParkingLot> unparkedParkingLot = parkingAttendant.unpark(car);

        Optional<ParkingLot> unparkSameCarTwice = parkingAttendant.unpark(car);

        assertTrue(unparkSameCarTwice.isEmpty());
    }

    @Test
    void shouldParkFourConsecutiveCarsInFourDifferentParkingLots() {
        int[] lotCapacities = {3,3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle car1 = new Vehicle();
        Vehicle car2 = new Vehicle();
        Vehicle car3 = new Vehicle();
        Vehicle car4 = new Vehicle();

        Optional<ParkingLot> firstCarParkedLot = parkingAttendant.park(car1);
        Optional<ParkingLot> secondCarParkedLot = parkingAttendant.park(car2);
        Optional<ParkingLot> thirdCarParkedLot = parkingAttendant.park(car3);
        Optional<ParkingLot> fourthCarParkedLot = parkingAttendant.park(car4);

        assertEquals(1,firstCarParkedLot.get().getId());
        assertEquals(2,secondCarParkedLot.get().getId());
        assertEquals(3,thirdCarParkedLot.get().getId());
        assertEquals(4,fourthCarParkedLot.get().getId());
    }

    @Test
    void shouldParkCarsAccordingToStrategyChange() {
        int[] lotCapacities = {3,3,3,3};
        Owner owner = new Owner();
        owner.createParkingLots(lotCapacities);
        List<ParkingLot> parkingLots = owner.getParkingLots();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingStrategy parkingStrategy = new EvenlyDistributedParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Vehicle car1 = new Vehicle();
        Vehicle car2 = new Vehicle();
        Vehicle car3 = new Vehicle();
        Vehicle car4 = new Vehicle();
        Vehicle car5 = new Vehicle();
        Vehicle car6 = new Vehicle();

        Optional<ParkingLot> firstCarParkedLot = parkingAttendant.park(car1);
        Optional<ParkingLot> secondCarParkedLot = parkingAttendant.park(car2);
        Optional<ParkingLot> thirdCarParkedLot = parkingAttendant.park(car3);
        Optional<ParkingLot> fourthCarParkedLot = parkingAttendant.park(car4);
        parkingStrategy = new FirstFreeLotParkingStrategy(parkingLots);
        parkingAttendant.setParkingStrategy(parkingStrategy);
        Optional<ParkingLot> fifthCarParkedLot = parkingAttendant.park(car5);
        Optional<ParkingLot> sixthCarParkedLot = parkingAttendant.park(car6);

        assertEquals(1,firstCarParkedLot.get().getId());
        assertEquals(2,secondCarParkedLot.get().getId());
        assertEquals(3,thirdCarParkedLot.get().getId());
        assertEquals(4,fourthCarParkedLot.get().getId());
        assertEquals(1,fifthCarParkedLot.get().getId());
        assertEquals(1,sixthCarParkedLot.get().getId());
    }
}