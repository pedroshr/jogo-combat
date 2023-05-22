package ufpel.combat;

public class Bomba extends Personagem{
	public Bomba(int nivel,int deslocamento,int quantidade,String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;

	}
	//Construtor
		public Bomba() {
			super();
			this.nivel = 4;
			this.deslocamento = 0;
			this.time = time;
		}
}
