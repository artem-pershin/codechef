package year2020.mar;

import java.io.*;
import java.util.InputMismatchException;

public class ENGXOR {
    public static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c & 15;
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String next() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public static boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

    }

    public static class OutputWriter {
        private PrintWriter writer;

        public OutputWriter(OutputStream stream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(stream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void println(int x) {
            writer.println(x);
            writer.flush();
        }

        public void print(int x) {
            writer.print(x);
        }

        public void printSpace() {
            writer.print(" ");
        }

        public void close() {
            writer.close();
        }
    }

    public static void main(String[] args) {
        InputReader br = new InputReader(
                (System.in));

        OutputWriter ow = new OutputWriter(System.out);

        int T = br.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = br.nextInt();
            int Q = br.nextInt();

            int evenCount = 0;

            for (int j = 0; j < N; j++) {
                if (Integer.bitCount(br.nextInt()) % 2 == 0) {
                    evenCount++;
                }
            }

            for (int j = 1; j <= Q; j++) {
                int P = br.nextInt();
                boolean isEven = Integer.bitCount(P) % 2 == 0;

                ow.print(isEven ? evenCount : N - evenCount);
                ow.printSpace();
                ow.println(isEven ? N - evenCount : evenCount);

                // System.out.println((isEven ? evenCount : N - evenCount) + " " + (isEven ? N - evenCount : evenCount));
            }

        }
    }
}
