package model;

public class Game {

    private String nickname;
    private double score;
    private int usedPipes;
    private Long elapsedTime;

    private Board board;

    public Game(String nickname){
        this.nickname = nickname;

        board = new Board();
        board.setUpBoard();
    }

    public double calculateScore(){
        return 0;
    }


    public String showBoard(){
        return this.board.showBoard();
    }

    public void putPipe(int row,int column, String pipe){
        this.board.putPipe(row, column, pipe);
    }

    public boolean simulateFlow(){
        return this.board.simulateFlow();
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getUsedPipes() {
        return usedPipes;
    }

    public void setUsedPipes(int usedPipes) {
        this.usedPipes = usedPipes;
    }

    public Long getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public Board getBoard(){
        return this.board;
    }

}
