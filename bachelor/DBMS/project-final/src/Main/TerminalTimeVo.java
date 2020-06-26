package Main;

/**
 * Created by pmw90 on 2015-12-04.
 */
public class TerminalTimeVo {

    int Interval_num;
    String bus;
    String start;
    String arrive;
    String time;
    int book_seat;
    String company;
    String date;
    int price;

    public TerminalTimeVo(int interval_num, String bus, String start, String arrive, String time, int book_seat, String company, String date, int price) {

        Interval_num = interval_num;
        this.bus = bus;
        this.start = start;
        this.arrive = arrive;
        this.time = time;
        this.book_seat = book_seat;
        this.company = company;
        this.date = date;
        this.price = price;
    }

    public TerminalTimeVo(){

    }


    public String getStart() {
        return start;
    }

    public String getArrive() {
        return arrive;
    }

    public String getTime() {
        return time;
    }

    public String getCompany() {
        return company;
    }
    public int getInterval_num() {
        return Interval_num;
    }
    public String getBus() {
        return bus;
    }

    public String getDate() {
        return date;
    }

    public int getPrice() {
        return price;
    }
    public int getBook_seat() {
        return book_seat;
    }
}
