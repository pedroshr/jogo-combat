package ufpel.combat;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class TelaInicial extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TelaInicial.class.getResource("TelaInicioView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image imagem = new Image(getClass().getResourceAsStream("img/logo_combat.png"));
        stage.getIcons().add(imagem);
        stage.setTitle("COMBAT");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        setStage(stage);
    }

    public static void main(String[] args) {
        launch();
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        TelaInicial.stage = stage;
    }
}
