import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Problem11047 {
    public static void main(String[] args) {

//        Scanner scan = new Scanner(System.in);
        Scanner scan = new Scanner("10 4200\n" +
                "1\n" +
                "5\n" +
                "10\n" +
                "50\n" +
                "100\n" +
                "500\n" +
                "1000\n" +
                "5000\n" +
                "10000\n" +
                "50000");

        int input_x = scan.nextInt();
        int input_y = scan.nextInt();

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < input_x; i++) {
            arrayList.add(scan.nextInt());
        }


        Collections.sort(arrayList);

        for (int i = 0; i < arrayList.size(); i++) {
            int temp = 0;
            int cnt = 0;
            for (int j = i; j < arrayList.size(); j++) {

                if (temp == input_y) {
                    break;
                } else if (temp + arrayList.get(j) < input_y) {
                    temp += arrayList.get(j);
                    cnt ++;
                }
            }
            if (temp == input_y) {
                System.out.println(cnt);
                break;
            }
        }
    }
}
