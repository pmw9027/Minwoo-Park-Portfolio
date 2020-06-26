package Main;

/**
 * Created by pmw90 on 2015-11-30.
 */
public class Session {
    String ID;
    String Name;
    public Session(String ID,String name){
        this.ID = ID;
        this.Name = name;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public String getID() {
        return ID;
    }
}
