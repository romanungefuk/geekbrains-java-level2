package homework3.task2;

public class TelephoneBookApplication {

    public static void main(String[] args) {
        TelephoneBook tb = new TelephoneBook();
        tb.add("Ungefuk", "89778727719");
        //tb.add("Ungefuk", "89778727719"); // проверка исключения при внесении существуещего номера тому же пользователю
        tb.add("Ungefuk", "89778727710");
        //tb.add("Иванов", "89778727715"); // проверка исключения при попытке внести номер другого пользователя
        tb.add("Иванов", "89778727715");
        tb.get("Ungefuk");
        tb.get("Иванов");
    }
}
