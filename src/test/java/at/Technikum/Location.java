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

    public Location(Location location)
    {
        this.name = location.name;
        this.status = location.status;
        this.Longitude = location.Longitude;
        this.Altitude = location.Altitude;
        for (int i=0;i <= 9;i++)
        {
        this.Chargers[i]=location.Chargers[i];
        }
    }

    public void Setname (String name) {this.name = name;}
    public void SetLongitude(double Longitude) {this.Longitude = Longitude;}
    public void SetAltitude(double Altitude) {this.Altitude = Altitude;}

    public void addfeature(String Type, String ChargerID,int number)
    {
        Chargers[number] = new Charger(ChargerID,Type);
        this.status="featureactive";
    }


    public void delete(){this.status = "deleted";}

    public boolean hasActiveSessions() {
        if (this.status != null)
        {return true;}
        else {
            return false;
        }
    }

    public String getStatus () {return this.status;}
    public String getName () {return this.name;}
    public double getAltitude() {return this.Altitude;}
    public double getLongitude() {return this.Longitude;}
    public Charger[] getChargers() {return this.Chargers;}

    @Override
    public String toString() {
        return name + ";" + Longitude + ";" + Altitude + ";" + status;
    }
}

