package packJogo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.FlowPane;

import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

import javafx.scene.Cursor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class TelaFim extends Application {
	
	boolean gg = true;
	String nome = "JJJ";
	int tempoAtual = 0;
	String dificuldade = "facil";
	boolean tres = false;
	
	public TelaFim() {
		gg = false;
	}
	
	public TelaFim(String diff, int t) {
		switch (diff.toUpperCase()) {
			case "F":
				break;
				
			case "N":
				dificuldade = "normal";
				break;
			
			case "D":
				dificuldade = "dificil";
				break;
				
			default:
				throw new IllegalArgumentException("NÃO ALTERE O VALOR");	
		}
		tempoAtual = t;
		gg = true;
	}
	
	public boolean confere() {
		try {
			File arq = new File("" + System.getProperty("user.dir") + "/src/packJogo/rank" + dificuldade + ".txt");
			FileReader leitor = new FileReader(arq);
			BufferedReader buff = new BufferedReader(leitor);
			
			int tempoSalvo = Integer.parseInt(buff.readLine());
			
			leitor.close();
			buff.close();
			if (tempoSalvo < tempoAtual) {
				return true;
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public void registra() {
		try {
			File arq = new File("" + System.getProperty("user.dir") + "/src/packJogo/rank" + dificuldade + ".txt");
			FileWriter escreve = new FileWriter(arq);
			BufferedWriter buff = new BufferedWriter(escreve);
			buff.write("" + tempoAtual + "\n" + "" + nome.toUpperCase());
			buff.close();
			escreve.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	@Override
	public void start(Stage palco) {
		
		Label decisao = new Label();
		
		if (gg) {
			decisao.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 48px; -fx-text-fill: #44dd44; -fx-background-color: #ddd; -fx-background-insets: 2px; -fx-background-radius: 10px; -fx-border-color: #44dd44; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-padding: 10px;");
			decisao.setText("Ganhou");
		} else {
			decisao.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 48px; -fx-text-fill: #dd4444; -fx-background-color: #ddd; -fx-background-insets: 2px; -fx-background-radius: 10px; -fx-border-color: #dd4444; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-padding: 10px;");
			decisao.setText("Perdeu");
		}
		
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
		
		TextInputDialog dialogo = new TextInputDialog();
		dialogo.setTitle("Novo Recorde!!!");
		dialogo.setHeaderText("Novo Recorde!!!");
		dialogo.setContentText("Seu nome (3 letras)");
		
		FlowPane painel = new FlowPane();
		painel.getChildren().add(decisao);
		
		painel.getChildren().add(voltar);
		painel.setVgap(80);
		painel.setOrientation(Orientation.VERTICAL);
		painel.setColumnHalignment(HPos.CENTER);
		painel.setAlignment(Pos.CENTER);
		
		Scene cena = new Scene(painel, 600, 600);
		
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		palco.setScene(cena);
		palco.show();
		if (gg) {
			if (confere()) {
				while (!tres) {
					Optional<String> resp = dialogo.showAndWait();
					if (resp.isPresent() && resp.get().length() == 3) {
						tres = true;
						nome = resp.get();
					}	
				}
				registra();
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
