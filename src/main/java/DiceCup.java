public class DiceCup {

    Die[] diceArray;

    //returns the total sum of all dice
    public int getSum(){
        int sum = 0;
        for(Die d : diceArray){
            sum += d.getFaceValue();
        }
        return sum;
    }

    //rolls all dice
    public void roll(){
        for(Die d : diceArray){
            d.roll();
        }
    }
    public int[] getFaceValueArray(){
        int[] res = new int[diceArray.length];
        for (int i = 0; i < diceArray.length; i++) {
            res[i] = diceArray[i].getFaceValue();
        }
        return res;
    }

    //int dice defined in Main as the total number of dice in a cup
    public DiceCup(int dice){
        diceArray = new Die[dice];
        for (int i = 0; i < dice; i++) {
            diceArray[i] = new Die();
        }
    }
    public DiceCup(int dice, int max){
        diceArray = new Die[dice];
        for (int i = 0; i < dice; i++) {
            diceArray[i] = new Die(max);
        }
    }
}
