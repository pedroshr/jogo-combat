package ufpel.combat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PartidaController implements Initializable {
    @FXML
    private AnchorPane anchorPanePartida;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane anchorPane1;
    @FXML
    private HBox hboxTabuleiro;
    @FXML
    private VBox vbox;
    @FXML
    private Label labelparaDica;
    @FXML
    private Tooltip tooltipDica;
    @FXML
    private Button dicaButton;
    @FXML
    private Button sairButton;
    @FXML
    private Label labelVermelho, labelAzul;
    @FXML
    private Button fecharJogoButton;
    @FXML
    private Button novoJogoButton;
    @FXML
    private Button reiniciarPartidaButton;
    @FXML
    private  Button voltarButton;
    @FXML
    void dicaButton(ActionEvent event) {
        if (pecaPosicionando == 0) {
            labelparaDica.setVisible(true);
            dicaPedida = 1;
            if (colSelecionada == 1) {
                System.out.println("DICA");
                pedirDica(tabuleiro, posYPlayer);
                dicaPedida = 0;
                colSelecionada = 0;
            }
        }
    }
    @FXML
    void voltarButton(ActionEvent event) throws IOException{
        dicaButton.setVisible(true);
        sairButton.setVisible(true);
        fecharJogoButton.setVisible(false);
        reiniciarPartidaButton.setVisible(false);
        novoJogoButton.setVisible(false);
        voltarButton.setVisible(false);
    }
    @FXML
    void sairButton(ActionEvent event) throws IOException {
        dicaButton.setVisible(false);
        sairButton.setVisible(false);
        fecharJogoButton.setVisible(true);
        reiniciarPartidaButton.setVisible(true);
        novoJogoButton.setVisible(true);
        voltarButton.setVisible(true);
    }
    @FXML
    void fecharJogoButton(ActionEvent event) {
        System.out.println("to saindo do jogo!");
        TelaInicial.getStage().close();
    }
    @FXML
    void novoJogoButton(ActionEvent event) throws IOException {
        System.out.println("novo jogo!");
        PartidaController.setnDicas(3);

        Personagem[][] tabuleiro = new Personagem[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++){
                tabuleiro[i][j]=null;
            }
        }
        Tabuleiro.setTabuleiro(tabuleiro);

        FXMLLoader fxmlLoader = new FXMLLoader(TelaInicial.class.getResource("TelaInicioView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        TelaInicial.getStage().setScene(scene);
        TelaInicial.getStage().show();

    }
    @FXML
    void reiniciarJogoButton(ActionEvent event) throws IOException {
        System.out.println("reiniciando partida!");
        Tabuleiro.setTabuleiro(Tabuleiro.getTabuleiroCopia());

        System.out.println("=========tabuleiro=============");
        Tabuleiro.imprimeTabuleiro(Tabuleiro.getTabuleiro());
        System.out.println("===============================");

        PartidaController.setnDicas(3);
        Tabuleiro.setContVermelho(0);
        Tabuleiro.setContAzul(0);
        new TrocarCena(anchorPanePartida, "PartidaView.fxml");
    }
    @FXML
    void clickGridPane(MouseEvent event) {
        if (dicaPedida == 1) { //CLICK PARA DICA
            Node clickedNode = event.getPickResult().getIntersectedNode();
            if (clickedNode != gridPane) {
                Integer colIndex = GridPane.getColumnIndex(clickedNode);
                Integer rowIndex = GridPane.getRowIndex(clickedNode);
                if (rowIndex == null || colIndex == null) {
                } else {
                    posXPlayer = rowIndex;
                    posYPlayer = colIndex;
                    System.out.println("Linha: " + posXPlayer + " Coluna: " + posYPlayer);
                }
            }
            colSelecionada = 1;
        } else if (dicaPedida == 0) { //CLICK PARA MOVIMENTAR PEÇAS
            Tabuleiro.ContBandeira(tabuleiro);
            if (Tabuleiro.getContAzul() == 0 || Tabuleiro.getContVermelho() == 0) {
                if (Tabuleiro.getContAzul() == 0) {
                    labelparaDica.setText("O time LARANJA ganhou!");
                    labelparaDica.setVisible(true);
                    dicaButton.setVisible(false);
                } else {
                    labelparaDica.setText("O time AZUL ganhou!");
                    labelparaDica.setVisible(true);
                    dicaButton.setVisible(false);
                }
            } else {
                pecaPosicionando = 1;
                Node clickedNode = event.getPickResult().getIntersectedNode();
                if (clickedNode != gridPane) {
                    Integer colIndex = GridPane.getColumnIndex(clickedNode);
                    Integer rowIndex = GridPane.getRowIndex(clickedNode);
                    if (rowIndex == null || colIndex == null) {
                    } else {
                        if (pecaSel == 0) {
                            posXPlayer = rowIndex;
                            posYPlayer = colIndex;
                            personagem = tabuleiro[posXPlayer][posYPlayer];
                            if (personagem != null && personagem.getTime() == "vermelho") {
                                if (personagem.getNivel() == 4 || personagem.getNivel() == 0) { //nao deixa selecionar bombas
                                    System.out.println("Você não pode selecionar bombas ou uma bandeira!");
                                } else {
                                    imgsGrid[posXPlayer][posYPlayer].setOpacity(0.25);
                                    imgsGrid[posXPlayer][posYPlayer].setFitWidth(70);
                                    imgsGrid[posXPlayer][posYPlayer].setFitHeight(70);
                                    tabuleiro[posXPlayer][posYPlayer] = null;
                                    pecaSel = 1;
                                }
                            } else {
                                System.out.println("Você só pode selecionar uma peça sua laranja!");
                            }
                            System.out.println("Linha: " + posXPlayer + " Coluna: " + posYPlayer);
                        } else if (pecaSel == 1) {
                            posXDestino = rowIndex;
                            posYDestino = colIndex;
                            Personagem pDestino = tabuleiro[posXDestino][posYDestino];
                            if ((posXDestino == posXPlayer) && (posYDestino == posYPlayer)) {
                                System.out.println("Você precisa selecionar uma posição diferente!");
                                imgsGrid[posXPlayer][posYDestino].setOpacity(1);
                                imgsGrid[posXPlayer][posYPlayer].setFitWidth(80);
                                imgsGrid[posXPlayer][posYPlayer].setFitHeight(80);
                                tabuleiro[posXPlayer][posYPlayer] = personagem;
                                pecaPosicionando = 0;
                                pecaSel = 0;
                                Tabuleiro.caminharIA(tabuleiro, imgsGrid);
                            } else if (pDestino == null) {
                                if (personagem.getNivel() == 2) {
                                    if ((posXDestino == posXPlayer) || (posYDestino == posYPlayer)) {
                                        Tabuleiro.setImagemGridSalvo(gridPane, Tabuleiro.getImagemPersonagem(personagem, "vermelho"), imgsGrid, posXDestino, posYDestino);
                                        imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                        tabuleiro[posXDestino][posYDestino] = personagem;
                                        pecaSel = 0;
                                        System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        pecaPosicionando = 0;
                                        Tabuleiro.caminharIA(tabuleiro, imgsGrid);
                                    } else {
                                        System.out.println("Você só pode movimentar-se na vertical ou horizontal!");
                                    }
                                } else {
                                    if ((posXDestino == (posXPlayer + 1) && posYDestino == posYPlayer) || (posXDestino == (posXPlayer - 1) && posYDestino == posYPlayer) || (posYDestino == (posYPlayer + 1) && posXDestino == posXPlayer) || (posYDestino == (posYPlayer - 1) && posXDestino == posXPlayer)) {
                                        Tabuleiro.setImagemGridSalvo(gridPane, Tabuleiro.getImagemPersonagem(personagem, "vermelho"), imgsGrid, posXDestino, posYDestino);
                                        imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                        tabuleiro[posXDestino][posYDestino] = personagem;
                                        pecaSel = 0;
                                        System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        pecaPosicionando = 0;
                                        Tabuleiro.imprimeTabuleiro(tabuleiro);
                                        setLabelQuantPecas();
                                        Tabuleiro.caminharIA(tabuleiro, imgsGrid);
                                        setLabelQuantPecas();
                                    } else {
                                        System.out.println("Você só pode movimentar-se na vertical ou horizontal!");
                                    }
                                }
                            } else if (pDestino.getTime() == "azul") {
                                if (personagem.getNivel() == 2) {
                                    if (((posXDestino > posXPlayer + 1) && (posYDestino == posYPlayer)) || ((posYDestino > posYPlayer + 1) && (posXDestino == posXPlayer)) || ((posXDestino < posXPlayer - 1) && (posYDestino == posYPlayer)) || ((posYDestino < posYPlayer - 1) && (posXDestino == posXPlayer))) {
                                        tabuleiro[posXPlayer][posYPlayer] = personagem;
                                        imgsGrid[posXPlayer][posYPlayer].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                        imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXDestino][posYDestino].getImage());
                                        System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        System.out.println("Você não pode correr e atacar na mesma vez!");
                                    } else if ((posXPlayer == posXDestino + 1) || (posYPlayer == posYDestino + 1) || (posYPlayer == posYDestino - 1) || (posXPlayer == posXDestino - 1)) {
                                        if (pDestino.getNivel() == 4 && personagem.getNivel() != 3) {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            imgsGrid[posXDestino][posYDestino].setImage(Tabuleiro.getImagemPersonagem(pDestino, "azul"));
                                            tabuleiro[posXDestino][posYDestino].setAssassino(1);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (pDestino.getNivel() == 4 && personagem.getNivel() == 3) {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (pDestino.getNivel() == personagem.getNivel()) { //as duas pecas forem iguais
                                            tabuleiro[posXDestino][posYDestino] = null;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(null);
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (personagem.getNivel() == 1 && pDestino.getNivel() == 10) { //espiao e marechal
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (personagem.getNivel() > pDestino.getNivel()) {
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            imgsGrid[posXDestino][posYDestino].setImage(Tabuleiro.getImagemPersonagem(pDestino, "azul"));
                                            tabuleiro[posXDestino][posYDestino].setAssassino(1);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        }
                                        pecaSel = 0;
                                        pecaPosicionando = 0;
                                        setLabelQuantPecas();
                                        Tabuleiro.caminharIA(tabuleiro, imgsGrid);
                                        setLabelQuantPecas();

                                        int contador[] = Tabuleiro.verificaPecas(tabuleiro);
                                        int contAzul = contador[0];
                                        int contVermelho = contador[1];
                                        System.out.println("====================");
                                        System.out.println("Contador Azul:" + contAzul);
                                        System.out.println("Contador Vermelho:" + contVermelho);
                                        System.out.println("====================");
                                        if (contAzul == 0 || contVermelho == 0) {
                                            if (contAzul == 0) {
                                                labelparaDica.setText("O time LARANJA ganhou!");
                                                labelparaDica.setTextFill(Color.ORANGE);
                                                labelparaDica.setVisible(true);
                                                dicaButton.setVisible(false);
                                            } else if (contVermelho == 0) {
                                                labelparaDica.setText("O time AZUL ganhou!");
                                                labelparaDica.setTextFill(Color.BLUE);
                                                labelparaDica.setVisible(true);
                                                dicaButton.setVisible(false);
                                            }
                                        }
                                    } else {
                                        System.out.println("Você não pode atacar esta peça!");
                                    }
                                } else {
                                    if ((posXDestino == (posXPlayer + 1) && posYDestino == posYPlayer) || (posXDestino == (posXPlayer - 1) && posYDestino == posYPlayer) || (posYDestino == (posYPlayer + 1) && posXDestino == posXPlayer) || (posYDestino == (posYPlayer - 1) && posXDestino == posXPlayer)) {
                                        if (pDestino.getNivel() == 4 && personagem.getNivel() != 3) {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            imgsGrid[posXDestino][posYDestino].setImage(Tabuleiro.getImagemPersonagem(pDestino, "azul"));
                                            tabuleiro[posXDestino][posYDestino].setAssassino(1);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (pDestino.getNivel() == 4 && personagem.getNivel() == 3) {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (pDestino.getNivel() == personagem.getNivel()) { //as duas pecas forem iguais
                                            tabuleiro[posXDestino][posYDestino] = null;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(null);
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (personagem.getNivel() == 1 && pDestino.getNivel() == 10) { //espiao e marechal
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else if (personagem.getNivel() > pDestino.getNivel()) {
                                            tabuleiro[posXDestino][posYDestino] = personagem;
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXDestino][posYDestino].setImage(imgsGrid[posXPlayer][posYPlayer].getImage());
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        } else {
                                            tabuleiro[posXPlayer][posYPlayer] = null;
                                            imgsGrid[posXPlayer][posYPlayer].setImage(null);
                                            imgsGrid[posXDestino][posYDestino].setImage(Tabuleiro.getImagemPersonagem(pDestino, "azul"));
                                            tabuleiro[posXDestino][posYDestino].setAssassino(1);
                                            System.out.println("Linha: " + posXDestino + " Coluna: " + posYDestino);
                                        }

                                        pecaSel = 0;
                                        pecaPosicionando = 0;
                                        imgsGrid[posXPlayer][posYPlayer].setOpacity(1);
                                        imgsGrid[posXPlayer][posYPlayer].setFitWidth(85);
                                        imgsGrid[posXPlayer][posYPlayer].setFitHeight(85);
                                        setLabelQuantPecas();
                                        Tabuleiro.caminharIA(tabuleiro, imgsGrid);
                                        setLabelQuantPecas();
                                        int contador[] = Tabuleiro.verificaPecas(tabuleiro);
                                        int contAzul = contador[0];
                                        int contVermelho = contador[1];
                                        System.out.println("====================");
                                        System.out.println("Contador Azul:" + contAzul);
                                        System.out.println("Contador Vermelho:" + contVermelho);
                                        System.out.println("====================");
                                        if (contAzul == 0 || contVermelho == 0) {
                                            if (contAzul == 0) {
                                                labelparaDica.setText("O time LARANJA ganhou!");
                                                labelparaDica.setTextFill(Color.ORANGE);
                                                labelparaDica.setVisible(true);
                                                dicaButton.setVisible(false);
                                            } else if (contVermelho == 0) {
                                                labelparaDica.setText("O time AZUL ganhou!");
                                                labelparaDica.setTextFill(Color.BLUE);
                                                labelparaDica.setVisible(true);
                                                dicaButton.setVisible(false);
                                            }
                                        }
                                    } else {
                                        System.out.println("Você só pode movimentar-se uma posição na vertical ou horizontal!");
                                    }
                                }
                            } else {
                                System.out.println("Você só pode atacar uma peça do time azul!");
                            }
                        }
                    }
                }
            }

        }
        System.out.println("\n========================");
        Tabuleiro.imprimeTabuleiro(Tabuleiro.getTabuleiro());
        System.out.println("===============\n");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.arraycopy(Tabuleiro.getTabuleiro(), 0, tabuleiro, 0, Tabuleiro.getTabuleiro().length);

        fecharJogoButton.setVisible(false);
        reiniciarPartidaButton.setVisible(false);
        novoJogoButton.setVisible(false);
        voltarButton.setVisible(false);

        tooltipDica.setText("Para pedir uma dica,\napós clicar no botão \"Dica\" ,\nclique em uma posição e\nclique novamente no botão  \"Dica\".");
        labelparaDica.setVisible(false);
        Tabuleiro.setContAzul(0);
        Tabuleiro.setContVermelho(0);
        Tabuleiro.imprimeTabuleiro(tabuleiro);

        //contagem de peças
        setLabelQuantPecas();

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

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i < 2) {
                    Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/ocultadoazul.png"));
                    Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                } else if (i == 2) {
                    if (tabuleiro[i][j] != null) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/lago.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/vazio.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    }
                } else if (i > 2) {
                    Personagem p = tabuleiro[i][j];
                    if (p.getNivel() == 0) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bandeira.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else if (p.getNivel() == 1) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/espiao.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else if (p.getNivel() == 2) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/soldado.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else if (p.getNivel() == 3) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/armeiro.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else if (p.getNivel() == 4) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bomba.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    } else if (p.getNivel() == 10) {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/marechal.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    }
                }
            }
        }

        //Tabuleiro.setImagensGrid(tabuleiro, gridPane, imgsGrid); //coloca todas as imagens certinhas e aparentes dos dois times
    }
    private Personagem[][] tabuleiro = new Personagem[5][5];
    private int colunaBomba;
    private static int nDicas = 2;
    private int posXPlayer, posYPlayer;
    private int dicaPedida = 0, colSelecionada = 0;
    private static ImageView[][] imgsGrid = new ImageView[5][5];
    private int pecaSel = 0, pecaPosicionando = 0;
    private int posXDestino, posYDestino;
    private Personagem personagem;
    public static void setnDicas(int nDicas) {
        PartidaController.nDicas = nDicas;
    }
    private void setLabelQuantPecas() {
        Tabuleiro.contPecas(tabuleiro, "azul", labelAzul);
        Tabuleiro.contPecas(tabuleiro, "vermelho", labelVermelho);
    }
    public void pedirDica(Personagem[][] tabuleiro, int j) {
        int aux = 0;
        if (nDicas > 0) {
            for (int i = 0; i < 3; i++) {
                Personagem p = tabuleiro[i][j];
                Personagem bomba = new Personagem();
                bomba.setNivel(4);
                if (p == null) {
                } else {
                    if (p.getNivel() == bomba.getNivel()) {
                        aux++;
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bomba.png"));
                        Tabuleiro.setImagemGrid(gridPane, imagem, j, i);
                        colunaBomba = j;
                    }
                }
            }
            if (aux == 0) {
                System.out.println("Sem bomba");
                labelparaDica.setText("Não há bomba nessa coluna!");
            } else if (aux != 0) {
                if (aux < 2) { //uma bomba
                    System.out.println(aux + " bomba na coluna: " + "!");
                    labelparaDica.setText("Há " + aux + " bomba na coluna " + colunaBomba + "!");
                } else { //mais de uma bomba //so muda a palvra no plural
                    System.out.println(aux + " bombas na coluna: " + "!");
                    labelparaDica.setText("Há " + aux + " bombas na coluna " + colunaBomba + "!");
                }
            }
        } else {
            System.out.println("Acabaram suas dicas!");
            labelparaDica.setText("Acabaram suas dicas!");
        }
        nDicas--;
    }
}
