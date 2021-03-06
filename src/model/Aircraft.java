package model;

import java.util.Set;

/**
 * Created by QQZhao on 3/5/17.
 */
public class Aircraft {

    private String model;
    private int capacity;

    private Set<Airline> airlineSet;

    private Set<Flight> flightSet;

    public Set<Flight> getFlightSet() {
        return flightSet;
    }

    public void setFlightSet(Set<Flight> flightSet) {
        this.flightSet = flightSet;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Airline> getAirlineSet() {
        return airlineSet;
    }

    public void setAirlineSet(Set<Airline> airlineSet) {
        this.airlineSet = airlineSet;
    }
}
