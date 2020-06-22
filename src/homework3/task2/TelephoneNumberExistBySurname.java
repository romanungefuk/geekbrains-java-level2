package homework3.task2;

public class TelephoneNumberExistBySurname extends IllegalArgumentException{
    public TelephoneNumberExistBySurname() {
    }

    public TelephoneNumberExistBySurname(String s) {
        super(s);
    }
}
