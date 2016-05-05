package flightman;

public class rCustomers {
    public int id;
    public String ssid;
    public String firstName;
    public String lastName;
    public String creditCard;
    public String cvc2;
    public String userName;
    public String password;

    rCustomers(){
        id = 0;
        ssid = "";
        firstName = "";
        lastName = "";
        creditCard = "";
        cvc2 = "";
        userName = "";
        password = "";
    }

    rCustomers(int Id, String SSID, String FirstName, String LastName, String CreditCard, String CVC2, String UserName, String Password){
        id = Id;
        ssid = SSID;
        firstName = FirstName;
        lastName = LastName;
        creditCard = CreditCard;
        cvc2 = CVC2;
        userName = UserName;
        password = Password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
