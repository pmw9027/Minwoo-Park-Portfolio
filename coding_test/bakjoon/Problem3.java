import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Problem3 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int input_x = scan.nextInt();
        String input = scan.nextLine();
        input = scan.nextLine();
        String[] splitted = input.split("\\s+");

        HashMap<Integer, ArrayList<Integer>> map = new HashMap();

        int[] player = new int[input_x];

        for (int i = 0; i < splitted.length; i++) {
            if (splitted[i].equals("A")) {
                ArrayList<Integer> arrayList = new ArrayList<>();
                player[i % input_x] ++;
                map.get(i % input_x);

            } else if (splitted[i].equals("J")) {

                player[(i+1) % input_x] ++;
                if(i % input_x == 0){
                    player[player.length-1] ++;
                }
                else {
                    player[(i-1) % input_x] ++;
                }
            } else if (splitted[i].equals("Q")) {

                for(int j = 0; j < player.length;j++ ){
                    player[j] ++;
                }

            } else if (splitted[i].equals("K")) {
                try {
                    map.get(i % input_x).add(Integer.parseInt(splitted[i + 1]));
                    i++;
                } catch (Exception e) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(Integer.parseInt(splitted[i+1]));
                    map.put(i+1 % input_x, arrayList);
                    i++;
                }
            }
        }
        for(int j = 0; j < player.length;j++ ){
            System.out.print(player[j] + " ");
        }
    }
}
