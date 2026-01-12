package at.Technikum;

public class Charger {
    public String ChargerID;
    public String Type;
    public String Status;

    public Charger () {}
    public Charger (String ChargerID, String Type)
    {
        this.ChargerID=ChargerID;
        this.Type=Type;
    }

    public void updateStatus(String Status) {this.Status = Status;}
    public String toString() {
        return ChargerID + ";" + Type + ";" + Status;
    }

}

