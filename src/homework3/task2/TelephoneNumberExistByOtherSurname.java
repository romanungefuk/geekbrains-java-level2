package homework3.task2;

public class TelephoneNumberExistByOtherSurname extends IllegalArgumentException{
    public TelephoneNumberExistByOtherSurname() {
    }

    public TelephoneNumberExistByOtherSurname(String s) {
        super(s);
    }
}
