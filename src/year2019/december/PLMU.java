package year2019.december;

import java.util.*;
import java.util.stream.Collectors;

public class PLMU {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            List<Long> sequence = new ArrayList<>();
            for (int j = 1; j <= N; j++) {
                sequence.add(sc.nextLong());
            }

            Map<Long,Long> counts =
                    sequence.stream()
                            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            sequence = new ArrayList<>(counts.keySet());
            Collections.sort(sequence);

            int ans = 0;

            N = sequence.size();

            for (int j = 0; j < N; j++) {
                long ai = sequence.get(j);
                ans += (counts.get(ai) * (counts.get(ai) - 1)) / 2;

                for (int k = j + 1; k < N; k++) {
                    long aj = sequence.get(k);

                    if (ai + aj < ai * aj) break;

                    if (ai + aj == ai * aj) ans += counts.get(ai) * counts.get(aj);
                }
            }

            System.out.println(ans);
        }
    }
}
