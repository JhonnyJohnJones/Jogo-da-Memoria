package packJogo;


//Partes essênciais para o funcionamento de qualquer App JavaFX
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

//Tipos de layout
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;

//Partes Visívies do App
import javafx.scene.control.Label;
import javafx.scene.control.Button;

//Imagem
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Configurações 
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Orientation;
import javafx.geometry.HPos;

//Eventos
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.Cursor;

/*
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
*/




public class TelaInicio extends Application {
	
	@Override
	public void start(Stage palco) {
		
		//Definindo e estilizando o título
		Label titulo = new Label("Jogo da Memória Animal");
		titulo.setStyle("-fx-font-family: Arial; -fx-font-size: 32px; -fx-color: black; -fx-background-color: #4488c2; -fx-text-fill: white; -fx-padding: 16px; -fx-font-weight: bold; -fx-background-radius: 10px;");
		
		//Definindo, estilizando e dando funções aos botões
		Button jogar = new Button("Jogar");
		jogar.setStyle("-fx-background-color: yellow; -fx-background-radius: 10px; -fx-text-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 12px 48px; -fx-font-size: 24px; -fx-font-family: Arial; -fx-font-weight: bold; -fx-background-insets: 2px;");
		jogar.setOnMouseEntered(event -> {
			jogar.setCursor(Cursor.HAND);
		});
		jogar.setOnMouseExited(event -> {
			jogar.setCursor(Cursor.DEFAULT);
		});
		jogar.setOnAction(event -> {
			TelaNiveis tn= new TelaNiveis();
			
			((Stage) jogar.getScene().getWindow()).close();
			
			Stage palcoTN = new Stage();
			tn.start(palcoTN);
		});
		
		Button comoJogar = new Button("Como Jogar");
		comoJogar.setStyle("-fx-background-color: orange; -fx-background-radius: 10px; -fx-text-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 4px 8px; -fx-font-size: 20px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		comoJogar.setOnMouseEntered(event -> {
			comoJogar.setCursor(Cursor.HAND);
		});
		comoJogar.setOnMouseExited(event -> {
			comoJogar.setCursor(Cursor.DEFAULT);
		});
		comoJogar.setOnAction(event -> {
			TelaComoJogar tcj = new TelaComoJogar();
			
			((Stage) comoJogar.getScene().getWindow()).close();
			
			Stage palcoTCJ = new Stage();
			tcj.start(palcoTCJ);
		});
		
		Button rankings = new Button("Rankings");
		rankings.setStyle("-fx-background-color: lightgray; -fx-background-radius: 10px; -fx-text-color: black; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-border-color: black; -fx-border-style: solid; -fx-padding: 4px 8px; -fx-font-size: 20px; -fx-font-family: Arial; -fx-background-insets: 2px;");
		rankings.setOnMouseEntered(event -> {
			rankings.setCursor(Cursor.HAND);
		});
		rankings.setOnMouseExited(event -> {
			rankings.setCursor(Cursor.DEFAULT);
		});
		rankings.setOnAction(event -> {
			TelaRankings tr = new TelaRankings();
			
			((Stage) rankings.getScene().getWindow()).close();
			
			Stage palcoTR = new Stage();
			tr.start(palcoTR);
		});
		
		//organizando a posição dos botões
		FlowPane botoes = new FlowPane();
		FlowPane.setMargin(botoes, new Insets(0, 20, 0, 20));
		botoes.setColumnHalignment(HPos.CENTER);
		botoes.setOrientation(Orientation.VERTICAL);
		botoes.setAlignment(Pos.TOP_CENTER);
		botoes.setVgap(20);
		botoes.setPadding(new Insets(60));
		botoes.getChildren().addAll(jogar, comoJogar, rankings);
		
		//organizando a posição do título
		VBox ficaTitulo = new VBox(titulo);
		ficaTitulo.setAlignment(Pos.TOP_CENTER);
		
		//colocando tudo em um lugar só
		VBox ficaTudo = new VBox();
		ficaTudo.getChildren().addAll(ficaTitulo, botoes);
		VBox.setMargin(titulo, new Insets(50, 20, 50, 20));
		
		//Definindo a parte principal
		StackPane raiz = new StackPane();
		
		//Definindo imagem
		Image imagem = new Image("file:./../Imagens_Memoria/Background.jpg");
		ImageView fundo = new ImageView(imagem);
		
		//Alterando o tamanho da imagem para igualar ao tamanho da tela
		fundo.fitHeightProperty().bind(raiz.heightProperty());
		fundo.fitWidthProperty().bind(raiz.widthProperty());
		
		//Adicionando tudo à parte principal
		raiz.getChildren().addAll(fundo, ficaTudo);

		//Definindo a Scene
		Scene cena = new Scene(raiz, 600, 600);
		
		//Mínimo de tamanho da tela
		palco.setMinHeight(600);
		palco.setMinWidth(600);
		
		//Título da janela
		palco.setTitle("JM Animal");
		
		//Add a Scene à tela e mostrar
		palco.setScene(cena);
		palco.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}