package Main;

/**
 * Created by pmw90 on 2015-11-26.
 */
public class UserVo {

    String ID;
    String PW;
    String name;
    String address;
    int money;

    public UserVo() {

    }

    public UserVo(String ID, String PW, String name, String address, int money) {
        this.ID = ID;
        this.PW = PW;
        this.name = name;
        this.address = address;
        this.money = money;
    }

    public UserVo(String ID, String name, String address, int money) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.money = money;
    }

    public String getID() {
        return ID;
    }

    public String getPW() {
        return PW;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getMoney() {
        return money;
    }
}
