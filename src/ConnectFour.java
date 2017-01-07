
public class ConnectFour{


    public static void main(String[] args){

        //Create new board object
        Board game = new Board();
        //Set player tokens for player 1 and player 2
        game.setPlayerOne('A');
        game.setPlayerTwo('B');

        //Create Player objects
        Player p1 = new HumanPlayer(1,6,7);
        //Player p2 = new HumanPlayer(2,6,7); //comment this line when playing against AIPlayer

		Player p2 = new AIPlayer(2,6,7); //uncomment this line when playing against AIPlayer


        //Print your empty board
        game.printArray();

        while (game.canPlay()){

            p2.getPlayerID();
            int b = p2.playToken();
            p2.lastMove(b);
            game.play(2, b);
            game.printArray();

            if (game.isFinished() != -1) {
                break;
            }

            p1.getPlayerID();
            int a = p1.playToken();
            p1.lastMove(a);
            game.play(1, a);
            game.printArray();

            if (game.isFinished() != -1) {
                break;
            }
        }

        // prints the result -> 1 if player 1 wins, 2 if player 2 wins, or 0 if tied
        System.out.println(game.isFinished());
    }

}
