package ufpel.combat;

public class Cabo extends Personagem {
	public Cabo(int nivel,int deslocamento, int quantidade, String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;

	}
	//Construtor
		public Cabo() {
			super();
			this.nivel = 3;
			this.deslocamento = 1;
			this.time = time;
		}
	
}
