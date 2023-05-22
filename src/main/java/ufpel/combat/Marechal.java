package ufpel.combat;

public class Marechal extends Personagem {
	public Marechal(int nivel,int deslocamento,int quantidade,String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;
	}
	//Construtor
	public Marechal() {
		super();
		this.nivel = 10;
		this.deslocamento = 1;
		this.time = time;
	}
}
