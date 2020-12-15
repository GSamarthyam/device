package after;

import java.time.LocalDate;

public class Device
{
    private Warranty warranty;

    public Device(Warranty warranty)
    {
        this.warranty = warranty;
    }

    public void visibleDamageDone()
    {
        this.warranty = VoidWarranty.getInstance();
    }

    public boolean claim(LocalDate claimDate)
    {
        return warranty.claim(claimDate);
    }
}


