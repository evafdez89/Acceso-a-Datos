import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	
	static Scanner lector;

	public static void main(String[] args) throws ClassNotFoundException, IOException {

		lector = new Scanner(System.in);
        ArrayList<Coche> coches = new ArrayList<Coche>();
        int opc = 0;
        String linea=""; //

        File ficheroCoches = new File("coches.dat"); // Creo el fichero
        boolean ok = ficheroCoches.createNewFile();
        	if (ok) 
        		System.out.println("El fichero se ha creado con éxito");
        	else
        		System.out.println("El fichero no ha podido crearse");
       
        	//COMPROBAR SI YA EXISTE EL FICHERO, SI EXISTE LEERLO Y COPIAR LOS OBJETOS AL ARRAY. SI NO EXISTE EL FICHERO NO HACER NADA
        	//Abrir el fichero coches.dat para lectura
        	FileInputStream file1 = new FileInputStream("coches.dat"); 
        	ObjectInputStream buffer1 = new ObjectInputStream(file1);
        	try {
        		file1 = new FileInputStream("coches.dat");
        		buffer1 = new ObjectInputStream(file1);
        	} catch (IOException e) {
        		System.out.println("No se ha podido abrir el fichero");
        		System.out.println(e.getMessage());
        		return;
        	}
        	
        	//Leer los objetos guardados en el fichero coches.dat y almacenarlos en el array coches
        	try {
        		Object obj;
        		while((obj = buffer1.readObject()) instanceof Coche){
        			coches.add((Coche)obj);
        			System.out.println("Objeto leido y guardado: " + obj);
        		}
        		
        	}catch (IOException e){
        		System.out.println("Error al escribir en el fichero");
        		System.out.println(e.getMessage());
        	}

        while (opc!=6) {
        	 mostrarMenu();
             opc = lector.nextInt();
             lector.nextLine(); // Para recoger el retorno de carro.                                 

             switch(opc) { //Para que en función de la opción elegida por el usuario se ejecuten los métodos
	             case 1:
	            	 nuevoCoche(coches);
	            	 break;
	             case 2:
	            	 borrarCoche(coches);
	            	 break;
	             case 3:
	            	 consultarCoche(coches);
	            	 break;
	             case 4:
	            	 listadoCoches(coches);
	            	 break;
	             case 5:
	            	 exportarCoches(coches);
	            	 break;
             }
       }

        
        //ABRIR EL FICHERO PARA ESCRITURA Y GUARDAR TODOS LOS OBJETOS QUE CONTENGA EN EL ARRAY. SI YA EXISTE EL FICHERO DEBERA SER SOBREESCRITO.
        //Abro fichero coches.dat para escritura
        FileOutputStream file;
        ObjectOutputStream buffer;
        
        try {
        	file = new FileOutputStream("coches.dat");
        	buffer = new ObjectOutputStream(file);
        } catch (IOException e) {
        	System.out.println("No se ha podido abrir el fichero");
        	System.out.println(e.getMessage());
        	return;
        }
        
        //Guardo los objetos que estan en el array en el fichero
        try {
        	for(Coche c: coches) { //recorro el array      	
        	buffer.writeObject(c); //escribo en el buffer los objetos que contiene el array
        	System.out.println("El objeto " + c +  " se ha grabado con éxito");
        	}
        } catch (IOException e) {
        	System.out.println("Error al escribir en el fichero");
        	System.out.println(e.getMessage());
        }
        
        //Cierro el fichero coches.dat
        try {
        	buffer.close();
        	file.close();
        } catch (IOException e) {
        	System.out.println("Error al cerrar el fichero");
        	System.out.println(e.getMessage());
        }
  

       lector.close();
}
	
	public static void mostrarMenu() {
              System.out.println("        COCHES MATRICULADOS");
              System.out.println("---------------------------------------");
              System.out.println("1. Añadir nuevo coche");
              System.out.println("2. Borrar coche");
              System.out.println("3. Consultar coche");
              System.out.println("4. Listado de coches");
              System.out.println("5. Exportar coches a archivo de texto");
              System.out.println("6. Terminar programa");
              System.out.println("---------------------------------------");
              System.out.println("¿Qué opción eliges?");
	}

	public static void nuevoCoche(ArrayList<Coche> coches) { //El usuario incluye un nuevo coche al array
		String matricula, marca, modelo, color;
		System.out.println("Matrícula: ");
		matricula = lector.nextLine();
		System.out.println("Marca: ");
		marca = lector.nextLine();
		System.out.println("Modelo: ");
		modelo = lector.nextLine();
		System.out.println("Color: ");
		color = lector.nextLine();
		
		coches.add(new Coche(matricula,marca,modelo,color));
		System.out.println("El coche ha sido añadido con éxito");
	}

	public static void borrarCoche(ArrayList<Coche> coches) throws ClassNotFoundException, IOException { //El usuario introduce una matricula, y se elimina el registro que coincida con ella
		String matricula;
		System.out.println("Matrícula buscada: ");
		matricula = lector.nextLine();

			for (int i=0; i < coches.size();i++) {
				if(coches.get(i).getMatricula().equals(matricula)) {
					System.out.println(coches.get(i).toString() + " SERA ELIMINADO");
					coches.remove(i);
				}
			}
	}

	public static void consultarCoche(ArrayList<Coche> coches) { //Permite al usuario introducir una matricula y si existe mostrar en pantalla los datos del coche consultado
		String matricula;
		System.out.println("Matrícula buscada: ");
		matricula = lector.nextLine();

			for (int i=0; i < coches.size();i++) {
				if(coches.get(i).getMatricula().equals(matricula)) { 
				System.out.println(coches.get(i));
				}
			}
	}

	public static void listadoCoches(ArrayList<Coche> coches) { //Recorre el array para mostrar los coches almacenados
		for (int i=0; i < coches.size();i++) {
			System.out.println(coches.get(i).getMatricula() +" "+ coches.get(i).getMarca() +" " + coches.get(i).getModelo()  +" "+ coches.get(i).getColor());
			}
		}


	public static void exportarCoches(ArrayList<Coche> coches) throws IOException {
		File ficheroTxt = new File("coches.txt");
		ficheroTxt.createNewFile();
		if (!ficheroTxt.exists()) {
			System.out.println("El fichero no existe");
			return;
		}
		
		
			while(lector.hasNext()) {
				String linea = lector.nextLine();
				System.out.println(linea);
			}
		
	}
             		
		/*
                * Recorre secuencialmente la colección de coches y genera

                * un fichero de texto llamado coches.txt

                */

         }
