import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Main {

    // Метод для пошуку потяга
    public static void findTrain(int n, Train[] trains) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть пункт призначення: ");
        String place = sc.nextLine();

        switch (n) {
            case 1:
                for (Train train : trains) {
                    if (place.equals(train.getDestination())) {
                        System.out.println(train);
                    }

                }
                break;
            case 2:
                System.out.print("Введіть час для порівняння (у формі год.хв): ");
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
            default:
                System.out.println("Не знайдено підходящого потягу");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        System.out.print("Введіть кількість потягів: ");
        int n = sc.nextInt();
        sc.nextLine();  // Очищення буфера після nextInt()
        Train[] trains = new Train[n];

        // Введення всіх даних про потяг
        for (int i = 0; i < n; i++) {
            trains[i] = new Train();
            System.out.println("Введіть інформацію для потяга №" + (i + 1));
            trains[i].inputData();
        }

        do {
            System.out.println("1. Показати потяги за пунктом призначення");
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
