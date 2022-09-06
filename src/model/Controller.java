package model;

import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    private ArrayList<Player> players;
    private BST scoreboard;
    private Board board;

    public Controller(){
        players = new ArrayList<>();
        scoreboard = new BST();
        board = new Board();
    }

    public void newGame(String nickname){

        players.add(new Player(nickname));
        board.setUpBoard();
    }

    public Player searchPlayer(String nickname){

        Optional<Player> found = players.stream()
                .filter(p -> p.getNickname().equals(nickname))
                .findFirst();

        return found.isPresent()
                ? found.get()
                : null;

    }

    public String showBoard(){
        return this.board.showBoard();
    }

    public void putPipe(int row,int column, String pipe){
        this.board.putPipe(row, column, pipe);
    }

    public void simulateFlow(){
        this.board.simulateFlow();
    }

    public String showScoreboard(){

        return this.scoreboard.showScoreboard();

    }

    public Board getBoard(){
        return  this.board;
    }


}
