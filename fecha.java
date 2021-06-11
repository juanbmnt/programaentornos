
public class fecha {

	int dia;
	int mes;
	int ano;
	
	
	fecha(){
		
		this.dia=1;
		this.mes=1;
		this.ano=1;
	}
	
	fecha(String f){
		String [] separar=f.split("/");
		this.dia=Integer.parseInt(separar[0]);
		this.mes=Integer.parseInt(separar[1]);
		this.ano=Integer.parseInt(separar[2]);
		
		
	}

}
