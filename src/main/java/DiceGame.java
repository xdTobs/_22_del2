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
        gui.addPlayer(player1);
        gui.addPlayer(player2);

    }
}
