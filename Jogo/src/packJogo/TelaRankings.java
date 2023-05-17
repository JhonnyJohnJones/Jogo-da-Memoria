package packJogo;

import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.FlowPane;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class TelaRankings extends Application{
	
	
	public String[] rankFacil() {
		try {
			File arqFacil = new File("" + System.getProperty("user.dir") + "/src/packJogo/rankfacil.txt");
			FileReader leitorFacil = new FileReader(arqFacil);
			BufferedReader buffFacil = new BufferedReader(leitorFacil);
			
			String tempoFacil = buffFacil.readLine();
			String nomeFacil = buffFacil.readLine();
			
			buffFacil.close();
			leitorFacil.close();
			String[] tudoFacil = {tempoFacil, nomeFacil};
			return tudoFacil;
		} catch (IOException e) {
			System.out.println(e);
		}
		String[] temp = {"000", "AAA"};
		return temp;	
	}
	
	public String[] rankNormal() {
		try {
			File arqNormal = new File("" + System.getProperty("user.dir") + "/src/packJogo/ranknormal.txt");
			FileReader leitorNormal = new FileReader(arqNormal);
			BufferedReader buffNormal = new BufferedReader(leitorNormal);
			
			String tempoNormal = buffNormal.readLine();
			String nomeNormal = buffNormal.readLine();
			
			leitorNormal.close();
			buffNormal.close();
			String[] tudoNormal = {tempoNormal, nomeNormal};
			return tudoNormal;
		} catch (IOException e) {
			System.out.println(e);
		}
		String[] temp = {"000", "AAA"};
		return temp;	
	}
	
	public String[] rankDificil() {
		try {
			File arqDificil = new File("" + System.getProperty("user.dir") + "/src/packJogo/rankdificil.txt");
			FileReader leitorDificil = new FileReader(arqDificil);
			BufferedReader buffDificil = new BufferedReader(leitorDificil);
			
			String tempoDificil = buffDificil.readLine();
			String nomeDificil = buffDificil.readLine();
			
			leitorDificil.close();
			buffDificil.close();
			String[] tudoDificil = {tempoDificil, nomeDificil};
			return tudoDificil;
		} catch (IOException e) {
			System.out.println(e);
		}
		String[] temp = {"000", "AAA"};
		return temp;	
	}
	
	@Override
	public void start(Stage palco) {
		
		Label titulo = new Label("Rankings");
		titulo.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 32px;");
		
		Label titTempo = new Label("Tempo(seg)");
		titTempo.setStyle("-fx-font-family: Arial; -fx-font-size: 24px;");
		
		Label titNome = new Label("Nome");
		titNome.setStyle("-fx-font-family: Arial; -fx-font-size: 24px;");
		
		FlowPane painelTit = new FlowPane(titTempo, titNome);
		painelTit.setAlignment(Pos.CENTER);
		painelTit.setHgap(40);
		
		String[] tudoFacil = rankFacil();
		Label tempoFacil = new Label(tudoFacil[0]); 
		tempoFacil.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
		Label nomeFacil = new Label(tudoFacil[1]);
		nomeFacil.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
		FlowPane painelFacil = new FlowPane(tempoFacil, nomeFacil);
		painelFacil.setStyle("-fx-background-color: #74b8f2; -fx-background-radius: 20px; -fx-padding: 4px;");
		painelFacil.setAlignment(Pos.CENTER);
		FlowPane.setMargin(painelFacil, new Insets(0, 0, 0, 25));
		painelFacil.setHgap(120);
		
		
		String[] tudoNormal = rankNormal();
		Label tempoNormal = new Label(tudoNormal[0]);
		tempoNormal.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
		Label nomeNormal = new Label(tudoNormal[1]);
		nomeNormal.setStyle("-fx-font-family: Arial; -fx-font-size: 16px;");
		FlowPane painelNormal = new FlowPane(tempoNormal, nomeNormal);
		painelNormal.setStyle("-fx-background-color: #44b244; -fx-background-radius: 20px; -fx-padding: 4px;");
		painelNormal.setAlignment(Pos.CENTER);
		FlowPane.setMargin(painelNormal, new Insets(0, 0, 0, 25));
		painelNormal.setHgap(120);
		
		
		String[] tudoDificil = rankDificil();
		Label tempoDificil = new Label(tudoDificil[0]);
		tempoDificil.setStyle("-fx-font-family: Arial; -fx-font-size: 16px; -fx-text-fill: #eee;");
		Label nomeDificil = new Label(tudoDificil[1]);
		nomeDificil.setStyle("-fx-font-family: Arial; -fx-font-size: 16px; -fx-text-fill: #eee;");
		FlowPane painelDificil = new FlowPane(tempoDificil, nomeDificil);
		painelDificil.setStyle("-fx-background-color: red; -fx-background-radius: 20px; -fx-padding: 4px;");
		painelDificil.setAlignment(Pos.CENTER);
		FlowPane.setMargin(painelDificil, new Insets(0, 0, 0, 25));
		painelDificil.setHgap(120);
		
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
		
		FlowPane painelTodos = new FlowPane(titulo, painelTit,  painelFacil, painelNormal, painelDificil, voltar);
		painelTodos.setVgap(40);
		painelTodos.setOrientation(Orientation.VERTICAL);
		painelTodos.setColumnHalignment(HPos.CENTER);
		painelTodos.setAlignment(Pos.CENTER);
		
		Scene cena = new Scene(painelTodos, 600, 600);
		
		
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		palco.setScene(cena);
		palco.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


