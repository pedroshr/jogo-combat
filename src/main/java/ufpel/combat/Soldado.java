package ufpel.combat;

public class Soldado extends Personagem{
	public Soldado(int nivel,int deslocamento,String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;
	}
	//Construtor
		public Soldado() {
			super();
			this.nivel = 2;
			this.deslocamento = 2;
			this.time = time;
		}
}
