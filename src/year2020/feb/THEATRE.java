package year2020.feb;

import java.util.*;

public class THEATRE {
    private static List<List<Integer>> permutations = new ArrayList<>();

    private static class MovieRequest {
        private char movieName;
        private int showTime;

        public MovieRequest(char movieName, int showTime) {
            this.movieName = movieName;
            this.showTime = showTime;
        }
    }

    private static class Movie {
        private char name;
        private int price;
        private int requests;

        public Movie(char name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    public static void permute(List<Integer> arr, int k) {
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            permute(arr, k + 1);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() - 1) {
            permutations.add(new ArrayList<>(arr));
            // System.out.println(Arrays.toString(arr.toArray()));
        }
    }

    public static void main(String[] args) {
        permute(Arrays.asList(1, 2, 3, 4), 0);
        // System.out.println(permutations.size());

        List<List<Movie>> allPossibleTimetables = new ArrayList<>(24 * 24);

        for (List<Integer> perm : permutations) {
            for (List<Integer> perm2 : permutations) {
                List<Movie> curTimetable = new ArrayList<>(4);
                for (int i = 0; i < perm.size() /* always = 4 */; i++) {
                    curTimetable.add(new Movie((char) ((perm.get(i) - 1) + 'A'), perm2.get(i) * 25));
                }

                allPossibleTimetables.add(curTimetable);
            }
        }

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int totalProfit = 0;

        for (int x = 1; x <= T; x++) {
            int N = sc.nextInt();
            sc.nextLine();

            int answer = Integer.MIN_VALUE;

            List<MovieRequest> allRequests = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                String movieWatchRequest = sc.nextLine();

                String[] req = movieWatchRequest.split(" ");

                allRequests.add(
                        new MovieRequest(
                                req[0].charAt(0), Integer.parseInt(req[1]) / 3 - 1));
            }

            for (List<Movie> timetbl : allPossibleTimetables) {
                answer = Math.max(calculateProfitOfTimetableForRequests(allRequests, new ArrayList<>(timetbl)), answer);
            }

            totalProfit += answer;
            System.out.println(answer);
        }

        System.out.println(totalProfit);
    }

    private static int calculateProfitOfTimetableForRequests(List<MovieRequest> requests, List<Movie> timetable) {
        for (MovieRequest req: requests) {
            if (timetable.get(req.showTime).name == req.movieName) {
                timetable.get(req.showTime).requests++;
            }
        }

        int ret = 0;

        for (Movie m: timetable) {
            if (m.requests > 0) {
                ret += m.requests * m.price;
            } else {
                ret -= 100;
            }

            m.requests = 0;
        }

        return ret;
    }
}
