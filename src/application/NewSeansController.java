package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class NewSeansController {
	private SampleController sampleController = new SampleController();

	@FXML
	private TextField idFilmu;
	@FXML
	private TextField numerSali;
	@FXML
	private TextField dataSeansu;

	@FXML
	private Button dodajButton;

	@FXML
	public void dodaj() throws NumberFormatException, ClassNotFoundException, SQLException {
		sampleController.dodajSeans(Integer.parseInt(idFilmu.getText()), Integer.parseInt(numerSali.getText()),
				dataSeansu.getText());
	}

}
