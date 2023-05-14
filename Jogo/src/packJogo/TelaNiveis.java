package packJogo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.geometry.HPos;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.Cursor;

public class TelaNiveis extends Application {

	@Override
	public void start(Stage palco) {
		
		Label titulo = new Label("Dificuldades");
		titulo.setStyle("-fx-background-color: #4488c2; -fx-background-radius: 10px; -fx-padding: 16px; -fx-text-fill: #eee; -fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 32px;");
		
		Button facil = new Button("Fácil");
		facil.setStyle("-fx-background-color: #74b8f2; -fx-background-radius: 10px; -fx-text-fill: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 8px 20px; -fx-font-size: 20px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		facil.setOnMouseEntered(event -> {
			facil.setCursor(Cursor.HAND);
		});
		facil.setOnMouseExited(event -> {
			facil.setCursor(Cursor.DEFAULT);
		});
		facil.setOnAction(event -> {
			TelaJogo tj = new TelaJogo("f");
			
			((Stage) facil.getScene().getWindow()).close();
			
			Stage palcoTJ = new Stage();
			tj.start(palcoTJ);
		});
		
		Button normal = new Button("Normal");
		normal.setStyle("-fx-background-color: #44b244; -fx-background-radius: 10px; -fx-text-fill: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 8px 20px; -fx-font-size: 20px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		normal.setOnMouseEntered(event -> {
			normal.setCursor(Cursor.HAND);
		});
		normal.setOnMouseExited(event -> {
			normal.setCursor(Cursor.DEFAULT);
		});
		normal.setOnAction(event -> {
			TelaJogo tj = new TelaJogo("n");
			
			((Stage) normal.getScene().getWindow()).close();
			
			Stage palcoTJ = new Stage();
			tj.start(palcoTJ);
		});
		
		Button dificil = new Button("Dificil");
		dificil.setStyle("-fx-background-color: red; -fx-background-radius: 10px; -fx-text-fill: white; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 8px 20px; -fx-font-size: 20px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		dificil.setOnMouseEntered(event -> {
			dificil.setCursor(Cursor.HAND);
		});
		dificil.setOnMouseExited(event -> {
			dificil.setCursor(Cursor.DEFAULT);
		});
		dificil.setOnAction(event -> {
			TelaJogo tj = new TelaJogo("d");
			
			((Stage) dificil.getScene().getWindow()).close();
			
			Stage palcoTJ = new Stage();
			tj.start(palcoTJ);
		});
		
		FlowPane niveis = new FlowPane(facil, normal, dificil);
		niveis.setHgap(40);
		niveis.setAlignment(Pos.CENTER);
		
		Button voltar = new Button("Voltar");
		voltar.setStyle("-fx-background-color: lightgray; -fx-background-radius: 10px; -fx-text-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 4px 8px; -fx-font-size: 16px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		voltar.setOnMouseEntered(event -> {
			voltar.setCursor(Cursor.HAND);
		});
		voltar.setOnMouseExited(event -> {
			voltar.setCursor(Cursor.DEFAULT);
		});
		voltar.setOnAction(event -> {
			TelaInicio ti = new TelaInicio();
			
			((Stage) voltar.getScene().getWindow()).close();
			Stage palcoTI = new Stage(); 
			ti.start(palcoTI);
		});
		
		ImageView fundo = new ImageView(new Image("file:./../Imagens_Memoria/Background.jpg"));
		fundo.fitHeightProperty().bind(palco.heightProperty());
		fundo.fitWidthProperty().bind(palco.widthProperty());

		FlowPane tudo = new FlowPane(titulo, niveis, voltar);
		tudo.setAlignment(Pos.CENTER);
		tudo.setVgap(60);
		tudo.setOrientation(Orientation.VERTICAL);
		tudo.setColumnHalignment(HPos.CENTER);
		
		
		Scene cena = new Scene(new StackPane(fundo, tudo), 600, 600);
		
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		palco.setScene(cena);
		palco.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
