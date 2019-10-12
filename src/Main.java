import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int countOfShips = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter count of ships : ");
        countOfShips = scanner.nextInt();
        for (int i = 0; i<countOfShips;i++){
            Ship ship = new Ship();
            ship.setName("Ship" + (i+1));
            Ship.getShips().add(ship);
        }

        synchronized (Ship.getShips()){
            for (Ship ship : Ship.getShips()) ship.start();

        }
//        System.out.println(Ship.getShips().get(0).getName() + " WIN!!!!");
    }
}
