package ufpel.combat;

public class Espiao extends Personagem {
	public Espiao(int nivel,int deslocamento,int quantidade, String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;
	}
	//Construtor
		public Espiao() {
			super();
			this.nivel = 1;
			this.deslocamento = 1;
			this.time = time;
		}
	
}
