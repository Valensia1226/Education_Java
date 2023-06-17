import java.util.*;

public class HomeWork4 {
    public static Integer DequeToInt(ArrayDeque<String> numbers){
        String num = "";
        while(numbers.peek()!=null){
            num = num + numbers.pollLast();
        }
        Integer n = Integer.parseInt(num);
        return n;
    }
    public static Deque<Object> Multiplication(Integer a, Integer b){
        Integer multi = a * b;
        Deque<Object> rezult = new LinkedList<>();
        boolean flag = false;
        //проверяем отрицательное ли число
        if(multi < 0){
            flag = true;
            multi *= (-1);
        }
        //вносим в список по цифрам
        while(multi > 0){
            rezult.addFirst(multi%10);
            multi = multi / 10;
        }
        //если число было отрицательным, добавляем "-"
        if (flag == true) {
            rezult.addFirst("-");
        }
        return rezult;
    }
    public static Deque<Object> Addition(Integer a, Integer b){
        Integer sum = a + b;
        Deque<Object> rezult = new LinkedList<>();
        boolean flag = false;
        //проверяем отрицательное ли число
        if(sum < 0){
            flag = true;
            sum *= (-1);
        }
        //вносим в список по цифрам
        while(sum > 0){
            rezult.addFirst(sum%10);
            sum = sum / 10;
        }
        //если число было отрицательным, добавляем "-"
        if (flag == true) {
            rezult.addFirst("-");
        }
        return rezult;
    }

    public static void main(String[] args) {
        ArrayDeque<String> number1 = new ArrayDeque<>(Arrays.asList("3","2","1"));
        System.out.println(number1);
        ArrayDeque<String> number2 = new ArrayDeque<>(Arrays.asList("5","4","3", "-"));
        System.out.println(number2);
        Integer num1 = DequeToInt(number1);
        Integer num2 = DequeToInt(number2);
        
        //Tas1
        Deque<Object> multi = Multiplication(num1, num2);
        if (num2 < 0) System.out.printf("%s * (%s) = %s\n", num1, num2, multi);
        else System.out.printf("%s * %s = %s\n", num1, num2, multi);

        //Task2
        Deque<Object> sum = Addition(num1, num2);
        if (num2 < 0) System.out.printf("%s + (%s) = %s\n", num1, num2, sum);
        else System.out.printf("%s + %s = %s\n", num1, num2, sum);
    }
}
