package after;

import java.time.LocalDate;

abstract class Warranty
{
    // returns true if claim successful - false if unsucessful
    abstract boolean claim(LocalDate claimDate);
}
