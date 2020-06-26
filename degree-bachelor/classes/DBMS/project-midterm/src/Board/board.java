package Board;

import java.util.ArrayList;

public class board {

	private String title;
	private String user;
	private String number;
	private String date;
	private String pw;
	private String content;
	private String reply;

	public board(){}
	public board(String number,String user, String pw, String title,
			String content, String date,String reply) {
		this.number = number;
		this.user = user;
		this.pw = pw;
		this.title = title;
		this.content =content;
		this.date = date;
		this.reply=reply;
	}
	public String getReply(){
		return reply;
	}
	public void setReply(String reply){
		this.reply=reply;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String string) {
		this.number = string;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}



}
