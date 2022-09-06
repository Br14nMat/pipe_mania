package model;

public class Player {

    private String nickname;
    private double score;
    private int usedPipes;
    private Long elapsedTime;

    public Player(String nickname){
        this.nickname = nickname;
    }

    public double calculateScore(){
        return 0;
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
}
