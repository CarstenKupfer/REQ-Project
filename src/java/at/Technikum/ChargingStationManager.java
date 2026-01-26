package at.Technikum;

public class ChargingStationManager {

    public Invoice startCharging(LocationManager lm,
                                 CustomerManager cm,
                                 InvoiceManager im,
                                 String locationName,
                                 String chargerId,
                                 String customerId,
                                 int kwh) {

        Charger c = lm.findChargerById(chargerId);
        if (c == null) throw new IllegalStateException("Charger not found");

        if ("out_of_order".equals(c.Status)) throw new IllegalStateException("Rejected");

        double price = "DC".equals(c.Type) ? lm.getDcPrice(locationName) : lm.getAcPrice(locationName);
        double totalCost = kwh * price;
        int costInt = (int) Math.round(totalCost);

        if (cm.getBalance(customerId) < costInt) throw new IllegalStateException("Rejected");

        c.updateStatus("occupied");
        cm.deduct(customerId, costInt);

        return im.createInvoice(totalCost, customerId, locationName, chargerId, kwh);
    }

    public NetworkOverview getOverview(Location loc) {
        NetworkOverview o = new NetworkOverview();
        for (int i = 0; i < loc.Chargers.length; i++) {
            Charger c = loc.Chargers[i];
            if (c == null) continue;

            if ("free".equals(c.Status)) o.free++;
            else if ("occupied".equals(c.Status)) o.occupied++;
            else if ("out_of_order".equals(c.Status)) o.outOfOrder++;
        }

        o.acPrice = loc.acPrice;
        o.dcPrice = loc.dcPrice;

        return o;
    }
}
