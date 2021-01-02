package com.stdplatform.gui_selector.component;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import com.stdplatform.gui_selector.Constant.Color.FixedColor;
import com.stdplatform.gui_selector.selector.Shape;

public class XTree extends JTree {

	private String SelectedItem;
	private DefaultMutableTreeNode dmt;
	private ArrayList<Shape> shapeList;

	public XTree() {
		initialize();
		Action();

	}

	private void initialize() {
		setColorConfig();
		dmt = new DefaultMutableTreeNode("Items");
		setModel(new DefaultTreeModel(dmt));
	}

	private void Action() {
		addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) getLastSelectedPathComponent();
				if (selectedNode == null
						|| !selectedNode.toString().contains("-")) {
					SelectedItem = null;
					return;
				}
				SelectedItem = selectedNode.toString();
				if (shapeList == null || shapeList.size() == 0) {
					return;
				}
				int selctedId = Integer.parseInt(SelectedItem.split("-")[1]);
				for (int i = 0; i < shapeList.size(); i++) {
					if (selctedId == shapeList.get(i).getItemModel().getId()) {
						shapeList.get(i).ShapePressAction();
						return;
					}
				}

			}
		});

	}

	public void ActivateSelectItemPalette(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;

	}

	private void setColorConfig() {
		if (getCellRenderer() instanceof DefaultTreeCellRenderer) {
			final DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) (getCellRenderer());
			renderer.setBackgroundNonSelectionColor(FixedColor.WINDOWS);
			renderer.setBackgroundSelectionColor(FixedColor.SELECTED_ITEM_TREE);
			renderer.setTextNonSelectionColor(FixedColor.TEXT);
			renderer.setTextSelectionColor(Color.black);
		}
	}

	public String getSelectedItem() {
		return SelectedItem;
	}

	public void addNode(String s) {
		this.setModel(null);
		dmt.add(new DefaultMutableTreeNode(s));
		setModel(new DefaultTreeModel(dmt));
	}

	public ArrayList<String> getAllNodes() {

		ArrayList<String> nodeList = new ArrayList<String>();
		int size = getModel().getChildCount(getModel().getRoot());
		for (int i = 0; i < size; i++) {
			nodeList.add(getModel().getChild(getModel().getRoot(), i)
					.toString());

		}
		return nodeList;
	}

	public void clear() {
		this.setModel(null);
		dmt = new DefaultMutableTreeNode("Items");
		setModel(new DefaultTreeModel(dmt));
	}

	public void MoveUp(int id) {
		// name - id
		ArrayList<String> list = getAllNodes();
		clear();
		for (int i = 0; i < list.size(); i++) {
			if (i + 1 < list.size()) {
				int thisId = Integer.parseInt(list.get(i + 1).split("-")[1]);
				if (id == thisId) {
					Collections.swap(list, i, i + 1);
				}
			}
			addNode(list.get(i));
		}
	}

	public void MoveDown(int id) {
		ArrayList<String> list = getAllNodes();
		boolean once = false;
		clear();
		for (int i = 0; i < list.size(); i++) {
			int thisId = Integer.parseInt(list.get(i).split("-")[1]);
			if (i + 1 < list.size() && id == thisId && !once) {
				Collections.swap(list, i, i + 1);
				once = true;
			}
			addNode(list.get(i));
		}
	}

	public void deleteNode(int id) {
		ArrayList<String> list = getAllNodes();
		clear();
		for (int i = 0; i < list.size(); i++) {
			int thisId = Integer.parseInt(list.get(i).split("-")[1]);
			if (id == thisId) {
				continue;
			}
			addNode(list.get(i));
		}
	}
}
