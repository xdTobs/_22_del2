import gui_fields.*;
import gui_main.GUI;

import java.awt.*;


/**
 * The Dice game is the driver class for our game.
 */
public class DiceGame {
    GUI_Player[] players = new GUI_Player[2];
    GUI gui;
    GUI_Field[] fields;
    /**
     * Language class is used to show english messages but can easily be switched.
     */
    Language language = new Language();
    /**
     * This is the index of the current player. Is used to determine which player is playing. Works for more than 2 players.
     */
    int playerTurn;
    /**
     * The Field values is the value of all fields, where fieldValues[0] is when dice sum is 2, and fieldsValues[11] is when dice sum is 12.
     */
    final int[] fieldValues = {0, 0, 250, -100, 100, -20, 180, 0, -70, 60, -80, -50, 650};

    /**
     * The Dice cup. Used to throw the dice, and get the sum of the dice.
     */
    DiceCup diceCup = new DiceCup();

    /**
     * Instantiates a new Dice game.
     */
    public DiceGame() {
        // The player color is the color of the car.
        // If we don't set the colors, there is a chance the colors will be the same.
        GUI_Car car1 = new GUI_Car();
        car1.setPrimaryColor(Color.black);
        GUI_Car car2 = new GUI_Car();
        car2.setPrimaryColor(Color.white);

        this.players[0] = new GUI_Player(language.playerName1, 1000, car1);
        this.players[1] = new GUI_Player(language.playerName2, 1000, car2);

        initializeFields();
        this.gui = new GUI(fields, Color.white);

        for (GUI_Player player :
                players) {
            gui.addPlayer(player);
        }

    }

    private void playGame() {
        playerTurn = 0;
        // The game loop will run until the isGameOver method returns true.
        while (true) {
            GUI_Player currentPlayer = players[playerTurn];
            playRound(currentPlayer);

            // If player lands on werewall, then the current player gets an extra turn.
            boolean landedOnWerewallField = diceCup.getSum() == 10;
            if (isGameover(currentPlayer)) {
                // We break out of the loop if the game is over.
                this.gui.showMessage(currentPlayer.getName() + " " + language.gameWon);
                break;
            } else if (landedOnWerewallField) {
                // If the player lands on werewall, then we use continuue to go to the start of while loop, meaning we will skip nextPlayer().
                this.gui.showMessage(currentPlayer.getName() + " " + language.onWereWall);
                continue;
            }
            nextPlayer();
        }
    }

    private void initializeFields() {
        // There are 40 fields on the board, but we only show 12 of them.
        this.fields = new GUI_Field[40];
        fields[0] = new GUI_Start("Start", " ", " ", Color.white, Color.black);
        // These are the fields we will show.
        for (int i = 1; i < 13; i++) {
            fields[i] = new GUI_Street(language.fieldNames[i - 1], Integer.toString(fieldValues[i]), " ", Integer.toString(fieldValues[i]), Color.white, Color.BLACK);
        }
        // The rest of the fields we turn black.
        for (int i = 13; i < 40; i++) {
            fields[i] = new GUI_Street(" ", " ", " ", " ", Color.BLACK, Color.BLACK);
        }
    }

    private void playRound(GUI_Player currentPlayer) {
        diceCup.roll();
        int diceSum = diceCup.getSum();
        gui.showMessage(currentPlayer.getName() + " " + language.onRollDice);

        // Places player on gui
        showPlayerOnGui(diceSum, currentPlayer);

        // Check if player is going to be under 0 in value.
        // If so, value is set to value, instead of negative value.
        int fieldValue = fieldValues[diceSum];
        int player_balance = currentPlayer.getBalance();
        int new_balance = updateBalance(player_balance, fieldValue);
        currentPlayer.setBalance(new_balance);
    }

    int updateBalance(int oldBalance, int fieldValue) {
        int newBalance = oldBalance + fieldValue;
        return Math.max(newBalance, 0);
    }

    // We first remove all cars from gui the show where current player landed.
    private void showPlayerOnGui(int diceSum, GUI_Player currentPlayer) {
        clearCarsFromGui();
        fields[diceSum].setCar(currentPlayer, true);
    }

    // Removes all visible cars from the gui from every player.
    private void clearCarsFromGui() {
        for (GUI_Player player : players) {
            for (int i = 0; i < 13; i++) {
                fields[i].setCar(player, false);
            }
        }
    }


    // Works for more than two players.
    private void nextPlayer() {
        if (playerTurn >= players.length - 1) {
            playerTurn = 0;
        } else {
            playerTurn++;
        }
    }

    // Checks if current player has won.
    private boolean isGameover(GUI_Player currentPlayer) {
        int winLimit = 3000;
        return currentPlayer.getBalance() >= winLimit;
    }

    /**
     * The entry point of the game.
     */
    public static void main(String[] args) {
        DiceGame game = new DiceGame();
        game.playGame();
    }
}

