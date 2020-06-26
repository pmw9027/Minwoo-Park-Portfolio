package Main;

/**
 * Created by pmw90 on 2015-12-06.
 */
public class BookVo {
    int book_num;
    String book_user;
    String date;
    String date_time;
    String start;
    String arrive;
    int price;

    public BookVo(int book_num, String book_user, String date, String date_time, String start, String arrive, int price) {
        this.book_num = book_num;
        this.book_user = book_user;
        this.date = date;
        this.date_time = date_time;
        this.start = start;
        this.arrive = arrive;
        this.price = price;
    }

    public String getBook_user() {
        return book_user;
    }

    public int getBook_num() {
        return book_num;
    }

    public void setBook_num(int book_num) {
        this.book_num = book_num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
