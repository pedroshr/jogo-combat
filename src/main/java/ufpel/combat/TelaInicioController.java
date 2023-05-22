package ufpel.combat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.io.IOException;

public class TelaInicioController {

    @FXML
    private Button aleatoriamenteButtonTelaInicial;

    @FXML
    private AnchorPane anchorPaneGuia;

    @FXML
    private AnchorPane anchorPaneTelaInicial;

    @FXML
    private Text combatTextTelaInicial;

    @FXML
    private Label criadoporTelaInicial;

    @FXML
    private Button manualmenteButtonTelaInicial;

    @FXML
    private Label perguntaTelaInicial;

    @FXML
    private SVGPath svgLogoTelaInicial;

    @FXML
    private Button fecharjogoButtonTelaInicial;

    @FXML
    private Button guiaButtonTelaInicial;

    @FXML
    private Button voltarButtonTelaInicial;

    @FXML
    void fecharjogoButtonTelaInicial(ActionEvent event) {
        System.out.println("to saindo do jogo!");
        TelaInicial.getStage().close();
    }

    private Personagem[][] tabuleiro = Tabuleiro.getTabuleiro(); /// Tabuleiro criado

    // CRIACAO DE PERSONAGENS DO TIME AZUL
    private Bandeira bandeiraVermelho = new Bandeira();
    private Espiao espiaoVermelho = new Espiao();
    private Bomba bomba1Vermelho = new Bomba();
    private Bomba bomba2Vermelho = new Bomba();
    private Soldado soldado1Vermelho = new Soldado();
    private Soldado soldado2Vermelho = new Soldado();
    private Soldado soldado3Vermelho = new Soldado();
    private Cabo cabo1Vermelho = new Cabo();
    private Cabo cabo2Vermelho = new Cabo();
    private Marechal marechalVermelho = new Marechal();

    // CRIACAO DE PERSONAGENS DO TIME VERMELHO
    private Bandeira bandeiraAzul = new Bandeira();
    private Espiao espiaoAzul = new Espiao();
    private Bomba bomba1Azul = new Bomba();
    private Bomba bomba2Azul = new Bomba();
    private Soldado soldado1Azul = new Soldado();
    private Soldado soldado2Azul = new Soldado();
    private Soldado soldado3Azul = new Soldado();
    private Cabo cabo1Azul = new Cabo();
    private Cabo cabo2Azul = new Cabo();
    private Marechal marechalAzul = new Marechal();

    @FXML
    void aleatoriamenteButton(ActionEvent event) throws IOException {
        System.out.println("posicionando aleatoriamente!");

        Lago lago0;
        lago0 = new Lago();

        // SETANDO TIME AZUL PARA PERSONAGENS DO TIME AZUL
        bandeiraAzul.setTime("azul");
        espiaoAzul.setTime("azul");
        bomba1Azul.setTime("azul");
        bomba2Azul.setTime("azul");
        soldado1Azul.setTime("azul");
        soldado2Azul.setTime("azul");
        soldado3Azul.setTime("azul");
        cabo1Azul.setTime("azul");
        cabo2Azul.setTime("azul");
        marechalAzul.setTime("azul");

        //POSICIONANDO RANDOMICAMENTE AZUL -- O TIME AZUL SEMPRE É POSICIONADO RANDOMICAMENTE
        Tabuleiro.colocaPersonagem(tabuleiro, bandeiraAzul);
        Tabuleiro.colocaPersonagem(tabuleiro, espiaoAzul);
        Tabuleiro.colocaPersonagem(tabuleiro, bomba1Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, bomba2Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado1Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado2Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado3Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, cabo1Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, cabo2Azul);
        Tabuleiro.colocaPersonagem(tabuleiro, marechalAzul);

        //SETANDO TIME VERMELHO PARA PERSONAGENS DO TIME VERMELHO
        bandeiraVermelho.setTime("vermelho");
        espiaoVermelho.setTime("vermelho");
        bomba1Vermelho.setTime("vermelho");
        bomba2Vermelho.setTime("vermelho");
        soldado1Vermelho.setTime("vermelho");
        soldado2Vermelho.setTime("vermelho");
        soldado3Vermelho.setTime("vermelho");
        cabo1Vermelho.setTime("vermelho");
        cabo2Vermelho.setTime("vermelho");
        marechalVermelho.setTime("vermelho");

        //POSICIONANDO RANDOMICAMENTE VERMELHO
        Tabuleiro.colocaPersonagem(tabuleiro, bandeiraVermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, espiaoVermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, bomba1Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, bomba2Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado1Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado2Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, soldado3Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, cabo1Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, cabo2Vermelho);
        Tabuleiro.colocaPersonagem(tabuleiro, marechalVermelho);

        //POSICIONA O LAGO
        Tabuleiro.colocaPersonagem(tabuleiro, lago0);

        Tabuleiro.setTabuleiro(tabuleiro);
        Tabuleiro.setTabuleiroCopia(tabuleiro);

        //TROCANDO PARA A CENA DA PARTIDA
        new TrocarCena(anchorPaneTelaInicial, "PartidaView.fxml");
    }

    @FXML
    void manualmenteButton() throws IOException {
        System.out.println("posicionando manualmente!");
        new TrocarCena(anchorPaneTelaInicial, "TabuleiroManualmente.fxml");
    }

    @FXML
    void guiaButton() throws IOException {
        new TrocarCena(anchorPaneTelaInicial, "Guia.fxml");
    }

    @FXML
    void voltarButton() throws IOException {
        new TrocarCena(anchorPaneGuia, "TelaInicioView.fxml");
    }
}
