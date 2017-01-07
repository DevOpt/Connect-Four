/**
 * This file creates the game board.
 */
public class Board {

    //defines field
    char[][] board;
    int row,col;
    char p1,p2;

    //default board size
    public Board(){
        board = new char[6][7];

        for (int i=0; i<6; i++){
            for (int j=0; j<7; j++){
                board[i][j] = ' ';
            }
        }

        row = 6;
        col = 7;
    }

    //custom board size
    public Board(int row, int col){
        board = new char[row][col];

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                board[i][j] = ' ';
            }
        }

        this.row = row;
        this.col = col;
    }

    public int getNumRows(){
        return row;
    }

    public int getNumCols(){
        return col;
    }

    public void setPlayerOne(char o) {
        p1 = o;
    }

    public void setPlayerTwo(char t) {
        p2 = t;
    }

    public char getPlayerOne(){
        return this.p1;
    }

    public char getPlayerTwo(){
        return this.p2;
    }

    public char getToken(int row, int col){
        if (board[row][col] == p1){
            return p1;
        }else if (board[row][col] == p2){
            return p2;
        }else{
            return '\0';
        }
    }

    //checks if all indexes in array are occupied
    public boolean canPlay(){
        boolean can = false;

        outerloop:
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (board[i][j] == ' '){
                    can = true;
                    break outerloop;
                }
            }
        }
        return can;
    }

    //adds token in the 'colm' position
    public boolean play(int p, int colm){

        char side;

        if (p==1){
            side = p1;
        }else if (p==2){
            side = p2;
        }else{
            return false;
        }

        if (board[0][colm] != ' ')
            return false;

        for (int i = 0; i < row; ++i) {

            if (board[i][colm] != ' ') {
                board[i-1][colm] = side;
                return true;
            }
        }

        board[row-1][colm] = side;
        return true;
    }

    //checks if the game is finished
    public int isFinished(){
        int result=-1;

        // Check row
        for (int i = row-1; i >0; i--) {
            int count = 0;
            if ((board[5][0] == board[5][1]) &&  (board[5][2] == board[5][3])
                    && (board[5][1] == board[5][2])) {
                if (board[5][0] == p1){
                    result=1;
                } else {
                    result=2;
                }
                break;
            }

            for (int j = 1; j < col; ++j) {
                if (board[i][j] != ' ' &&
                        board[i][j] == board[i][j-1])
                    ++count;
                else
                    count = 1;

                // Check if there are 4 same tokens in a row.
                if (count >= 4) {
                    if (board[i][j] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        // Check column
        for (int j = 0; j < col; ++j) {
            int count = 0;
            for (int i = 1; i < row; ++i) {
                if (board[i][j] != ' ' &&
                        board[i][j] == board[i-1][j])
                    ++count;
                else
                    count = 1;

                // Check if there are 4 same tokens in a row.
                if (count >= 4) {
                    if (board[i][j] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        // There are 2 kinds of diagonals, let's check those that go from top-left to bottom right
        // There are diagonals, that starts on top of each column, let's check them
        for (int j = 0; j < col; ++j) {
            int count = 0;
            for (int i = 1; i < row; ++i) {
                if (j + i >= col) break;
                if (board[i][j+i] != ' ' &&
                        board[i-1][j + i - 1] == board[i][j+i])
                    ++count;
                else
                    count = 1;
                if (count >= 4) {
                    if (board[i][i+j] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        // There are diagonals, that starts on left of each row, let's check them
        for (int i = 0; i < row; ++i) {
            int count = 0;
            for (int j = 1; j < col; ++j) {
                if (j + i >= row) break;

                if (board[i + j][j] != ' ' &&
                        board[i+j - 1][j - 1] == board[i + j][j])
                    ++count;
                else
                    count = 1;

                if (count >= 4) {
                    if (board[j+i][j] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        // There are diagonals, that starts on top of each column, let's check them
        for (int j = 0; j < col; ++j) {
            int count = 0;
            for (int i = 1; i < row; ++i) {
                if (j - i < 0) break;
                if (board[i][j-i] != ' ' &&
                        board[i - 1][j - i + 1] == board[i][j-i])
                    ++count;
                else
                    count = 1;
                if (count >= 4) {
                    if (board[i][j-i] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        // There are diagonals, that starts on left of each row, let's check them
        for (int i = 0; i < row; ++i) {
            int count = 0;
            for (int j = col-2; j >= 0; --j) {

                if (j - i < 0) break;
                if (board[j-i][j] != ' ' &&
                        board[j - i - 1][j + 1] == board[j - i][j])
                    ++count;
                else
                    count = 1;
                if (count >= 4) {
                    if (board[j - i][j] == p1){
                        result=1;
                    } else {
                        result=2;
                    }
                }
            }
        }

        int countOccupied=0;
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (board[i][j] != ' '){
                    countOccupied++;
                }
            }
        }
        if (countOccupied == (row*col)){
            result = 0;
        }

        return result;
    }

    //prints the board
    public char[][] printArray(){
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }

        return board;
    }

}

