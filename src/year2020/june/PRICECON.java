package year2020.june;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PRICECON {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            List<Integer> prices = new ArrayList<>(N);

            for (int i = 0; i < N; i++) {
                prices.add(sc.nextInt());
            }

            int totalPrice = prices.stream().mapToInt(Integer::valueOf).sum();
            int reducedPrice = prices.stream().mapToInt(price -> Math.min(price, K)).sum();

            System.out.println(totalPrice - reducedPrice);
        }
    }
}
