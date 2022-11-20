package model;

public class FormationFormateur {
	private int id;
	private int idF;
	private String cin;
	public FormationFormateur(int idF,String cin) {
		this.idF=idF;
		this.cin=cin;
	}
	public FormationFormateur(int id,int idF,String cin) {
		this.id=id;
		this.idF=idF;
		this.cin=cin;
	}
	public FormationFormateur() {
		
	}
	public String getCin() {
		return this.cin;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public void setCin(String cin) {
		this.cin=cin;
	}
	public int getIdF() {
		return this.idF;
	}
	public void setIdF(int idF) {
		this.idF=idF;
	}
}
