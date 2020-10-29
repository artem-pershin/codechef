package year2019.srm758_10may;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class WildSequence {
    public static int[] construct(int head, int[] rest) {
        long start = System.currentTimeMillis();

        ArrayList<Integer> ll = new ArrayList<>(Arrays.stream(rest)
                .boxed().collect(Collectors.toList()));

        int ans[] = new int[rest.length + 1];

        while ((System.currentTimeMillis() - start) < 1950) {
            Collections.shuffle(ll);
            if (isWild(head, ll)) {
                ans[0] = head;

                for (int i = 1; i <= rest.length; i++) {
                    ans[i] = ll.get(i - 1);
                }
                return ans;
            }
        }

        return new int[0];
    }

    private static boolean isWild(int head, ArrayList<Integer> a) {
        if (a.size() < 2) {
            return true;
        }

        if (head < a.get(0) && a.get(0) < a.get(1)) {
            return false;
        }

        if (head > a.get(0) && a.get(0) > a.get(1)) {
            return false;
        }

        for (int i = 0; i < a.size() - 2; i++) {
            if (a.get(i) < a.get(i + 1) && a.get(i + 1) < a.get(i + 2)) {
                return false;
            }

            if (a.get(i) > a.get(i + 1) && a.get(i + 1) > a.get(i + 2)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(construct(4, new int[]{1, 2, 3, 6, 7, 5, 100, 300, 500, 700, 900})));
    }
}
