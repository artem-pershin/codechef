package year2019.october;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class S10E {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            int ans = 0;
            int currentMin5;
            LinkedList<Integer> lastFivePrices = new LinkedList<>();

            int currentPrice = sc.nextInt();
            lastFivePrices.add(currentPrice);
            ans++;

            for (int j = 1; j < N; j++) {
                currentPrice = sc.nextInt();
                currentMin5 = Collections.min(lastFivePrices);

                if (currentPrice < currentMin5) {
                    ans++;
                }

                if (lastFivePrices.size() >= 5) {
                    lastFivePrices.removeFirst();
                }

                lastFivePrices.add(currentPrice);
            }

            System.out.println(ans);
        }
    }
}
