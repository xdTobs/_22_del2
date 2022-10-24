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
        int res =0;
       int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues)res +=i;
        assertEquals(diceCup.getSum(),res);
    }

    @Test
    void roll() {
        //not used since it just calls roll on dice, which is tested
    }

    @Test
    void getFaceValueArray() {
        int res =0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues)res +=i;
        assertEquals(res,diceCup.getSum());
    }
}