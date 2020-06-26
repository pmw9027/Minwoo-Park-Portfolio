package Custom;

public class Search {
	private String c_num;
	private String c_color;
	private String car_kind;
	private String c_name;
	private int c_fuel ;
	private int c_price;
	private int Check;
	private String Area;
	private String Date;
	private int day;
	
	private String pnum;
	private String address;
	private String license;
	
	public String getlicense(){
		return license;
	}
	public void setlicense(String licen){
		this.license =licen;
	}
	
	
	public String getaddress(){
		return address;
	}
	public void setaddress(String add){
		this.address =add;
	}
	
	public String getpnum(){
		return pnum;
	}
	public void setpnum(String num){
		this.pnum =num;
	}
	
	
	
	
	public int getday(){
		return day;
	}
	public void setday(int da){
		this.day =da;
	}

	public String getDate(){
		return Date;
	}
	public void setDate(String date){
		this.Date =date;
	}
	
	public String getArea(){
		return Area;
	}
	public void setArea(String area){
		this.Area =area;
	}
	
	public int getCheck(){
		return Check;
	}
	public void setCheck(int check){
		this.Check =check;
	}
	
	
	
	public String getNumber() {
		return c_num;
	}

	public void setNumber(String number) {
		this.c_num = number;
	}

	public String getCar_kind() {
		return car_kind;
	}

	public void setCar_kind(String car_kind) {
		this.car_kind = car_kind;
	}

	public String getName() {
		return c_name;
	}
	public void setName(String Name) {
		this.c_name = Name;
	}

	public int getPrice() {
		return c_price;
	}

	public void setPrice(int Price) {
		this.c_price = Price;
	}

	public int getfuel() {
		return c_fuel;
	}

	public void setfuel(int fuel) {
		this.c_fuel = fuel;
	}
	public String getcolor() {
		return c_color;
	}

	public void setcolor(String color) {
		this.c_color = color;
	}



}
