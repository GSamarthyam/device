package after;

import java.time.LocalDate;

class VoidWarranty extends Warranty {
    //This will ensure thread safety
    private static final VoidWarranty instance = new VoidWarranty();

    private VoidWarranty() {
    }

    public static VoidWarranty getInstance() {
        return instance;
    }

    @Override
    public boolean claim(LocalDate claimDate) {
        // since the warranty is voided, it can never succeed! So always return false
        return false;
    }
}
