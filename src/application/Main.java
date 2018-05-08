package application;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		Main.primaryStage = primaryStage;
		try {
			Parent root = (Parent) FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene = new Scene(root, 960, 640);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws SQLException {
		theaterConnection();
		saleConnection();
		seansyConnection();

		launch(args);
	}

	private static void theaterConnection() throws SQLException {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		DatabaseMetaData meta = c.getMetaData();
		ResultSet res = meta.getTables(null, null, "THEATER", new String[] { "TABLE" });

		if (!res.next()) {
			try {
				Statement stmt = c.createStatement();
				String sql = "CREATE TABLE THEATER " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "NAZWA TEXT NOT NULL, " + "OPIS TEXT NOT NULL, " + "CZASTRWANIA TEXT, "
						+ "LIMITWIEKOWY TEXT)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Baza filmów zosta³a stworzona");
		} else {
			c.close();
			System.out.println("£¹czê z istniej¹c¹ baz¹ filmów");
		}
	}

	private static void saleConnection() throws SQLException {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		DatabaseMetaData meta = c.getMetaData();
		ResultSet res = meta.getTables(null, null, "SALE", new String[] { "TABLE" });

		if (!res.next()) {
			try {
				Statement stmt = c.createStatement();
				String sql = "CREATE TABLE SALE " + "(NUMER INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "LICZBAMIEJSCE INT NOT NULL, " + "TYP TEXT)";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Baza sal zosta³a stworzona");
		} else {
			c.close();
			System.out.println("£¹czê z istniej¹c¹ baz¹ sal");
		}
	}

	private static void seansyConnection() throws SQLException {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Theater.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

		DatabaseMetaData meta = c.getMetaData();
		ResultSet res = meta.getTables(null, null, "SEANSY", new String[] { "TABLE" });

		if (!res.next()) {
			try {
				Statement stmt = c.createStatement();
				String sql = "CREATE TABLE SEANSY " + "(IDSEANS INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ "FILMID INTEGER, " + "NUMERSALI INT, " + "CZASSEANSU TEXT, "
						+ "FOREIGN KEY(FILMID) REFERENCES THEATER(ID), "
						+ "FOREIGN KEY(NUMERSALI) REFERENCES SALE(NUMER))";
				stmt.executeUpdate(sql);
				stmt.close();
				c.close();
			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			System.out.println("Baza seansów zosta³a stworzona");
		} else {
			c.close();
			System.out.println("£¹czê z istniej¹c¹ baz¹ seansów");
		}
	}
}
