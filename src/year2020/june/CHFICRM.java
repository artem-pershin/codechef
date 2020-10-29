package year2020.june;

import java.util.Scanner;

public class CHFICRM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();

            int[] coins = new int[16];
            boolean canBeServed = true;

            for (int i = 0; i < N; i++) {
                int buyerCoin = sc.nextInt();

                switch (buyerCoin) {
                    case 10: {
                        if (coins[5] < 1) {
                            canBeServed = false;
                        }

                        coins[5]--;
                        break;
                    }
                    case 15: {
                        if (coins[5] < 2 && coins[10] < 1) {
                            canBeServed = false;
                        }

                        if (coins[10] >= 1) {
                            coins[10]--;
                        } else if (coins[5] >= 2) {
                            coins[5] -= 2;
                        }

                        break;
                    }
                }

                coins[buyerCoin]++;
            }

            System.out.println(canBeServed ? "YES" : "NO");
        }
    }
}
