package xmlmanipulator;

import java.io.File;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class for reading from a specified XML file.
 */
public class XMLReader {
	
	/**
	 * Logger of the class.
	 */
	private static Logger log = LoggerFactory.getLogger(XMLReader.class);
	
	/**
	 * List of the XML file's elements.
	 */
	private ArrayList< XmlStructure > backList = new ArrayList<XmlStructure>();

	/**
	 * Name of the XML file's.
	 */
	private String from;
	
	/**
	 * Constructor for creating an {@code XMLReader} object.
	 *
	 * @param from the name of the XML file's
	 */
	public XMLReader(String from){
		this.from=from;
		log.info("XMLReader object created.");
	}
	
	/**
	 * Returns an ArrayList containing the {@code XmlStructure} objects it's read from the XML file.
	 * 
	 * @return an ArrayList containing the {@code XmlStructure} objects it's read from the XML file
	 */
	public ArrayList< XmlStructure > read(){
		XmlStructure xml = null;
		try{
			File xmlFile;
			if(from.matches("highscores.xml")){
				log.info("Reading from " + from + " file.");
				String home=System.getProperty("user.home");
				File prop = new File(home, ".roulettegame");
				xmlFile= new File(prop,from);
			}
			else{
				URL url = XMLReader.class.getClassLoader().getResource("testscores.xml");
				xmlFile = new File(url.getPath());
			}
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			
			Document doc = dBuilder.parse(xmlFile);
			
			doc.getDocumentElement().normalize();
	
			
			NodeList nodes = doc.getElementsByTagName("highscore");
			
			for(int i=0; i< nodes.getLength();i++){
				Node node = nodes.item(i);
				
				if(node.getNodeType()==Node.ELEMENT_NODE){
					Element element = (Element) node;
					String str = element.getElementsByTagName("name").item(0).getTextContent();
					String tmp = element.getElementsByTagName("money").item(0).getTextContent();
					int m = Integer.parseInt(tmp);
					xml = new XmlStructure(str,m);

				}
				backList.add(xml);
			}
			log.info("Reading successful.");
		}
		catch(ParserConfigurationException e){
			log.error("ParserConfigurationException caught:");
			log.error(e.toString());
		}
		catch(IOException e){
			log.error("IOException caught:");
			log.error(e.toString());
		}
		catch(SAXException e){
			log.error("SAXException caught:");
			log.error(e.toString());
		}
		return backList;
	}
}
