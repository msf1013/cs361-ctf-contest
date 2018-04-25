import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long seed = getCurrentSeed(-1101592371, 1934930505);
        System.out.println("Seed: " + seed);
        System.out.println("---------");
        
        Random r = new Random(seed);
        
        System.out.println("1: " + r.nextInt());
        System.out.println("2: " + r.nextInt());
        System.out.println("3: " + r.nextInt() + " (Answer)");
    }
    
    public static long getCurrentSeed(int i1, int i2) {
        final long multiplier = 0x5DEECE66DL;
        final long inv_mult = 0xDFE05BCB1365L;
        final long increment = 0xBL;
        final long mask = ((1L << 48) - 1);

        long suffix = 0L;
        long lastSeed;
        long currSeed;
        int lastInt;

        for (long i=0; i < (1<<16); i++) {
                suffix = i;
                currSeed = ((long)i2 << 16) | suffix;
                lastSeed = ((currSeed - increment) * inv_mult) & mask;
                lastInt = (int)(lastSeed >>> 16);

                if (lastInt == i1) {
                        /* We've found the current seed, need to roll back 2 seeds */
                        currSeed = lastSeed;
                        lastSeed = ((currSeed - increment) * inv_mult) & mask;
                        return  lastSeed ^ multiplier;
                }
        }

        /* Error, current seed not found */
        System.err.println("current seed not found");
        return 0;
	}

}
