package model;

import java.util.ArrayList;
import java.util.Optional;

public class Controller {

    private ArrayList<Game> games;
    private BST scoreboard;


    public Controller(){
        games = new ArrayList<>();
        scoreboard = new BST();
    }

    public void newGame(String nickname){

        Game newGame = new Game(nickname);
        games.add(newGame);

    }

    public Game searchGame(String nickname){

        Optional<Game> found = games.stream()
                .filter(p -> p.getNickname().equals(nickname))
                .findFirst();

        return found.isPresent()
                ? found.get()
                : null;

    }

    public String showBoard(){
        return this.getCurrentGame().showBoard();
    }

    public void putPipe(int row,int column, String pipe){
        this.getCurrentGame().putPipe(row, column, pipe);
    }

    public void simulateFlow(){
        boolean response = this.getCurrentGame().simulateFlow();
    }

    public String showScoreboard(){

        return this.scoreboard.showScoreboard();

    }

    public Board getLastBoard(){
        return this.getCurrentGame().getBoard();
    }

    public Game getCurrentGame(){
        return games.get(games.size() - 1);
    }

}
