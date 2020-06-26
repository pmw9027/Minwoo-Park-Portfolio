import java.util.ArrayList;
import java.util.Scanner;


/*
https://www.acmicpc.net/problem/1193
 */
public class Problem2798 {

    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner("5 21\n" +
                "5 6 7 8 9");

        int input_x, input_y;
        ArrayList<Integer> cards = new ArrayList<>();

        input_x = scan.nextInt();
        input_y = scan.nextInt();

        for(int i = 0; i < input_x; i++) {
            cards.add(scan.nextInt());

        }
        int tmp = 0 ;
        for(int i = 0;i < input_x; i++) {
            for(int j = i + 1;j < input_x; j++) {
                for(int k = j + 1;k < input_x; k++) {
                    if (cards.get(i) + cards.get(j) + cards.get(k) <= input_y && tmp < cards.get(i) + cards.get(j) + cards.get(k)) {
                        tmp = cards.get(i) + cards.get(j) + cards.get(k);
                    }
                }
            }
        }

        System.out.println(tmp);
    }
}
