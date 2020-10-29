package year2019.july;

import java.util.*;
import java.util.stream.Collectors;

public class GUESSPRM {
    private static final int PRIMES_LESS_THAN = (int) Math.ceil(Math.sqrt(1_000_000_000));
    private static final boolean[] prime_sieve = new boolean[PRIMES_LESS_THAN];

    private static final List<Long> primes = new ArrayList<>();

    static {
        Arrays.fill(prime_sieve, true);
        prime_sieve[0] = false;
        prime_sieve[1] = false;

        long curPrime = 2;
        while (curPrime < PRIMES_LESS_THAN) {
            // System.out.println("prime: " + curPrime);
            primes.add(curPrime);

            for (int i = 2; i * curPrime < PRIMES_LESS_THAN; i++) {
                prime_sieve[i * (int) curPrime] = false;
            }

            curPrime++;

            while (curPrime < PRIMES_LESS_THAN && !prime_sieve[(int) curPrime]) {
                curPrime++;
            }
        }
    }

    private static class Oracle {
        private final long P;

        public Oracle(long P) {
            this.P = P;
            // System.out.println("initialized with prime: " + this.P);
        }

        public Answer ask(long x) {
            // System.out.println("asked: " + x);
            return new Answer(x, (x * x) % P);
        }

        public boolean isCorrectGuess(long guessedPrime) {
            if (guessedPrime != this.P) {
                System.out.println(String.format("guessed: %d, prime: %d", guessedPrime, this.P));
            }
            return guessedPrime == this.P;
        }
    }

    static class Answer {
        public long x;
        public long x2;
        public long q;

        public Answer(long x, long q) {
            this.x = x;
            this.x2 = x * x;
            this.q = q;
        }
    }

    private static class PrimeGuesser {
        private int M = 0;
        private long answer;
        // private Answer[] answers = new Answer[11];
        private List<Answer> answers = new ArrayList<>();
        private List<Long> primeCandidates = new ArrayList<>();
        private long FIRST_QUESTION = PRIMES_LESS_THAN;

        private PrimeGuesser(long ans) {
            this.answer = ans;
        }

        private long questionProd() {
            long q = question();
            System.out.println(String.format("1 %d", q));
            System.out.flush();

            return q;
        }

        private long question() {
            M++;
            long q;

            if (M == 1) {
                q = FIRST_QUESTION;
            } else {
                q = (long) Math.ceil(Math.sqrt(Collections.max(primeCandidates)));
            }

            return q;
        }

        private void processAnswer(Answer a) {
            answers.add(a);

            if (primeCandidates.isEmpty()) {
                primeCandidates.addAll(factorize(a.x2 - a.q));
            } else {
                primeCandidates = intersect(primeCandidates, factorize(a.x2 - a.q));
            }

            for (Answer answer: answers) {
                primeCandidates = primeCandidates.stream().filter(p -> (answer.x2 % p == answer.q)).collect(Collectors.toList());
            }

        }

        private boolean isGuessReady() {
            return (primeCandidates.size() == 1);
        }

        private long guess() {
            return primeCandidates.get(0);
        }

        private void guessProd() {
            System.out.println(String.format("2 %d", primeCandidates.get(0)));
            System.out.flush();
        }

        private int questionNum() {
            return M;
        }

        private List<Long> intersect(List<Long> s1, List<Long> s2) {
            List<Long> res = new ArrayList<>(s1.size());

            for (Long l1: s1) {
                if (s2.contains(l1)) {
                    res.add(l1);
                }
            }

            return res;
        }

        private static List<Long> factorize(long num) {
            List<Long> res = new ArrayList<>();

            while (num != 1) {
                boolean hasDivisors = false;

                for (Long p: primes) {
                    if (num % p == 0) {
                        if (!res.contains(p)) {
                            res.add(p);
                        }
                        num /= p;

                        hasDivisors = true;
                    }
                }

                if (!hasDivisors && num != 1) {
                    // already simple number
                    res.add(num);
                    break;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) {
        interactiveTest();

        // randomTest();
    }

    private static void interactiveTest() {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= T; i++) {
            PrimeGuesser guesser = new PrimeGuesser(0L);

            while (!guesser.isGuessReady() && guesser.questionNum() <= 10) {
                long curQuestion = guesser.questionProd();
                String ans = sc.nextLine();
                long ansL = Long.parseLong(ans);
                guesser.processAnswer(new Answer(curQuestion, ansL));
            }

            guesser.guessProd();
            String verdict = sc.nextLine();

            if (!"Yes".equals(verdict)) {
                System.exit(0);
            }
        }
    }

    private static void randomTest() {
        Random r = new Random();
        int numTests = 1000;

        for (int i = 0; i < numTests; i++) {
            int rndIdx = r.nextInt(primes.size());

            Oracle o = new Oracle(primes.get(rndIdx));

            PrimeGuesser guesser = new PrimeGuesser(0L);

            while (!guesser.isGuessReady()) {
                guesser.processAnswer(o.ask(guesser.question()));
            }

            boolean isCorrect = o.isCorrectGuess(guesser.guess());
            if (!isCorrect || guesser.questionNum() > 2) {
                System.out.println("incorrect");
            }
            // System.out.println((isCorrect ? "correct" : "incorrect") + " guess");
            // System.out.println("steps needed: " + guesser.questionNum());
            // assert o.isCorrectGuess(guesser.guess());
        }
    }
}
