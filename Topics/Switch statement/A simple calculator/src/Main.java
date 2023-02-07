import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long v1 = scanner.nextInt();
        String op = scanner.next();
        long v2 = scanner.nextInt();
        switch(op){
            case "+":
                System.out.println(v1+v2);
                break;
            case "-":
                System.out.println(v1-v2);
                break;
            case "/":
                if(v2==0){
                    System.out.println("Division by 0!");
                    break;
                }
                System.out.println(v1/v2);
                break;
            case "*":
                System.out.println(v1*v2);
                break;
            default: System.out.println("Unknown operator");
        }
    }
}

