import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

public class DiceGame {


    public static void main(String[] args) {
        Language l = new Language();
        int[] fieldValues = {0,250,-100,100,-20,180,0,-70,60,-80,-50,650};
        GUI_Field[] fields = new GUI_Field[40];
        fields[0] = new GUI_Start("Start"," "," ",Color.white,Color.black);
        for (int i = 1; i < 13; i++) {

            fields[i] = new GUI_Street(l.fieldNames[i-1], Integer.toString(fieldValues[i-1]), " ", Integer.toString(fieldValues[i-1]), Color.white, Color.BLACK);

        }
        for (int i = 13; i < 40; i++) {
            fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);
        }
        GUI gui = new GUI(fields, Color.WHITE);
        GUI_Player player1 = new GUI_Player(l.playerName1,1000);
        GUI_Player player2 = new GUI_Player(l.playerName2,1000);
        GUI_Player[] players = new GUI_Player[2];
        players[0] = player1;
        players[1] = player2;
        int playerTurn = 0;
        int[] faceValues;
        int[] carPos = new int[players.length];
        for (int i :carPos) i =0;
        GUI_Player selectedPlayer = new GUI_Player("temp");
        for (GUI_Player p : players){
            gui.addPlayer(p);
        }
DiceCup diceCup = new DiceCup();


while (player1.getBalance()<3000 && player2.getBalance()<3000){

    selectedPlayer = players[playerTurn];
    gui.showMessage( selectedPlayer.getName() +" "+ l.onRollDice);
    diceCup.roll();
    faceValues = diceCup.getFaceValueArray();
    gui.setDice(faceValues[0],faceValues[1]);
    fields[carPos[playerTurn]].setCar(selectedPlayer,false);
    fields[diceCup.getSum()].setCar(selectedPlayer,true);
    carPos[playerTurn] = diceCup.getSum();
    selectedPlayer.setBalance(selectedPlayer.getBalance()+fieldValues[diceCup.getSum()-1]);


    if (playerTurn+1 == players.length) playerTurn = 0;
    else playerTurn++;
}
        gui.showMessage(selectedPlayer.getName() + l.gameWon);

}
    }

