package model;

public class Formateur {
	private String cin;
	private String nom;
	private int age;
	public Formateur(String cin, String nom, int age) {
		this.cin=cin;
		this.nom=nom;
		this.age=age;
	}
	public Formateur(){
		
	}
	public String getCin() {
		return this.cin;
	}
	public String getNom() {
		return this.nom;
	}
	public int getAge() {
		return this.age;
	}
	public void setCin(String cin) {
		this.cin=cin;
	}
	public void setNom(String nom) {
		this.nom=nom;
	}
	public void setAge(int age) {
		this.age=age;
	}
	
}
