package at.Technikum;

public class MainApp {

    private final LocationManager locationManager;
    private final CustomerManager customerManager;
    private final InvoiceManager invoiceManager;
    private final ChargingStationManager chargingStationManager;

    public MainApp() {
        locationManager = new LocationManager();
        customerManager = new CustomerManager();
        invoiceManager = new InvoiceManager();
        chargingStationManager = new ChargingStationManager();
    }

    public Location getLocation(String name) {
        return locationManager.getLocation(name);
    }

    public void deleteLocation(String locationName) {
        Location l = locationManager.getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");
        l.delete();
    }

    public void copyLocation(String newLocation, String baseLocation) {
        Location base = locationManager.getLocation(baseLocation);
        if (base == null) throw new IllegalStateException("Base location not found");
        locationManager.openNewLocation(newLocation);
        Location copy = locationManager.getLocation(newLocation);

        for (int i = 0; i < base.Chargers.length; i++) {
            if (base.Chargers[i] != null) {
                Charger c = base.Chargers[i];
                copy.Chargers[i] = new Charger(c.ChargerID, c.Type);
                copy.Chargers[i].Status = c.Status;
            }
        }
    }
    public void createLocation(String name) {
        locationManager.openNewLocation(name);
    }

    public void setPrices(String locationName, double ac, double dc) {
        locationManager.setPrices(locationName, ac, dc);
    }

    public double getAcPrice(String locationName) { return locationManager.getAcPrice(locationName); }
    public double getDcPrice(String locationName) { return locationManager.getDcPrice(locationName); }

    public void registerCustomer(String name, String customerId) {
        customerManager.registerAccount(name, customerId);
    }

    public void setBalance(String customerId, int balance) {
        customerManager.setBalance(customerId, balance);
    }

    public int getBalance(String customerId) {
        return customerManager.getBalance(customerId);
    }

    public void addCharger(String locationName, String chargerId, String type, String status) {
        locationManager.addCharger(locationName, chargerId, type);
        locationManager.setChargerStatus(locationName, chargerId, status);
    }

    public Charger findCharger(String chargerId) {
        return locationManager.findChargerById(chargerId);
    }

    public Invoice startCharging(String locationName, String chargerId, String customerId, int kwh) {
        return chargingStationManager.startCharging(
                locationManager, customerManager, invoiceManager,
                locationName, chargerId, customerId, kwh
        );
    }

    public void removeCharger(String locationName, String chargerId) {
        locationManager.removeCharger(locationName, chargerId);
    }

    public NetworkOverview getOverview(String locationName) {
        Location loc = locationManager.getLocation(locationName);
        return chargingStationManager.getOverview(loc);
    }
}
