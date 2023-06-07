import java.util.Scanner;

public class HomeWork1 {

    public static int TriangleNumber(int n){
        return n*(n+1)/2;
    }
    public static int Factorial(int n){
        if (n == 0 || n == 1) return 1;
        else return n*(Factorial(n-1));
    }
    public static void PrimeNumbers(int n){
        System.out.print("1 ");
        boolean flag = true;
        for (int i = 2; i < n; i++){
            for (int j = 2; j < i; j++){
                if (i % j == 0){
                    flag = false;
                    break;
                }
                else flag = true;
            }
            if (flag == true) System.out.printf("%s ", i);
        }
    }
    public static void Calc(){
        System.out.print("Введите математическое выражение через пробелы: ");
        Scanner sc = new Scanner(System.in);
        String math = sc.nextLine();
        sc.close();
        String[] m = math.split(" ");
        int rezult;
        boolean flag = false;
        if (math.contains("+")) {
            rezult = Integer.parseInt(m[0]) + Integer.parseInt(m[2]);
            System.out.printf("%s = %s", math, rezult);
            flag = true;
        }
        if (math.contains("-")) {
            rezult = Integer.parseInt(m[0]) - Integer.parseInt(m[2]);
            System.out.printf("%s = %s", math, rezult);
            flag = true;
        }
        if (math.contains("*")) {
            rezult = Integer.parseInt(m[0]) * Integer.parseInt(m[2]);
            System.out.printf("%s = %s", math, rezult);
            flag = true;
        }
        if (math.contains("/")) {
            rezult = Integer.parseInt(m[0]) / Integer.parseInt(m[2]);
            System.out.printf("%s = %s", math, rezult);
            flag = true;
        }
        if (flag == false) System.out.println("Неверный математический оператор");
    }

    public static void Equation(){
        System.out.print("Введите уравнение вида q + w = e через пробелы; q,w,e >= 0: ");
        Scanner sc = new Scanner(System.in);
        String equation = sc.nextLine(); //вводим уравнение
        sc.close();
        String[] eq = equation.split(" "); //создаем массив из строк и записываем в него уравнение по частям
        if (eq.length == 5) { //проверяем, что элементов в уравнении правильное количество
            int a; // для первой переменной уравнения
            int b; // для второй переменной уравнения
            int c = Integer.parseInt(eq[4]); //выводим в отдельную int переменную результат после =
            boolean flag = false; //флаг для проверки уравнения на существование

            int i = 0;
            while (i < 10) {
                String number = String.valueOf(i);
                String numA = eq[0].replaceAll("\\?", number); //подставляем текущее значение i, перебираем все цифры
                a = Integer.parseInt(numA); //переводим в int
                String numB = eq[2].replaceAll("\\?", number);
                b = Integer.parseInt(numB);
                if (a + b == c) {
                    System.out.printf("Ваше выражение: %s + %s = %s", a, b, c);
                    flag = true;
                    break;
                }
                i++;
            }
            if (flag == false) System.out.println("Такого уравнения не существует");
        }
        else System.out.println("Неверно введено уравнение, убедитесь в наличие пробелов между элементами уравнения");
    }

    public static void main(String[] args) {
        //Task1
        //System.out.println(TriangleNumber(5));

        //Task1_1
        //System.out.println(Factorial(5));

        //Task2
        //PrimeNumbers(1000);

        //Task3
        //Calc();

        //Task4
        Equation();
    }
}
