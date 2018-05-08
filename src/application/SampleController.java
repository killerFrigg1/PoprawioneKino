package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SampleController {
	private static ObservableList<Film> filmyData = FXCollections.observableArrayList();
	private static ObservableList<Sala> screeningRooms = FXCollections.observableArrayList();
	private static ObservableList<Seans> seansy = FXCollections.observableArrayList();

	@FXML
	private Button dodajFilmStage;
	@FXML
	private Button wczytajFilmyButton;
	@FXML
	private Button dodajSaleStage;
	@FXML
	private Button wczytajSaleButton;
	@FXML
	private Button dodajSeansStage;
	@FXML
	private Button wczytajSeanseButton;

	@FXML
	private TableView<Film> filmyTableView;
	@FXML
	private TableView<Sala> screeningRoomsTableView;
	@FXML
	private TableView<Seans> seansyTableView;

	@FXML
	private TableColumn<Film, Integer> idColumn;
	@FXML
	private TableColumn<Film, String> nazwaColumn;
	@FXML
	private TableColumn<Film, String> opisColumn;
	@FXML
	private TableColumn<Film, String> czasTrwaniaColumn;
	@FXML
	private TableColumn<Film, String> limitWiekowyColumn;

	@FXML
	private TableColumn<Sala, Integer> screeningRoomNumbercolumn;
	@FXML
	private TableColumn<Sala, Integer> liczbaMiejscScreeningRoomColumn;
	@FXML
	private TableColumn<Sala, String> typSaliScreeningRoomColumn;

	@FXML
	private TableColumn<Seans, Integer> seansIDColumn;
	@FXML
	private TableColumn<Seans, Integer> seansFilmIDColumn;
	@FXML
	private TableColumn<Seans, Integer> seansNumberSaliColumn;
	@FXML
	private TableColumn<Seans, String> seansDataSeansuColumn;

	@FXML
	public void dodajFilmStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewFilm.fxml"));
		Parent addNewEmployee = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewEmployee);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	@FXML
	public void dodajSaleStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewAuditorium.fxml"));
		Parent addNewScreeningRoom = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewScreeningRoom);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	@FXML
	public void dodajSeansStage() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("NewSeans.fxml"));
		Parent addNewScreeningRoom = loader.load();

		Stage newFilmStage = new Stage();
		newFilmStage.initModality(Modality.WINDOW_MODAL);
		newFilmStage.initOwner(Main.primaryStage);
		Scene scene = new Scene(addNewScreeningRoom);
		newFilmStage.setScene(scene);
		newFilmStage.showAndWait();
	}

	// DODAJ FILM
	public void dodaj(String nazwa, String opis, String czasTrwania, String limitWiekowy)
			throws SQLException, ClassNotFoundException {
		try {
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			PreparedStatement pstmt = c.prepareStatement(
					"INSERT INTO THEATER (NAZWA,OPIS,CZASTRWANIA,LIMITWIEKOWY) " + "VALUES (?, ?, ?, ?);");
			pstmt.setString(1, nazwa);
			pstmt.setString(2, opis);
			pstmt.setString(3, czasTrwania);
			pstmt.setString(4, limitWiekowy);
			pstmt.executeUpdate();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Film zosta³ dodany do bazy");
	}

	public void dodajSale(int liczbaMiejsc, String typ) throws SQLException, ClassNotFoundException {
		try {
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			PreparedStatement pstmt = c.prepareStatement("INSERT INTO SALE (LICZBAMIEJSCE, TYP) " + "VALUES (?, ?);");
			pstmt.setInt(1, liczbaMiejsc);
			pstmt.setString(2, typ);
			pstmt.executeUpdate();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Sala zosta³a dodana do bazy");
	}

	public void dodajSeans(int filmID, int numberSali, String czasSeansu) throws SQLException, ClassNotFoundException {
		try {
			Connection c = null;
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			PreparedStatement pstmt = c
					.prepareStatement("INSERT INTO SEANSY (FILMID, NUMERSALI, CZASSEANSU) " + "VALUES (?, ?, ?);");
			pstmt.setInt(1, filmID);
			pstmt.setInt(2, numberSali);
			pstmt.setString(3, czasSeansu);
			pstmt.executeUpdate();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Seans zosta³ dodany do bazy");
	}

	@FXML
	public void wczytajFilmy() {
		filmyTableView.getItems().clear();
		idColumn.setCellValueFactory(new PropertyValueFactory<Film, Integer>("id"));
		nazwaColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("nazwa"));
		opisColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("opis"));
		czasTrwaniaColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("czasTrwania"));
		limitWiekowyColumn.setCellValueFactory(new PropertyValueFactory<Film, String>("limitWiekowy"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM THEATER;");

			while (rs.next()) {
				int id = rs.getInt("id");
				String nazwa = rs.getString("NAZWA");
				String opis = rs.getString("OPIS");
				String czasTrwania = rs.getString("CZASTRWANIA");
				String limitWiekowy = rs.getString("LIMITWIEKOWY");

				System.out.println("ID = " + id);
				System.out.println("NAZWA = " + nazwa);
				System.out.println("OPIS = " + opis);
				System.out.println("CZASTRWANIA = " + czasTrwania);
				System.out.println("LIMITWIEKOWY = " + limitWiekowy);

				filmyData.add(new Film(id, nazwa, opis, czasTrwania, limitWiekowy));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		filmyTableView.setItems(filmyData);
	}

	@FXML
	public void wczytajSale() {
		screeningRoomsTableView.getItems().clear();
		screeningRoomNumbercolumn.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("number"));
		liczbaMiejscScreeningRoomColumn.setCellValueFactory(new PropertyValueFactory<Sala, Integer>("liczbaMiejsc"));
		typSaliScreeningRoomColumn.setCellValueFactory(new PropertyValueFactory<Sala, String>("typSali"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SALE;");

			while (rs.next()) {
				int number = rs.getInt("NUMER");
				int liczbaMiejsc = rs.getInt("LICZBAMIEJSCE");
				String typSali = rs.getString("TYP");

				System.out.println("NUMER = " + number);
				System.out.println("LICZBAMIEJSCE = " + liczbaMiejsc);
				System.out.println("TYP = " + typSali);

				screeningRooms.add(new Sala(number, liczbaMiejsc, typSali));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		screeningRoomsTableView.setItems(screeningRooms);
	}

	@FXML
	public void wczytajSeansy() {
		seansyTableView.getItems().clear();
		seansIDColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("id"));
		seansFilmIDColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("filmID"));
		seansNumberSaliColumn.setCellValueFactory(new PropertyValueFactory<Seans, Integer>("numberSali"));
		seansDataSeansuColumn.setCellValueFactory(new PropertyValueFactory<Seans, String>("czasSeansu"));

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SEANSY;");

			while (rs.next()) {
				int id = rs.getInt("IDSEANS");
				int filmID = rs.getInt("FILMID");
				int numberSali = rs.getInt("NUMERSALI");
				String czasSeansu = rs.getString("CZASSEANSU");

				System.out.println("NUMER = " + id);
				System.out.println("LICZBAMIEJSCE = " + filmID);
				System.out.println("TYP = " + numberSali);
				System.out.println("TYP = " + czasSeansu);

				seansy.add(new Seans(id, filmID, numberSali, czasSeansu));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		seansyTableView.setItems(seansy);
	}

}
