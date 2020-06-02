package Ej2;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Ej1.VideojocObj;

public class Videojocs {
	
	public static ArrayList<VideojocObj> juegos = new ArrayList<VideojocObj>();
	
	public static void main(String[] args) {
		File f = new File("..\\UF1_Examen_Recu\\src\\Videojocs.dat");
		File xml = new File("..\\UF1_Examen_Recu\\src\\Videojocs.xml");
		
		leerDat(f);
		
		crearXML(f, xml);
	}
	
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
	
	public static void crearXML(File f, File xml) {
		String[] split1 = null;
		
		// Guardar Personas en un Split
		try {
			Scanner reader = new Scanner(f);
			while (reader.hasNextLine()) {
				String linea = reader.nextLine();
				split1 = linea.split("/");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			
			Document documento = implementation.createDocument(null, "Videoconsolas", null);
			documento.setXmlVersion("1.0");
			
			boolean xbox = false;
			boolean play = false;
			Element juegostodosx = null;
			Element juegostodosp = null;
			Element x = null;
			Element p = null;
			
			for (int i = 0; i < split1.length; i++) {
				String[] split2 = split1[i].split("-");
				for (int j = 0; j < split2.length; j++) {
					if (split2[j].equalsIgnoreCase("XboxOne")) {
						xbox = true;
					}
					if (split2[j].equalsIgnoreCase("PlayStation4")) {
						play = true;
					}
				}
			}
			
			if (xbox == true) {
				x = documento.createElement("XboxOne");
				juegostodosx = documento.createElement("Jocs");
				x.appendChild(juegostodosx);
			}
			
			if (play == true) {
				p = documento.createElement("PlayStation4");
				juegostodosp = documento.createElement("Jocs");
				p.appendChild(juegostodosp);
			}
			
			for (VideojocObj videojuego : juegos) {
				Element juego = documento.createElement("Joc");
				if (videojuego.getPlataforma().equalsIgnoreCase("XboxOne")) {
					// Atributo del juego
					Attr atrJ = documento.createAttribute("id");
					String atr = String.valueOf(videojuego.getNumJoc());
					atrJ.setValue(atr);
					juego.setAttributeNode(atrJ);
					
					// Nombre del juego
					Element nombre = documento.createElement("nombre");
					nombre.setTextContent(videojuego.getNomJoc());
					juego.appendChild(nombre);
					
					// Precio del juego
					Element precio = documento.createElement("preu");
					String pre = String.valueOf(videojuego.getPreu());
					precio.setTextContent(pre);
					juegostodosx.appendChild(juego);
				}
				if (videojuego.getPlataforma().equalsIgnoreCase("PlayStation4")) {
					// Atributo del juego
					Attr atrJ = documento.createAttribute("id");
					String atr = String.valueOf(videojuego.getNumJoc());
					atrJ.setValue(atr);
					juego.setAttributeNode(atrJ);
					
					// Nombre del juego
					Element nombre = documento.createElement("nombre");
					nombre.setTextContent(videojuego.getNomJoc());
					juego.appendChild(nombre);
					
					// Precio del juego
					Element precio = documento.createElement("preu");
					String pre = String.valueOf(videojuego.getPreu());
					precio.setTextContent(pre);
					juegostodosp.appendChild(juego);
				}
				
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(documento);
			StreamResult result = new StreamResult(xml);

			transformer.transform(source, result);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
}
