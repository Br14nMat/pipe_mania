package model;

import exception.PipeException;

import java.util.Date;

public class Game {

    private String nickname;
    private int score;
    private int usedPipes;
    private int elapsedTime;

    private Date startTIme;

    private Board board;

    public Game(String nickname){
        this.nickname = nickname;
        this.usedPipes = 0;

        board = new Board();
        board.setUpBoard();

        startTIme = new Date(System.currentTimeMillis());
    }

    public void calculateScore(){
        calculateElapsedTime();
        this.score = usedPipes * 100 - (60 - elapsedTime ) * 10;
    }

    public void calculateElapsedTime(){

        Date now = new Date(System.currentTimeMillis());

        this.elapsedTime = (int) (now.getTime() - startTIme.getTime())/1000;

    }

    public String showBoard(){
        return this.board.showBoard();
    }

    public void putPipe(int row,int column, String pipe) throws PipeException {
        this.board.putPipe(row, column, pipe);
        usedPipes++;

    }

    public boolean simulateFlow() throws Exception {
        return this.board.simulateFlow();
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUsedPipes() {
        return usedPipes;
    }

    public void setUsedPipes(int usedPipes) {
        this.usedPipes = usedPipes;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Board getBoard(){
        return this.board;
    }

}
