import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiceCupTest {
    DiceCup diceCup;

    @BeforeEach
    void setUp() {
        diceCup = new DiceCup();
    }

    @Test
    void getSumStandard() {
        int res = 0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues) res += i;
        assertEquals(diceCup.getSum(), res);
    }

    @Test
    void getSumNegative() {
        diceCup = new DiceCup(-8);
        int res = 0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues) res += i;
        assertEquals(2, res);
    }

    @Test
    void getSumLarge() {
        diceCup = new DiceCup(1000);
        int res = 0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues) res += i;
        assertEquals(diceCup.getSum(), res);
    }


    @Test
    void roll() {
        //not used since it just calls roll on dice, which is tested
    }

    @Test
    void getFaceValueArrayStandard() {
        int res = 0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues) res += i;
        assertEquals(res, diceCup.getSum());
    }

    @Test
    void getFaceValueArrayNegative() {
        diceCup = new DiceCup(-8);
        int[] faceValues = diceCup.getFaceValueArray();
        assertArrayEquals(new int[]{1, 1}, faceValues);
    }

    @Test
    void getFaceValueArrayLarge() {
        diceCup = new DiceCup(1000);
        int res = 0;
        int[] faceValues = diceCup.getFaceValueArray();
        for (int i : faceValues) res += i;
        assertEquals(res, diceCup.getSum());
    }
}