package at.Technikum;

public class Main {

    public static void main(String[] args) {


        LocationManager locationManager = new LocationManager();
        CustomerManager customerManager = new CustomerManager();
        InvoiceManager invoiceManager = new InvoiceManager();
        ChargingStationManager chargingStationManager = new ChargingStationManager();

        // Location + Pricing
        String locationName = "Vienna Center";
        locationManager.openNewLocation(locationName);
        locationManager.setPrices(locationName, 0.50, 0.80);

        // Charger werden hinzugefügt und auf free gesetzt
        locationManager.addCharger(locationName, "AC1", "AC");
        locationManager.setChargerStatus(locationName, "AC1", "free");

        locationManager.addCharger(locationName, "AC2", "AC");
        locationManager.setChargerStatus(locationName, "AC2", "free");

        // Customer + Balance
        customerManager.registerAccount("Alice", "C1");
        customerManager.setBalance("C1", 20);

        // 4) Charging starten (10 kWh insgesamt an AC1 = 10 x 0.50 = 5, 20 - 5 = 15 Balance bleibt übrig)
        Invoice invoice = chargingStationManager.startCharging(
                locationManager,
                customerManager,
                invoiceManager,
                locationName,
                "AC1",
                "C1",
                10
        );

        // Outputs
        Charger charger = locationManager.findChargerById("AC1");
        System.out.println("===Charging Station===");
        System.out.println("Location: " + locationName + " | AC price=" + locationManager.getAcPrice(locationName)
                + " | DC price=" + locationManager.getDcPrice(locationName));
        System.out.println("Charger AC1 status: " + (charger != null ? charger.Status : "not found"));
        System.out.println("Customer C1 balance: " + customerManager.getBalance("C1"));
        System.out.println("Invoice: " + invoice.InvoiceID + " | status=" + invoice.Status + " | totalCost=" + invoice.totalCost);

        // 6) Network status übersicht
        NetworkOverview overview = chargingStationManager.getOverview(locationManager.getLocation(locationName));
        System.out.println("Network overview: free=" + overview.free + " occupied=" + overview.occupied + " out_of_order=" + overview.outOfOrder);
    }
}
