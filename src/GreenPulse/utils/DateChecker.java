package GreenPulse.utils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateChecker {
    public static boolean isDateAvailable(LocalDate startDate , LocalDate endDate , List<LocalDate> range) {
        for (LocalDate sdate = startDate; !sdate.isAfter(endDate); sdate = sdate.plusDays(1)){
            if(range.contains(sdate)){
                return false;
            }
        }
        return true;
    }
    public static List<LocalDate> dateList(LocalDate start , LocalDate end){
        return start.datesUntil(end.plusDays(1)).collect(Collectors.toList());

    }

}
