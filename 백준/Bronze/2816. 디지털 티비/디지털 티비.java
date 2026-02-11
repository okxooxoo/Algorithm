import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringBuilder sb = new StringBuilder();

    private static String[] channels;

    private static void swap(int i, int j) {
        String temp = channels[i];
        channels[i] = channels[j];
        channels[j] = temp;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        channels = new String[N];

        for (int i = 0; i < N; i++) {
            channels[i] = br.readLine();
        }

        int pointer = 0;
        int[] method = new int[500];
        int index = 0;

        while (true) {
            if (channels[0].equals("KBS1")) {
                break;
            }

            if (channels[pointer].equals("KBS1")) {
                swap(pointer - 1, pointer);
                pointer--;
                method[index++] = 4;
            } else {
                pointer++;
                method[index++] = 1;
            }
        }

        while (true) {
            if (channels[1].equals("KBS2")) {
                break;
            }

            if (channels[pointer].equals("KBS2")) {
                swap(pointer - 1, pointer);
                pointer--;
                method[index++] = 4;
            } else {
                pointer++;
                method[index++] = 1;
            }
        }

        for (int i = 0; i < 500; i++) {
            if (method[i] == 0) break;

            sb.append(method[i]);
        }

        System.out.println(sb);
    }
}