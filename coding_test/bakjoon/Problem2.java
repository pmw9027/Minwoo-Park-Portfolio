import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem2 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int input_x = scan.nextInt();
        HashMap<String, Integer> map = new HashMap();
        ArrayList<Integer> arrayList = new ArrayList<>();
        String input = scan.nextLine();

        for (int i = 0; i < input_x; i++) {
            input = scan.nextLine();
            String[] splitted = input.split("\\s+");
            if (splitted.length == 2) {
                arrayList.add(Integer.parseInt(splitted[1]));
                try {
                    map.put(splitted[1], map.get(splitted[1]) + 1);
                } catch (Exception e) {
                    map.put(splitted[1], 1);
                }
            } else {
                int tmp = 0;
                for (String key : map.keySet()) {
                    if (tmp < map.get(key)) {
                        tmp = map.get(key);
                    }
                }

                for (int j=0; j<arrayList.size();j++) {
                    if (map.get(String.valueOf(arrayList.get(j))) == tmp) {
                        map.put(String.valueOf(arrayList.get(j)), map.get(String.valueOf(arrayList.get(j))) - 1);
                        arrayList.remove(j);
                        break;
                    }
                }
            }
        }
        for(int elm: arrayList){

            System.out.print(elm+" ");
        }
    }
}
