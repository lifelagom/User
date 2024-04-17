import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Введите ФИО, дату рождения(dd.mm.yyyy), номер телефона и пол(m/f) через запятую: ");
        try (Scanner scanner = new Scanner(System.in, "cp866")) {
            String data = scanner.nextLine();
            String[] parts = data.split(",");
            if (parts.length < 4)
            throw new MyException("Вы ввели меньше данных, чем требуется!");   
            if (parts.length > 4)
            throw new MyException("Вы ввели больше данных, чем требуется!");         
            for (char c: parts[0].toCharArray()) {
                if (Character.isLetter(c) || c == '-' || c == ' ') {
                    continue;
                } else {
                    throw new IllegalArgumentException("Не верно введено ФИО!");
                }
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.mm.yyyy");
            dateFormatter.parse(parts[1]);
            Long.parseLong(parts[2]);
            if (!(parts[3].equals("f") || parts[3].equals("m")))
                throw new MyException("Вы ввели не верный пол!"); 
            writeToFile(data.replace(","," "), parts[0].split(" ")[0]);
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }
        catch (DateTimeParseException e) {
            System.out.println("Вы неверно ввели дату рождения!");
        }
        catch (NumberFormatException e) {
            System.out.println("Вы неверно ввели номер телефона!");
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        }  
    }

    public static void writeToFile(String text, String name) throws Exception 
    { 
        BufferedWriter bw = new BufferedWriter(new FileWriter(name+".txt", true));
        bw.write(text+"\n");
        bw.close();
    }
}