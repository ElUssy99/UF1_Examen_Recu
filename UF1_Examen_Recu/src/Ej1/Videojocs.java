package Ej1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Videojocs {
	
	public static ArrayList<VideojocObj> juegos = new ArrayList<VideojocObj>();
	
	public static void main(String[] args) {
		File f = new File("..\\UF1_Examen_Recu\\src\\Videojocs.dat");
		
		juegos.clear();
		
		crearDat(f);
		leerDat(f);
		
		System.out.println("");
		for (VideojocObj juego : juegos) {
			System.out.println(juego);
		}
		
		menu(f);
	}
	
	// Este metodo crea los objetos y los escribo en el archivo.dat
	public static void crearDat (File f) {
		VideojocObj v1 = new VideojocObj(1, "TheLastofUs", "PlayStation4", 54.32);
		VideojocObj v2 = new VideojocObj(2, "CSGO", "XboxOne", 15.23);
		VideojocObj v3 = new VideojocObj(3, "Halo5", "XboxOne", 67.54);
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			bw.write(v1.getNumJoc() + "-" + v1.getNomJoc() + "-" + v1.getPlataforma() + "-" + v1.getPreu() + "/");
			bw.write(v2.getNumJoc() + "-" + v2.getNomJoc() + "-" + v2.getPlataforma() + "-" + v2.getPreu() + "/");
			bw.write(v3.getNumJoc() + "-" + v3.getNomJoc() + "-" + v3.getPlataforma() + "-" + v3.getPreu() + "/");
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				bw.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
		}
	}
	
	// Para leer el contenido del archivo, guardo con un split para dividir cada juego por una "/"
	// y otro split para dirivir cada atributo por un "-".
	public static void leerDat(File f) {
		String[] split1 = null;
		try {
			Scanner reader = new Scanner(f);
			while (reader.hasNextLine()) {
				String linea = reader.nextLine();
				split1 = linea.split("/");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		for (int i = 0; i < split1.length; i++) {
			String[] split2 = split1[i].split("-");
			VideojocObj v = new VideojocObj();
			for (int j = 0; j < split2.length; j++) {
				System.out.println(split2[j]);
				if (j == 0) {
					int num = Integer.parseInt(split2[j]);
					v.setNumJoc(num);
				} 
				if (j == 1) {
					v.setNomJoc(split2[j]);
				}
				if (j == 2) {
					v.setPlataforma(split2[j]);
				}
				if (j == 3) {
					double preu = Double.parseDouble(split2[j]);
					v.setPreu(preu);
				}
			}
			juegos.add(v);
		}
	}
	
	public static void menu(File f) {
		Scanner entrada = new Scanner(System.in);
		boolean continuar = true;
		while(continuar) {
			System.out.println("// EDITAR JUEGOS //");
			System.out.println("1. Modificar nombre de Juego");
			System.out.println("2. Modificar plataforma de Juego");
			System.out.println("3. Modificar precio de Juego");
			System.out.println("4. Eliminar juego");
			System.out.println("5. Salir");
			System.out.println("(Los datos se guardaran en el archivo al salir del programa)");
			System.out.println("Introduce un numero entre 1 y 4 (5 para salir):");
			
			int opcion = entrada.nextInt();
			switch (opcion) {
			case 1:
				moddNombre();
				break;
			case 2:
				moddPlataforma();
				break;
			case 3:
				moddPrecio();
				break;
			case 4:
				System.out.println("Ahora mismo no se pueden eliminar juegos. Vuelva mas tarde.");
				break;
			case 5:
				System.out.println("Has salido del programa.");
				System.out.println("Los datos se estan guardando en el archivo...");
				guardarDatos(f);
				System.out.println("Los datos se han guardado.");
				continuar = false;
				break;
			default:
				break;
			}
		}
	}
	
	public static boolean juegoExiste(int id) {
		for (VideojocObj videojocObj : juegos) {
			if (videojocObj.getNumJoc() == id) {
				return true;
			}
		}
		return false;
	}
	
	public static void moddNombre() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el numero del Juego que quieres modificar:");
		System.out.print("ID: ");
		int numJuego = entrada.nextInt();
		
		if (juegoExiste(numJuego)) {
			System.out.println("\nIntroduce el NUEVO nombre del Juego que quieres modificar.");
			System.out.print("Nombre: ");
			entrada.nextLine();
			String newNombreJuego = entrada.nextLine();
			
			System.out.println("Datos antiguos:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
			for (VideojocObj videojocObj : juegos) {
				if (videojocObj.getNumJoc() == numJuego) {
					videojocObj.setNomJoc(newNombreJuego);
				}
			}
			System.out.println("Datos modificados:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
		} else {
			System.err.println("ERROR: El juego no existe.");
		}
	}

	public static void moddPlataforma() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el numero del Juego que quieres modificar:");
		System.out.print("ID: ");
		int numJuego = entrada.nextInt();
		
		if (juegoExiste(numJuego)) {
			System.out.println("\nIntroduce la NUEVA plataforma del Juego que quieres modificar.");
			System.out.print("Plataforma: ");
			entrada.nextLine();
			String newPlataformaJuego = entrada.nextLine();
			
			System.out.println("Datos antiguos:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
			for (VideojocObj videojocObj : juegos) {
				if (videojocObj.getNumJoc() == numJuego) {
					videojocObj.setPlataforma(newPlataformaJuego);
				}
			}
			System.out.println("Datos modificados:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
		} else {
			System.err.println("ERROR: El juego no existe.");
		}
	}

	public static void moddPrecio() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el numero del Juego que quieres modificar:");
		System.out.print("ID: ");
		int numJuego = entrada.nextInt();
		
		if (juegoExiste(numJuego)) {
			System.out.println("\nIntroduce la NUEVA plataforma del Juego que quieres modificar.");
			System.out.print("Precio: ");
			Double newPrecioJuego = entrada.nextDouble();
			
			System.out.println("Datos antiguos:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
			for (VideojocObj videojocObj : juegos) {
				if (videojocObj.getNumJoc() == numJuego) {
					videojocObj.setPreu(newPrecioJuego);
				}
			}
			System.out.println("Datos modificados:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
		} else {
			System.err.println("ERROR: El juego no existe.");
		}
	}

	public static void guardarDatos(File f) {
		f.delete();
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			
			for (VideojocObj videojocObj : juegos) {
				bw.write(videojocObj.getNumJoc() + "-" + videojocObj.getNomJoc() + "-" + videojocObj.getPlataforma() + "-" + videojocObj.getPreu() + "/");
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				bw.close();
			} catch (Exception e2) {
				System.err.println(e2);
			}
		}
		
		String[] split1 = null;
		try {
			Scanner reader = new Scanner(f);
			while (reader.hasNextLine()) {
				String linea = reader.nextLine();
				split1 = linea.split("/");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		System.out.println("\nDatos en el Archivo:");
		for (int i = 0; i < split1.length; i++) {
			System.out.println(split1[i]);
		}
		System.out.println("");
	}

	/*public static void eliminarJuego() {
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Introduce el numero del Juego que quieres eliminar:");
		System.out.print("ID: ");
		int numJuego = entrada.nextInt();
		
		if (juegoExiste(numJuego)) {
			System.out.println("Datos antiguos:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
			int pos = 0;
			for (VideojocObj videojocObj : juegos) {
				if (videojocObj.getNumJoc() == numJuego) {
					juegos.remove(pos);
				}
				pos++;
			}
			System.out.println("Datos modificados:");
			for (VideojocObj videojocObj : juegos) {
				System.out.println(videojocObj);
			}
		} else {
			System.err.println("ERROR: El juego no existe.");
		}
	}*/
}
