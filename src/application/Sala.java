package application;

public class Sala {

	private int number;

	private int liczbaMiejsc;

	private String typSali;

	public Sala(int number, int liczbaMiejsc, String typSali) {
		this.number = number;
		this.liczbaMiejsc = liczbaMiejsc;
		this.typSali = typSali;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getLiczbaMiejsc() {
		return liczbaMiejsc;
	}

	public void setLiczbaMiejsc(int liczbaMiejsc) {
		this.liczbaMiejsc = liczbaMiejsc;
	}

	public String getTypSali() {
		return typSali;
	}

	public void setTypSali(String typSali) {
		this.typSali = typSali;
	}

}
