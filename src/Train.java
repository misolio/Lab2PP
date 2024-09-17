import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Train {
    private String destination;
    private int tnumb;
    private String time;
    private Map<String, Integer> SeatsCategories;

    public Train() {
        setDestination(destination);
        setTnumb(tnumb);
        setTime(time);
        this.SeatsCategories = new HashMap<>();

        initSeatsCategories();
    }

    private void initSeatsCategories(){
        SeatsCategories.put("Загальні",0);
        SeatsCategories.put("Плацкарт",0);
        SeatsCategories.put("Купе",0);
        SeatsCategories.put("Люкс",0);
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setTnumb(int tnumb) {
        this.tnumb = tnumb;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public void setSeatsCategories(String category, int count){
        if (SeatsCategories.containsKey(category)){
            SeatsCategories.put(category, count);
        } else {
            System.out.println("Категорії" + category+ "не існує");
        }
    }

    public String getDestination() {
        return destination;
    }
    public int getTnumb() {
        return tnumb;
    }
    public String getTime() {
        return time;
    }
    public Map<String, Integer> getSeatsCategories() {
        return SeatsCategories;
    }

    @Override
    public String toString() {
        StringBuilder seats = new StringBuilder();
        for (Map.Entry<String, Integer> entry : SeatsCategories.entrySet())
            seats.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");

        return "Потяг{\nПунк призначення: "+destination+
                "\nНомер потяга: "+tnumb+
                "\nЧас відправки: "+time+
                "\nКількість місць\n"+seats.toString()+"}";
    }
    public void inputData() {
        Scanner scanner = new Scanner(System.in);

        // Введення пункту призначення
        System.out.print("Введіть пункт призначення: ");
        this.destination = scanner.nextLine();

        // Введення номера потяга з перевіркою на ціле число
        System.out.print("Введіть номер потяга: ");
        String inputTnumb = scanner.nextLine();
        while (!isValidInteger(inputTnumb) || Integer.parseInt(inputTnumb) <= 0) {
            System.out.println("Некоректне введення номера потяга. Спробуйте знову.");
            System.out.print("Введіть номер потяга: ");
            inputTnumb = scanner.nextLine();
        }
        this.tnumb = Integer.parseInt(inputTnumb);

        // Введення часу з перевіркою формату "год:хв" за допомогою LocalTime
        System.out.print("Введіть час відправки (у формі год:хв): ");
        this.time = scanner.nextLine();
        while (!isValidTime(this.time)) {
            System.out.println("Час має бути у форматі год:хв (наприклад, 14:30). Спробуйте ще раз.");
            System.out.print("Введіть час відправки (у формі год:хв): ");
            this.time = scanner.nextLine();
        }

        // Введення кількості місць для кожної категорії
        System.out.println("Введіть кількість місць в кожній з категорій:");
        for (String category : SeatsCategories.keySet()) {
            System.out.print("Кількість місць для категорії '" + category + "': ");
            String inputSeats = scanner.nextLine();
            while (!isValidInteger(inputSeats) || Integer.parseInt(inputSeats) < 0) {
                System.out.println("Некоректне введення кількості місць. Введіть ціле невід'ємне число.");
                System.out.print("Кількість місць для категорії '" + category + "': ");
                inputSeats = scanner.nextLine();
            }
            setSeatsCategories(category, Integer.parseInt(inputSeats));
        }
    }

    // Перевірка на коректність введеного цілого числа
    private boolean isValidInteger(String input) {
        return input.matches("\\d+"); // Перевірка, що рядок складається лише з цифр
    }

    // Перевірка формату часу за допомогою LocalTime
    private boolean isValidTime(String time) {
        try {
            LocalTime.parse(time); // Перевірка, чи можна перетворити введений час у LocalTime
            return true; // Якщо формат правильний
        } catch (DateTimeParseException e) {
            return false; // Якщо формат неправильний
        }
    }

    public int getGeneralSeats(){
        return SeatsCategories.get("Загальні");
    }
}
