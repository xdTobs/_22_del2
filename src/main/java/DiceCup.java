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

    //int dice defined in Main as the total number of dice in a cup
    public DiceCup(int dice){
        diceArray = new Die[dice];
        for (int i = 0; i < dice; i++) {
            diceArray[i] = new Die();
        }
    }
}
