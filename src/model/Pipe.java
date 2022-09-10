package model;

public class Pipe {

    private int id;
    private String value;
    private Pipe next;
    private Pipe previous;

    public Pipe(int id){
        this.id = id;
    }

    public Pipe(String value){
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Pipe getNext() {
        return next;
    }

    public void setNext(Pipe next) {
        this.next = next;
    }

    public Pipe getPrevious() {
        return previous;
    }

    public void setPrevious(Pipe previous) {
        this.previous = previous;
    }
}
