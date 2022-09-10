package model;

import exception.BoardOutOFBoundsException;
import exception.PipeException;
import exception.SourceException;

public class Board {

    private Pipe head, tail;
    private Pipe source, drain;
    public final int ROWS = 8;
    public final int COLUMNS = 8;

    public final String HORIZONTAL = "=";
    public final String VERTICAL = "||";
    public final String CIRCULAR = "o";
    public final String VOID = "x";
    public final String SOURCE = "F";

    public final String DRAIN = "D";

    public Board(){}

    private void createBox(Pipe previous, Pipe current,  int id){

        if(id == (ROWS * COLUMNS)){
            current.setPrevious(previous);
            previous.setNext(current);

            current.setNext(tail);
            tail.setPrevious(current);

        }else{
            current.setPrevious(previous);
            previous.setNext(current);

            Pipe newPipe = new Pipe(id);
            newPipe.setValue(VOID);

            createBox(current, newPipe, id + 1);

        }

    }

    public void setUpBoard(){
        head = new Pipe(1);
        head.setValue(VOID);

        tail = new Pipe(ROWS * COLUMNS);
        tail.setValue(VOID);

        //this call creates all the boxes
        createBox(tail, head, 2);

        //disconnect head and tail
        head.setPrevious(null);
        tail.setNext(null);

        int sourcePosition = (int)(Math.random()*(ROWS * COLUMNS) + 1);
        int drainPosition = (int)(Math.random()*(ROWS * COLUMNS) + 1);

        source = searchById(sourcePosition);
        source.setValue(SOURCE);
        drain = searchById(drainPosition);
        drain.setValue(DRAIN);

    }

    private Pipe searchById(Pipe current, int target){

        if(target < 1 || target > (COLUMNS * ROWS)){
            return null;
        }

        if(target == current.getId()){
            return current;
        }

        if(target < current.getId()){
            return searchById(current.getPrevious(), target);
        }else {
            return searchById(current.getNext(), target);
        }

    }

    public Pipe searchById(int id){
        return searchById(head, id);
    }


    public String showBoard(){

        return showBoard(head, "");

    }

    private String showBoard(Pipe current, String board){

        if(current == tail){
            board+= current.getValue();
            return board;
        }else {
            board+= current.getValue() + "  ";

            if(current.getId() % COLUMNS == 0){
                board+= "\n";
            }

            return showBoard(current.getNext(), board);
        }

    }

    public void putPipe(int row, int column, String pipe) throws PipeException{

        int position = (row * COLUMNS) + (column + 1);

        if(position != source.getId() && position != drain.getId()){
            searchById(position).setValue(pipe);
        }else {
            throw new PipeException("the position of the source or the drain cannot be changed");
        }



    }

    public boolean simulateFlow() throws Exception {
        return simulateFlow(this.source, this.source);
    }

    private boolean simulateFlow (Pipe previous, Pipe current) throws Exception{

        if(current == null || current.getValue().equalsIgnoreCase(VOID)){
            return false;
        }

        if(current == drain){
            return true;
        }

        if(current == source){

            Pipe topBox = searchById(source.getId() - COLUMNS);
            Pipe bottomBox = searchById(source.getId() + COLUMNS);
            Pipe rightBox = source.getNext();
            Pipe leftBox = source.getPrevious();


            if((rightBox.getValue().equalsIgnoreCase(HORIZONTAL) && leftBox.getValue().equalsIgnoreCase(HORIZONTAL)
            ||(topBox.getValue().equalsIgnoreCase(VERTICAL) && bottomBox.getValue().equalsIgnoreCase(VERTICAL)))){
                throw new SourceException("The source can only be connected to one pipe.");
            }

            if(rightBox.getValue().equalsIgnoreCase(HORIZONTAL)){
                return simulateFlow(current, rightBox);
            }else if(leftBox.getValue().equalsIgnoreCase(HORIZONTAL)){
                return simulateFlow(current, leftBox);
            }else if(topBox.getValue().equalsIgnoreCase(VERTICAL)){
                return simulateFlow(current, topBox);
            }else if(bottomBox.getValue().equalsIgnoreCase(VERTICAL)){
                return simulateFlow(current, bottomBox);
            }else {
                throw new SourceException("The source is not connected correctly");
            }

        }

        if (current.getValue().equalsIgnoreCase(HORIZONTAL)) {

            if(previous.getValue().equalsIgnoreCase(VERTICAL)){
                return false;
            }

            if(previous.getId() < current.getId()){
                if(previous.getId() % COLUMNS != 0 ){
                    return simulateFlow(current, current.getNext());
                }else{
                    throw new BoardOutOFBoundsException("Water overflowed from the board!");
                }
            }else {
                if((previous.getId() - 1) % COLUMNS != 0 ){
                    return simulateFlow(current, current.getPrevious());
                }else{
                    throw new BoardOutOFBoundsException("Water overflowed from the board!");
                }

            }

        }

        if(current.getValue().equalsIgnoreCase(VERTICAL)){

            if(previous.getValue().equalsIgnoreCase(HORIZONTAL)){
                return false;
            }

            if(previous.getId() < current.getId()){
                return simulateFlow(current, searchById(current.getId() + COLUMNS));
            }else {
                return simulateFlow(current, searchById(current.getId() - COLUMNS));
            }

        }

        if(current.getValue().equalsIgnoreCase(CIRCULAR) &&
                previous.getValue().equalsIgnoreCase(HORIZONTAL)){

            Pipe topBox = searchById(current.getId() - COLUMNS);
            Pipe bottomBox = searchById(current.getId() + COLUMNS);

            if(topBox == null || bottomBox == null){
                throw new BoardOutOFBoundsException("Water overflowed from the board!");
            }else{
                return simulateFlow(current, topBox) || simulateFlow(current, bottomBox);
            }

        }


        if(current.getValue().equalsIgnoreCase(CIRCULAR) &&
                previous.getValue().equalsIgnoreCase(VERTICAL)){

            Pipe rightBox = current.getNext();
            Pipe leftBox = current.getPrevious();


            if(leftBox.getId() % 8 == 0 && rightBox.getValue().equalsIgnoreCase(VOID)){
                throw new BoardOutOFBoundsException("Water overflowed from the board!");
            }else if((rightBox.getId() - 1) % 8 == 0 && leftBox.getValue().equalsIgnoreCase(VOID)){
                throw new BoardOutOFBoundsException("Water overflowed from the board!");
            }else {
                return simulateFlow(current, rightBox) || simulateFlow(current, leftBox);
            }


        }

        if(current.getValue().equalsIgnoreCase(CIRCULAR) &&
                previous.getValue().equalsIgnoreCase(CIRCULAR)){

            throw new PipeException("You cannot connect two circular pipes");
        }

        return false;

    }


    public Pipe getHead() {
        return head;
    }

    public void setHead(Pipe head) {
        this.head = head;
    }

    public Pipe getTail() {
        return tail;
    }

    public void setTail(Pipe tail) {
        this.tail = tail;
    }

    public Pipe getsource() {
        return source;
    }

    public void setsource(Pipe source) {
        this.source = source;
    }

    public Pipe getDrain() {
        return drain;
    }

    public void setDrain(Pipe drain) {
        this.drain = drain;
    }
}
