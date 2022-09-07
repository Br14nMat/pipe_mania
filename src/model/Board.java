package model;

public class Board {

    private Pipe head, tail;
    private Pipe source, drain;
    public final int ROWS = 8;
    public final int COLUMNS = 8;

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
            newPipe.setValue("X");

            createBox(current, newPipe, id + 1);

        }

    }

    public void setUpBoard(){
        head = new Pipe(1);
        head.setValue("X");

        tail = new Pipe(ROWS * COLUMNS);
        tail.setValue("X");

        //this call creates all the boxes
        createBox(tail, head, 2);

        //disconnect head and tail
        head.setPrevious(null);
        tail.setNext(null);

        int sourcePosition = (int)(Math.random()*(ROWS * COLUMNS) + 1);
        int drainPosition = (int)(Math.random()*(ROWS * COLUMNS) + 1);

        source = searchById(sourcePosition);
        source.setValue("F");
        drain = searchById(drainPosition);
        drain.setValue("D");

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

    public void putPipe(int row, int column, String pipe){

        int position = (row * COLUMNS) + (column + 1);

        searchById(position).setValue(pipe);

    }

    public boolean simulateFlow(){
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

    public Pipe getSource() {
        return source;
    }

    public void setSource(Pipe source) {
        this.source = source;
    }

    public Pipe getDrain() {
        return drain;
    }

    public void setDrain(Pipe drain) {
        this.drain = drain;
    }
}
