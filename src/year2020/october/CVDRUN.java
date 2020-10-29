package year2020.october;

import java.util.Arrays;
import java.util.Scanner;

class CVDRUN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int i = 0; i < T; i++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int X = sc.nextInt();
            int Y = sc.nextInt();

            boolean[] visited = new boolean[N];

            int currentCity = X;

            while (!visited[currentCity] && currentCity != Y) {
                visited[currentCity] = true;
                currentCity = (currentCity + K) % N;
            }

            System.out.println(currentCity == Y ? "YES" : "NO");
        }
    }
}
