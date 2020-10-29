package year2019.feb;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Scanner;

public class GUESSRT {
    private static final long MODULO = (long) 1e9 + 7;

    static long modInverse(long a, long m) {
        long m0 = m;
        long y = 0, x = 1;

        if (m == 1)
            return 0;

        while (a > 1) {
            long q = a / m;

            long t = m;

            m = a % m;
            a = t;
            t = y;

            y = x - q * y;
            x = t;
        }

        if (x < 0)
            x += m0;

        return x;
    }

    public static void main(String[] args) {
//        System.out.println(
//                (new BigRational(2, 69)).plus(
//                (new BigRational(44, 69).times(new BigRational(1, 43)))));

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int x = 0; x < T; x++) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int M = sc.nextInt();

            System.out.println(solve(N, K, M));
        }
    }

    private static long solve(int N, int K, int M) {
        int[] num = new int[M + 1];
        int[] act = new int[M + 1];
        num[1] = N;

        BigRational br = BigRational.ZERO;

        for (int move = 1; move < M; move++) {
            int[] lft = boxesLeft(br, num[move], K);

            if (num[move] > K && lft[0] == 0) {
                act[move] = 0;
                num[move + 1] = lft[1];
            } else {
                BigRational oppositeProb = BigRational.ONE.minus(br);
                BigRational curAdd = new BigRational(1, num[move]);
                curAdd = oppositeProb.times(curAdd);

                br = br.plus(curAdd);

                num[move + 1] = num[move] + K;
                act[move] = 1;
            }
        }

        act[M] = 1;

        BigRational oppositeProb = BigRational.ONE.minus(br);
        BigRational curAdd = new BigRational(1, num[M]);
        curAdd = oppositeProb.times(curAdd);

        br = br.plus(curAdd);
//
//                br = br.plus(curAdd);

//        BigRational br = BigRational.ZERO;
//
//        for (int i = 1; i <= M; i++) {
//            if (act[i] == 1) {
//                BigRational oppositeProb = BigRational.ONE.minus(br);
//                BigRational curAdd = new BigRational(1, num[i]);
//                curAdd = oppositeProb.times(curAdd);
//
//                br = br.plus(curAdd);
//            }
//        }

        long P = br.num.longValue();
        long Q = br.den.longValue();

        return P * modInverse(Q, MODULO);
    }

    public static int[] boxesLeft(BigRational br, int N, int K) {
        BigRational oppositeProb1 = BigRational.ONE.minus(br);
        BigRational firstGain = oppositeProb1.times(new BigRational(1, N));

        BigRational oppositeProb2 = BigRational.ONE.minus(firstGain.plus(br));
        BigRational secondGain = oppositeProb2.times(new BigRational(1, N + K));

        BigRational totalGain = firstGain.plus(secondGain);

        int[] res = new int[2];

        if (N % K == 0) {
            res[1] = K;
        } else {
            res[1] = N % K;
        }

        BigRational removeGain = oppositeProb1.times(new BigRational(1, res[1]));

        if (totalGain.compareTo(removeGain) < 0) {
            res[0] = 0;
        } else {
            res[0] = 1;
        }

        return res;
    }

    public static class BigRational implements Comparable<BigRational> {
        public final static BigRational ZERO = new BigRational(0);
        public final static BigRational ONE = new BigRational(1);
        public final static BigRational TWO = new BigRational(2);

        public BigInteger num;   // the numerator
        public BigInteger den;   // the denominator (always a positive integer)


        // create and initialize a new BigRational object
        public BigRational(int numerator, int denominator) {
            this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
        }

        // create and initialize a new BigRational object
        public BigRational(int numerator) {
            this(numerator, 1);
        }

        // create and initialize a new BigRational object from a string, e.g., "-343/1273"
        public BigRational(String s) {
            String[] tokens = s.split("/");
            if (tokens.length == 2)
                init(new BigInteger(tokens[0]), new BigInteger(tokens[1]));
            else if (tokens.length == 1)
                init(new BigInteger(tokens[0]), BigInteger.ONE);
            else
                throw new IllegalArgumentException("For input string: \"" + s + "\"");
        }

        // create and initialize a new BigRational object
        public BigRational(BigInteger numerator, BigInteger denominator) {
            init(numerator, denominator);
        }

        private void init(BigInteger numerator, BigInteger denominator) {

            // deal with x / 0
            if (denominator.equals(BigInteger.ZERO)) {
                throw new ArithmeticException("Denominator is zero");
            }

            // reduce fraction (if num = 0, will always yield den = 0)
            BigInteger g = numerator.gcd(denominator);
            num = numerator.divide(g);
            den = denominator.divide(g);

            // to ensure invariant that denominator is positive
            if (den.compareTo(BigInteger.ZERO) < 0) {
                den = den.negate();
                num = num.negate();
            }
        }

        // return string representation of (this)
        public String toString() {
            if (den.equals(BigInteger.ONE)) return num + "";
            else return num + "/" + den;
        }

        // return { -1, 0, + 1 } if a < b, a = b, or a > b
        public int compareTo(BigRational b) {
            BigRational a = this;
            return a.num.multiply(b.den).compareTo(a.den.multiply(b.num));
        }

        // is this BigRational negative, zero, or positive?
        public boolean isZero() {
            return num.signum() == 0;
        }

        public boolean isPositive() {
            return num.signum() > 0;
        }

        public boolean isNegative() {
            return num.signum() < 0;
        }

        // is this Rational object equal to y?
        public boolean equals(Object y) {
            if (y == this) return true;
            if (y == null) return false;
            if (y.getClass() != this.getClass()) return false;
            BigRational b = (BigRational) y;
            return compareTo(b) == 0;
        }

        // hashCode consistent with equals() and compareTo()
        public int hashCode() {
            return Objects.hash(num, den);
        }


        // return a * b
        public BigRational times(BigRational b) {
            BigRational a = this;
            return new BigRational(a.num.multiply(b.num), a.den.multiply(b.den));
        }

        // return a + b
        public BigRational plus(BigRational b) {
            BigRational a = this;
            BigInteger numerator = a.num.multiply(b.den).add(b.num.multiply(a.den));
            BigInteger denominator = a.den.multiply(b.den);
            return new BigRational(numerator, denominator);
        }

        // return -a
        public BigRational negate() {
            return new BigRational(num.negate(), den);
        }

        // return |a|
        public BigRational abs() {
            if (isNegative()) return negate();
            else return this;
        }

        // return a - b
        public BigRational minus(BigRational b) {
            BigRational a = this;
            return a.plus(b.negate());
        }

        // return 1 / a
        public BigRational reciprocal() {
            return new BigRational(den, num);
        }

        // return a / b
        public BigRational divides(BigRational b) {
            BigRational a = this;
            return a.times(b.reciprocal());
        }

        // return double reprentation (within given precision)
        public double doubleValue() {
            int SCALE = 32;        // number of digits after the decimal place
            BigDecimal numerator = new BigDecimal(num);
            BigDecimal denominator = new BigDecimal(den);
            BigDecimal quotient = numerator.divide(denominator, SCALE, RoundingMode.HALF_EVEN);
            return quotient.doubleValue();
        }
    }
}
