package application;

public class Film {

	private int id;

	private String nazwa;

	private String opis;

	private String czasTrwania;

	private String limitWiekowy;

	public Film(int id, String nazwa, String opis, String czasTrwania, String limitWiekowy) {
		this.id = id;
		this.nazwa = nazwa;
		this.opis = opis;
		this.czasTrwania = czasTrwania;
		this.limitWiekowy = limitWiekowy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getCzasTrwania() {
		return czasTrwania;
	}

	public void setCzasTrwania(String czasTrwania) {
		this.czasTrwania = czasTrwania;
	}

	public String getLimitWiekowy() {
		return limitWiekowy;
	}

	public void setLimitWiekowy(String limitWiekowy) {
		this.limitWiekowy = limitWiekowy;
	}

	@Override
	public String toString() {
		return nazwa + ": " + opis + "; czas trwania: " + czasTrwania + "; limit wiekowy: limitWiekowy";
	}

}
