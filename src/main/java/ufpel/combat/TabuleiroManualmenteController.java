package ufpel.combat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TabuleiroManualmenteController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private GridPane gridPecas;

    @FXML
    private HBox hboxTabuleiro;

    @FXML
    private Button jogarButton;

    @FXML
    private Button debugButton;

    @FXML
    private Label labelClique;

    @FXML
    private AnchorPane tabuleiroAnchorPane;

    @FXML
    private VBox vboxLabel;

    @FXML
    private VBox vboxPecas;

    @FXML
    private ImageView img00;

    @FXML
    private ImageView img01;

    @FXML
    private ImageView img02;

    @FXML
    private ImageView img10;

    @FXML
    private ImageView img11;

    @FXML
    private ImageView img12;

    @FXML
    private ImageView img20;

    @FXML
    private ImageView img21;

    @FXML
    private ImageView img22;

    @FXML
    private ImageView img31;


    private int pecaNivel;

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
    private int flagDebug = 0; //flag=1 -> visivel | flag=0 -> ocultado
    private int pecaPosicionando;
    private String imgSelecionada;
    private int posX, posY;
    private int pecaNaMao;
    private int contPecas = 10;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scanner sc = new Scanner(System.in);
        int round = 0, deslocamento, option = 0;
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

        //POSICIONA O LAGO
        Tabuleiro.colocaPersonagem(tabuleiro, lago0);

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

        Tabuleiro.imprimeTabuleiro(tabuleiro);

        //COLOCA AS IMAGENS DAS PEÇAS AZUL
        Tabuleiro.setImagensTimeGrid(tabuleiro, "azul", gridPane);

        //COLOCA O LAGO
        Tabuleiro.setImagensTimeGrid(tabuleiro, "lago", gridPane);

        //COLOCANDO RETANGULOS INVISIVEIS EM TODOS ESPAÇOS DO GRID PARA SER CLICÁVEL E RETORNAR COLUNA E LINHA QUE FOR CLICADA
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(85);
                rectangle.setWidth(85);
                rectangle.setFill(Color.BLACK);
                rectangle.setOpacity(0);
                gridPane.add(rectangle, j, i);
                GridPane.setHalignment(rectangle, HPos.CENTER);
                GridPane.setValignment(rectangle, VPos.CENTER);
            }
        }
    }


    @FXML
    void posBandeira(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img00);
            pecaNivel = 0;
            pecaPosicionando = 1;
            imgSelecionada = "img00";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posBomba1(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img01);
            pecaNivel = 4;
            pecaPosicionando = 1;
            imgSelecionada = "img01";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posBomba2(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img02);
            pecaNivel = 4;
            pecaPosicionando = 1;
            imgSelecionada = "img02";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posEspiao(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img10);
            pecaNivel = 1;
            pecaPosicionando = 1;
            imgSelecionada = "img10";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posMarechal(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img31);
            pecaNivel = 10;
            pecaPosicionando = 1;
            imgSelecionada = "img31";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posSoldado1(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img11);
            pecaNivel = 2;
            pecaPosicionando = 1;
            imgSelecionada = "img11";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posSoldado2(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img12);
            pecaNivel = 2;
            pecaPosicionando = 1;
            imgSelecionada = "img12";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posSoldado3(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img20);
            pecaNivel = 2;
            pecaPosicionando = 1;
            imgSelecionada = "img20";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posArmeiro1(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img21);
            pecaNivel = 3;
            pecaPosicionando = 1;
            imgSelecionada = "img21";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void posArmeiro2(MouseEvent event) {
        if (pecaNaMao != 1) {
            Tabuleiro.setImageEffect(img22);
            pecaNivel = 3;
            pecaPosicionando = 1;
            imgSelecionada = "img22";
            pecaNaMao = 1;
            System.out.println("Peça clicada: " + pecaNivel + " || " + imgSelecionada);
        }
    }

    @FXML
    void jogarButton(ActionEvent event) throws IOException {
        if (contPecas == 0) {
            System.out.println("TROCANDO DE TELA!");
            Tabuleiro.setTabuleiro(tabuleiro);
            Tabuleiro.setTabuleiroCopia(tabuleiro);
            TrocarCena trocarCena = new TrocarCena(tabuleiroAnchorPane, "PartidaView.fxml");
        }
    }

    @FXML
    void debugButton(ActionEvent event) throws IOException {
        if (flagDebug == 0) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    Tabuleiro.setImagemTodasPecas(tabuleiro, "azul", gridPane, i, j);
                }
            }
            flagDebug = 1;
        } else if (flagDebug == 1) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    Tabuleiro.setImagemTodasPecas(tabuleiro, "azulCensurado", gridPane, i, j);
                }
            }
            flagDebug = 0;
        }

    }

    @FXML
    void clickGridPane(MouseEvent event) {
        Node clickedNode = event.getPickResult().getIntersectedNode();
        if (clickedNode != gridPane) {
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            if (rowIndex == null || colIndex == null) {
            } else {
                posX = rowIndex;
                posY = colIndex;
                System.out.println("Linha: " + posX + " Coluna: " + posY);
                if (posX < 3) {
                    System.out.println("Você não pode posicionar o time vermelho neste local!");
                } else {
                    Personagem p = new Personagem();
                    p = tabuleiro[posX][posY];
                    if (pecaPosicionando == 1) {
                        if (p != null) {
                            System.out.println("Já tem uma peça neste local!");
                        } else {
                            if (imgSelecionada == "img00") {
                                tabuleiro[posX][posY] = bandeiraVermelho;
                                img00.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/bandeira.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img01") {
                                tabuleiro[posX][posY] = bomba1Vermelho;
                                img01.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/bomba.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img02") {
                                tabuleiro[posX][posY] = bomba2Vermelho;
                                img02.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/bomba.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img10") {
                                tabuleiro[posX][posY] = espiaoVermelho;
                                img10.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/espiao.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img11") {
                                tabuleiro[posX][posY] = soldado1Vermelho;
                                img11.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/soldado.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img12") {
                                tabuleiro[posX][posY] = soldado2Vermelho;
                                img12.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/soldado.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img20") {
                                tabuleiro[posX][posY] = soldado3Vermelho;
                                img20.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/soldado.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img21") {
                                tabuleiro[posX][posY] = cabo1Vermelho;
                                img21.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/armeiro.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img22") {
                                tabuleiro[posX][posY] = cabo2Vermelho;
                                img22.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/armeiro.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            } else if (imgSelecionada == "img31") {
                                tabuleiro[posX][posY] = marechalVermelho;
                                img31.setVisible(false);
                                Image imagem = new Image(getClass().getResourceAsStream("img/laranja/marechal.png"));
                                Tabuleiro.setImagemGrid(gridPane, imagem, posY, posX);
                            }
                            Tabuleiro.imprimeTabuleiro(tabuleiro);
                            pecaPosicionando = 0;
                            pecaNaMao = 0;
                            contPecas--;
                            if (contPecas == 0) {
                                labelClique.setVisible(false);
                            }
                        }
                    }
                }
            }
        }
    }
}
