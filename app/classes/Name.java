package app.classes;


public class Name {
    String firstName, lastName, middleName;

    public Name(String firstName, String lastName, String middleName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFullName(){
        return this.firstName + " " + this.middleName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}