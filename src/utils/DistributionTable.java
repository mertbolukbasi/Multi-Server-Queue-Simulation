package utils;

import java.util.Random;

public class DistributionTable {
    private final Random random;

    public DistributionTable() {
        this.random = new Random();
    }

    // %10 1, %20 2, %15 3, %25, 4, %30 5
    public int getInterarrivalTime() {
        double number = random.nextDouble();

        if(number < 0.10) return 1;
        else if(number < 0.30) return 2;
        else if(number < 0.45) return 3;
        else if(number < 0.70) return 4;
        else return 5;
    }

    // %15 2, %25 3, %20 4, %10 5, %30 6
    public int getServiceTimeForService1() {
        double number = random.nextDouble();

        if(number < 0.15) return 2;
        else if(number < 0.40) return 3;
        else if(number < 0.60) return 4;
        else if(number < 0.70) return 5;
        else return 6;
    }

    // %25 2, %30 3, %15 4, %15 5, %15 6
    public int getServiceTimeForService2() {
        double number = random.nextDouble();

        if(number < 0.25) return 2;
        else if(number < 0.55) return 3;
        else if(number < 0.70) return 4;
        else if(number < 0.85) return 5;
        else return 6;
    }
}
