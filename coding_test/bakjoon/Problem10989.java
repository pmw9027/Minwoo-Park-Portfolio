import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;


/*
https://www.acmicpc.net/problem/1193
 */
public class Problem10989 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        Scanner scan = new Scanner("10\n" +
//                "5\n" +
//                "2\n" +
//                "3\n" +
//                "1\n" +
//                "4\n" +
//                "2\n" +
//                "3\n" +
//                "5\n" +
//                "1\n" +
//                "7");

        int input_x;
        input_x = scan.nextInt();

        int[] numbers = new int[10001];
        Arrays.fill(numbers, 0);
        for(int i = 0; i < input_x; i++) {
            numbers[scan.nextInt()]++ ;
        }

        for(int i = 1; i < 10001; i++) {
            if (numbers[i] > 0) {
                for(int j = 0; j < numbers[i]; j++) {
                    System.out.println(i);
                }
            }
        }
    }
}

