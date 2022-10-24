import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Start;
import gui_fields.GUI_Street;
import gui_main.GUI;

import java.awt.*;

public class DiceGame {

    GUI_Player[] players = new GUI_Player[2];
    GUI gui;
    GUI_Field[] fields;
    Language language;
    int playerTurn;
    final int[] fieldValues = {0, 0, 250, -100, 100, -20, 180, 0, -70, 60, -80, -50, 650};

    DiceCup diceCup = new DiceCup();

    public DiceGame() {

        this.language = new Language();
        // REMEMBER TO GIVE THE CAR A COLOR, SO P1 AND P2 DONT HAVE SAME COLORS.
        this.players[0] = new GUI_Player(language.playerName1, 1000);
        this.players[1] = new GUI_Player(language.playerName2, 1000);
        this.fields = initializeFields();
        this.gui = new GUI(fields, Color.white);

        for (GUI_Player player :
                players) {
            gui.addPlayer(player);
        }
    }

    private void playGame() {
        playerTurn = 0;
        while (true) {
            GUI_Player currentPlayer = players[playerTurn];
            playRound(currentPlayer);
            boolean isWerewall = diceCup.getSum() == 10;
            if (isGameover()) {
                this.gui.showMessage(currentPlayer.getName() + language.gameWon);
                break;
            } else if (isWerewall) {
                this.gui.showMessage(currentPlayer.getName() + " " + language.onWereWall);
                continue;
            }
            nextPlayer();
        }
    }

    private GUI_Field[] initializeFields() {
        GUI_Field[] fields;
        fields = new GUI_Field[40];
        fields[0] = new GUI_Start("Start", " ", " ", Color.white, Color.black);
        for (int i = 1; i < 13; i++) {
            fields[i] = new GUI_Street(language.fieldNames[i - 1], Integer.toString(fieldValues[i]), " ", Integer.toString(fieldValues[i]), Color.white, Color.BLACK);
        }
        for (int i = 13; i < 40; i++) {
            fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);
        }
        return fields;
    }

    private void playRound(GUI_Player currentPlayer) {
        diceCup.roll();
        int diceSum = diceCup.getSum();
        gui.showMessage(currentPlayer.getName() + " " + language.onRollDice);
        showPlayerOnGui(diceSum, currentPlayer);

        // Check if player is going to be under 0 in value.
        // If so, value is set to value, instead of negative value.
        int fieldValue = fieldValues[diceSum];
        int player_balance = currentPlayer.getBalance();
        int new_balance = player_balance + fieldValue;
        if (new_balance >= 0) {
            currentPlayer.setBalance(new_balance);
        } else {
            currentPlayer.setBalance(0);
        }
    }

    private void showPlayerOnGui(int diceSum, GUI_Player currentPlayer) {
//        int playerPosition = currentPlayer.getCar().getPosition();
//        int newPosition = playerPosition + diceSum;
//        if (newPosition > 12) {
//            newPosition -= 12;
//        }
//        currentPlayer.getCar().setPosition(newPosition);
//    }
        clearCarsFromGui();
        fields[diceSum].setCar(currentPlayer, true);
    }

    private void clearCarsFromGui() {

        for (int i = 1; i < 13; i++) {
            for (GUI_Player player :
                    players) {
                fields[i].setCar(player, false);
            }
        }
    }


    private void nextPlayer() {
        if (playerTurn >= players.length - 1) {
            playerTurn = 0;
        } else {
            playerTurn++;
        }
    }

    private boolean isGameover() {
        int winLimit = 2100;
        return players[0].getBalance() > winLimit || players[1].getBalance() > winLimit;
    }

    public static void main(String[] args) {
        DiceGame game = new DiceGame();
        game.playGame();
    }
}

