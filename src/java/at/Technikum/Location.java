package at.Technikum;

public class Location {
    public String name;
    public String status;
    public double Longitude;
    public double Altitude;
    public Charger[] Chargers = new Charger [10];
    public double acPrice;
    public double dcPrice;

    public Location(){}

    public Location(String name){
        this.name = name;
        status = "new";
    }

    public Location(String name,double Longitude, double Altitude)
    {
        this.name = name;
        this.Longitude = Longitude;
        this.Altitude = Altitude;
        status = "new";
    }

    public Location(Location location) {
        this.name = location.name;
        this.status = location.status;
        this.Longitude = location.Longitude;
        this.Altitude = location.Altitude;
        this.acPrice = location.acPrice;
        this.dcPrice = location.dcPrice;

        for (int i = 0; i < location.Chargers.length; i++) {
            this.Chargers[i] = location.Chargers[i];
        }
    }


    public void delete(){this.status = "deleted";}

    public String getStatus () {return this.status;}

    @Override
    public String toString() {
        return name + ";" + Longitude + ";" + Altitude + ";" + status;
    }
}

