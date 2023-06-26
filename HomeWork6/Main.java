import java.util.*;

public class Main {
    public static Deque<String> filter(HashMap<String, HashMap<String, Object>> noteList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Добро пожаловать в наш магазин! Мы поможем с выбором ноутбука. \n" +
                "Введите цифру, соответствующую необходимому критерию:\n" +
                "1 - ОЗУ\n" +
                "2 - Объем ЖД \n" +
                "3 - Операционная система\n" +
                "4 - Количество ядер\n" +
                "5 - Цена\n" +
                "6 - Цвет\n");
        String answer = sc.nextLine();
        int number = Integer.parseInt(answer); //первый фильтр по критерию
        filterCase(number);
        answer = sc.nextLine(); //второй фильтр по уточняющему критерию

        Deque<String> rezult = new LinkedList<>();
        if (((number > 0) && (number < 3)) || ((number > 3) && (number < 6))){ //если критерий не цвет
            int answ = Integer.parseInt(answer);
            for (String key : noteList.keySet()){ //идем по карте с ноутбуками, для каждого конкретного ноута достаем его параметры и сверяем нужный
                HashMap<String, Object> parameters = noteList.get(key);
                int filter = (int)parameters.get(forMap(number)); //смотрим что за параметр через switch
                if (number != 5) {
                    if (filter >= answ) rezult.addFirst(key);
                } else {
                    if (filter <= answ) rezult.addFirst(key); //цена по верхней границе
                }
            }
        }
        else if (number == 3) {
            int answ = Integer.parseInt(answer);
            String os = null;
            if (answ == 1) os = "Windows";
            else if (answ == 2) os = "Linux";
            else if (answ == 3) os = "MacOS";
            for (String key : noteList.keySet()) {
                HashMap<String, Object> parameters = noteList.get(key);
                String filter = parameters.get(forMap(number)).toString();
                if (os.equals(filter)) rezult.addFirst(key);
            }
        } 
        else if (number == 6){
            for (String key : noteList.keySet()) {
                HashMap<String, Object> parameters = noteList.get(key);
                String filter = parameters.get(forMap(number)).toString();
                if (answer.equals(filter)) rezult.addFirst(key);
            }
        }
        return rezult;
    }

    /**
     * Проверка соответствия введенного числа и параметра ноутбука
     * @param number
     * @return
     */
    private static String forMap(int number){
        String parameterKey = null;
        switch (number) {
            case 1:
                parameterKey = "RAM";
                break;
            case 2:
                parameterKey = "Hard";
                break;
            case 3:
                parameterKey = "OS";
                break;
            case 4:
                parameterKey = "Cores";
                break;
            case 5:
                parameterKey = "Price";
                break;
            case 6:
                parameterKey = "Color";
                break;
        }
        return parameterKey;
    }

    /**
     * Вывод различных уточняющих фраз в зависимости от выбранного параметра фильтрации
     * @param number
     */
    public static void filterCase(int number) {
        if (number == 1) {
            System.out.println("Уточните, какое минимальное значение ОЗУ вас устроит?");
        } else if (number == 2) {
            System.out.println("Уточните, какое минимальное значение объема жесткого диска вас устроит?");
        } else if (number == 3) {
            System.out.println("Уточните, какая операционная система вас интересует:\n" +
                    "1 - Windows\n" +
                    "2 - Linux \n" +
                    "3 - MacOS");
        } else if (number == 4) {
            System.out.println("Уточните, какое минимальное количество ядер вас устроит?");
        } else if (number == 5) {
            System.out.println("Уточните верхнюю границу цены:");
        } else if (number == 6) {
            System.out.println("Уточните, какой цвет вы ищите?");
        }
    }

    /**
     * Метод для заполнения карты с параметрами ноутбука
     * @param noteBook
     * @return
     */
    public static HashMap<String, Object> fillMap(NoteBook noteBook){
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("Color", noteBook.getColor());
        parameters.put("RAM", noteBook.getRam());
        parameters.put("Hard", noteBook.getHardDiskCapacity());
        parameters.put("OS", noteBook.getOperatingSystem());
        parameters.put("Price", noteBook.getPrice());
        parameters.put("Cores", noteBook.getCores());
        return parameters;
    }

    public static void main(String[] args) {
        HashMap<String, HashMap<String, Object>> noteList = new HashMap<>(); //карта с ноутбуками и их картами с параметрами

        NoteBook noteBook1 = new NoteBook(NoteBook.OS.Linux, "White");
        noteList.put("noteBook1", fillMap(noteBook1));
        NoteBook noteBook2 = new NoteBook(NoteBook.OS.Windows, "Black");
        noteList.put("noteBook2", fillMap(noteBook2));
        NoteBook noteBook3 = new NoteBook(NoteBook.OS.Windows, "White");
        noteList.put("noteBook3", fillMap(noteBook3));
        NoteBook noteBook4 = new NoteBook(NoteBook.OS.MacOS, "Pink");
        noteList.put("noteBook4", fillMap(noteBook4));
        NoteBook noteBook5 = new NoteBook(NoteBook.OS.Linux, "Green");
        noteList.put("noteBook5", fillMap(noteBook5));

        Deque<String> rezult = filter(noteList);
        System.out.println("Вам подходят:" + rezult.toString().replace('[', ' ').replace(']', ' '));

        for(String key : rezult){
            HashMap<String, Object> parameters = noteList.get(key);
            System.out.println(key + ": " + parameters);
        }
    }
}
