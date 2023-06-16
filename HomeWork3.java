import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Main {
    public static void FillRandom(ArrayList<Integer> array, int n){
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array.add(rand.nextInt(100));
        }
    }
    public static void DeleteEven(ArrayList<Integer> array){
        Iterator<Integer> iter = array.iterator();
        while(iter.hasNext()){
            if(iter.next() % 2 == 0){
                iter.remove();
            }
        }
        System.out.printf("List without even numbers: %s \n", array);
    }
    public static void FindMinAndMax(ArrayList<Integer> array){
        array.sort(null);
        int min = array.get(0);
        int max = array.get(array.size() - 1);
        System.out.printf("max = %s, min = %s \n", max, min);
    }
    public static void FindAverage(ArrayList<Integer> array){
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum = sum + array.get(i);
        }
        float average = sum/array.size();
        System.out.printf("average = %s", average);
    }

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        //заполнить случайными числами
        FillRandom(numbers, 10);
        System.out.printf("Our list: %s \n", numbers);

        //удалить четные
        DeleteEven(numbers);

        //найти минимальное и максимальное (не знаю, было ли принципиально искать их двумя разными функциями, поэтому положила в одну)
        FindMinAndMax(numbers);

        //найти среднее
        FindAverage(numbers);
    }
}
