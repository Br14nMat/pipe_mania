package ui;
import model.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {

    private Scanner sc;
    private static Controller controller;

    public Main(){
        sc = new Scanner(System.in);
        controller = new Controller();
    }

    public static void main(String[] args) {

        Main app = new Main();

        app.mainMenu();

    }

    public void mainMenu() throws InputMismatchException {

        boolean exit = false;
        int option = 0;

        while (!exit) {

            try {
                System.out.println("1. New game");
                System.out.println("2. Show scoreboard");
                System.out.println("3. Exit");

                option = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e){
                System.out.println("Enter a valid option.");
                sc.nextLine();
            }

            switch (option) {
                case 1:
                    System.out.println("Creating new game...");
                    newGame();
                    break;
                case 2:
                    System.out.println("Scoreboard: ");
                    showScoreboard();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 3");
            }
        }
    }


    public void newGame(){

        System.out.println("Type the nickname: ");
        String nick = sc.nextLine();

        controller.newGame(nick);

        System.out.println(controller.showBoard());

        int option = 0;

        gameMenu();

    }

    public void gameMenu() throws InputMismatchException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            try {
                System.out.println("1. Put pipe");
                System.out.println("2. Simulate flow");
                System.out.println("3. Exit");

                option = sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException e) {
                System.out.println("Enter a valid option.");
                sc.nextLine();
            }

            switch (option) {
                case 1:
                    putPipe();
                    break;
                case 2:
                    System.out.println("Simulating flow");
                    simulateFlow();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Enter a number between 1 and 3");
            }
        }


    }

    public void showScoreboard(){
        System.out.println(controller.showScoreboard());
    }

    public void putPipe() throws InputMismatchException {

        System.out.println("Type in the position where you want to place the pipe");

        int rowPipe;
        int columnPipe;

        try {

            do{
                System.out.println("First write the row number between 0 and 7");
                rowPipe = sc.nextInt();
                sc.nextLine();

            }while(rowPipe < 0 || rowPipe > 7 );

            do{
                System.out.println("Enter the column number between 0 and 7");
                columnPipe = sc.nextInt();
                sc.nextLine();

            }while(columnPipe < 0 || columnPipe > 7 );

            int typeOfPipe = typePipe();

            System.out.println(controller.putPipe(rowPipe, columnPipe, typeOfPipe));
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid option");
            sc.nextLine();
        }


        System.out.println(controller.showBoard());
        gameMenu();
    }

    public   int typePipe() throws InputMismatchException {

        System.out.println("Choose the type of pipe");

        boolean exit = false;
        int pipeType = 0;

        try {

            do{
                System.out.println("1. = horizontal");
                System.out.println("2. || vertical");
                System.out.println("3. o circular");
                System.out.println("4. x delete");

                pipeType = sc.nextInt();
                sc.nextLine();

            }while (pipeType < 0 || pipeType > 4);
        }
        catch(InputMismatchException e){
            System.out.println("Enter a valid option");
            sc.nextLine();
        }

        return pipeType;

    }


    public void simulateFlow(){

        try {
            boolean isCorrect = controller.simulateFlow();

            if(isCorrect){
                System.out.println("Congratulations, you won!");
                mainMenu();
            }else {
                System.out.println("Sorry, the piping does not work");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

}
