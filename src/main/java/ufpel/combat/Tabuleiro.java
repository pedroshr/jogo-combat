package ufpel.combat;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Tabuleiro {
    private static Personagem[][] tabuleiro = new Personagem[5][5]; /// Tabuleiro criado

    private static Personagem[][] tabuleiroCopia = new Personagem[5][5];

    private static int contAzul = 0, contVermelho = 0;

    public static void setTabuleiro(Personagem[][] tabuleiroT) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiro[i][j] = tabuleiroT[i][j];
            }
        }
    }

    public static Personagem[][] getTabuleiro() {
        return tabuleiro;
    }

    public static Personagem[][] getTabuleiroCopia() {
        return tabuleiroCopia;
    }

    public static void setTabuleiroCopia(Personagem[][] tabuleiroT) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tabuleiroCopia[i][j] = tabuleiroT[i][j];
            }
        }
    }

    public static void setImagensGrid(Personagem[][] tabuleiroT, GridPane gridPane, ImageView[][] imgsGrid) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Personagem p = tabuleiroT[i][j];
                if (i < 3) { //COLOCA IMAGENS DO TIME AZUL E LAGO
                    if (p != null) {
                        if (p.getNivel() == 0) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bandeira.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 1) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/espiao.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 2) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/soldado.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 3) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/armeiro.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 5) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/lago.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 4) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bomba.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        } else if (p.getNivel() == 10) {
                            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/marechal.png"));
                            Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                        }
                    } else {
                        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/vazio.png"));
                        Tabuleiro.setImagemGridSalvo(gridPane, imagem, imgsGrid, i, j);
                    }
                } else if (i > 2) { //COLOCA IMAGENS DO TIME LARANJA
                    if (p != null) {
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
        }
    }

    public static void colocaPersonagem(Personagem[][] tabuleiro, Personagem personagem) {
        Random gerador = new Random();
        int i, j;
        if (personagem.getTime() == "azul") {
            if (personagem.getNivel() == 0) {
                i = 0;
                j = gerador.nextInt(5);
                while (tabuleiro[i][j] != null || i > 4 || j > 4) {
                    i = gerador.nextInt(2);
                    j = gerador.nextInt(5);
                }
                tabuleiro[i][j] = personagem;
            } else {
                i = gerador.nextInt(2);
                j = gerador.nextInt(5);
                while (tabuleiro[i][j] != null || i > 4 || j > 4) {
                    i = gerador.nextInt(2);
                    j = gerador.nextInt(5);
                }
                tabuleiro[i][j] = personagem;
            }
        } else if (personagem.getTime() == "vermelho") {
            i = gerador.nextInt(5);
            j = gerador.nextInt(5);
            if (personagem.getNivel() == 0) {
                i = 4;
                while (tabuleiro[i][j] != null || i > 4 || i < 3 || j > 4) {
                    i = gerador.nextInt(5);
                    j = gerador.nextInt(5);
                }
                tabuleiro[i][j] = personagem;
            } else {
                while (tabuleiro[i][j] != null || i > 4 || i < 3 || j > 4) {
                    i = gerador.nextInt(5);
                    j = gerador.nextInt(5);
                }
                tabuleiro[i][j] = personagem;
            }
        } else {
            i = 2;
            j = gerador.nextInt(5);
            while (j > 5) {
                j = gerador.nextInt(5);
            }
            tabuleiro[i][j] = personagem;
            System.out.println("Lago adicionado na posição:" + i + "," + j);
        }
    }

    public static int getContAzul() {
        return contAzul;
    }

    public static void setContAzul(int contAzul) {
        Tabuleiro.contAzul = contAzul;
    }

    public static int getContVermelho() {
        return contVermelho;
    }

    public static void setContVermelho(int contVermelho) {
        Tabuleiro.contVermelho = contVermelho;
    }

    public static void ContBandeira(Personagem[][] tabuleiro) {
        contAzul = 0;
        contVermelho = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] != null) {
                    if (tabuleiro[i][j].getNivel() == 0) {
                        if (tabuleiro[i][j].getTime() == "azul") {
                            contAzul++;
                        } else {
                            contVermelho++;
                        }
                    }
                }
            }
        }
    }

    public static void contPecas(Personagem[][] tabuleiro, String time, Label label) {
        int cont = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (tabuleiro[i][j] != null && tabuleiro[i][j].getTime() == time) {
                    cont++;
                }
            }
        }
        if (time == "azul") {
            if (cont == 1) {
                label.setText("Resta " + cont + " peça azul!");
            } else {
                label.setText("Resta " + cont + " peças azuis!");
            }
        } else {
            if (cont == 1) {
                label.setText("Resta " + cont + " peça laranja!");
            } else {
                label.setText("Resta " + cont + " peças laranjas!");
            }
        }
    }

    public static void caminharIA(Personagem[][] tabuleiro, ImageView[][] imgsGrid) throws ArrayIndexOutOfBoundsException {
        int linha;
        int coluna;
        char direcao = 'c';
        Personagem troca;
        troca = new Personagem();
        Random gerador = new Random();
        int dir;

        linha = gerador.nextInt(5);
        coluna = gerador.nextInt(5);
        while (coluna > 4 || linha > 4 || tabuleiro[linha][coluna] == null || tabuleiro[linha][coluna].getTime() == "vermelho" || tabuleiro[linha][coluna].getTime() == "lago" || tabuleiro[linha][coluna].getDeslocamento() == 0 || (direcao == 'c' && linha == 0) || (direcao == 'e' && coluna == 0) || (direcao == 'd' && coluna == 4) || (direcao == 'b' && linha == 4)) {
            linha = gerador.nextInt(5);
            coluna = gerador.nextInt(5);
            dir = gerador.nextInt(4);
            if (dir == 0) {
                direcao = 'c';
            } else if (dir == 1) {
                direcao = 'b';
            } else if (dir == 2) {
                direcao = 'e';
            } else if (dir == 3) {
                direcao = 'd';
            }
        }

        troca = tabuleiro[linha][coluna];

        switch (direcao) {
            case 'c':
                while (linha == 0) {
                    linha = gerador.nextInt(5);
                }
                if (tabuleiro[linha - 1][coluna] == null) {
                    System.out.println("Linha: " + linha + "Coluna: " + coluna + " cima");
                    tabuleiro[linha][coluna] = null;
                    tabuleiro[linha - 1][coluna] = troca;
                    if (imgsGrid[linha - 1][coluna] == null) {
                        if (tabuleiro[linha - 1][coluna].getAssassino() == 1) {
                            imgsGrid[linha - 1][coluna] = new ImageView(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha - 1][coluna] = new ImageView(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    } else {
                        if (tabuleiro[linha - 1][coluna].getAssassino() == 1) {
                            imgsGrid[linha - 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha - 1][coluna].setImage(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    }
                    imgsGrid[linha - 1][coluna].setOpacity(1);
                    imgsGrid[linha][coluna].setImage(null);
                    imgsGrid[linha - 1][coluna].setFitHeight(80);
                    imgsGrid[linha - 1][coluna].setFitWidth(80);
                } else {
                    if (tabuleiro[linha - 1][coluna].getNivel() == 5 || tabuleiro[linha - 1][coluna].getTime() == "azul" || tabuleiro[linha - 1][coluna].getTime() == "azulA") {// lago
                        caminharIA(tabuleiro, imgsGrid);
                    } else if (tabuleiro[linha][coluna].getNivel() == tabuleiro[linha - 1][coluna].getNivel()) {// empate
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha - 1][coluna] = null;
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha - 1][coluna].setImage(null);
                    } else if (tabuleiro[linha][coluna].getNivel() < tabuleiro[linha - 1][coluna].getNivel()) {// perde
                        if (tabuleiro[linha][coluna].getNivel() == 3 && tabuleiro[linha - 1][coluna].getNivel() == 4) {
                            tabuleiro[linha][coluna] = null;
                            tabuleiro[linha - 1][coluna] = troca;
                            imgsGrid[linha][coluna].setImage(null);
                            imgsGrid[linha - 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            tabuleiro[linha][coluna] = null;
                            imgsGrid[linha][coluna].setImage(null);
                        }
                    } else {// ganha
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha - 1][coluna] = troca;
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha - 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        imgsGrid[linha - 1][coluna].setFitHeight(80);
                        imgsGrid[linha - 1][coluna].setFitWidth(80);
                    }
                }
                break;
            case 'b':
                while (linha == 4) {
                    linha = gerador.nextInt(5);
                }
                if (tabuleiro[linha + 1][coluna] == null) {
                    System.out.println("Linha: " + linha + "Coluna: " + coluna + " baixo");
                    tabuleiro[linha][coluna] = null;
                    tabuleiro[linha + 1][coluna] = troca;
                    if (imgsGrid[linha + 1][coluna] == null) {
                        if (tabuleiro[linha + 1][coluna].getAssassino() == 1) {
                            imgsGrid[linha + 1][coluna] = new ImageView(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha + 1][coluna] = new ImageView(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    } else {
                        if (tabuleiro[linha + 1][coluna].getAssassino() == 1) {
                            imgsGrid[linha + 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha + 1][coluna].setImage(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    }
                    imgsGrid[linha + 1][coluna].setOpacity(1);
                    imgsGrid[linha][coluna].setImage(null);
                    imgsGrid[linha + 1][coluna].setFitHeight(80);
                    imgsGrid[linha + 1][coluna].setFitWidth(80);
                } else {
                    if (tabuleiro[linha + 1][coluna].getNivel() == 5 || tabuleiro[linha + 1][coluna].getTime() == "azul" || tabuleiro[linha + 1][coluna].getTime() == "azulA") {
                        caminharIA(tabuleiro, imgsGrid);
                    } else if (tabuleiro[linha][coluna].getNivel() == tabuleiro[linha + 1][coluna].getNivel()) {// empate
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha + 1][coluna] = null;
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha + 1][coluna].setImage(null);
                    } else if (tabuleiro[linha][coluna].getNivel() < tabuleiro[linha + 1][coluna].getNivel()) {// perde
                        if (tabuleiro[linha][coluna].getNivel() == 3 && tabuleiro[linha + 1][coluna].getNivel() == 4) {
                            tabuleiro[linha][coluna] = null;
                            tabuleiro[linha + 1][coluna] = troca;
                            imgsGrid[linha][coluna].setImage(null);
                            imgsGrid[linha + 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            tabuleiro[linha][coluna] = null;
                            imgsGrid[linha][coluna].setImage(null);
                        }
                    } else {// ganha
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha + 1][coluna] = troca;
                        imgsGrid[linha + 1][coluna].setImage(getImagemPersonagem(troca, "azul"));
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha + 1][coluna].setFitHeight(80);
                        imgsGrid[linha + 1][coluna].setFitWidth(80);
                    }
                }
                break;
            case 'e':
                while (coluna == 0) {
                    coluna = gerador.nextInt(5);
                }
                // não é soldado
                if (tabuleiro[linha][coluna - 1] == null) {
                    System.out.println("Linha: " + linha + "Coluna: " + coluna + " esquerda");
                    tabuleiro[linha][coluna] = null;
                    tabuleiro[linha][coluna - 1] = troca;
                    if (imgsGrid[linha][coluna - 1] == null) {
                        if (tabuleiro[linha][coluna - 1].getAssassino() == 1) {
                            imgsGrid[linha][coluna - 1] = new ImageView(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha][coluna - 1] = new ImageView(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    } else {
                        if (tabuleiro[linha][coluna - 1].getAssassino() == 1) {
                            imgsGrid[linha][coluna - 1].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha][coluna - 1].setImage(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    }
                    imgsGrid[linha][coluna - 1].setOpacity(1);
                    imgsGrid[linha][coluna].setImage(null);
                    imgsGrid[linha][coluna - 1].setFitHeight(80);
                    imgsGrid[linha][coluna - 1].setFitWidth(80);
                } else {// tem algo
                    if (tabuleiro[linha][coluna - 1].getNivel() == 5 || tabuleiro[linha][coluna - 1].getTime() == "azul"
                            || tabuleiro[linha][coluna - 1].getTime() == "azulA"
                    ) {
                        caminharIA(tabuleiro, imgsGrid);
                    } else if (tabuleiro[linha][coluna].getNivel() == tabuleiro[linha][coluna - 1].getNivel()) {// empate
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha][coluna - 1] = null;
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha][coluna - 1].setImage(null);
                    } else if (tabuleiro[linha][coluna].getNivel() < tabuleiro[linha][coluna - 1].getNivel()) {// perde
                        if (tabuleiro[linha][coluna].getNivel() == 3 && tabuleiro[linha][coluna - 1].getNivel() == 4) {
                            tabuleiro[linha][coluna] = null;
                            tabuleiro[linha][coluna - 1] = troca;
                            imgsGrid[linha][coluna].setImage(null);
                            imgsGrid[linha][coluna - 1].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            tabuleiro[linha][coluna] = null;
                            imgsGrid[linha][coluna].setImage(null);
                        }
                    } else {// ganha
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha][coluna - 1] = troca;
                        imgsGrid[linha][coluna - 1].setImage(getImagemPersonagem(troca, "azul"));
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha][coluna - 1].setFitHeight(80);
                        imgsGrid[linha][coluna - 1].setFitWidth(80);
                    }
                }
                break;
            case 'd':
                while (coluna == 4) {
                    coluna = gerador.nextInt(5);
                }
                // Não é soldado ou não pode andar 2
                if (tabuleiro[linha][coluna + 1] == null) {
                    System.out.println("Linha: " + linha + "Coluna: " + coluna + " direita");
                    tabuleiro[linha][coluna] = null;
                    tabuleiro[linha][coluna + 1] = troca;
                    if (imgsGrid[linha][coluna + 1] == null) {
                        if (tabuleiro[linha][coluna + 1].getAssassino() == 1) {
                            imgsGrid[linha][coluna + 1] = new ImageView(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha][coluna + 1] = new ImageView(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    } else {
                        if (tabuleiro[linha][coluna + 1].getAssassino() == 1) {
                            imgsGrid[linha][coluna + 1].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            imgsGrid[linha][coluna + 1].setImage(getImagemPersonagem(troca, "azulCensurado"));
                        }
                    }
                    imgsGrid[linha][coluna + 1].setOpacity(1);
                    imgsGrid[linha][coluna].setImage(null);
                    imgsGrid[linha][coluna + 1].setFitHeight(80);
                    imgsGrid[linha][coluna + 1].setFitWidth(80);
                } else {
                    if (tabuleiro[linha][coluna + 1].getNivel() == 5 || tabuleiro[linha][coluna + 1].getTime() == "azul"
                            || tabuleiro[linha][coluna + 1].getTime() == "azulA"
                    ) {
                        caminharIA(tabuleiro, imgsGrid);
                    } else if (tabuleiro[linha][coluna].getNivel() == tabuleiro[linha][coluna + 1].getNivel()) {// empate
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha][coluna + 1] = null;
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha][coluna + 1].setImage(null);
                    } else if (tabuleiro[linha][coluna].getNivel() < tabuleiro[linha][coluna + 1].getNivel()) {// perde
                        if (tabuleiro[linha][coluna].getNivel() == 3 && tabuleiro[linha][coluna + 1].getNivel() == 4) {
                            tabuleiro[linha][coluna] = null;
                            tabuleiro[linha][coluna + 1] = troca;
                            imgsGrid[linha][coluna].setImage(null);
                            imgsGrid[linha][coluna + 1].setImage(getImagemPersonagem(troca, "azul"));
                        } else {
                            tabuleiro[linha][coluna] = null;
                            imgsGrid[linha][coluna].setImage(null);
                        }
                    } else {// ganha
                        tabuleiro[linha][coluna] = null;
                        tabuleiro[linha][coluna + 1] = troca;
                        imgsGrid[linha][coluna + 1].setImage(getImagemPersonagem(troca, "azul"));
                        imgsGrid[linha][coluna].setImage(null);
                        imgsGrid[linha][coluna + 1].setFitHeight(80);
                        imgsGrid[linha][coluna + 1].setFitWidth(80);
                    }
                }
                break;
        }

    }

    public static int[] verificaPecas(Personagem[][] tabuleiroT) {
        int contador[] = new int[2];
        contador[0] = 0;
        contador[1] = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Personagem aux = tabuleiroT[i][j];
                if (aux == null) {
                } else if (aux.getDeslocamento() != 0 && aux.getTime() == "azul") {
                    contador[0]++;
                } else if (aux.getDeslocamento() != 0 && aux.getTime() == "vermelho") {
                    contador[1]++;
                }
            }
        }
        return contador;
    }

    public static void imprimeTabuleiro(Personagem[][] tabuleiroT) {
        for (int i = 0; i < tabuleiroT.length; i++) {
            for (int j = 0; j < tabuleiroT[0].length; j++) {
                Personagem p = tabuleiroT[i][j];
                if (p != null) {
                    if (p.getTime() == "azul") {
                        System.out.print(p.getNivel() + " ");
                        //System.out.print("*" + " ");
                    } else if (p.getTime() == "vermelho" || p.getTime() == "Lago" || p.getTime() == "azulA") {
                        System.out.print(p.getNivel() + " ");
                    } else if (p.getTime() == null) {
                        System.out.print("-");
                    }
                } else {
                    System.out.print("-" + " ");
                }
            }
            System.out.println(); // pula para a próxima linha
        }
    }

    public static void setImageEffect(ImageView img) {
        img.setOpacity(0.25);
        img.setFitWidth(70);
        img.setFitHeight(70);
    }

    public static void setImagemGrid(GridPane gridPane, Image imagem, int i, int j) {
        ImageView img = new ImageView();
        img.setFitHeight(80);
        img.setFitWidth(80);
        img.setImage(imagem);
        gridPane.add(img, i, j);
        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setValignment(img, VPos.CENTER);
    }

    public static void setImagemGridSalvo(GridPane gridPane, Image imagem, ImageView[][] imgsGrid, int i, int j) {
        ImageView img = new ImageView();
        imgsGrid[i][j] = img;
        img.setFitHeight(80);
        img.setFitWidth(80);
        img.setImage(imagem);
        gridPane.add(img, j, i);
        GridPane.setHalignment(img, HPos.CENTER);
        GridPane.setValignment(img, VPos.CENTER);
    }

    public static Image getImagemPersonagem(Personagem p, String time) {
        if (time == "vermelho") {
            if (p.getNivel() == 0) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bandeira.png"));
                return imagem;
            } else if (p.getNivel() == 1) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/espiao.png"));
                return imagem;
            } else if (p.getNivel() == 2) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/soldado.png"));
                return imagem;
            } else if (p.getNivel() == 3) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/armeiro.png"));
                return imagem;
            } else if (p.getNivel() == 4) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bomba.png"));
                return imagem;
            } else if (p.getNivel() == 10) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/marechal.png"));
                return imagem;
            }
        } else if (time == "azul") {
            if (p.getNivel() == 0) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bandeira.png"));
                return imagem;
            } else if (p.getNivel() == 1) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/espiao.png"));
                return imagem;
            } else if (p.getNivel() == 2) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/soldado.png"));
                return imagem;
            } else if (p.getNivel() == 3) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/armeiro.png"));
                return imagem;
            } else if (p.getNivel() == 4) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bomba.png"));
                return imagem;
            } else if (p.getNivel() == 10) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/marechal.png"));
                return imagem;
            }
        } else if (time == "lago") {
            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/lago.png"));
            return imagem;
        } else if (time == "azulCensurado") {
            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/ocultadoazul.png"));
            return imagem;
        }
        Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/vazio.png"));
        return imagem;
    }

    public static void setImagemTodasPecas(Personagem[][] tabuleiro, String time, GridPane gridPane, int i, int j) {
        if (time == "azul") {
            if (tabuleiro[j][i].getNivel() == 0) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bandeira.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 1) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/espiao.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 2) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/soldado.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 3) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/armeiro.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 4) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/bomba.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 10) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/marechal.png"));
                setImagemGrid(gridPane, imagem, i, j);
            }
        } else if (time == "vermelho") {
            if (tabuleiro[j][i].getNivel() == 0) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bandeira.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 1) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/espiao.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 2) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/soldado.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 3) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/armeiro.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 4) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/bomba.png"));
                setImagemGrid(gridPane, imagem, i, j);
            } else if (tabuleiro[j][i].getNivel() == 10) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/laranja/marechal.png"));
                setImagemGrid(gridPane, imagem, i, j);
            }
        } else if (time == "azulCensurado") {
            Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/azul/ocultadoazul.png"));
            setImagemGrid(gridPane, imagem, i, j);
        } else {
            if (tabuleiro[j][i] != null) {
                Image imagem = new Image(Tabuleiro.class.getResourceAsStream("img/lago.png"));
                setImagemGrid(gridPane, imagem, i, j);
            }
        }
    }

    public static void setImagensTimeGrid(Personagem[][] tabuleiro, String time, GridPane gridPane) {
        if (time == "azul") {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    setImagemTodasPecas(tabuleiro, "azulCensurado", gridPane, i, j);
                }
            }
        } else if (time == "vermelho") {
            for (int i = 0; i < 5; i++) {
                for (int j = 3; j < 5; j++) {
                    setImagemTodasPecas(tabuleiro, "vermelho", gridPane, i, j);
                }
            }
        } else if (time == "lago") {
            for (int i = 0; i < 5; i++) {
                if (tabuleiro[i][2] == null) {

                }
                setImagemTodasPecas(tabuleiro, "lago", gridPane, i, 2);
            }
        }
    }
}
