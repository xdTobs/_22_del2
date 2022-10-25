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
    void rollRandom() {
        final double rolls = 10000;
        double standardDeviation =0;
        double sum =0;
        for (int i = 0; i < rolls; i++) {
            d1.roll();
            sum += Math.pow(d1.getFaceValue(),2)-Math.pow(3.5,2);
        }
        standardDeviation = Math.sqrt(1/(rolls)*sum);


        int[] res = new int[6];
        for (int i = 0; i < rolls; i++) {
            d1.roll();
            res[d1.getFaceValue()-1] ++;
        }
        for (int i : res){
            assertEquals(rolls/6,i,standardDeviation*100);
        }


    }
    @org.junit.jupiter.api.Test
    void rollInBounds() {
        int[] res = new int[6];
        int count = 0;
        final double rolls = 10000;
        for (int i = 0; i < rolls; i++) {
            d1.roll();
            res[d1.getFaceValue()-1] ++;
        }
        for (int i : res){
            count += i;
        }
        assertEquals(count,rolls);
    }


    @org.junit.jupiter.api.Test
    void getFaceValue() {
    }

    @org.junit.jupiter.api.Test
    void setFaceValue() {
    }
}