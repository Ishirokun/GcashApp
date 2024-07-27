package app.classes;


public class Name{
    String firstName, lastName, middleName;

    Name(String firstName, String lastName, String middleName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFullName(){
        return this.firstName + " " + this.middleName + " " + this.lastName + ", " + this.suffix;
    }

    @Override
    public String toString() {
        return getFullName();
    }
}