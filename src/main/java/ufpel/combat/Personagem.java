package ufpel.combat;

import java.util.Scanner;

public class Personagem {
    protected int nivel;
    protected int deslocamento;
    protected int assassino;

    public void setAssassino(int assassino) {
        this.assassino = assassino;
    }

    public int getAssassino() {
        return assassino;
    }


    protected int quantidade;
    protected String time;

    public Personagem() { // Construtor Default
    }

    public Personagem(int nivel, int deslocamento) { // Construtor
        this.nivel = nivel;
        this.deslocamento = deslocamento;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getDeslocamento() {
        return deslocamento;
    }

    public void setDeslocamento(int deslocamento) {
        this.deslocamento = deslocamento;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}


