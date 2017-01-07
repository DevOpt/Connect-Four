/**
 * This file creates an object for computer(AI) player.
 */
public class AIPlayer implements Player{

    //Define your fields here
    int row,col;
    int playerID;
    Board board;
    int sPlayer;


    //constructor takes the player id for this player, and the number of
    // rows and columns of the board we're playing on
    public AIPlayer(int playerID, int row, int col){
        this.row = row;
        this.col = col;
        this.playerID = playerID;

        if (playerID == 1){
            sPlayer = 2;
        }else if (playerID == 2){
            sPlayer = 1;
        }

        board = new Board(row,col);
    }

    //used to notify your AI player of the other players last move
    public void lastMove(int c) {
        board.play(1, c);
    }

    //returns column of where to play a token
    public int playToken(){
        System.out.println("Computer's turn");

        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                if (board.play(playerID, j)){
                    return j;
                }
            }
        }

        return -1;
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
