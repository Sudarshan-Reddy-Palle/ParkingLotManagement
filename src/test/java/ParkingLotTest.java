
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParkingLotTest {
    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(4);
    }

    @Test
    void shouldReturnNoWhenAvailableSlotsAre0() {
        int occupiedSlots = 4;

        for(int i=0;i<occupiedSlots;i++){
            parkingLot.park();
        }
        String canBeParked = parkingLot.checkForParking();

        assertEquals("No",canBeParked);
    }

    @Test
    void shouldReturnYesWhenAvailableSlotsAre1() {
        int occupiedSlots = 3;

        for(int i=0;i<occupiedSlots;i++){
            parkingLot.park();
        }
        String canBeParked = parkingLot.checkForParking();

        assertEquals("Yes",canBeParked);
    }
}

