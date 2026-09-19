package lw01.prelab;

public class ColourPrint extends PrintJob {

    private static final int FIRST_TIER_LIMIT = 10;
    private static final int FIRST_TIER_RATE = 1500;
    private static final int SECOND_TIER_RATE = 1000;
    private static final int SETUP_FEE = 2000;

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();
        int charge;
        if (pages <= FIRST_TIER_LIMIT) {
            charge = pages * FIRST_TIER_RATE;
        } else {
            int extraPages = pages - FIRST_TIER_LIMIT;
            charge = (FIRST_TIER_LIMIT * FIRST_TIER_RATE) + (extraPages * SECOND_TIER_RATE);
        }
        charge += SETUP_FEE;
        return charge;
    }

    @Override
    public String label() {
        return "Colour";
    }
}