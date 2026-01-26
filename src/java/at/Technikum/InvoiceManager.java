package at.Technikum;

public class InvoiceManager {
    public int counter = 1;

    public Invoice createInvoice(double totalCost, String customerId, String locationName, String chargerId, int kwh) {
        Invoice i = new Invoice();
        i.InvoiceID = "INV-" + counter++;
        i.totalCost = totalCost;
        i.Status = "open";
        i.CreationDate = "today";
        i.CustomerID = customerId;
        i.LocationName = locationName;
        i.ChargerID = chargerId;
        i.kWh = kwh;
        return i;
    }
}
