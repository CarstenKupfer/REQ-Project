package at.Technikum;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        MainApp mainApp = new MainApp();

        Scanner sc = new Scanner(System.in);

        System.out.print("Location: ");
        String location = sc.nextLine();

        System.out.print("AC price: ");
        double ac = Double.parseDouble(sc.nextLine());

        System.out.print("DC price: ");
        double dc = Double.parseDouble(sc.nextLine());

        System.out.print("Customer name: ");
        String cname = sc.nextLine();

        System.out.print("Customer ID: ");
        String cid = sc.nextLine();

        System.out.print("Balance: ");
        int bal = Integer.parseInt(sc.nextLine());

        System.out.print("Charger ID: ");
        String chargerId = sc.nextLine();

        System.out.print("Type (AC/DC): ");
        String type = sc.nextLine();

        System.out.print("kWh: ");
        int kwh = Integer.parseInt(sc.nextLine());

        mainApp.createLocation(location);
        mainApp.setPrices(location, ac, dc);
        mainApp.registerCustomer(cname, cid);
        mainApp.setBalance(cid, bal);
        mainApp.addCharger(location, chargerId, type, "free");

        Invoice inv = mainApp.startCharging(location, chargerId, cid, kwh);
        NetworkOverview ov = mainApp.getOverview(location);

        System.out.println("Invoice: " + inv.InvoiceID + " status= " + inv.Status + " totalCost= " + inv.totalCost);
        System.out.println("Balance now: " + mainApp.getBalance(cid));
        System.out.println("Network overview: free= " + ov.free + " occupied= " + ov.occupied + " out_of_order= " + ov.outOfOrder);
    }
}
