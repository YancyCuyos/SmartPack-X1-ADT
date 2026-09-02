import java.util.ArrayList;

public class SmartPack {

    // =========================
    // ATTRIBUTES / DATA
    // =========================
    private String owner;
    private double battery;
    private double capacity;
    private double currentLoad;
    private boolean locked;
    private boolean gpsOn;
    private boolean alarmOn;
    private ArrayList<String> items;

    // =========================
    // CONSTRUCTOR
    // =========================
    public SmartPack(String owner, double capacity) {
        this.owner = owner;
        this.capacity = capacity;
        this.battery = 50;
        this.currentLoad = 0;
        this.locked = true;
        this.gpsOn = false;
        this.alarmOn = false;
        this.items = new ArrayList<>();
    }

    // =========================
    // OPERATIONS / METHODS
    // =========================

    public void unlock() {
        locked = false;
    }

    public void lock() {
        locked = true;
    }

    public boolean isLocked() {
        return locked;
    }

    public void addItem(String item, double weight) {

        if (locked) {
            return;
        }

        if (currentLoad + weight > capacity) {
            return;
        }

        items.add(item);
        currentLoad += weight;
    }

    public void removeItem(String item, double weight) {

        if (items.remove(item)) {
            currentLoad -= weight;

            if (currentLoad < 0) {
                currentLoad = 0;
            }
        }
    }

    // FUTURISTIC FEATURE 1
    // Solar Charging
    public void solarCharge() {

        battery += 20;

        if (battery > 100) {
            battery = 100;
        }
    }

    // FUTURISTIC FEATURE 2
    // GPS Tracking
    public void toggleGPS() {
        gpsOn = !gpsOn;
    }

    // FUTURISTIC FEATURE 3
    // Anti-Theft Alarm
    public void triggerAlarm() {
        alarmOn = true;
    }

    public void turnOffAlarm() {
        alarmOn = false;
    }

    // Smart Weight Monitoring
    public boolean isOverloaded() {
        return currentLoad > capacity * 0.80;
    }

    // =========================
    // GETTERS
    // =========================

    public String getOwner() {
        return owner;
    }

    public double getBattery() {
        return battery;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public boolean isGPSOn() {
        return gpsOn;
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }

    public ArrayList<String> getItems() {
        return items;
    }
}