import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class AllDataAtOnce {
    Scanner sc;
    Examination exam;

    public AllDataAtOnce() {
        this.sc = new Scanner(System.in);
        this.exam = new Examination();
    }
    private String dataToStringBuilder(String data, String[] temp){
        StringBuilder sb = new StringBuilder();
        String birth = null, gender = null, number = null;
        int stringCount = 0, birthCount = 0, genderCount = 0, numberCount = 0;
        for (String el : temp) {
            if (el.contains(".")) {
                try {
                    exam.birthExam(el);
                    birth = el;
                    birthCount++;
                } catch (RuntimeException ex) {
                    System.out.println(ex.getMessage());
                    data = getData();
                }
            }
            else if (el.length() == 1) {
                try{
                    exam.genderExam(el);
                    gender = el;
                    genderCount++;
                } catch (RuntimeException ex){
                    System.out.println(ex.getMessage());
                    data = getData();
                }
            }
            else if(exam.isItNumber(el)) {
                try{
                    exam.numberExam(el);
                    number = el;
                    numberCount++;
                } catch (RuntimeException ex) {
                    System.out.println(ex.getMessage());
                    data = getData();
                }
            }
            else {
                sb.append(el + " ");
                stringCount++;
            }
        }
        if(counterExam(birthCount, genderCount, numberCount, stringCount, sb, data)){
            sb.append(birth + " ");
            sb.append(number + " ");
            sb.append(gender);
        }
        return sb.toString();
    }
    private boolean counterExam(int birthCount, int genderCount, int numberCount, int stringCount, StringBuilder sb, String data){
        if (birthCount < 1) {
            System.out.println("Дата не введена или введена с некорректным форматом");
            data = getData();
        }
        else if (genderCount < 1) {
            System.out.println("Пол не введен или введено больше одного символа");
            data = getData();
        }
        else if (numberCount < 1) {
            System.out.println("Номер не введен или введен с форматированием");
            data = getData();
        }
        else if (stringCount < 3) {
            System.out.println("Не введена фамилия, имя или отчество");
            data = getData();
        }
        return true;
    }
    private String getData() {
        System.out.println("Введите через пробелы: Фамилия Имя Отчество датарождения номертелефона пол\n" +
                "датарождения - строка формата dd.mm.yyyy\n" +
                "номертелефона - целое беззнаковое число без форматирования\n" +
                "пол - символ латиницей f или m.");
        String data = sc.nextLine();
        try {
            exam.stringNotEmpty(data);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            data = getData();
        }
        String[] temp = data.split(" ");
        try {
            exam.dataCount(temp);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            data = getData();
        }
        return dataToStringBuilder(data, temp);
    }
    public void dataToFile() {
        String sb = getData();
        String[] temp = sb.split(" ");
        Path path = Path.of(temp[0] + ".txt");
        if (Files.exists(path)) {
            StringBuilder str = new StringBuilder();
            try {
                List<String> list = Files.readAllLines(path);
                exam.reEntry(list, sb);
                list.add(sb);
                for (String el : list) {
                    str.append(el);
                    str.append("\n");
                }
                Files.writeString(path, str.toString());
                System.out.println("Данные успешно записаны в файл");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.writeString(path, sb);
                System.out.println("Данные успешно записаны в файл");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
