package flightman;

public class rFlights {
    public int id;
    public String origin;
    public String destination;
    public boolean stopOrTransit;
    public boolean availablity;

    rFlights(){
        id = 0;
        origin = "";
        destination = "";
        stopOrTransit = false;
        availablity = true;
    }

    rFlights(int Id, String Origin, String Destination, boolean StopOrTransit, boolean Availablity){
        id = Id;
        origin = Origin;
        destination = Destination;
        stopOrTransit = StopOrTransit;
        availablity = Availablity;
    }

    @Override
    public String toString() {
        return origin + " - " + destination;
    }
}
