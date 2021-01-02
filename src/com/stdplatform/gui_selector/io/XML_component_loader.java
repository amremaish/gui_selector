package com.stdplatform.gui_selector.io;

import com.stdplatform.gui_selector.Constant.Path.Path;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.stdplatform.gui_selector.Dialogs.SelectorDialog;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Model.Attribute;
import com.stdplatform.gui_selector.Model.ItemModel;
import com.stdplatform.gui_selector.component.XDialog;

public class XML_component_loader {

	private ArrayList<ItemModel> ItemModelList;
	private HashMap<Integer, ItemModel> ItemModelIdMap;
	private HashMap<String, ItemModel> ItemModelCompNameMap;

	public XML_component_loader(MainHandler mainHandler) {
		try {
			ItemModelCompNameMap = new HashMap<String, ItemModel>();
			ItemModelList = new ArrayList<ItemModel>();
			ItemModelIdMap = new HashMap<Integer, ItemModel>();
			Document doc = loadXMLFromString(TempReadStringXML());
			buildModel(doc);
		} catch (Exception e) {
                    e.printStackTrace();
			new SelectorDialog(
					mainHandler.getMainDesign().getMainFrame(),
					"XML file Error , maybe dublicate items or space exist." ,
					XDialog.OK);
			mainHandler.getMainDesign().close(null);
		}

	}

	private void buildModel(Document doc) throws Exception {
		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName("item");
		for (int i = 0; i < nList.getLength(); i++) {
			ItemModel newModel = new ItemModel();
			ItemModelIdMap.put(newModel.getId(), newModel);
			Node iNode = nList.item(i);
			if (iNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) iNode;
				newModel.setSource(e.getAttribute("source"));
				newModel.setCompName(e.getAttribute("compName"));
				newModel.setVBenchName(e.getAttribute("VBenchName"));
				if (ItemModelCompNameMap.get(newModel.getCompName()) != null
						|| HasSpace(newModel.getCompName())) {
					throw new Exception();
				}
				ItemModelCompNameMap.put(newModel.getCompName(), newModel);

			}
			NodeList childNodes = iNode.getChildNodes();
			for (int j = 0; j < childNodes.getLength(); j++) {
				Node tempNode = childNodes.item(j);
				// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();
					Attribute att = new Attribute();
					String temp;
					att.setAttributeName(tempNode.getNodeName());
					temp = nodeMap.getNamedItem("type").getTextContent();
					att.setType(temp);
					temp = nodeMap.getNamedItem("VBenchName").getTextContent();
					att.setVBenchName(temp);
					newModel.addAttribute(att);

				}
			}
			ItemModelList.add(newModel);
		}

		// print();

	}

	private void print() {
		System.out.println("----------------------");
		for (int i = 0; i < ItemModelList.size(); i++) {
			ItemModel thisModel = ItemModelList.get(i);
			System.out.println(thisModel.getCompName() + " "
					+ thisModel.getSource() + " " + thisModel.getId());
			for (int j = 0; j < thisModel.getAttributes().size(); j++) {
				Attribute att = thisModel.getAttribute(j);
				System.out.println("     " + att.getAttributeName() + " "
						+ att.getType() + " " + att.getVBenchName());
			}
		}

	}

	private String readStringXML() throws IOException {
		String XML = "";
		InputStream inputStream = XML_component_loader.class.getClassLoader()
				.getResourceAsStream("components.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(
				inputStream));

		String line;
		while ((line = br.readLine()) != null) {
			XML += line;
		}
		return XML;
	}

	private String TempReadStringXML() throws IOException {
		String XML = "";
		Scanner br = new Scanner(new File(Path.COMPONENTS));
		while (br.hasNext()) {
			XML += br.nextLine();
		}
		return XML;
	}

	private Document loadXMLFromString(String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		return (Document) builder.parse(is);
	}

	public ArrayList<ItemModel> getItemModelList() {
		return ItemModelList;
	}

	public void setItemModelList(ArrayList<ItemModel> itemModelList) {
		ItemModelList = itemModelList;
	}

	public HashMap<Integer, ItemModel> getItemModelIdMap() {
		return ItemModelIdMap;
	}

	public HashMap<String, ItemModel> getItemModelCompNameMap() {
		return ItemModelCompNameMap;
	}

	private boolean HasSpace(String compName) {
		for (int i = 0; i < compName.length(); i++) {
			if (compName.charAt(i) == ' ') {
				return true;
			}
		}
		return false;
	}

}
