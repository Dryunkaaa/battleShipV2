import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Ship extends Thread {

    private static List<Ship> ships = Collections.synchronizedList(new ArrayList<>());
    // temp variable to recharge ship
    private static Ship currentShip = null;
    private static Random random = new Random();

    private final static float minHp = 50.0f;
    private final static float maxHp = 100.0f;
    private final static float minDamage = 10.0f;
    private final static float maxDamage = 30.0f;
    private final static float minCooldown = 0.1f;
    private final static float maxCooldown = 2.0f;

    private float healPoints;
    private float damage;
    private float cooldown;

    public Ship(String name) {
        initFields();
        this.setName(name);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (ships) {
                if (this.isInterrupted()) break;
                if (ships.size() != 1) {
                    if (currentShip != null) currentShip.recharge(currentShip);
                    Ship target = getRandomShip(this);
                    shootAShip(target);
                } else {
                    System.out.println(this.getName() + " WIN!!!");
                    break;
                }
            }
        }
    }

    private void shootAShip(Ship target) {
        currentShip = this;
        float targetHp = target.getHealPoints();
        float currentShipDamage = this.getDamage();
        target.setHealPoints(targetHp - currentShipDamage);
        System.out.println(this.getName() + " SHOOT TO " + target.getName());
        checkDeathStatus(target);
    }

    private void recharge(Ship ship) {
        System.out.println(ship.getName() + " IS RECHARGING ...");
        try {
            ship.sleep((long) (ship.getCooldown() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkDeathStatus(Ship target) {
        if (target.getHealPoints() <= 0) {
            target.interrupt();
            ships.remove(target);
            System.out.println(target.getName() + " KILLED BY " + this.getName());
        }
    }

    private Ship getRandomShip(Ship thisShip) {
        List<Ship> listOfShips = new ArrayList<>();
        listOfShips.addAll(ships);
        listOfShips.remove(thisShip);
        int randomIndex = random.nextInt(listOfShips.size());
        return listOfShips.get(randomIndex);
    }

    private void initFields() {
        float healPoints = random.nextFloat() * (maxHp - minHp) + minHp;
        float damage = random.nextFloat() * (maxDamage - minDamage) + minDamage;
        float cooldown = random.nextFloat() * (maxCooldown - minCooldown) + minCooldown;
        this.healPoints = healPoints;
        this.damage = damage;
        this.cooldown = cooldown;
    }

    public static List<Ship> getShips() {
        return ships;
    }

    public static void setShips(List<Ship> ships) {
        Ship.ships = ships;
    }

    public float getHealPoints() {
        return healPoints;
    }

    public void setHealPoints(float healPoints) {
        this.healPoints = healPoints;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getCooldown() {
        return cooldown;
    }

    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "healPoints=" + healPoints +
                ", damage=" + damage +
                ", cooldown=" + cooldown +
                '}';
    }
}
