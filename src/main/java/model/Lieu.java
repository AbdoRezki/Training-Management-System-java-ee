package model;

public class Lieu {
	private int id;
	private String adresse;
	private String ville;
	public Lieu(String adresse, String ville) {
		this.adresse=adresse;
		this.ville=ville;
	}
	public Lieu(int id,String adresse, String ville) {
		this.id=id;
		this.adresse=adresse;
		this.ville=ville;
	}
	public Lieu() {
		
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getAdresse() {
		return this.adresse;
	}
	public String getVille() {
		return this.ville;
	}
	public void setAdresse(String adresse) {
		this.adresse=adresse;
	}
	public void setVille(String ville) {
		this.ville=ville;
	}
}
