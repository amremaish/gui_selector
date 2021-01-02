package com.stdplatform.gui_selector.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Dialogs.SelectorDialog;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Handler.PaletteHandler;
import com.stdplatform.gui_selector.Model.Attribute;
import com.stdplatform.gui_selector.Model.ItemModel;
import com.stdplatform.gui_selector.component.XDialog;
import com.stdplatform.gui_selector.selector.Rectangle;
import com.stdplatform.gui_selector.selector.Shape;
import com.stdplatform.gui_selector.util.Chooser;

public class ioManager {

	private MainHandler mainHandler;
	private DataModel dataModel;

	public ioManager(MainHandler mainHandler) {
		this.mainHandler = mainHandler;
	}

	public void LoadDataModel() {
		try {
			if (!OpenFile()){
				return ;
			}
			addImageAndCommponents();
		} catch (Exception e) {
			new SelectorDialog(mainHandler.getMainDesign().getMainFrame(),
					"Error while reading file", XDialog.OK);
		}
	}

	private void addImageAndCommponents() throws Exception {
		// clear all first
		mainHandler.getNewActionClick(true);
		// add image
		if (dataModel.getImagePath() != null) {
			mainHandler.getDropImageAction(new File(dataModel.getImagePath()));
		}
		// add components to list
		PaletteHandler handler = new PaletteHandler(null, mainHandler);
		int lastAttListIdx = 0 ;
		for (int i = 0; i < dataModel.getCompNameList().size(); i++) {
			ArrayList<Double> LPWidthList = dataModel.getLPWidthList();
			ArrayList<Double> LPHeightList = dataModel.getLPHeightList();
			ArrayList<Double> LPXList = dataModel.getLPXList();
			ArrayList<Double> LPYList = dataModel.getLPYList();
			ArrayList<String> CompNameList = dataModel.getCompNameList();
			ArrayList<Attribute> attList = dataModel.getAttList();

			Rectangle  thisShape = 	handler.CreateShape(CompNameList.get(i));
			thisShape.setLastPWidth(LPWidthList.get(i));
			thisShape.setLastPHeight(LPHeightList.get(i));
			thisShape.setLastPX(LPXList.get(i));
			thisShape.setLastPY(LPYList.get(i));
			ArrayList<Attribute> CurAttList  = thisShape.getItemModel().getAttributes();

			for (int j = 0 ; j < CurAttList.size() ; j ++ ,lastAttListIdx++){
				String content = attList.get(lastAttListIdx).getContent();
				CurAttList.get(j).getRefTextField().setText(content);
			}

			// update X and Y
			double x = thisShape.getLoadedImage().getCalcX()
					+ (LPXList.get(i) * thisShape.getLoadedImage()
							.getCalcWidth());
			double y = thisShape.getLoadedImage().getCalcY()
					+ (LPYList.get(i) * thisShape.getLoadedImage()
							.getCalcHeight());
			thisShape.setLocation((int) Math.ceil(x), (int) Math.ceil(y));

			// Update Size
			double width = (LPWidthList.get(i) * thisShape.getLoadedImage()
					.getCalcWidth());
			double height = (LPHeightList.get(i) * thisShape.getLoadedImage()
					.getCalcHeight());
			thisShape.setSize((int) Math.ceil(width), (int) Math.ceil(height));

		}
		mainHandler.getMainDesign().getImagePanel().repaint();

	}

	private boolean OpenFile() {
		File openedFile = Chooser.OpenChooser(Chooser.GSEL);
		if (openedFile == null) {
			return false;
		}
		Scanner scn = null;
		try {
			scn = new Scanner(openedFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/*
		 * structure -> ImagePath Size WidthList HeightList XList YList
		 * LPWidthList LPHeightList LPXList LPYList nameList CompNameList
		 * PinIdList
		 */

		dataModel = new DataModel();
		// Image Path
		dataModel.setImagePath(scn.next());
		// Size
		int size = scn.nextInt();
		// LastPwidth
		for (int i = 0; i < size; i++) {
			dataModel.getLPWidthList().add(scn.nextDouble());
		}
		// LastPHeight
		for (int i = 0; i < size; i++) {
			dataModel.getLPHeightList().add(scn.nextDouble());
		}

		// LastPX
		for (int i = 0; i < size; i++) {
			dataModel.getLPXList().add(scn.nextDouble());
		}

		// LastPY
		for (int i = 0; i < size; i++) {
			dataModel.getLPYList().add(scn.nextDouble());
		}

		for (int i = 0; i < size; i++) {
			dataModel.getCompNameList().add(scn.next());
			int attSize = scn.nextInt();
			for (int j = 0; j < attSize; j++) {
				Attribute att = new Attribute();
				att.setContent(scn.next());
				dataModel.getAttList().add(att);
			}
		}
		scn.close();
		return true ;
	}

	// ------------------------------------------------------------ save
	// Operation
	public void SaveDataModel() {
		// output file
		String Path = Chooser.SaveChooser();
		if (Path == null) {
			return;
		}
		PrintFile(Path);
	}

	private void PrintFile(String Path) {
		if (!Path.endsWith(".gsel")) {
			Path += ".gsel";
		}
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(Path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		/*
		 * structure -> ImagePath Size WidthList HeightList XList YList nameList
		 * CompNameList PinIdList
		 */

		ArrayList<Shape> ShapeList = mainHandler.getShapeList();
		// image Path
		pw.println(mainHandler.getLoadedImagePath());
		// Size
		pw.println(ShapeList.size());
		// LPWidth
		for (int i = 0; i < ShapeList.size(); i++) {
			pw.print(ShapeList.get(i).getLastPWidth() + " ");
		}
		pw.println();

		// LPHeight
		for (int i = 0; i < ShapeList.size(); i++) {
			pw.print(ShapeList.get(i).getLastPHeight() + " ");
		}
		pw.println();

		// LPX
		for (int i = 0; i < ShapeList.size(); i++) {
			pw.print(ShapeList.get(i).getLastPX() + " ");
		}
		pw.println();

		// LPY
		for (int i = 0; i < ShapeList.size(); i++) {
			pw.print(ShapeList.get(i).getLastPY() + " ");
		}
		pw.println();


		for (int i = 0; i < ShapeList.size(); i++) {
			ItemModel thisModel  = ShapeList.get(i).getItemModel();
			pw.println(thisModel.getCompName() + " " + thisModel.getAttributes().size());
			for (int j = 0; j < thisModel.getAttributes().size(); j++) {
				Attribute att  = thisModel.getAttributes().get(j);
				String x = att.getContent();
				pw.print(att.getRefTextField().getText() + " ");
			}
			pw.println();
		}


		pw.println();
		pw.print("// Print Order -> ImagePath // Size of list ");
		pw.println("// LastPWidthList // LastPHeightList ");
		pw.println("// LastPXList // LastPYList  // comName -  (Attributes number) <-- Attributes ");

		pw.close();


	}

}
