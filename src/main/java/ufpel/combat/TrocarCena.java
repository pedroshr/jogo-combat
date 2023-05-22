package ufpel.combat;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class TrocarCena {
    public TrocarCena(AnchorPane atualAnchorPane, String fxml) throws IOException {
        AnchorPane proximoAnchorPane = FXMLLoader.load(Objects.requireNonNull(TelaInicial.class.getResource(fxml)));
        atualAnchorPane.getChildren().removeAll();
        atualAnchorPane.getChildren().setAll(proximoAnchorPane);
    }
}
