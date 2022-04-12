import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


public class FourInRowController {
    private final int EMPTY = 0;
    private final int RED = 1;
    private final int BLUE = 2;

    private int drawn_shape_counter = 0;

    private int GameMatrix[][] = new int[6][7];
    private boolean redsTurn = true;

    @FXML
    private GridPane game_board;

    @FXML
    private VBox main_vbox;

    @FXML
    private Label Label_turn;

    @FXML
    private Button Clear_button;

    @FXML
    private Button Column1_button;

    @FXML
    private Button Column2_button;

    @FXML
    private Button Column3_button;

    @FXML
    private Button Column4_button;

    @FXML
    private Button Column5_button;

    @FXML
    private Button Column6_button;

    @FXML
    private Button Column7_button;


    @FXML
    void Column1_button_onclick(ActionEvent event) {

        if (fillColumn(0)) {
            Column1_button.setDisable(true);
        }
    }

    @FXML
    void Column2_button_onclick(ActionEvent event) {

        if (fillColumn(1)) {
            Column2_button.setDisable(true);
        }
    }

    @FXML
    void Column3_button_onclick(ActionEvent event) {
        if (fillColumn(2)) {
            Column3_button.setDisable(true);
        }
    }

    @FXML
    void Column4_button_onclick(ActionEvent event) {
        if (fillColumn(3)) {
            Column4_button.setDisable(true);
        }
    }

    @FXML
    void Column5_button_onclick(ActionEvent event) {
        if (fillColumn(4)) {
            Column5_button.setDisable(true);
        }
    }

    @FXML
    void Column6_button_onclick(ActionEvent event) {
        if (fillColumn(5)) {
            Column6_button.setDisable(true);
        }
    }

    @FXML
    void Column7_button_onclick(ActionEvent event) {
        if (fillColumn(6)) {
            Column7_button.setDisable(true);
        }
    }

    @FXML
    void clear_button_onclick(ActionEvent event) {
        restartGame();
    }

    @FXML
    public void initialize() {


        //TODO set vbox to not resizable
        //TODO draw grid lines

    }

    //
    private boolean fillColumn(int column) {
        boolean disableButton = false;
        if (column <= 6 && column >= 0) {
            int i = 0;
            while (i < 6 && GameMatrix[i][column] != EMPTY) {//find first empty cell in column
                i++;
            }
            if (i < 6 && GameMatrix[i][column] == EMPTY) {
                drawn_shape_counter++;
                //put disk in place and change turn
                if (redsTurn) {
                    redsTurn = false;
                    GameMatrix[i][column] = RED;
                    drawDisk(column, 5 - i, Color.RED);
                    Label_turn.setTextFill(Color.BLUE);
                    Label_turn.setText("Blue's turn");
                } else {
                    redsTurn = true;
                    GameMatrix[i][column] = BLUE;
                    drawDisk(column, 5 - i, Color.BLUE);
                    Label_turn.setTextFill(Color.RED);
                    Label_turn.setText("Red's turn");
                }
            }
            if (i == 5) {//if just filled last cell then disable the column
                disableButton = true;
            }
            if (checkWinner(i, column)) {
                announceWinner();
            }
        }

        return disableButton;
    }

    //draws a game disk at a specific location with color
    private void drawDisk(int x, int y, Paint c) {
        GameDisk gd = new GameDisk(c);
        game_board.add(gd, x, y);

    }


    private void restartGame() {
        //clear matrix
        for (int i = 0; i < GameMatrix.length; i++)
            for (int j = 0; j < GameMatrix[1].length; j++)
                GameMatrix[i][j] = EMPTY;
        //clear game board
        for (int i = 0; i < drawn_shape_counter; i++)
            game_board.getChildren().remove(game_board.getChildren().size() - 1);
        drawn_shape_counter = 0;

        //Reset disable buttons
        Column1_button.setDisable(false);
        Column2_button.setDisable(false);
        Column4_button.setDisable(false);
        Column3_button.setDisable(false);
        Column5_button.setDisable(false);
        Column6_button.setDisable(false);
        Column7_button.setDisable(false);

        //reset the game where it's red's turn
        redsTurn = true;
        Label_turn.setTextFill(Color.RED);//set label
        Label_turn.setText("Red's turn");

    }


