package xmlmanipulator;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Class for writing to a specified XML file.
 */
public class XMLWriter{
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(XMLWriter.class);

	
	/**
	 * List of {@code XmlStructure} objects.
	 */
	ArrayList< XmlStructure > list;
	
	/**
	 * Name of the XML file's.
	 */
	String to;
	
	/**
	 * Constructor for creating an {@code XMLWriter} object.
	 *
	 * @param list the list of {@code XmlStructure} objects.
	 * @param to the name of the XML file's
	 */
	public XMLWriter(ArrayList< XmlStructure > list, String to){
		this.list = list;
		this.to = to;
		log.info("XMLWriter object created.");
	}
	
	/**
	 * Writes the {@code list} content to a {@code to} named XML file.
	 * 
	 */
	public void write(){
		try {
			log.info("Writing to "+ to +" file"); 
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	 
			Document doc = docBuilder.newDocument();
			
			Element rootElement = doc.createElement("highscores");
			doc.appendChild(rootElement);
			
			int size = list.size()<=5 ? list.size() : 5;
			
			for(int i=0;i<size;i++){
			
				Element highscore = doc.createElement("highscore");
				rootElement.appendChild(highscore);
		 
				Element name = doc.createElement("name");
				name.appendChild(doc.createTextNode(list.get(i).getName()));
				highscore.appendChild(name);
				
				String tmp = list.get(i).getMoney() + "";
				Element money = doc.createElement("money");
				money.appendChild(doc.createTextNode(tmp));
				highscore.appendChild(money);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			String home=System.getProperty("user.home");
			File prop = new File(home, ".roulettegame");
			prop.mkdir();
			StreamResult result = new StreamResult(new File(prop,to));
	 
			transformer.transform(source, result);
			log.info("Writing successful");
			
		} 
		catch (ParserConfigurationException e) {
			log.error("ParserConfigurationException caught");
			log.error(e.toString());
		}
		catch(TransformerConfigurationException e){
			log.error("TransformerConfigurationException caught");
			log.error(e.toString());
		}
		catch(TransformerException e){
			log.error("TransformerException caught");
			log.error(e.toString());
		}
	}
}
