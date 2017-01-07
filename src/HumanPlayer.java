/**
 * This file creates an object for human players.
 */

public class HumanPlayer implements Player{

    //Define your fields here
    int row,col;
    int playerID;
    Board board;

    //constructor takes the player id for this player, and the number of
    // rows and columns of the board we're playing on
    public HumanPlayer(int playerID, int row, int col){
        this.row = row;
        this.col = col;
        this.playerID = playerID;

        board = new Board(row,col);

    }

    //used to notify your AI player of the other players last move
    public void lastMove(int c) {
        board.play(playerID, c);
    }

    //returns column of where to play a token
    public int playToken(){
        System.out.println("Enter a column index: ");
        int input = IO.readInt();

        while(input >= col || input < 0){
            System.out.println("Error! enter between 0 - " + (col-1) +" : ");
            input = IO.readInt();
        }

        //asks the user for a valid input
        while (!board.play(playerID, input)){
            System.out.println("Error! Enter a valid input: ");
            input = IO.readInt();
        }

        return input;
    }

    //get this player's id
    public int getPlayerID(){
        return playerID;
    }

    //resets the state of the player in preparation for a new game
    public void reset(){
        board = new Board(row,col);
    }

}
