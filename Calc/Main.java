import java.util.ArrayList;
import java.util.Scanner;

class Main {

    static boolean isRome = false; // по умолчанию число считается целым

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println(calc(s));
    }
    public static String calc(String input) {
        ArrayList<Integer> numbers = addNumbers(input);
        int result = 0;

        if ( (numbers.get(0) <= 0) || (numbers.get(0) > 10) || (numbers.get(1) <= 0) || (numbers.get(1) > 10) ) { //проверка на входящие целые числа
            System.out.println("Введите числа от 1 до 10 включительно");
            throw new RuntimeException();
        }

        if (input.contains("+")) {
            result = numbers.get(0) + numbers.get(1);
        } else if (input.contains("-")) {
            result = numbers.get(0) - numbers.get(1);
            if (isRome & (result < 1)) {
                System.out.println("В римских числах результат не может быть меньше одного");
                throw new RuntimeException();
            }
        }
        else if (input.contains("*")) {
            result = numbers.get(0) * numbers.get(1);
        }
        else if (input.contains("/")) {
            result = numbers.get(0) / numbers.get(1);
            if (isRome & (result < 1)) {
                System.out.println("В римских числах результат не может быть меньше одного");
                throw new RuntimeException();
            }
        } else {
            System.out.println("Введите один из операторов: +, -, *, /");
        }

        if (isRome) {
            return toRome(result);
        } else {
            return Integer.toString(result);
        }
    }

    /**
     * Получает на вход искомую строку, возвращает массив из двух чисел
     * @param input
     * @return
     */
    public static ArrayList<Integer> addNumbers(String input) {

        try {
            String newString = input.replace(" ", ""); //удаляем все пробелы из строки

            String[] numbers = newString.split("[+\\-*/]"); //добавляем элементы в массив, с разделителями в виде операндов


            if (isDigit(numbers[0]) & isDigit(numbers[1])) { //если первый и второй элемент числа, значит ответ число
                ArrayList<Integer> result = new ArrayList<>();
                for (String number : numbers) {
                    result.add(Integer.parseInt(number));
                }
                return result;
            } else if (!isDigit(numbers[0]) & !isDigit(numbers[1])) { //если первый и второй элемент не числа, значит ответ строка (римское число)
                isRome = true;
                ArrayList<Integer> result = new ArrayList<>();
                result.add(toInt(numbers[0]));
                result.add(toInt(numbers[1]));
                return result;
            } else {
                System.out.println("Введите либо два целых, либо два римских числа в диапазоне от 1 до 10 включительно! Римские числа должны быть в виде строки");
                throw new RuntimeException();
            }
        } catch (Exception e) {
            System.out.println("Введите строку вида 1 + 1, или I + I");
        }
        return null;
    }


    //преобразуем римское число в целое
    private static int toInt(String str) {
        int number = 0;
        switch (str) {
            case ("I") -> number = 1;
            case ("II") -> number = 2;
            case ("III") -> number = 3;
            case ("IV") -> number = 4;
            case ("V") -> number = 5;
            case ("VI") -> number = 6;
            case ("VII") -> number = 7;
            case ("VIII") -> number = 8;
            case ("IX") -> number = 9;
            case ("X") -> number = 10;
            default -> {
                System.out.println("Введите римское число от I до X, или целое число");
                throw new RuntimeException();
            }
        }
        return number;
    }


    //Преобразуем целое число в римское
    private static String toRome(int arab) {
        StringBuilder result = new StringBuilder();
        int num = arab;

        String[] symbols = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I",};
        int[] cost = {100, 90, 50, 40, 10, 9, 5, 4, 1};

        for (int i = 0; i < cost.length;) {
            if (num >= cost[i]) {
                result.append(symbols[i]);
                num-=cost[i];
            } else {
                i++;
            }
        }
        return result.toString();
    }

    //Проверка, является ли входящая строка числом
    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}