package year2020.mar;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class ADASHOP2 {
    private static LinkedList<String> ROUTE_FROM_C1_R1 = new LinkedList<>(Arrays.asList(
            /*"1,1",*/ "8,8", "7,7", "6,8", "1,3", "2,4", "1,5", "4,8", "3,7", "2,8", "1,7", "7,1", "8,2", "7,3", "5,1",
            "8,4", "7,5", "8,6", "3,1"));

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();

            LinkedList<String> fullRoute = new LinkedList<>(ROUTE_FROM_C1_R1);

            if ((r == c) && r != 1) {
                fullRoute.addFirst("1 1");
            } else if (r != 1 || c != 1) {
                fullRoute.addFirst("1 1");
                fullRoute.addFirst(findMoveOnMainDiagonal(r, c));
            }

            System.out.println(fullRoute.size());
            for (String pos: fullRoute) {
                System.out.println(pos.replace(',', ' '));
            }
        }
    }

    private static String findMoveOnMainDiagonal(int r, int c) {
        int ans = 0;

        for (int i = 2; i <= 7 ; i++) {
            if (Math.abs(r - i) == Math.abs(c - i)) {
                ans = i;
                break;
            }
        }

        return String.format("%d %d", ans, ans);
    }
}
