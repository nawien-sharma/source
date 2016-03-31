package swip.ch17jquerydatepicker;


import org.openqa.selenium.By;

import java.util.function.Supplier;

import static org.openqa.selenium.By.className;

/**
 * This enum is a Supplier of ByClassName from Selenium By API.
 */
public enum JQueryByClassName implements Supplier<By> {

    CALENDAR("ui-datepicker-calendar"),      //<1>
    NEXT_MONTH_BUTTON("ui-datepicker-next"), //<2>
    PREV_MONTH_BUTTON("ui-datepicker-prev"), //<3>
    DISPLAY_MONTH("ui-datepicker-month"),    //<4>
    CALENDAR_HEADER("ui-datepicker-header"),
    CALENDAR_TITLE("ui-datepicker-title"),
    DISPLAY_YEAR("ui-datepicker-year");      //<5>

    private final By by;

    JQueryByClassName(String id) {
        this.by = className(id);
    }

    /**
     * @return the by instance variable which is a ByClassName.
     */
    @Override
    public By get() {
        return by;
    }

    @Override
    public String toString() {
        return by.toString();
    }
}