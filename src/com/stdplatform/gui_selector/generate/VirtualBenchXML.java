package com.stdplatform.gui_selector.generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Constant.XML.XML_GEN;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Model.Attribute;
import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.selector.Shape;

public class VirtualBenchXML {

	/*
	 * GridWidth ,GridHeight in Virtual Bench
	 */
	private int GridWidth, GridHeight;

	private final String KEY_1 = "Grid";
	private final String KEY_2 = "Width";
	private String LoadedImagePath;
	private  ArrayList<Shape>  ShapesList;
	private String XMLComponents = "";
	private String VirtualBenchXML = "";

	public VirtualBenchXML( ArrayList<Shape> ShapesList,
			String LoadedImagePath) {
		this.LoadedImagePath = LoadedImagePath;
		this.ShapesList = ShapesList;
		initialize();

	}

	private void initialize() {
		CreateXMLForComponent();
		try {
			MergeOutput();
                        outputVirtualBenchXML(Path.XML_OUTPUT);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void CreateXMLForComponent() {
		boolean setImage = false;

			for (int i = 0; i < ShapesList.size(); i++) {
				// variables
				Shape thisShape = ShapesList.get(i);
				ImageLabelResized image = thisShape.getLoadedImage();
				// -----------------------------------------------------

				// calculate Bounds
				int imageWidth = image.getIcon().getIconWidth();
				int imageHeight = image.getIcon().getIconHeight();
				// update X and Y
				double x = thisShape.getLoadedImage().getCalcX()
						+ (thisShape.getLastPX() * imageWidth);
				double y = thisShape.getLoadedImage().getCalcY()
						+ (thisShape.getLastPY() * imageHeight);
				// Update Size
				double width = (thisShape.getLastPWidth() * imageWidth);
				double height = (thisShape.getLastPHeight() * imageHeight);
				// -------------------------------------------------------
				// generate XML
				if (!setImage) {
					GridWidth = imageWidth;
					GridHeight = imageHeight;
					XML xml = new XML();
					xml.CreateTag("Image");
					xml.addAttribute("Width", image.getIcon().getIconWidth()
							+ "");
					xml.addAttribute("Height", image.getIcon().getIconHeight()
							+ "");
					xml.addAttribute("HorizontalAlignment", "left");
					xml.addAttribute("VerticalAlignment", "top");
					xml.addAttribute("Stretch", "None");
					xml.addAttribute("Margin", "0,0,0,0");
					xml.addAttribute("Source", this.LoadedImagePath);
					XMLComponents += "\t\t" + xml.getTag() + "\n";
					setImage = true;
				}
				if (!thisShape.getItemModel().getVBenchName().equalsIgnoreCase("NULL")) {
					XML xml = new XML();
					xml.CreateTag(thisShape.getItemModel().getVBenchName());
					xml.addAttribute("Width", (int) width + "");
					xml.addAttribute("Height", (int) height + "");
					xml.addAttribute("Margin", (int) (x - 66) + "," + (int) y
							+ ",0,0");
					xml.addAttribute("HorizontalAlignment", "left");
					xml.addAttribute("VerticalAlignment", "top");
					// Add attributes
					ArrayList<Attribute> attList = thisShape.getItemModel()
							.getAttributes();
					for (int j = 0; j < attList.size(); j++) {
						if (!attList.get(j).getVBenchName().equalsIgnoreCase("NULL")) {
							xml.addAttribute(attList.get(j).getVBenchName(),
									attList.get(j).getRefTextField().getText());
						}
					}
				XMLComponents += "\t\t" + xml.getTag() + "\n";
				}
			}
	}

	private void MergeOutput() throws FileNotFoundException {
		Scanner beforeXml = new Scanner(XML_GEN.BEFORE_COMP);
		Scanner afterXml = new Scanner(XML_GEN.AFTER_COMP);

		while (beforeXml.hasNext()) {
			String temp = beforeXml.nextLine();
			if (temp.contains(KEY_1) && temp.contains(KEY_2)) {
				VirtualBenchXML += "\t<Grid Width=\"" + GridWidth
						+ "\" Height=\"" + GridHeight + "\" >\n";
			} else {
				VirtualBenchXML += temp + "\n";
			}
		}
		// add comp
		VirtualBenchXML += XMLComponents;
		while (afterXml.hasNext()) {
			VirtualBenchXML += afterXml.nextLine() + "\n";
		}

		beforeXml.close();
		afterXml.close();

	}

	public void outputVirtualBenchXML(String path) {
		PrintWriter XMLfile;
		try {
			File file = new File(path);
			if (file.getParentFile() != null && !file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			XMLfile = new PrintWriter(file);
			XMLfile.print(VirtualBenchXML);
			XMLfile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
