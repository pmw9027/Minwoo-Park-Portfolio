package Main;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class Main {

    static Login login;
    static Json jsn;
    public static DB db;
    public static Session session ;
    public static Home home;
    public static void main(String args[]){
        db = new DB();
        //login = new Login();
        home = new Home();

    }
    public static void setSession(String ID,String Name){
        session = new Session(ID,Name);
    }
}

