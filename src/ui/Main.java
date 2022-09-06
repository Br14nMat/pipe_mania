package ui;
import model.Controller;

import java.util.Scanner;

public class Main {

    private Scanner sc;
    private Controller controller;

    public Main(){
        sc = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {

        Controller test = new Controller();

        test.newGame("Brian");


        System.out.println(test.showBoard());

        test.putPipe(3, 2, "=");

        System.out.println("");

        System.out.println(test.showBoard());


    }



}
