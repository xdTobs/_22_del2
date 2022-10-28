import static org.junit.jupiter.api.Assertions.*;

class BalanceTest {
    DiceGame diceGame = new DiceGame();
    int oldBalance;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        oldBalance = 1000;
    }

    @org.junit.jupiter.api.Test
    void balanceIncrease() {
        int fieldValue = 100;
        int newBalance = diceGame.updateBalance(oldBalance, fieldValue);
        assertEquals(1100, newBalance);
    }

    @org.junit.jupiter.api.Test
    void balanceDecrease() {
        int fieldValue = -100;
        int newBalance = diceGame.updateBalance(oldBalance, fieldValue);
        assertEquals(900, newBalance);
    }

    @org.junit.jupiter.api.Test
    void balanceUnderZero() {
        int fieldValue = -1200;
        int newBalance = diceGame.updateBalance(oldBalance, fieldValue);
        assertEquals(0, newBalance);
    }
}