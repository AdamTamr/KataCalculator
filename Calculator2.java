import java.util.Scanner;
import java.util.HashMap;

public class Calculator2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение (например, X + III или 10 + 3)");
        String input = scanner.nextLine();

        String result = calc(input);
        System.out.println("Результат: " + result);
    }

    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат строки");
        }

        if (isRoman(parts[0]) && isRoman(parts[2])) {
            int num1 = convertRomanToNumber(parts[0]);
            int num2 = convertRomanToNumber(parts[2]);

            if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                throw new IllegalArgumentException("Числа должны быть в диапазоне от I до X");
            }

            char operation = parts[1].charAt(0);
            int result = 0;
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция");
            }

            return convertNumberToRoman(result);
        } else if (isArabic(parts[0]) && isArabic(parts[2])) {
            int num1 = Integer.parseInt(parts[0]);
            int num2 = Integer.parseInt(parts[2]);

            if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
                throw new IllegalArgumentException("Числа должны быть в диапазоне от 1 до 10");
            }


            char operation = parts[1].charAt(0);
            int result = 0;
            switch (operation) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException("Неверная операция");
            }

            return String.valueOf(result);
        } else {
            throw new IllegalArgumentException("Неподдерживаемые форматы чисел");
        }
    }

    public static boolean isRoman(String input) {
        // Проверим, является ли римским
        String romanNumeral = "IVXLC";
        for (char ch : input.toCharArray()) {
            if (romanNumeral.indexOf(ch) == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isArabic(String input) {
        // Проверяем, является ли арабским
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int convertRomanToNumber(String roman) {
        // Преобразовываем римские в арабсике
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);

        int sum = map.get(roman.charAt(roman.length() - 1));
        for (int i = roman.length() - 2; i >= 0; i--) {
            if (map.get(roman.charAt(i)) < map.get(roman.charAt(i + 1))) {
                sum -= map.get(roman.charAt(i));
            } else {
                sum += map.get(roman.charAt(i));
            }
        }
        return sum;
    }

    public static String convertNumberToRoman(int number) {
        // Преобразовываем арабские в римские
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Невозможно конвертировать число");
        }

        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C"};
        int[] values = {1, 4, 5, 9, 10, 40, 50, 90, 100};
        StringBuilder roman = new StringBuilder();

        int i = values.length - 1;
        while (number > 0) {
            if (number - values[i] >= 0) {
                roman.append(romanSymbols[i]);
                number -= values[i];
            } else {
                i--;
            }
        }
        return roman.toString();
    }
}