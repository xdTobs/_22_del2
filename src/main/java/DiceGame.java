import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;
import java.lang.reflect.Field;

public class DiceGame {


    public static void main(String[] args) {
        //creates reference to Language, to get access to the variables within
        Language l = new Language();
        //initialize values of the board, might be able to be done elsewhere
        int[] fieldValues = {0,0,250,-100,100,-20,180,0,-70,60,-80,-50,650};
        //initialize fields, with the given field values. Specified to the specific type of board, where only the first 14 squares are used
        GUI_Field[] fields = initializeFields(fieldValues);
        GUI gui = new GUI(fields,Color.white);
//creates players, and inserts them into an array
        GUI_Player player1 = new GUI_Player(l.playerName1,1000);
        GUI_Player player2 = new GUI_Player(l.playerName2,1000);
        GUI_Player[] players = new GUI_Player[2];
        players[0] = player1;
        players[1] = player2;
        //integer that corresponds to the index of the players array, so if it is player1 turn, this value will be 0
        int playerTurn = 0;
        //array to hold the values of the dice
        int[] faceValues;
        //array to hold the positions of the players, index corresponds to player
        int[] carPos = new int[players.length];
        for (int i :carPos) i =0;
        //creates selectedPlayer which will point to the active player in a given turn
        GUI_Player selectedPlayer = new GUI_Player("temp");
        //adds the players to the gui
        for (GUI_Player p : players){
            gui.addPlayer(p);
        }
        //defines diceCup
DiceCup diceCup = new DiceCup();

//main loop
while (player1.getBalance()<3000 && player2.getBalance()<3000){
    //points selectedPlayer
    selectedPlayer = players[playerTurn];
    //removes car from the square that it was on, and moves it to start
    fields[carPos[playerTurn]].setCar(selectedPlayer,false);
    fields[0].setCar(selectedPlayer,true);
    carPos[playerTurn] = 0;
    // pauses for player to roll dice
    gui.showMessage( selectedPlayer.getName() +" "+ l.onRollDice);
    diceCup.roll();
    //updates facevalues
    faceValues = diceCup.getFaceValueArray();
    //shows dice on gui
    gui.setDice(faceValues[0],faceValues[1]);
    //moves player to the sum of the dice
    fields[carPos[playerTurn]].setCar(selectedPlayer,false);
    fields[diceCup.getSum()].setCar(selectedPlayer,true);
    carPos[playerTurn] = diceCup.getSum();
    //updates balance
    int balanceUpdate = selectedPlayer.getBalance()+fieldValues[diceCup.getSum()];
    if (balanceUpdate<0)selectedPlayer.setBalance(0);
    else selectedPlayer.setBalance(selectedPlayer.getBalance()+fieldValues[diceCup.getSum()]);
//logic for extra turn on square 10 (wereWall)
if (!(diceCup.getSum()==10)){
    if (playerTurn+1 == players.length) playerTurn = 0;
    else playerTurn++;
}
//tells player they got extra turn
else gui.showMessage( selectedPlayer.getName() +" "+ l.onWereWall);
}
//when loop ends, it means a player won
        gui.showMessage(selectedPlayer.getName() + l.gameWon);

}

    public static GUI_Field[] initializeFields(int[] fieldValues){
        Language l = new Language();

        GUI_Field[] fields;
        fields = new GUI_Field[40];
        fields[0] = new GUI_Start("Start"," "," ",Color.white,Color.black);
        for (int i = 1; i < 13; i++) {

            fields[i] = new GUI_Street(l.fieldNames[i-1], Integer.toString(fieldValues[i]), " ", Integer.toString(fieldValues[i]), Color.white, Color.BLACK);

        }
        for (int i = 13; i < 40; i++) {
            fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);
        }
        return fields;
    }

    }

