package com.stdplatform.gui_selector.generate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import com.stdplatform.gui_selector.Constant.Color.FixedColor;
import com.stdplatform.gui_selector.Model.Attribute;
import com.stdplatform.gui_selector.Model.ItemModel;

public class AttributesGenerator {
	// vertical box
	// id , map
	private ArrayList<Attribute> attList;
	private ArrayList<Box> BoxList;

	public AttributesGenerator(ItemModel model) {
		BoxList = new ArrayList<Box>();
		attList = new ArrayList<Attribute>();
		ArrayList<Attribute> attModel = model.getAttributes();
		for (int j = 0; j < attModel.size(); j++) {
			Box verticalBox = null;
			if (attModel.get(j).getType().equalsIgnoreCase(Attribute.INTEGER)) {
				verticalBox = addBox(attModel.get(j).getAttributeName(),
						attModel.get(j), true);
			} else {
				verticalBox = addBox(attModel.get(j).getAttributeName(),
						attModel.get(j), false);
			}
			BoxList.add(verticalBox);
		}

	}

	private Box addBox(String AttributeName, Attribute thisAtt,
			boolean isInteger) {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setPreferredSize(new Dimension(170, 60));
		verticalBox.setMinimumSize(new Dimension(200, 0));
		verticalBox.setMaximumSize(new Dimension(200, 0));
		JLabel lblNewLabel_1 = new JLabel(AttributeName);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(FixedColor.TEXT);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		verticalBox.add(lblNewLabel_1);
		Component rigidArea = Box.createRigidArea(new Dimension(0, 20));
		rigidArea.setPreferredSize(new Dimension(7, 0));
		rigidArea.setMinimumSize(new Dimension(0, 0));
		rigidArea.setMaximumSize(new Dimension(0, 8));
		verticalBox.add(rigidArea);
		JTextField textField = new JTextField("");
		textField.setForeground(Color.WHITE);
		textField.setBackground(FixedColor.TEXT);
		textField.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
		textField.setMinimumSize(new Dimension(0, 0));
		textField.setMaximumSize(new Dimension(2147483647, 25));
		textField.setPreferredSize(new Dimension(0, 0));
		verticalBox.add(textField);
		if (isInteger) {
			ChangeValue(textField, true, thisAtt);
		} else {
			ChangeValue(textField, false, thisAtt);
		}
		// create new attribute
		Attribute newAtt = new Attribute();
		newAtt.setAttributeName(thisAtt.getAttributeName());
		newAtt.setType(thisAtt.getType());
		newAtt.setVBenchName(thisAtt.getVBenchName());
		newAtt.setRefTextField(textField);
		attList.add(newAtt);

		return verticalBox;

	}

	private void ChangeValue(final JTextField textField,
			final boolean IntegerValidation, final Attribute thisAtt) {
		textField.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String text = textField.getText();
				if (IntegerValidation) {
					String temp = "";
					for (int i = 0; i < text.length(); i++) {
						if (Character.isDigit(text.charAt(i))) {
							temp += text.charAt(i);
						}
					}
					text = temp;
				}
				textField.setText(text);
				thisAtt.setContent(text);
			}

			public void keyTyped(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}
		});
	}

	public ArrayList<Box> getBoxList() {
		return BoxList;
	}

	public void setBoxList(ArrayList<Box> boxList) {
		BoxList = boxList;
	}

	public ArrayList<Attribute> getAttList() {
		return attList;
	}

	public void setAttList(ArrayList<Attribute> attList) {
		this.attList = attList;
	}

}
