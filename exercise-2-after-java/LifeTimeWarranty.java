package after;

import java.time.LocalDate;

class LifeTimeWarranty extends Warranty
{
    private final LocalDate purchaseDate;

    public LifeTimeWarranty(LocalDate purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public boolean claim(LocalDate claimDate)
    {
        return purchaseDate.isBefore(claimDate);
    }

}
