import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceprionHW2 {

    public static void message() {
        System.out.println("Введите дробное число");
        boolean flag = true;
        while (flag) {
            try {
                floatNumber(flag);
                flag = false;
            } catch (InputMismatchException ex) {
                System.out.println("Некорректный ввод, введите дробное число");
            }
        }
    }
    private static void floatNumber(boolean flag) {
        Scanner sc = new Scanner(System.in);
        float number = sc.nextFloat();
    }

    public static void emptyString(){
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        emptyStringException(str);
    }
    public static void emptyStringException(String str){
        if (str == null || str.isEmpty()) throw new RuntimeException("Нельзя вводить пустые строки!");
    }

    public static void main(String[] args) {
        //Task1
        //Реализуйте метод, который запрашивает у пользователя ввод дробного числа (типа float), и возвращает введенное значение.
        //Ввод текста вместо числа не должно приводить к падению приложения, вместо этого, необходимо повторно запросить у пользователя ввод данных.
        message();

        //Task2
        //int d = 0 можно вынести из блока try
        //кроме этого все работает, ловится ошибка вычисления и выводится какая именно ошибка

        //Task3
        //Методу printSum(Integer a, Integer b) не нужно пробрасывать исключение FileNotFoundException - 
        //он вообще с файлами не работает, принимает два числа, выводит их сумму.
        
        //Методу main не нужно пробрасывать исключение Exception, потому что этот метод как правило конечный и 
        //через него не пробрасывают (в нем обрабатывают) исключения, к тому же внутри метода у нас есть catch (Throwable ex), 
        //который поймает абсолютно все исключения и даже ошибки, пробрасывать ничего не надо
        
        //Объявление переменных можно вынести за блок try.
        
        //Нет обработки исключения деления на ноль (нужен блок catch (ArithmeticException e)).
        
        //Блок catch (Throwable ex) не может быть первым, он является родителем всех классов исключений, 
        //поэтому поймает все исключения собой. Его нужно поставить последним.
        
        //Блок catch (NullPointerException ex) хорошо бы положить в метод printSum, так как туда подаются Integer, 
        //которые могут быть null, либо обработать внутри метода, либо этим методом пробросить это исключение выше и 
        //тогда уже блок catch оставить в main.

        //Task4
        emptyString();
    }
}
