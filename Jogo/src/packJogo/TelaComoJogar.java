package packJogo;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.layout.FlowPane;

import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.HPos;

import javafx.scene.Cursor;



public class TelaComoJogar extends Application {

	@Override
	public void start(Stage palco) {
		
		Label titulo = new Label("Como Jogar");
		titulo.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 32px;");
		
		
		Label explicacao = new Label("O jogo começa com um menu onde o jogador pode selecionar a dificuldade e configurar algumas opções. Quando a partida começa, as cartas são reveladas por um curto período antes de serem ocultadas. As mecânicas do jogo incluem cartas, vidas e tempo. O jogador tem que virar as cartas para formar pares, e as vidas servem como uma chance extra de erro antes que o tempo comece a ser reduzido consideravelmente. O objetivo é encontrar todos os pares antes do tempo acabar para vencer. O jogo tem três dificuldades e um sistema de ranking local baseado no tempo de jogo vencido.");
		explicacao.setStyle("-fx-font-size: 14px;");
		explicacao.setWrapText(true);
		explicacao.setTextAlignment(TextAlignment.JUSTIFY);
		
				
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
		
		FlowPane painel = new FlowPane();
		painel.setOrientation(Orientation.VERTICAL);
		painel.setColumnHalignment(HPos.CENTER);
		painel.setAlignment(Pos.CENTER);
		painel.setVgap(60);
		painel.getChildren().addAll(titulo, explicacao, voltar);
		
		
		Scene cena = new Scene(painel, 600, 600);
		cena.widthProperty().addListener((obs, velho, novo) -> {
			explicacao.setMaxWidth(novo.doubleValue() * 0.8);
		});
		
		double larguraJanela = cena.getWidth() * 0.8;
		
		explicacao.setMaxWidth(larguraJanela);
		
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		palco.setScene(cena);
		palco.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
