import java.util.Random;

public class NoteBook {
    private OS operatingSystem; //ос
    private int ram; //озу
    private int hardDiskCapacity; //объем жесткого диска
    private String color;
    private int price;
    private int cores; //ядра

    /**
     * Конструктор с заданием минимального состояния экземпляра класса, остальные поля задаются случайно
     * @param operatingSystem операционная система
     * @param color цвет
     */
    public NoteBook(OS operatingSystem, String color){
        this.operatingSystem = operatingSystem;
        this.ram = randChoice(arrays("ram"));
        this.color = color;
        this.cores = randChoice(arrays("core"));
        this.price = new Random().nextInt(20_000, 1_000_000);
        this.hardDiskCapacity = randChoice(arrays("hard"));
    }

    /**
     * Конструктор позволяющий полностью описать экземпляр класса
     * @param operatingSystem операционная система
     * @param color цвет
     * @param ram ОЗУ
     * @param cores количество производительных ядер
     * @param price цена
     * @param hard объем жесткого диска
     */
    public NoteBook(OS operatingSystem, String color, int ram, int cores, int price, int hard){
        this.operatingSystem = operatingSystem;
        this.ram = ram;
        this.color = color;
        this.cores = cores;
        this.price = price;
        this.hardDiskCapacity = hard;
    }

    /**
     * Вспомогательный метод для хранения массивов возможных значений для ОЗУ, ядер и объема жесткого диска
     * @param name "ram", "core" or "hard"
     * @return
     */
    private int[] arrays(String name){
        int[] array;
        if (name == "ram") {
            array = new int[] {4, 6, 8, 12, 16, 32, 64};
            return array;
        }
        else if (name == "core") {
            array = new int[]{2, 4, 6, 8, 12};
            return array;
        }
        else if (name == "hard"){
            array = new int[]{128, 240, 256, 512, 1_000, 1024, 2_000, 3_000};
            return array;
        }
        return null;
    }
    private int randChoice(int[] array){
        int rand = new Random().nextInt(array.length);
        return array[rand];
    }
    public enum OS{ Windows, Linux, MacOS }

    public OS getOperatingSystem() {
        return operatingSystem;
    }

    public int getRam() {
        return ram;
    }

    public int getHardDiskCapacity() {
        return hardDiskCapacity;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public int getCores() {
        return cores;
    }
}
