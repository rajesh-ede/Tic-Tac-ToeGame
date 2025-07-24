package Projects.Tic;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static String[] board;
    static String turn;

    static String Winner(){
        for(int r = 0; r < 8; r++){
            String line = switch (r) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };
            if(line.equals("XXX")){
                return "X";
            }else if(line.equals("OOO")){
                return "O";
            }
        }
        // It is used to check the draw Condition
        for(int a = 0; a < 9; a++){
            if(Arrays.asList(board).contains(String.valueOf(a+1))){
                break;
            } else if (a == 8) {
                return "draw";
            }
        }
        System.out.println(turn + " 's turn a slot number to place "+ turn+"in:");
        return null;
    }
    static void Board()
    {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | "
                + board[1] + " | " + board[2]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | "
                + board[4] + " | " + board[5]
                + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | "
                + board[7] + " | " + board[8]
                + " |");
        System.out.println("|---|---|---|");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;
        for(int i = 0; i < 9; i++){
            board[i] = String.valueOf(i+1);
        }
        System.out.println("welcome to the Tic Tac Toe ♥️");
        Board();
        System.out.println("Enter a slot number to place X in :");
        while (winner == null) {
            int num;
            try {
                num = sc.nextInt();
                if (!(num > 0 && num <= 9)) {
                    System.out.println("Invalid input; re-enter slot number:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; re-enter slot number:");
                sc.next(); // Clear invalid input
                continue;
            }

            if (board[num - 1].equals(String.valueOf(num))) {
                board[num - 1] = turn;
                turn = turn.equals("X") ? "O" : "X";
                Board();
                winner = Winner();
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("Game is Draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won!");
        }
        sc.close();
    }
}
