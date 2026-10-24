import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        task_1();
        task_2();
        task_3();
        task_4();
        DepositCalculator.task_5();
    }

    static void task_1() {
        System.out.println("Задание №1");
        System.out.println("Элементы с нечётными номерами по возрастанию, элементы с чётными по убыванию.");
        int[] arr = {45, 12, 8, 99, 23, 7, 54, 1, 36};
        int n = arr.length;

        System.out.println("\nЭлементы с нечётными индексами (по возрастанию):");
        for (int i = 1; i < n; i += 2) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nЭлементы с чётными индексами (по убыванию):");
        int firstEvenIndex = n - 2 + n % 2;

        for (int i = firstEvenIndex; i >= 0; i -= 2) {
            System.out.print(arr[i] + " ");

        }
    }

    static void task_2() {
        System.out.println("\n\nЗадание №2");
        System.out.println("Найти два соседних элемента c max суммой и вывести в порядке возрастания индексов.");
        int[] arr = {45, 12, 8, 99, 23, 7, 54, 1, 36};
        int n = arr.length;
        int highest_sum = arr[0] + arr[1];
        int high_index = 0;

        for (int i = 0; i < n - 1; i += 1) {
            if (highest_sum < arr[i] + arr[i + 1]) {
                highest_sum = arr[i] + arr[i + 1];
                high_index = i;
            }
        }
        System.out.println("Самая большая сумма между числами " + arr[high_index] + " и " + arr[high_index + 1]);
    }

    static int[] getMaxArray(int[] A, int[] B) {
        int[] C = new int[A.length];

        for (int k = 0; k < A.length; k++) {
            C[k] = Math.max(A[k], B[k]);
        }

        return C;
    }

    static void task_3() {
        System.out.println("\n\nЗадание №3");
        System.out.println("Даны A и B. Сформировать C, где Ck равен максимуму Ak и Bk");
        int[] A = {1, 5, 3, 9, 2};
        int[] B = {4, 2, 8, 1, 6};

        int[] C = getMaxArray(A, B);

        System.out.println("Массив A: " + Arrays.toString(A));
        System.out.println("Массив B: " + Arrays.toString(B));
        System.out.println("Итоговый массив C: " + Arrays.toString(C));
    }

    static void printCorners(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;

        System.out.println("Вывод уголками:");
        for (int k = 0; k < n; k++) {
            for (int i = 0; i <= n - 1 - k; i++) {
                System.out.printf("%3d ", matrix[i][k]);
            }

            for (int j = k + 1; j < n; j++) {
                System.out.printf("%3d ", matrix[n - 1 - k][j]);
            }
        }
        System.out.println();
    }

    static void task_4() {
        System.out.println("\n\nЗадание №4");
        System.out.print("Вывести квадратную матрицу уголками");

        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        System.out.println("\nИсходная матрица:");
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.printf("%3d ", val);
            }
            System.out.println();
        }
        printCorners(matrix);
    }

    public static class DepositCalculator {
        private static final String[] PROMPTS = {
                "Введите начальную сумму инвестиций (P):",
                "Введите годовую процентную ставку в десятичной дроби (r, например, 0.05 для 5%):",
                "Введите количество начислений процентов в год (n):",
                "Введите срок размещения вклада или инвестиций в годах (t):"
        };
        private static final String[] ERROR_MESSAGES = {
                "Ошибка: сумма не может быть отрицательной!",
                "Ошибка: процентная ставка не может быть отрицательной!",
                "Ошибка: количество начислений должно быть строго больше 0!",
                "Ошибка: срок вклада должен быть строго больше 0!"
        };

        private static final boolean[] ALLOW_ZERO = {true, true, false, false};

        static void task_5() {
            System.out.println("\n\nЗадание №5 Меню");
            boolean isRunning = true;

            while (isRunning) {
                System.out.println("\n--- Главное меню ---");
                System.out.println("1. Выполнить расчёт");
                System.out.println("2. Информация о программе");
                System.out.println("3. Информация о разработчике");
                System.out.println("4. Выход");
                System.out.print("Выберите пункт меню: ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1 -> runCalculationFlow();
                        case 2 -> printInformation();
                        case 3 -> printAbout();
                        case 4 -> {
                            System.out.println("Выход из программы. До свидания!");
                            isRunning = false;
                        }
                        default -> System.out.println("Неверный пункт меню. Пожалуйста, выберите от 1 до 4.");
                    }
                } else {
                    System.out.println("Ошибка: пожалуйста, введите число (1-4).");
                    scanner.next();
                }
            }
            scanner.close();
        }

        public static double[] getInputParameters() {
            double[] params = new double[4]; // Индексы: 0=P, 1=r, 2=n, 3=t
            System.out.println("\n--- Расчёт вклада ---");

            for (int i = 0; i < 4; i++) {
                params[i] = getValidDouble(PROMPTS[i], ERROR_MESSAGES[i], ALLOW_ZERO[i]);
            }
            return params;
        }

        // 2. МЕТОД ПРОВЕРКИ (валидация)
        public static boolean isValid(double value, boolean allowZero) {
            return allowZero ? value >= 0 : value > 0;
        }

        // Вспомогательный метод для безопасного чтения double с использованием проверки
        private static double getValidDouble(String prompt, String errorMessage, boolean allowZero) {
            while (true) {
                System.out.println(prompt);
                if (scanner.hasNextDouble()) {
                    double value = scanner.nextDouble();
                    if (isValid(value, allowZero)) {
                        return value;
                    } else {
                        System.out.println(errorMessage);
                    }
                } else {
                    System.out.println("Ошибка: введите корректное число!");
                    scanner.next(); // Очистка буфера сканера
                }
            }
        }

        // 3. МЕТОД ВЫЧИСЛЕНИЯ
        public static double calculateCompoundInterest(double[] params) {
            double P = params[0];
            double r = params[1];
            double n = params[2];
            double t = params[3];

            return P * Math.pow((1 + r / n), n * t);
        }

        // 4. МЕТОД ВЫВОДА
        public static void printResult(double[] params, double result) {
            System.out.println("\n--- Результаты расчёта ---");
            System.out.printf("Начальная сумма (P): %.2f руб.\n", params[0]);
            System.out.printf("Годовая ставка (r): %.4f\n", params[1]);
            System.out.printf("Начислений в год (n): %.0f\n", params[2]);
            System.out.printf("Срок вклада (t): %.2f лет\n", params[3]);
            System.out.println("--------------------------");
            System.out.printf("Итоговая сумма накоплений: %.2f руб.\n", result);
        }

        private static void runCalculationFlow() {
            double[] params = getInputParameters();           // Ввод
            double result = calculateCompoundInterest(params); // Вычисление
            printResult(params, result);                      // Вывод
        }

        public static void printInformation() {
            System.out.println("\n--- О программе ---");
            System.out.println("Привет, ты похоже решил положить деньги на вклад? Мы тебе поможем!");
            System.out.println("Это программа считает сложный процент для вкладов.");
            System.out.println("В конце ты получишь итоговую сумму денег, которая накопится к концу расчетного периода!");
        }

        public static void printAbout() {
            System.out.println("\n--- О разработчиках ---");
            System.out.println("Разработчики: Ефимов Дмитрий, Бабушкин Евгений, Кравченко Владислава.");
        }

    }
}

