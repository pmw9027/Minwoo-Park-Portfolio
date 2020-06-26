import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem4 {

    static HashMap<String, ArrayList<String[]>> map = new HashMap();

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
//        HashMap<String, ArrayList<String[]>> map = new HashMap();

        int input_x = scan.nextInt();
        int input_y = scan.nextInt();
        scan.nextLine();
        int input_z = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < input_z; i++) {
            String input = scan.nextLine();
            String[] splitted = input.split("\\s+");

            try {
                String[] str = {splitted[1], splitted[2]};
                map.get(splitted[0]).add(str);
            } catch (Exception e) {
                ArrayList<String[]> arrayList = new ArrayList<>();
                String[] str = {splitted[1], splitted[2]};
                arrayList.add(str);
                map.put(splitted[0], arrayList);
            }
        }
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        for (String[] strings: map.get("A")) {

            if (input_y - Integer.parseInt(strings[1]) > 0) {

                ArrayList<String> route = new ArrayList<>();
                route.add("A");
                route.add(strings[0]);
                arrayLists.addAll(test(strings[0], Integer.parseInt(strings[1]), route));

            }

        }
    }

    static ArrayList<ArrayList<String>> test(String str, int fuel, ArrayList<String> route) {

        int fuels = fuel + 10;
//        int new_earn = earn + 30;
        ArrayList<ArrayList<String>> arrayLists = new ArrayList<>();

        try {
            for (String[] strings: map.get(str)) {
                if (fuels - Integer.parseInt(strings[1]) > 0) {

                    ArrayList<String> new_route = new ArrayList<>();
                    new_route.addAll(route);
                    new_route.add(strings[1]);

                    arrayLists.addAll(test(strings[0], Integer.parseInt(strings[1]), new_route));


                } else {
                    arrayLists.add(route);

                }
            }
        } catch (Exception e) {
            arrayLists.add(route);

        }
        return arrayLists;

    }
}
