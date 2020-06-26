import java.util.Scanner;


/*
https://www.acmicpc.net/problem/1193
 */
public class Problem1193 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        Scanner scan = new Scanner("2");

        int input_x;
        input_x = scan.nextInt();

        int output, layer;

        for (layer = 1 ;(layer + 1) * layer / 2 < input_x; layer++) {

        }
        layer -= 1;
        input_x = input_x - (layer + 1) * layer / 2;

        int a, b;
        a = layer + 2 - input_x;
        b = input_x;


        System.out.println(layer % 2 == 0 ? a+"/"+b : b+"/"+a);

    }
}
