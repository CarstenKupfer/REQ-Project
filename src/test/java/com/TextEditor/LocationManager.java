package com.TextEditor;

public class LocationManager {
   public String locations;

   public Charger Station = new Charger ();

   public Location  openNewLocation(String name)
   {
       Location returnvalue = new Location (name);
       return returnvalue;

   }
public String CheckstationStatus()
{
    return Station.Status;
}

    }