    private boolean checkWinner(int r, int c) {
        boolean winner = false;

        int player_color = GameMatrix[r][c];

        //check horizontal for a 4 disk sequence

        int i = r;//row index
        int j = c;//column index
        int counter = 0;//amount of continues disks with the same color
        int k = 1;
        boolean isTurned = false;
        while (k < 4) {
            if (!isTurned) {
                i--;
            } else {
                i++;
            }
            if (i >= 0 && i < GameMatrix.length && j >= 0 && j < GameMatrix[0].length && GameMatrix[i][j] == player_color) {
                {
                    counter++;

                }
            } else {
                if (!isTurned) {
                    isTurned = true;
                    i = r;
                    j = c;
                } else {
                    k = 4;
                }
                k++;
            }
        }
        if (counter == 3) {
            winner = true;
        }

        //check vertical for a 4 disk sequence

        i = r;//row index
        j = c;//column index
        counter = 0;//amount of continues disks with the same color
        k = 1;
        isTurned = false;
        while (k < 4) {
            if (!isTurned) {
                j--;
            } else {
                j++;
            }
            if (i >= 0 && i < GameMatrix.length && j >= 0 && j < GameMatrix[0].length && GameMatrix[i][j] == player_color) {
                {
                    counter++;

                }
            } else {
                if (!isTurned) {
                    isTurned = true;
                    i = r;
                    j = c;
                } else {
                    k = 4;
                }
                k++;
            }
        }
        if (counter == 3) {
            winner = true;
        }


        //check Main diagonal for a 4 disk sequence
        i = r;//row index
        j = c;//column index
        counter = 0;//amount of continues disks with the same color
        k = 1;
        isTurned = false;
        while (k < 4) {
            if (!isTurned) {
                j--;
                i--;
            } else {
                j++;
                i++;
            }
            if (i >= 0 && i < GameMatrix.length && j >= 0 && j < GameMatrix[0].length && GameMatrix[i][j] == player_color) {
                {
                    counter++;

                }
            } else {
                if (!isTurned) {
                    isTurned = true;
                    i = r;
                    j = c;
                } else {
                    k = 4;
                }
                k++;
            }
        }
        if (counter == 3) {
            winner = true;
        }

        //check Main diagonal for a 4 disk sequence
        i = r;//row index
        j = c;//column index
        counter = 0;//amount of continues disks with the same color
        k = 1;
        isTurned = false;
        while (k < 4) {
            if (!isTurned) {
                j--;
                i++;
            } else {
                j++;
                i--;
            }
            if (i >= 0 && i < GameMatrix.length && j >= 0 && j < GameMatrix[0].length && GameMatrix[i][j] == player_color) {
                {
                    counter++;

                }
            } else {
                if (!isTurned) {
                    isTurned = true;
                    i = r;
                    j = c;
                } else {
                    k = 4;
                }
                k++;
            }
        }
        if (counter == 3) {
            winner = true;
        }


        return winner;
    }


    private void announceWinner() {
        if (redsTurn) {
            Label_turn.setTextFill(Color.BLUE);//set label
            Label_turn.setText("Blue has won");
        } else {
            Label_turn.setTextFill(Color.RED);//set label
            Label_turn.setText("Red has won");
        }
        Column1_button.setDisable(true);
        Column2_button.setDisable(true);
        Column3_button.setDisable(true);
        Column4_button.setDisable(true);
        Column5_button.setDisable(true);
        Column6_button.setDisable(true);
        Column7_button.setDisable(true);

    }

}