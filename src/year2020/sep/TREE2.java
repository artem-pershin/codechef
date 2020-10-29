package year2020.sep;

import java.util.HashSet;
import java.util.Scanner;

class TREE2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            HashSet<Integer> differentHeights = new HashSet<>();
            for (int j = 1; j <= N; j++) {
                int curHeight = sc.nextInt();
                if (curHeight != 0) {
                    differentHeights.add(curHeight);
                }
            }

            System.out.println(differentHeights.size());
        }
    }
}
