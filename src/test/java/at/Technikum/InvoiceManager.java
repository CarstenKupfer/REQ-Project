package at.Technikum;

public class InvoiceManager {
    public int counter = 1;

    public Invoice createInvoice(double totalCost) {
        Invoice i = new Invoice();
        i.InvoiceID = "INV-" + counter;
        counter++;
        i.totalCost = totalCost;
        i.Status = "open";
        i.CreationDate = "today";
        return i;
    }
}
