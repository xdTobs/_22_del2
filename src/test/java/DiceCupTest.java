import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceCupTest {
    DiceCup diceCup;
    @BeforeEach
    void setUp() {
        diceCup = new DiceCup();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSum() {
    }

    @Test
    void roll() {
        diceCup.roll();
        int[] data = new int[36];
        int index =0;
        int[] currentRoll = diceCup.getFaceValueArray();
        index+=(currentRoll[0]-1)*6;
        index+=currentRoll[1]-1;
        data[index]++;
    }

    @Test
    void getFaceValueArray() {
    }
}