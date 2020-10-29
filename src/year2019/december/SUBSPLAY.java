package year2019.december;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SUBSPLAY {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            sc.nextLine();

            String s = sc.nextLine();

//            long ans =
//                    s.chars().boxed()
//                        .collect(Collectors.groupingBy(e -> e, Collectors.counting()))
//                        .values().stream().mapToLong(v -> v / 2).sum();
            //List<Integer>[] charFreqs = new ArrayList<Integer>[26];

            List<List<Integer>> positions = new ArrayList<>();
            for (int j = 0; j < 26; j++) {
                positions.add(new ArrayList<>());
            }

            int ans = 0;

            for (int j = 0; j < N; j++) {
                int c = s.charAt(j) - 'a';
                positions.get(c).add(j);
            }

            int minDiff = Integer.MAX_VALUE;

            for (List<Integer> poses: positions) {
                if (poses.size() <= 1) {
                    continue;
                }

                for (int j = 0; j < poses.size() - 1; j++) {
                    minDiff = Math.min(minDiff, Math.abs(poses.get(j) - poses.get(j +1)));
                }
            }

            if (minDiff != Integer.MAX_VALUE) {
                ans = N - minDiff;
            }

            System.out.println(ans);
        }
    }
}
