package before;

import java.time.Duration;
import java.time.LocalDate;

public class Device
{
    private LocalDate purchasedDate;
    private Duration duration;
    private boolean isLifeTime;
    private boolean isVoid;

    //Looks like the Domain object device is mixed up with the Warranty logic
    // Any new requirement will make this harder to maintain and more buggy
    public Device(LocalDate purchasedDate, Duration duration, boolean isLifeTime)
    {
        this.purchasedDate = purchasedDate;
        this.duration = duration;
        this.isLifeTime = isLifeTime;
        this.isVoid = false;
    }

    public void visibleDamageDone()
    {
        isVoid = true;
    }

    public void claim(LocalDate claimDate)
    {
        System.out.println(isValidOn(claimDate)
                ? "Claim of the limited warranty successful"
                : "Sorry dude! No Donut for you!");
    }

    private boolean isValidOn(LocalDate claimDate)
    {
        if (isLifeTime) return true;
        if (isVoid) return false;

        return claimDate.isBefore(purchasedDate.plus(duration));
    }
}