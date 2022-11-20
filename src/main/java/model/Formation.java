package model;

public class Formation {
	private int id;
	private String theme;
	private int idlieu;
	public Formation(String theme) {
		//this.id=id;
		this.theme=theme;
	}
	public Formation(int id,String theme) {
		this.id=id;
		this.theme=theme;
	}
	public Formation(int id,String theme, int idlieu) {
		this.id=id;
		this.theme=theme;
		this.idlieu=idlieu;
	}
	public Formation() {
		
	}
	public String getTheme() {
		return this.theme;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setTheme(String theme) {
		this.theme=theme;
	}
	public int getIdlieu() {
		return this.idlieu;
	}
	public void setIdlieu(int idlieu) {
		this.idlieu=idlieu;
	}
}
