package homework3.task2;

import java.util.ArrayList;
import java.util.HashMap;

public class TelephoneBook {

    private ArrayList<HashMap<String, String>> arrListTB = new ArrayList<>();

    public void add(String surname,String telephoneNumber) {
        HashMap<String, String> hm = new HashMap<>();
        hm.put(surname,telephoneNumber);
        if (arrListTB.contains(hm)) {
            throw new TelephoneNumberExistBySurname("У этого пользователя этот номер уже добавлен " +
                    "в телефонну книгу.");
        } else if (isTelephoneNumberAllreadyAdded(arrListTB, telephoneNumber)) {
            throw new TelephoneNumberExistByOtherSurname("Вы уверены, что хотите добавить именно телефонный номер "
                    + telephoneNumber + "? Этот телефонный номер уже присвоен другому лицу.");
        } else {
            this.arrListTB.add(hm);
        }

    }

    public void get(String surname) {
        System.out.println(surname + ":");
        for (HashMap<String, String> hm : this.arrListTB) {
            if (hm.containsKey(surname) && !hm.get(surname).isEmpty()) {
                System.out.println(hm.get(surname));
            }
        }
    }

    public boolean isTelephoneNumberAllreadyAdded(ArrayList<HashMap<String, String>> arrListTB, String telephoneNumber) {
        boolean telephoneNumberAlreadyAdded = false;
        for (HashMap<String, String> hm : arrListTB) {
            if (hm.containsValue(telephoneNumber)) {
                telephoneNumberAlreadyAdded = true;
            }
        }
        return telephoneNumberAlreadyAdded;
    }
}

