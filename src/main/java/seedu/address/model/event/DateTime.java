package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event's start or end date time in the address book.
 */
public class DateTime {
    public static final String MESSAGE_CONSTRAINTS =
            "Date times should follow this format: DD-MM-YYYY HH:mm and only values within valid range are allowed.";
    public final String dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date-time.
     */
    public DateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = dateTime;
    }

    /**
     * Returns true if a given string is a valid date time.
     */
    public static boolean isValidDateTime(String test) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        try {
            LocalDateTime validDateTime = LocalDateTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if a valid startDate is earlier than or equal to a valid endDate.
     */
    public static boolean isValidDateRange(String startDate, String endDate) {
        if (!isValidDateTime(startDate) || !isValidDateTime(endDate)) {
            return false;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime startDateTime = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(endDate, formatter);
        return startDateTime.equals(endDateTime) || startDateTime.isBefore(endDateTime);
    }

    @Override
    public String toString() {
        return dateTime;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && dateTime.equals(((DateTime) other).dateTime)); // state check
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }
}