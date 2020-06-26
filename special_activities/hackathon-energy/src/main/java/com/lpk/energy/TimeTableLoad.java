package com.lpk.energy;

import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Minwoo on 2017. 4. 7..
 */
public class TimeTableLoad {

    public ArrayList<ClassDo> send() {

        ArrayList<ClassDo> output = new ArrayList<>();

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";


        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/Minwoo/Downloads/time2.csv"), "utf-8"));
            while ((line = br.readLine()) != null) {
                String[] field = line.split(cvsSplitBy);
                if(field[0].equals(""))
                    continue;

                output.add(new ClassDo(field[3],field[4],field[6],field[7],field[8],field[9]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return output;
    }
}
