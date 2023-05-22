package ufpel.combat;

public class Bandeira extends Personagem{
	public Bandeira(int nivel,int deslocamento,int quantidade, String time){ //Construtor Default
		super();
		this.nivel = nivel;
		this.deslocamento = deslocamento;
		this.time = time;
	}
	//Construtor
		public Bandeira() {
			super();
			this.nivel = 0;
			this.deslocamento = 0;
			this.time = time;
		}
}

