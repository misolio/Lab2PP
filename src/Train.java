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
    public int getGeneralSeats(){
        return SeatsCategories.get("Загальні");
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
   }
