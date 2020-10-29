package year2020.mar;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Scanner;

public class CHPINTU {
    private static final int MAX_NUMBER_OF_BASKETES = 50;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            int[] fruitTypes = new int[MAX_NUMBER_OF_BASKETES + 1];
            int[] fruitCosts = new int[MAX_NUMBER_OF_BASKETES + 1];
            int[] totalPrice = new int[MAX_NUMBER_OF_BASKETES + 1];
            Arrays.fill(totalPrice, -1);

            for (int j = 1; j <= N; j++) {
                fruitTypes[j] = sc.nextInt();
            }

            for (int j = 1; j <= N; j++) {
                fruitCosts[j] = sc.nextInt();
            }

            for (int j = 1; j <= N; j++) {
                int ind = fruitTypes[j];

                if (totalPrice[ind] == -1) {
                    totalPrice[ind] = fruitCosts[j];
                } else {
                    totalPrice[ind] += fruitCosts[j];
                }
            }

            OptionalInt ans = Arrays.stream(totalPrice).filter(totalCost -> totalCost != -1).min();
            System.out.println(ans.getAsInt());
        }
    }
}
