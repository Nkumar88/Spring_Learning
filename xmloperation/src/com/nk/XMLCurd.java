package com.nk;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLCurd {

	public Map<String, String> getCityList() {
		Map<String, String> cityMap = new HashMap<>();
		try {
			File file = new File("customer.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);

			doc.normalize();

			NodeList cityList = doc.getElementsByTagName("city");
			for (int i = 0; i < cityList.getLength(); i++) {
				Node node = cityList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					cityMap.put(element.getElementsByTagName("id").item(0).getTextContent(),
							element.getElementsByTagName("name").item(0).getTextContent());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return cityMap;
	}

	public void formatCustomerFile() {
		try {
			Path path = Path.of("customer.xml");
			String text = Files.readString(path);
			String newLine = System.getProperty("line.separator");
			
			String newText = text.replaceAll("\n", "").replaceAll("\r", "").replaceAll("\t", "");
			newText.trim().strip();
			boolean hasNewLine = newText.contains(newLine);
			System.out.println("text = "+newText);
			System.out.println("Newlines?"+hasNewLine);

			FileWriter writer = new FileWriter("customer.xml");
			writer.write(newText);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Customer> getAllCustomer() {
		List<Customer> custList = new ArrayList<>();
		try {
			File file = new File("customer.xml");

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.normalize();

			NodeList customers = doc.getElementsByTagName("customer");
			for (int i = 0; i < customers.getLength(); i++) {
				Node node = customers.item(i);
				Customer cust = new Customer();
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					cust.setId(element.getElementsByTagName("cid").item(0).getTextContent());
					cust.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
					cust.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
					cust.setCity(getCityList().get(element.getElementsByTagName("cityId").item(0).getTextContent()));

					custList.add(cust);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custList;
	}

	public Customer getCustomerbyId(String id) {
		Customer cust = new Customer();
		try {
			File file = new File("customer.xml");
			String cityId;

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.normalize();

			NodeList customerList = doc.getElementsByTagName("customer");

			for (int i = 0; i < customerList.getLength(); i++) {
				Node node = customerList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					if (element.getElementsByTagName("cid").item(0).getTextContent().equals(id)) {
						cust.setId(id);
						cust.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
						cust.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
						cityId = element.getElementsByTagName("cityId").item(0).getTextContent();
						cust.setCity(getCityList().get(cityId));

						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return cust;
	}

	public List<Customer> getCustomerByFirstName(String firstName) {
		List<Customer> custList = new ArrayList<>();

		try {
			File file = new File("customer.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);

			doc.normalize();
			NodeList customerNodeList = doc.getElementsByTagName("customer");

			for (int i = 0; i < customerNodeList.getLength(); i++) {
				Node node = customerNodeList.item(i);
				Customer cust = new Customer();
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					if (element.getElementsByTagName("firstName").item(0).getTextContent().equals(firstName)) {
						cust.setId(element.getElementsByTagName("cid").item(0).getTextContent());
						cust.setFirstName(firstName);
						cust.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
						cust.setCity(
								getCityList().get(element.getElementsByTagName("cityId").item(0).getTextContent()));

						custList.add(cust);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return custList;
	}

	public void createCustomer(Customer cust) {
		try {
			formatCustomerFile();
			File file = new File("customer.xml");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);
			doc.normalize();
			Element root = doc.getDocumentElement();
			String auto_incremented_id = root.getAttribute("auto_incremented_id");
			root.setAttribute("auto_incremented_id", String.valueOf(Integer.parseInt(auto_incremented_id) + 1));

			Element customerElement = doc.createElement("customer");
			Element cidElement = doc.createElement("cid");
			Element firstNameElement = doc.createElement("firstName");
			Element lastNameElement = doc.createElement("lastName");
			Element cityIdElement = doc.createElement("cityId");

			cidElement.setTextContent(auto_incremented_id);
			firstNameElement.setTextContent(cust.getFirstName());
			lastNameElement.setTextContent(cust.getLastName());
			cityIdElement.setTextContent(cust.getCity());

			customerElement.appendChild(cidElement);
			customerElement.appendChild(firstNameElement);
			customerElement.appendChild(lastNameElement);
			customerElement.appendChild(cityIdElement);

			root.insertBefore(customerElement, doc.getElementsByTagName("cities").item(0));

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			transformerFactory.setAttribute("indent-number", 0);
			Transformer tf = transformerFactory.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.setOutputProperty(OutputKeys.METHOD, "xml");

			DOMSource ds = new DOMSource(doc);
			StreamResult sr = new StreamResult("customer.xml");

			tf.transform(ds, sr);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCustomer(String id, String firstName, String lastName, String cityId)
	{
		
	}
	
	public void deleteCustomer(String id)
	{
		
	}
}
