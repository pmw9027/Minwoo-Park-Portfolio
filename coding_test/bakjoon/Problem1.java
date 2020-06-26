import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Problem1 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int input_x = scan.nextInt();
        HashMap<String, Integer> map = new HashMap();
        HashMap<Integer, Integer> cnt = new HashMap();
        String input = scan.nextLine();
        input = scan.nextLine();
        String[] splitted = input.split("\\s+");

        for (int i=0; i<splitted.length;i++) {
            try {
                map.put(splitted[i], map.get(splitted[i]) + 1);
            } catch (Exception e) {
                map.put(splitted[i], 0);

            }
        }
        for (String i: map.keySet()) {
            cnt.put(map.get(i), 1);
        }

        if (cnt.keySet().size() == 1) {
            System.out.println("Y");
            System.out.println(splitted.length);
            System.out.println(map.size());
        }
        else if (cnt.keySet().size() == 2) {

            Iterator<Integer> iter = cnt.keySet().iterator();
            int i = iter.next();
            int j = iter.next();

            if (i-j == 1 || i-j == -1 || i-j == 0){

                System.out.println("Y");
                System.out.println(splitted.length + 1);
                System.out.println(map.size());

            } else {

                System.out.println("N");
                System.out.println(splitted.length);
                System.out.println(map.size());
            }

        } else {
            System.out.println("N");
            System.out.println(splitted.length);
            System.out.println(map.size());
        }
    }
}
