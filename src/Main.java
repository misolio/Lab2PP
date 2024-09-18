import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void inputData(Train train) {
        Scanner scanner = new Scanner(System.in);

        // Введення пункту призначення
        System.out.print("Введіть пункт призначення: ");
        train.setDestination(scanner.nextLine());

        // Введення номера потяга з перевіркою на ціле число
        System.out.print("Введіть номер потяга: ");
        String inputTnumb = scanner.nextLine();
        while (!isValidInteger(inputTnumb) || Integer.parseInt(inputTnumb) <= 0) {
            System.out.println("Некоректне введення номера потяга. Спробуйте знову.");
            System.out.print("Введіть номер потяга: ");
            inputTnumb = scanner.nextLine();
        }
        train.setTnumb(Integer.parseInt(inputTnumb));

        // Введення часу з перевіркою формату "год:хв" за допомогою LocalTime
        System.out.print("Введіть час відправки (у формі год:хв): ");
        String time = scanner.nextLine();
        while (!isValidTime(time)) {
            System.out.println("Час має бути у форматі год:хв (наприклад, 14:30). Спробуйте ще раз.");
            System.out.print("Введіть час відправки (у формі год:хв): ");
            time = scanner.nextLine();
        }
        train.setTime(time);

        // Введення кількості місць для кожної категорії
        System.out.println("Введіть кількість місць в кожній з категорій:");
        for (String category : train.getSeatsCategories().keySet()) {
            System.out.print("Кількість місць для категорії '" + category + "': ");
            String inputSeats = scanner.nextLine();
            while (!isValidInteger(inputSeats) || Integer.parseInt(inputSeats) < 0) {
                System.out.println("Некоректне введення кількості місць. Введіть ціле невід'ємне число.");
                System.out.print("Кількість місць для категорії '" + category + "': ");
                inputSeats = scanner.nextLine();
            }
            train.setSeatsCategories(category, Integer.parseInt(inputSeats));
        }
    }

    // Перевірка на коректність введеного цілого числа
    private static boolean isValidInteger(String input) {
        return input.matches("\\d+"); // Перевірка, що рядок складається лише з цифр
    }

    // Перевірка формату часу за допомогою LocalTime
    private static boolean isValidTime(String time) {
        try {
            LocalTime.parse(time); // Перевірка, чи можна перетворити введений час у LocalTime
            return true; // Якщо формат правильний
        } catch (DateTimeParseException e) {
            return false; // Якщо формат неправильний
        }
    }

    // Метод для пошуку потяга
    public static void findTrain(int n, Train[] trains) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть пункт призначення: ");
        String place = sc.nextLine();

        switch (n) {
            case 1:
                for (Train train : trains) {
                    if (place.equals(train.getDestination())) {
                        System.out.println("\n" + train);
                    }
                }
                break;
            case 2:
                System.out.print("Введіть час для порівняння (у формі год:хв): ");
                String time = sc.nextLine();
                LocalTime t1 = LocalTime.parse(time);
                for (Train train : trains) {
                    LocalTime t2 = LocalTime.parse(train.getTime());
                    if (place.equals(train.getDestination()) && t2.isAfter(t1)) {
                        System.out.println(train);
                    }
                }
                break;
            case 3:
                for (Train train : trains) {
                    if (place.equals(train.getDestination()) && train.getGeneralSeats() > 0) {
                        System.out.println(train);
                    }
                }
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        System.out.print("Введіть кількість потягів: ");
        int n = sc.nextInt();
        sc.nextLine();  // Очищення буфера після nextInt()
        Train[] trains = new Train[n];

        // Введення всіх даних про потяг
        for (int i = 0; i < n; i++) {
            trains[i] = new Train();
            System.out.println("\nВведіть інформацію для потяга №" + (i + 1));
            inputData(trains[i]);  // Виклик методу для введення даних
        }

        do {
            System.out.println("============================================");
            System.out.println("\n\n1. Показати потяги за пунктом призначення");
            System.out.println("2. Показати потяги за часом відправки");
            System.out.println("3. Показати потяги з загальними місцями");
            System.out.println("4. Вийти");
            System.out.print("Ваш вибір: ");
            choice = sc.nextInt();
            sc.nextLine();  // Очищення буфера після nextInt()
            if (choice != 4) {
                findTrain(choice, trains);
            }
        } while (choice != 4);

        System.out.println("Програма завершена.");
    }
}
