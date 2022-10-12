import java.lang.Math;
//Terningen udviklet med brug af ideer og kode fra Java Software Solutions: Foundations of Program design, 9th Edition, listing 4.2


public class Die {
    //Private attributter for vores terninger
    private final int Max = 6;
    private int faceValue;

    //Konstruktør for terningerne
    public Die() {
        faceValue = 1;

    }

    //Terningerne bygges ud fra samme princip som TestDice, da vores test viser en normalfordeling
    public int roll1() {
        faceValue = (int) (Math.random() * Max + 1);
        return faceValue;
    }

    //Get-metode til at se den nuværende faceValue af terningerne
    public int getFaceValue() {
        return faceValue;
    }

    //Set-metode til ændre værdien af faceValue1 og faceValue2
    public int setFaceValue(int x) {
        faceValue = x;
        return x;
    }

    //Metode til at få værdien af 2 terninger
    public int getSum(int a, int b) {
        return a + b;
    }
    public boolean getEns(int a, int b) {
        if (a == b) {
            return true;
        } else {
            return false;
        }

    }
}
