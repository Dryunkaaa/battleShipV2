import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int countOfShips = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of ships : ");
        countOfShips = scanner.nextInt();
        for (int i = 0; i<countOfShips;i++){
            String name = "Ship" + (i+1);
            Ship.getShips().add(new Ship(name));
        }
        synchronized (Ship.getShips()){
            for (Ship ship : Ship.getShips()) ship.start();

        }
    }
}
