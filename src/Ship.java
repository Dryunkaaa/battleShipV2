import java.util.ArrayList;
import java.util.List;

public class Ship extends Thread {

    private static List<Ship> ships = new ArrayList<>();

    private float healPoints;

    private float damage;

    private float cooldown;

    public Ship(){
        //init fields  : TODO
    }

    @Override
    public void run() {

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
}
