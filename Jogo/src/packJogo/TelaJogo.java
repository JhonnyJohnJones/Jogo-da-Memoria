package packJogo;

import java.util.Random;

import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.Cursor;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.geometry.Pos;
import javafx.geometry.Orientation;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.geometry.HPos;
import javafx.geometry.Insets;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.util.Duration;



public class TelaJogo extends Application{
	
	String diff = "f";
	int linhas = 3;
	int colunas = 4;
	boolean acabou = false;
	
	int[][] listaCartas = new int[linhas][colunas]; 
	int paresAchados = 0; 
	int paresTotais = (linhas * colunas) / 2;
	int quantCartasAtivas = 0;
	int[] posCarta1 = {-1,-1};
	int[] posCarta2 = {-1,-1};
	int tempo = 60;
	int tempoInicio = 5;
	boolean inicio = true; 
	boolean ePar = true;
	int vidas = 3;
	Timeline tm;
	public TelaJogo(String dificuldade) {
		switch (dificuldade.toUpperCase()) {
			case "F":
				break;
				
			case "N":
				diff = "n";
				linhas = 4;
				colunas = 4;
				tempo = 30;
				break;
			
			case "D":
				diff = "d";
				linhas = 4;
				colunas = 5;
				tempo = 20;
				break;
				
			default:
				throw new IllegalArgumentException("NÃO ALTERE O VALOR");	
		}
		listaCartas = new int[linhas][colunas];
		paresTotais = (linhas * colunas) / 2;
	}
	
