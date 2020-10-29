package year2020.aug;

import java.util.Scanner;

public class CHEFWARS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int H = sc.nextInt();
            int P = sc.nextInt();

            while (P > 0 && H > 0) {
                H -= P;
                P /= 2;
            }

            System.out.println(H > 0 ? 0 : 1);
        }
    }
}
