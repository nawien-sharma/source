package swip.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import swip.framework.robust.Retry;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Stream;

public interface FormElements extends ExplicitWait {

    default void setInputText(Supplier<By> by, Object value) {
        Retry retry = new Retry(5, 1, TimeUnit.SECONDS);

        retry.attempt(
            () -> {
                Element element = findElement(by);
                element.clear();
                element.sendKeys(value.toString());
                assert value.toString().equals(element.getAttribute("value"));
            }
        );
    }

    default String getInputText(Supplier<By> by) {
        return untilFound(by).getAttribute("value");
    }

    default void setCheckboxValue(Supplier<By> by, boolean value) {
        Element checkbox = untilFound(by);
        if (checkbox.isSelected() != value) {
            checkbox.click();
        }
    }

    default boolean isChecked(Supplier<By> by) {
        return untilFound(by).isSelected();
    }

    default void setRadio(Supplier<By> by, Object value) {
        Optional<Element> radio =
            findElements(by)
                .filter(e -> e.getAttribute("value").equals(value.toString()))
                .findFirst();
        if (radio.isPresent()) {
            radio.get().click();
        } else {
            throw new IllegalArgumentException(
                "unable to find element with value " + value);
        }

    }

    default String getRadio(Supplier<By> by) {
        Optional<Element> radio =
            findElements(by)
                .filter(e -> Boolean.valueOf(e.getAttribute("checked")))
                .findFirst();

        return radio.isPresent() ? radio.get().getAttribute("value") : null;

    }

    default Select getSelect(Supplier<By> by) {
        Element element = untilFound(by);
        until((SearchScope driver) -> {
            element.click();
            return !element.findElements(By.tagName("option")).isEmpty();
        });
        return new Select(element);
    }

    default void select(Supplier<By> by, Object ...select) {
        Stream.of(select).map(Object::toString).forEach(getSelect(by)::selectByVisibleText);
        try {
            if (getSelect(by).getAllSelectedOptions().size()  != select.length) {
                select(by, select);
            }
        } catch (Exception e) {
            //Don't need to handle it.
        }
    }
}