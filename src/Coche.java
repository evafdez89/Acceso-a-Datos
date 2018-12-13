import java.io.Serializable;

public class Coche implements Serializable {

	private static final long serialVersionUID = 3715768902297561458L;

	//ATRIBUTOS
	private String matricula;
    private String marca;
    private String modelo;
    private String color;

    //CONSTRUCTOR
    public Coche (String matricula, String marca, String modelo, String color) {
    	this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
   }

    //METODOS GETTER Y SETTER          
    public String getMatricula() {
        return matricula;
    }

	public String getMarca() {
	        return marca;
	}
	
	public String getModelo() {
	        return modelo;
	}
	
	public String getColor() {
	        return color;
	}            
	            
    @Override
    //se sobreescribe el método toString
    public String toString() {
                   return matricula + " " + marca + " " + modelo + " " + color;
}            



}