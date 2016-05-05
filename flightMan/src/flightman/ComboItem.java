package flightman;

public class ComboItem{
    public int id;
    public String name;
    ComboItem(int Id, String Name){
        id = Id;
        name = Name;
    }

    public String toString() {
        return name;
    }
}
