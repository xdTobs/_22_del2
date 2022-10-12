import static org.junit.jupiter.api.Assertions.*;

class DieTest {
Die d1;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
         d1 = new Die();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void roll() {
        int[] res = new int[6];
        for (int i = 0; i < 10000; i++) {
            d1.roll();
            res[d1.getFaceValue()-1] =
        }


    }

    @org.junit.jupiter.api.Test
    void getFaceValue() {
    }

    @org.junit.jupiter.api.Test
    void setFaceValue() {
    }
}