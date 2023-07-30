import java.time.LocalDate;
import java.util.List;

public class Examination {
    private final String DATA_IS_EMPTY = "Вы не ввели данные";
    private final String MORE_DATA_THEN_REQUIRE = "Вы ввели больше данных, чем требуется";
    private final String LESS_DATA_THEN_REQUIRE = "Вы ввели меньше данных, чем требуется";
    private final String YEAR_ERROR = "Введенный год больше нынешнего";
    private final String NEGATIVE_ERROR = "Число не может быть отрицательным";
    private final String MONTH_ERROR = "Значение месяца не может быть больше 12";
    private final String LEAP_FEB_ERROR = "Значение дня не может быть больше 29 в этом месяце";
    private final String NOT_LEAP_FEB_ERROR = "Значение дня не может быть больше 28 в этом месяце";
    private final String DAY_MORE_31_ERROR = "Значение дня не может быть больше 31 в этом месяце";
    private final String DAY_MORE_30_ERROR = "Значение дня не может быть больше 30 в этом месяце";

    private boolean leapYearExamination(int year){
        if (year % 4 != 0) return false;
        else{
            if (year % 100 != 0) return true;
            else {
                if (year % 400 != 0) return false;
                else return true;
            }
        }
    }
    private void dayExamination(int day, int month, int year) {
        if (day < 0) {
            throw new RuntimeException(NEGATIVE_ERROR);
        }
        boolean leapYear = leapYearExamination(year);
        if (leapYear && (month == 2) && (day > 29)){
            throw new RuntimeException(LEAP_FEB_ERROR);
        }
        if (!leapYear && (month == 2) && (day > 28)) {
            throw new RuntimeException(NOT_LEAP_FEB_ERROR);
        }
        if (((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) && day > 31){
            throw new RuntimeException(DAY_MORE_31_ERROR);
        }
        if (((month == 2) || (month == 4) || (month == 6) || (month == 9) || (month == 11)) && day > 30){
            throw new RuntimeException(DAY_MORE_30_ERROR);
        }
    }
    private void monthExamination(int month) {
        if (month > 12) throw new RuntimeException(MONTH_ERROR);
        if (month < 0) throw new RuntimeException(NEGATIVE_ERROR);
    }
    private void yearExamination(int year){
        if (year > LocalDate.now().getYear()) throw new RuntimeException(YEAR_ERROR);
        if (year < 0) throw new RuntimeException(NEGATIVE_ERROR);
    }
    private void isNumber(String str){
        try{
            Double.parseDouble(str);
        } catch (NumberFormatException ex){
            throw new RuntimeException("введено не число");
        }
    }
    public boolean isItNumber(String str){
        try{
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException ex){
            return false;
        }
    }

    public void stringNotEmpty(String str){
        if (str == null || str.isEmpty()) throw new RuntimeException(DATA_IS_EMPTY);
    }
    public void birthExam(String birth) throws RuntimeException {
        if (birth == null || birth.isEmpty()) {
            throw new RuntimeException(DATA_IS_EMPTY);
        }
        if (birth.contains(".")) {
            //проверяем формат, что есть день, месяц и год
            String[] temp = birth.split("\\.");
            if (temp.length < 3) {
                throw new RuntimeException(LESS_DATA_THEN_REQUIRE);
            } else if (temp.length > 3) {
                throw new RuntimeException(MORE_DATA_THEN_REQUIRE);
            }
            //проверяем что это числа
            try {
                isNumber(temp[0]);
            } catch (RuntimeException ex) {
                throw new RuntimeException("День - " + ex.getMessage());
            }
            try {
                isNumber(temp[1]);
            } catch (RuntimeException ex) {
                throw new RuntimeException("Месяц - " + ex.getMessage());
            }
            try {
                isNumber(temp[2]);
            } catch (RuntimeException ex) {
                throw new RuntimeException("Год - " + ex.getMessage());
            }
            dayExamination(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
            monthExamination(Integer.parseInt(temp[1]));
            yearExamination(Integer.parseInt(temp[2]));
        } else throw new RuntimeException("Некорректный ввод даты, отсутствуют точки-разделители");
    }
    public void fioExam(String fio) {
        if (fio == null || fio.isEmpty()) throw new RuntimeException(DATA_IS_EMPTY);
        String[] temp = fio.split(" ");
        if (temp.length < 3) throw new RuntimeException(LESS_DATA_THEN_REQUIRE);
        else if (temp.length > 3) throw new RuntimeException(MORE_DATA_THEN_REQUIRE);
        if(!Character.isUpperCase(temp[0].charAt(0))) throw new RuntimeException("Фамилия начинается не с заглавной буквы!");
        if(!Character.isUpperCase(temp[1].charAt(0))) throw new RuntimeException("Имя начинается не с заглавной буквы!");
        if(!Character.isUpperCase(temp[2].charAt(0))) throw new RuntimeException("Отчество начинается не с заглавной буквы!");
    }
    public void genderExam(String gender) {
        if (gender == null || gender.isEmpty()) throw new RuntimeException(DATA_IS_EMPTY);
        else if (gender.length() > 1) throw new RuntimeException("Введено больше одного символа");
        else if (!(gender.contains("f") || gender.contains("m"))) throw new RuntimeException("Введен не символ f или m");
    }
    public void numberExam(String number) throws RuntimeException {
        if (number == null || number.isEmpty()) throw new RuntimeException(DATA_IS_EMPTY);
        if (number.contains("+") || number.contains("-")) throw new RuntimeException("В значении номера не должно быть знаков");
        isNumber(number);
        if (number.length() != 11) throw new RuntimeException("Номер должен содержать 11 цифр"); //тут ориентируюсь только на наш формат номеров
    }
    //повторная запись в файл
    public void reEntry(List<String> list, String sb){
        for (String el : list) {
            if (el.equals(sb)) throw new RuntimeException("Человек с такими данными уже существует в файле");
        }
    }

    public void dataCount(String[] temp) {
        if(temp.length < 6) throw new RuntimeException(LESS_DATA_THEN_REQUIRE);
        if(temp.length > 6) throw new RuntimeException(MORE_DATA_THEN_REQUIRE);
    }
}
