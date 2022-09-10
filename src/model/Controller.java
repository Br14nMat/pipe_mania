package model;

import exception.PipeException;

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

    public String showBoard(){
        return this.getCurrentGame().showBoard();
    }

    public String putPipe(int row,int column, int pipeOption){

        String message = "";
        String pipe = "";

        switch (pipeOption){

            case 1:
                pipe = getLastBoard().HORIZONTAL;
                break;
            case 2:
                pipe = getLastBoard().VERTICAL;
                break;
            case 3:
                pipe = getLastBoard().CIRCULAR;
                break;
            case 4:
                pipe = getLastBoard().VOID;
                break;

        }

        try {
            this.getCurrentGame().putPipe(row, column, pipe);
        }catch (PipeException e){
            message+= e.getMessage();
        }

        return message;

    }

    public boolean simulateFlow() throws Exception{

        boolean isCorrect = this.getCurrentGame().simulateFlow();
        if(isCorrect){
            this.getCurrentGame().calculateScore();
            scoreboard.add(this.getCurrentGame());
        }

        return isCorrect;

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

    public BST getScoreboard(){
        return this.scoreboard;
    }

}
