package Main;

/**
 * Created by pmw90 on 2015-12-03.
 */
public class TerminalVo {

    int number;
    String name;
    TerminalVo(){

    }
    TerminalVo(int number,String name){
        this.number = number;
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
