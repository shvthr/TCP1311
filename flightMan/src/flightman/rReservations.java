package flightman;

import java.util.Date;

public class rReservations {
    public int id;
    public rCustomers customer;
    public rFlights flightId;
    public Date bookingDate;
    public Date travelingDate;
    public boolean mealOption;

    rReservations(){
        id = 0;
        customer = new rCustomers();
        flightId = new rFlights();
        bookingDate = new Date();
        travelingDate = new Date();
        mealOption = false;
    }

    rReservations(int Id, int CustomerId, int FlightId, Date BookingDate, Date TravelingDate, boolean MealOption){
        id = Id;
        
        customer = new DsCustomers().getRowById(CustomerId);
        flightId = new DsFlights().getRowById(FlightId);
        bookingDate = BookingDate;
        travelingDate = TravelingDate;
        mealOption = MealOption;
    }
}
