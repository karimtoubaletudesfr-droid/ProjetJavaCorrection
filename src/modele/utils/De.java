package modele.utils;

import java.util.Random;

public final class De {
    private static final Random RNG = new Random();
    private De() { }
    public static int d6() { return 1 + RNG.nextInt(6); }
}
