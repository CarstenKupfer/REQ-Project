package at.Technikum;

public class LocationManager {

    public Location[] locations = new Location[20];
    public int count = 0;

   public void openNewLocation(String name) {
       if(getLocation(name) != null) return;
       locations[count] = new Location(name);
       count++;
   }

   public Location getLocation(String name){
       for (int i = 0; i < count; i++) {
           if (locations[i] != null && locations[i].name.equals(name)) return locations[i];
       }
       return null;
   }

    public void setPrices(String locationName, double ac, double dc) {
        Location l = getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");
        l.acPrice = ac;
        l.dcPrice = dc;
    }

    public double getAcPrice(String locationName) {
        Location l = getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");
        return l.acPrice;
    }

    public double getDcPrice(String locationName) {
        Location l = getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");
        return l.dcPrice;
    }

    public void addCharger(String locationName, String chargerId, String type) {
        Location l = getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");

        for (int i = 0; i < l.Chargers.length; i++) {
            if (l.Chargers[i] == null) {
                l.Chargers[i] = new Charger(chargerId, type);
                return;
            }
        }
        throw new IllegalStateException("No slot");
    }

    public void setChargerStatus(String locationName, String chargerId, String status) {
        Location l = getLocation(locationName);
        if (l == null) throw new IllegalStateException("Location not found");

        for (int i = 0; i < l.Chargers.length; i++) {
            if (l.Chargers[i] != null && l.Chargers[i].ChargerID.equals(chargerId)) {
                l.Chargers[i].updateStatus(status);
                return;
            }
        }
        throw new IllegalStateException("Charger not found");
    }

    public Charger findChargerById(String chargerId) {
        for (int i = 0; i < count; i++) {
            Location l = locations[i];
            if (l == null) continue;

            for (int j = 0; j < l.Chargers.length; j++) {
                Charger c = l.Chargers[j];
                if (c != null && c.ChargerID.equals(chargerId)) return c;
            }
        }
        return null;
    }
}


