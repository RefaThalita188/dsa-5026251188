package unguided;

public class ProjectorRental extends Rental {

    public ProjectorRental(String id, int days) {
        super(id, days);
    }

    @Override
    public abstract int calculateCharge();

    public int RentalCharge() int days) {
        if (units <= 0) {
            throw new IllegalArgumentException("units must be positive");
        }
        return units * calculateCharge();
    }
    }

    @Override
    public String label() {
        return "Projector";
    }
}