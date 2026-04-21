package pett.iat.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatarData {
   private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy", new Locale("pt", "BR"));

   public static String converterData(LocalDateTime data) {
      if (data == null) {
         return "";
      }

      return data.format(FORMATTER);
   }
}
