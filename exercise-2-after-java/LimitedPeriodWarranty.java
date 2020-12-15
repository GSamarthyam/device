package after;

import java.time.Duration;
import java.time.LocalDate;

class LimitedPeriodWarranty extends Warranty
{
    private LocalDate purchaseDate;
    private Duration duration;

    public LimitedPeriodWarranty(LocalDate purchaseDate, Duration duration)
    {
        this.purchaseDate = purchaseDate;
        this.duration = duration;
    }

    @Override
    public boolean claim(LocalDate claimDate)
    {
        return claimDate.isBefore(purchaseDate.plus(duration));
    }
}