	public void randomizar() {
		Random random = new Random();
		
		ArrayList<Integer> listaNum = new ArrayList<Integer>();
		
		for (int i = 1; i <= (linhas * colunas); i++) {
			listaNum.add((i%2) + (i/2));
		}
		
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				int index = random.nextInt(listaNum.size());
				listaCartas[i][j] = listaNum.get(index);
				listaNum.remove(index);
			}
		}
	}
	
	
	public void verifica(int i, int j) {
		quantCartasAtivas++;
		switch (quantCartasAtivas) {
		case 1:
			posCarta1[0] = i;
			posCarta1[1] = j;
			break;
		case 2:
			posCarta2[0] = i;
			posCarta2[1] = j;
			if(listaCartas[posCarta1[0]][posCarta1[1]] == listaCartas[posCarta2[0]][posCarta2[1]]) {
				paresAchados++;
				quantCartasAtivas = 0;
				posCarta1[0] = -1;
				posCarta1[1] = -1;
				posCarta2[0] = -1;
				posCarta2[1] = -1;
			} else {
				ePar = false;
			}
			
			posCarta2[0] = -1;
			posCarta2[1] = -1;
			
			break;
		}
		
		
	}
	
	
	@Override
	public void start(Stage palco) {
		
		List<List> cartas = new ArrayList<List>();
		List<Button> cartasLinha = new ArrayList<Button>();
		
		
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				try {
					cartasLinha.set(j, new Button());
				} catch (Exception e) {
					cartasLinha.add(new Button());					
				}
			}
			cartas.add(new ArrayList<Button>(cartasLinha));
		}
		
		Label vd = new Label(Integer.toString(vidas));
		vd.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 32px;");
		
		int tamanhoIcone = 30;
		
		ImageView vdImg = new ImageView(new Image("file:./../Imagens_Memoria/Coracao.png")); 
		vdImg.setFitWidth(tamanhoIcone);
		vdImg.setFitHeight(tamanhoIcone);
		
		HBox vdComImagem = new HBox(vdImg, vd);
		HBox.setMargin(vdImg, new Insets(0, 10, 0, 0));
		vdComImagem.setAlignment(Pos.CENTER);
		
		Label crono = new Label(Integer.toString(tempoInicio));
		crono.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 32px;");
		
		ImageView cronoImg = new ImageView(new Image("file:./../Imagens_Memoria/Cronometro.png"));
		cronoImg.setFitWidth(tamanhoIcone);
		cronoImg.setFitHeight(tamanhoIcone);
		
		HBox cronoComImagem = new HBox(cronoImg, crono);
		HBox.setMargin(cronoImg, new Insets(0, 10, 0, 0));
		
		GridPane grade = new GridPane();		
		
		randomizar();
		
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				
				Button btn = (Button)cartas.get(i).get(j);
				
				GridPane.setConstraints(btn, i, j);
				btn.setPrefWidth(60);
				btn.setPrefHeight(80);
				btn.setBackground(new Background(new BackgroundFill(Color.web("#225"), new CornerRadii(5, false), Insets.EMPTY)));
				int j2 = j;
				int i2 = i;
				btn.setOnMouseEntered(event -> {btn.setCursor(Cursor.HAND);});
				btn.setOnMouseExited(event -> {btn.setCursor(Cursor.DEFAULT);});
				btn.setOnAction(event -> {
					if (quantCartasAtivas < 2 && !inicio){
						Image imagem = new Image("file:./../Imagens_Memoria/Animal"+ listaCartas[i2][j2] +".jpg");
						btn.setBackground(new Background(new BackgroundImage(imagem, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(btn.getWidth(), btn.getHeight(), false, false, false, false))));
						ePar =  true;
						verifica(i2, j2);
						if (!ePar) {
							Button btn2 = (Button)cartas.get(posCarta1[0]).get(posCarta1[1]);
							posCarta1[0] = -1;
							posCarta1[1] = -1;
							new Timeline(new KeyFrame(Duration.seconds(1), e -> {
								btn.setBackground(new Background(new BackgroundFill(Color.web("#225"), new CornerRadii(5, false), Insets.EMPTY)));
								btn2.setBackground(new Background(new BackgroundFill(Color.web("#225"), new CornerRadii(5, false), Insets.EMPTY)));
								quantCartasAtivas = 0;
								if (vidas > 0) {
									vidas--;
									vd.setText(Integer.toString(vidas));
								} else {
									tempo -= 10;
									crono.setText(Integer.toString(tempo));
								}
							})).play();
						} else {
							if (paresAchados >= paresTotais) {
								acabou = true;
								TelaFim tf = new TelaFim(diff, tempo);
								
								palco.close();
								Stage palcoTF = new Stage(); 
								tf.start(palcoTF);
							}
						}
					}
				});
				grade.add((Button)cartas.get(i).get(j), j, i);
			}
		}
		
		EventHandler<WindowEvent> evento = new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent eve) {
				tm = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
					if (tempoInicio > 0) {
						tempoInicio--;
						crono.setText(Integer.toString(tempoInicio));
					} else if (tempoInicio == 0){
						crono.setText(Integer.toString(tempo));
						tempoInicio--;
						inicio = false;
						for (int i = 0; i < linhas; i++) {
							for (int j = 0; j < colunas; j++) {
								Button btn = (Button)cartas.get(i).get(j);
								btn.setBackground(new Background(new BackgroundFill(Color.web("#225"), new CornerRadii(5, false), Insets.EMPTY)));
							}
						}
					} else if (acabou) {
						tm.stop();
					} else {
						tempo--;
						crono.setText(Integer.toString(tempo));
						if(tempo <= 0) {
							tm.stop();
							TelaFim tf = new TelaFim();
							
							palco.close();
							Stage palcoTF = new Stage(); 
							tf.start(palcoTF);
							
						}
					}
				})); 
				tm.setCycleCount(Timeline.INDEFINITE);
				for (int i = 0; i < linhas; i++) {
					for (int j = 0; j < colunas; j++) {
						Button btn = (Button)cartas.get(i).get(j);
						Image imagem = new Image("file:./../Imagens_Memoria/Animal"+ listaCartas[i][j] +".jpg");
						btn.setBackground(new Background(new BackgroundImage(imagem, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(btn.getWidth(), btn.getHeight(), false, false, false, false))));
					}
					
				}
				tm.play();
			}
		};
		
		
		grade.setVgap(20);
		grade.setHgap(20);
		grade.setAlignment(Pos.CENTER);
		
		FlowPane vdCrono = new FlowPane(vdComImagem, cronoComImagem);

		vdCrono.setOrientation(Orientation.HORIZONTAL);
		vdCrono.setColumnHalignment(HPos.CENTER);
		vdCrono.setAlignment(Pos.TOP_CENTER);
		vdCrono.setHgap(100);
		vdCrono.setBackground(new Background(new BackgroundFill(Color.web("#4488c2"), new CornerRadii(0, 0, 10, 10, false), Insets.EMPTY)));
		vdCrono.setPadding(new Insets(10));
		
		VBox todos = new VBox(vdCrono, grade);
		todos.setAlignment(Pos.TOP_CENTER);
		VBox.setMargin(grade, new Insets(30, 0, 0, 0));
		
		ImageView background = new ImageView(new Image("file:./../Imagens_Memoria/Background.jpg"));
		background.fitHeightProperty().bind(palco.heightProperty());
		background.fitWidthProperty().bind(palco.widthProperty());
		
		
		StackPane comBack = new StackPane(background, todos);
		
		Scene cena = new Scene(comBack, 600, 600);
		
		
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		palco.setOnShown(evento);
		palco.setScene(cena);
		palco.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}