import java.security.KeyStore;
import java.util.*;

public class HomeWork5 {
    public static void FullMap(Map<String, List<String>> phoneBook, HashMap<String, Integer> count){
        String name = "";
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 6; i++) {
            System.out.println("Введите фамилию:");
            name = sc.nextLine(); //считываем и запоминаем фамилию
            System.out.println("Введите номер:");
            String number = sc.nextLine(); //считываем и запоминаем номер

            if (!phoneBook.containsKey(name)){ //если в словаре еще нет такой фамилии
                List<String> phone = new ArrayList<>();
                phone.add(number); //добавляем номер в список с номерами
                phoneBook.put(name, phone); //добавляем фамилию и список с номерами в словарь
                count.put(name, 1); //количество телефонов с этой фамилией = 1
            }
            else{ //если в словаре уже есть такая фамилия
                List<String> phone = new ArrayList<>();
                phone = phoneBook.get(name); //вытаксиваем все имеющиеся номера по фамилии
                phone.add(number); //добавляем новый номер
                phoneBook.put(name,phone); //кладем все это обратно в словарь
                count.put(name, phone.size()); //обновляем значение в словаре, хранящем количество номеров по фамилии
            }
        }
        sc.close();
    }
    public static String getKeyByValue(HashMap<String, Integer> map, int value) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) { //если есть значение равное заданному, забираем его ключ
                return entry.getKey();
            }
        }
        return null;
    }

    public static void PrintMap(HashMap<String, List<String>> phoneBook, HashMap<String, Integer> count){
        Collection<Integer> some = count.values(); //вытаскиваем количетсво номеров всех фамилий
        ArrayList<Integer> list = new ArrayList<>(some);
        Collections.sort(list, Collections.reverseOrder()); //сортируем их по убыванию
        
        for (int i = 0; i < list.size(); i++) {
            String key = getKeyByValue(count, list.get(i)); //находим по значению ключ в словаре с фамилиями и количеством, запоминаем
            count.remove(key); //удаляем на случай появления людей с равным количеством номеров

            List<String> phone = new ArrayList<>();
            phone = phoneBook.get(key); //по этому ключу забираем все телефоны
            for (int j = 0; j < phone.size(); j++) {
                System.out.printf(key + " " + phone.get(j) + "\n"); //и выводим их по одному в строке
            }
        }
    }
    
    public static void main(String[] args) {
        HashMap<String, List<String>> phoneBook = new HashMap<>(); //словарь для хранения номеров и фамилий
        HashMap<String, Integer> count = new HashMap<>(); //словарь для хранения фамилий и количества номеров у них

        FullMap(phoneBook, count); //заполняем
        
        PrintMap(phoneBook, count); //выводим
        
    }
}
