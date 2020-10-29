package year2020.may;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class CORUS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();
            int Q = sc.nextInt();

            sc.nextLine();
            String S = sc.nextLine();

            List<Integer> sortedFreqs = new ArrayList<>(S.chars().boxed()
                    .collect(toMap(identity(), v -> 1, Integer::sum)).values());

            for (int q = 1; q <= Q; q++) {
                int C = sc.nextInt();
                int ans = sortedFreqs.stream().mapToInt(o -> Math.max(o - C, 0)).sum();
                System.out.println(ans);
            }
        }
    }
}
