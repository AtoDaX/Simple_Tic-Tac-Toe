package tictactoe;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

            boolean endFlag = false;
            Scanner scanner = new Scanner(System.in);
            int turn = 1;
            String currGame = "_________";
            String line1 = currGame.charAt(0) + " " + currGame.charAt(1) + " " + currGame.charAt(2);
            String line2 = currGame.charAt(3) + " " + currGame.charAt(4) + " " + currGame.charAt(5);
            String line3 = currGame.charAt(6) + " " + currGame.charAt(7) + " " + currGame.charAt(8);
            System.out.println("---------");
            System.out.println("| " + line1 + " |");
            System.out.println("| " + line2 + " |");
            System.out.println("| " + line3 + " |");
            System.out.println("---------");
            char[][] xoxo = new char[3][3];
            for (int n = 0; n < 3; n++) {
                for (int k = 0; k < 3; k++) {
                    xoxo[n][k] = '_';
                }
            }
            while (turn <= 9) {
                int posY,posX;

                if (scanner.hasNextInt()) {
                    posY = scanner.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    continue;
                }
                if (scanner.hasNextInt()) {
                    posX = scanner.nextInt();
                } else {
                    System.out.println("You should enter numbers!");
                    continue;
                }
                boolean okFlag = true;
                switch (posChek(posY,posX, xoxo,turn)){
                    case "Coordinates should be from 1 to 3!":
                        System.out.println("Coordinates should be from 1 to 3!");
                        okFlag = false;
                        break;
                    case "This cell is occupied! Choose another one!":
                        System.out.println("This cell is occupied! Choose another one!");
                        okFlag = false;
                        break;
                    default:

                }
                if(!okFlag){
                    continue;
                }
                currGame = "";

                for (int n = 0; n < 3; n++) {
                    for (int k = 0; k < 3; k++) {
                        currGame += xoxo[n][k];
                    }
                }

                line1 = currGame.charAt(0) + " " + currGame.charAt(1) + " " + currGame.charAt(2);
                line2 = currGame.charAt(3) + " " + currGame.charAt(4) + " " + currGame.charAt(5);
                line3 = currGame.charAt(6) + " " + currGame.charAt(7) + " " + currGame.charAt(8);
                System.out.println("---------");
                System.out.println("| " + line1 + " |");
                System.out.println("| " + line2 + " |");
                System.out.println("| " + line3 + " |");
                System.out.println("---------");

                switch (status(currGame)){
                    case "Game not finished":
                        turn++;
                        break;
                    case "Draw":
                        System.out.println("Draw");
                        endFlag = true;
                        break;
                    case "X wins":
                        System.out.println("X wins");
                        endFlag = true;
                        break;
                    case "O wins":
                        System.out.println("O wins");
                        endFlag = true;
                        break;
                    default:
                        System.out.println("wtf");
                        break;


                }
                if (endFlag){
                    break;
                }

            }



    }



    public static String status(String currGame) {
        char winner = ' ';
        int x = 0, y = 0;
        boolean winFlag = false;
        for (int n = 0; n < 3; n++) {
            if ((currGame.charAt(x) == currGame.charAt(x + 1)) && (currGame.charAt(x) == currGame.charAt(x + 2)) && (currGame.charAt(x) != '_')) {
                winFlag = true;
                winner = currGame.charAt(x);
                break;
            } else if ((currGame.charAt(y) == currGame.charAt(y + 3)) && (currGame.charAt(y) == currGame.charAt(y + 6)) && (currGame.charAt(n) != '_')) {
                winFlag = true;
                winner = currGame.charAt(n);
                break;
            }
            x += 3;
            y += 1;
        }

        if ((currGame.charAt(0) == currGame.charAt(4)) && (currGame.charAt(0) == currGame.charAt(8)) && (currGame.charAt(0) != '_')) {
            winFlag = true;
            winner = currGame.charAt(0);
        } else if ((currGame.charAt(2) == currGame.charAt(4)) && (currGame.charAt(2) == currGame.charAt(6)) && (currGame.charAt(0) != '_')) {
            winFlag = true;
            winner = currGame.charAt(2);
        }

        boolean drawFlag = false;
        if ((!currGame.contains("_")) && (!winFlag)) {
            drawFlag = true;
        }

        boolean impossibleFlagDiff = false;
        int countX = 0, countO = 0;
        for (char element : currGame.toCharArray()) {
            if (element == 'X') countX++;
            if (element == 'O') countO++;

        }
        int diff = countO - countX;
        if (Math.abs(diff) >= 2) {
            impossibleFlagDiff = true;
        }

        boolean impflag1 = false, impflag2 = false;
        boolean impossibleFlagWin = false;
        int xx = 0, yy = 0;
        for (int n = 0; n < 3; n++) {
            if ((currGame.charAt(xx) == currGame.charAt(xx + 1)) && (currGame.charAt(xx) == currGame.charAt(xx + 2)) && (currGame.charAt(xx) != '_')) {
                if (impflag1) {
                    impflag2 = true;
                } else {
                    impflag1 = true;
                }
            }
            if (((currGame.charAt(yy) == currGame.charAt(yy + 3)) && (currGame.charAt(yy) == currGame.charAt(yy + 6)) && (currGame.charAt(yy) != '_'))) {
                if (impflag1) {
                    impflag2 = true;
                } else {
                    impflag1 = true;
                }
            }
            xx += 3;
            yy += 1;
        }
        if (impflag1 && impflag2) {
            impossibleFlagWin = true;
        }



        if (impossibleFlagWin || impossibleFlagDiff) {
            return "Impossible";
        } else if (drawFlag) {
            return "Draw";
        } else if (winFlag) {
            return winner + " wins";
        } else {
            return "Game not finished";
        }

        }

        public static String posChek(int posX, int posY, char xoxo[][],int turn){

                if (posX > 3 || posX < 1 || posY > 3 || posY < 1) {
                    return "Coordinates should be from 1 to 3!";

                }
                if (xoxo[posX - 1][posY - 1] != '_') {
                    return "This cell is occupied! Choose another one!";

                } else {
                    if (turn%2==1){
                        xoxo[posX - 1][posY - 1] = 'X';
                    }else {
                        xoxo[posX - 1][posY - 1] = 'O';
                    }

                    return "";
                }
        }

}
