import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class DateTimeParser {
    private static final List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"),
            DateTimeFormatter.ofPattern("h:mm a dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
    );
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy, EEEE, HH:mm");

    public static LocalDateTime parse(String input) {
        for (DateTimeFormatter formatter : DateTimeParser.formatters) {
           try {
               return LocalDateTime.parse(input, formatter);
           } catch (DateTimeParseException e) {
               // continue
           }
        }
        throw new IllegalArgumentException("Unrecognized date/time format: " + input);
    }

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeParser.formatter);
    }
}
