package homework3.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WordsArray {

    public static void main(String[] args) {
        /*
        Создание массива и заполнение его 20-ю словами
         */
        List<String> wordsArray = new ArrayList<>(20);
        wordsArray.add("Армагедон");
        wordsArray.add("пастух");
        wordsArray.add("Армагедон");
        wordsArray.add("Армагедон");
        wordsArray.add("Машина");
        wordsArray.add("Фея");
        wordsArray.add("Флейта");
        wordsArray.add("стол");
        wordsArray.add("Стул");
        wordsArray.add("Телефон");
        wordsArray.add("Машина");
        wordsArray.add("фея");
        wordsArray.add("целеустремленность");
        wordsArray.add("упорство");
        wordsArray.add("честность");
        wordsArray.add("Мир");
        wordsArray.add("упорство");
        wordsArray.add("отзывчивость");
        wordsArray.add("эмпатия");
        wordsArray.add("любовь");
        Set<String> setWords = new TreeSet<>(); // коллекция для сохранения, вывода и подсчета уникальных слов
        for (String s : wordsArray) {
            setWords.add(s);
        }
        System.out.println("Список уникальных слов в коллекции:");
        for (String s : setWords) {
            System.out.println(s);
        }
        System.out.println("####################################################");
        System.out.println();
        System.out.println("Список уникальных слов в коллекции и их количество:");
        for (String uniqWordsAndQuantity : getQuantityForWords(wordsArray, setWords)) {
            System.out.println(uniqWordsAndQuantity);
        }
    }

    /**
     * Метод считает сколько раз слово встрецается в коллекции списка, создает новую строку,
     * состоящую из уникального слова и колличества его встречаемости в этом списке и
     * добавляет её в коллекцию TreeSet
     * @param wordsArray список всех слов
     * @param setWords список уникальных слов
     * @return коллекцию TreeSet с уникальными словами и количеством их встречаемости в списке
     */
    public static TreeSet<String> getQuantityForWords(List<String> wordsArray, Set<String> setWords) {
        TreeSet<String> setWordsAndQuantity = new TreeSet<>();
        for (String uniqWord : setWords) {
            int quantity = 0;
            for (String word : wordsArray) {
                if (uniqWord.equals(word)) {
                    quantity++;
                }
            }
            setWordsAndQuantity.add("Слово \"" + uniqWord + "\" встречается в коллекции " + quantity + " раз.");

        }
        return setWordsAndQuantity;
    }

}
