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
        final int rolls = 10000;
        int[] res = new int[6];
        for (int i = 0; i < rolls; i++) {
            d1.roll();
            res[d1.getFaceValue()-1] ++;
        }
        for (int i : res){
            assertEquals(rolls/6,res[i],rolls);
        }


    }

    @org.junit.jupiter.api.Test
    void getFaceValue() {
    }

    @org.junit.jupiter.api.Test
    void setFaceValue() {
    }
}