package year2019.may;

import java.util.Scanner;

public class REDONE {
    private static final long MODULO = 1000000007;
    private static final int MAX_N = 1000000;
    private static int[] answers = new int[MAX_N + 1];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        fillAnswers();

        for (int i = 1; i <= T; i++) {
            int N = sc.nextInt();

            System.out.println(answers[N]);
        }
    }

    private static void fillAnswers() {
        answers[0] = 0;

        long current_ans = 0L;

        for (int j = 1; j <= MAX_N; j++) {
            current_ans += j + current_ans * j;
            current_ans %= MODULO;
            answers[j] = (int) current_ans;
        }
    }
}
