import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/*
https://www.acmicpc.net/problem/1193
 */
public class Problem2748 {


    public static void main(String[] args) {

        long[] array = new long[100];
        array[0] = 0;
        array[1] = 1;


        Scanner scan = new Scanner(System.in);
//        Scanner scan = new Scanner("17");


        int input_x = scan.nextInt();

        for (int i = 2; i < input_x + 1; i++) {
            array[i] = array[i-1] + array[i-2];
        }

        System.out.print(array[input_x]);
    }
}

