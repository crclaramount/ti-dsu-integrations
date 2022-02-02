//EL codigo para ver si un archivo xml esta correcto lo saque de esta fuente.
//https://davidmcasas.com/blog/leer-xml-java/

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * Lectura de XML a ciegas, es decir sin conocer su estructura,
 * pero mostrando el nombre de las etiquetas y atributos,
 * y con una indentación apropiada.
 *
 * @author David M. Casas (davidmcasas.com)
 * @version 2020-10-01
 */
public class LectorXML {

	/**
	 * Carga y parsea el XML, luego ejecuta el método para imprimir
	 * 
	 * @param args el archivo .xml a leer (incluir extensión)
	 */
	public static void main(String[] args) {

		if (args.length != 1)
			return; // salir si no tenemos un argumento

		new LectorXML().esXML(args[0]);
	}

	public boolean esXML(String stFile) {
		File file = new File(stFile);
		Document doc = null;

		try {

			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
			doc.getDocumentElement().normalize();
			return true;
		} catch (Exception e) {
			System.out.println("Error leyendo el archivo");
			return false; 
		}

		// de esta forma (la forma nativa) se imprime todo el contenido pero sin el
		// nombre de las etiquetas ni atributos:
		// System.out.println(doc.getDocumentElement().getTextContent());

		// de esta forma (la nueva) se imprime todo el contenido, con el nombre de
		// etiquetas y sus atributos:

//		printXML(doc.getDocumentElement());
	}

	/**
	 * Imprime por pantalla un documento XML mostrando nombres
	 * de etiquetas y atributos, e indentando. Usa el metodo recursivo
	 * printXMLnodes()
	 * 
	 * @param root Element raíz del documento XML
	 */
	private static void printXML(Element root) {

		printXMLnodes((Node) root, 0);
		System.out.println();
	}

	/**
	 * Metodo recurisvo que imprime nodos con sus atributos y contenidos.
	 * La recursión solo ocurre cuando el nodo tiene hijos de tipo ELEMENT_NODE
	 * 
	 * @param node   nodo a imprimir
	 * @param indent sangría que le corresponde a este nodo
	 */
	private static void printXMLnodes(Node node, int indent) {

		System.out.println(); // salto de linea donde empezamos a escribir el nuevo nodo
		for (int i = 0; i < indent; i++)
			System.out.print("\t"); // aplicamos sangría correspondiente

		System.out.print(node.getNodeName()); // imprimimos nombre del nodo

		// si el nodo tiene atributos, los imprimimos
		if (node.hasAttributes()) {
			NamedNodeMap nnm = node.getAttributes();
			for (int i = 0; i < nnm.getLength(); i++) {
				System.out.print(" (" + nnm.item(i).getNodeName() + ": " + nnm.item(i).getNodeValue() + ")");
			}
		}

		System.out.print(": "); // parar separar el nombre del nodo de su contenido

		// por cada hijo de este nodo...
		for (int i = 0; i < node.getChildNodes().getLength(); i++) {

			// aqui filtramos solo los hijos directos, los hijos de hijos seran considerados
			// por recursividad
			if (node.getChildNodes().item(i).getParentNode().isSameNode(node)) {

				// si es de tipo elemento...
				if (node.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {

					// llamada recursiva con dicho hijo, y ampliamos sangria
					printXMLnodes(node.getChildNodes().item(i), indent + 1);

					// o si es de tipo texto...
				} else if (node.getChildNodes().item(i).getNodeType() == Node.TEXT_NODE) {

					// almacenamos su valor en un string por comodidad
					String text = node.getChildNodes().item(i).getNodeValue();

					// El parser considera como TEXT_NODE los saltos de linea y tabulaciones
					// internas
					// que pueda haber en el XML original, asi que aqui filtramos para solo tener en
					// cuenta
					// los strings que realmente tengan caracteres que no sean ni espacios ni '\t',
					// '\n', '\r'
					if (text.trim().length() > 0) {
						System.out.print(text); // imprimimos el contenido
					}
				}
			}
		}

	}

}