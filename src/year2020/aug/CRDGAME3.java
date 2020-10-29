package year2020.aug;

import java.util.Scanner;

public class CRDGAME3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int chefPower = sc.nextInt();
            int rickPower = sc.nextInt();

            int isChefWins = getMinDigitsForPower(chefPower) < getMinDigitsForPower(rickPower) ? 0 : 1;

            System.out.println(isChefWins + " " + Math.min(getMinDigitsForPower(chefPower), getMinDigitsForPower(rickPower)));
        }
    }

    private static int getMinDigitsForPower(int power) {
        return power / 9 + (power % 9 == 0 ? 0 : 1);
    }
}
