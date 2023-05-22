package ufpel.combat;

public class Lago extends Personagem{
	public Lago(int nivel,int deslocamento,String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
	}
	//Construtor
	public Lago() {
		super();
		this.nivel = 5;
		this.deslocamento = 0;
		this.time = "Lago";
	}
}
