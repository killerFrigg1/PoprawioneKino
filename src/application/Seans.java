package application;

public class Seans {

	private int id;

	private int filmID;

	private int numberSali;

	private String czasSeansu;

	public Seans(int id, int filmID, int numberSali, String czasSeansu) {
		this.id = id;
		this.filmID = filmID;
		this.numberSali = numberSali;
		this.czasSeansu = czasSeansu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFilmID() {
		return filmID;
	}

	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}

	public int getNumberSali() {
		return numberSali;
	}

	public void setNumberSali(int numberSali) {
		this.numberSali = numberSali;
	}

	public String getCzasSeansu() {
		return czasSeansu;
	}

	public void setCzasSeansu(String czasSeansu) {
		this.czasSeansu = czasSeansu;
	}

}
