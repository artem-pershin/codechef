package year2019.march;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class JAIN {
    private static int A = 1 << 4;
    private static int E = 1 << 3;
    private static int I = 1 << 2;
    private static int O = 1 << 1;
    private static int U = 1;

    private static final Map<Character, Integer> vowelMap;

    static {
        final Map<Character, Integer> m = new HashMap<>();
        m.put('a', A);
        m.put('e', E);
        m.put('i', I);
        m.put('o', O);
        m.put('u', U);

        vowelMap = Collections.unmodifiableMap(m);
    }

    private static int ALL_VOWELS = A | E | I | O | U; // 0x1F (31 dec);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();
            sc.nextLine(); // skip this empty line

            String[] dishes = new String[N];

            for (int j = 0; j < N; j++) {
                dishes[j] = sc.nextLine();
            }

            System.out.println(getAnswer(dishes));
        }
    }

    static long getAnswer(String[] dishes) {
        long ans = 0;
        int[] a = new int[ALL_VOWELS + 1];

        for (String dish: dishes) {
            int vowelConf = 0;

            for (int x = 0; x < dish.length(); x++) {
                vowelConf |= vowelMap.get(dish.charAt(x));
            }

            a[vowelConf]++;
        }

        for (int k = 1; k <= ALL_VOWELS; k++) {
            if (a[k] > 0) {
                long howManyGoodPairings = 0;
                for (int z = k + 1; z <= ALL_VOWELS; z++) {
                    if ((z | k) == ALL_VOWELS) {
                        howManyGoodPairings += a[z];
                    }
                }

                ans += howManyGoodPairings * a[k];

                if (k == ALL_VOWELS) {
                    ans += (a[k] * (a[k] - 1)) / 2;
                }
            }
        }

        return ans;
    }
}
