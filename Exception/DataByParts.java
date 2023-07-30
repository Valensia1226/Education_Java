import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class DataByParts {
    Scanner sc;
    Examination exam;

    public DataByParts() {
        this.sc = new Scanner(System.in);
        this.exam = new Examination();
    }

    private String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFio() + " ");
        sb.append(getBirth() + " ");
        sb.append(getNumber() + " ");
        sb.append(getGender());
        return sb.toString();
    }

    private String getFio() {
        System.out.println("Введите через пробелы: Фамилия Имя Отчество");
        String fio = sc.nextLine();
        try {
            exam.fioExam(fio);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            fio = getFio();
        }
        return fio;
    }

    private String getBirth() {
        System.out.println("Введите дату рождения через точки: dd.mm.yyyy");
        String birth = sc.nextLine();
        try {
            exam.birthExam(birth);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            birth = getBirth();
        }
        return birth;
    }

    private String getNumber() {
        System.out.println("Введите номер телефона без форматирования (целое беззнаковое число):");
        String number = sc.nextLine();
        try {
            exam.numberExam(number);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            number = getNumber();
        }
        return number;
    }

    private String getGender() {
        System.out.println("Введите пол одним символом(латиницей): f или m");
        String gender = sc.nextLine();
        try {
            exam.genderExam(gender);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            gender = getGender();
        }
        return gender;
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
