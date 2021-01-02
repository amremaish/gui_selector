package com.stdplatform.gui_selector.Frames;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;


import com.stdplatform.gui_selector.Constant.Color.FixedColor;
import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.component.XFrame;
import com.stdplatform.gui_selector.component.XLabel;
import com.stdplatform.gui_selector.component.XPanel;
import com.stdplatform.gui_selector.component.XTree;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;

import com.stdplatform.gui_selector.util.UIColor;
import com.stdplatform.gui_selector.util.util;

import java.awt.Font;

import javax.swing.JScrollPane;

import javax.swing.ScrollPaneConstants;

import java.awt.Rectangle;

import javax.swing.SwingConstants;

import java.awt.Cursor;

import java.awt.Point;

public class Main {

	private XFrame MainFrame;
	private XLabel MinLabel, maxLabel, ExitLabel;
	private XPanel MainPanel, content;
	private XPanel WindowTool;
	private XLabel ProjectNameLbl;
	private XPanel TitleBar;
	private ImageLabelResized Image;
	private XPanel TopPanel;
	private XPanel MenuPanel;
	private Box horizontalBox;
	private XLabel New;
	private XLabel lblNewLabel;
	private XLabel Load;
	private XLabel Save;
	private XLabel MoveUp, MoveDown, Remove;
	private JScrollPane scrollPane;
	private XTree ItemTree;
	private XPanel panel;
	private XLabel DropImage;
	private XPanel ImagePanel;
	private XLabel PaletteIcon;
	private XPanel PalettePanel;
	private JLabel CompLblName;
	private JPanel panel_3;
	private Component rigidArea_4;
	private Component rigidArea_7;
	private Component rigidArea_8;
	private JLabel lblProperties;
	private JPanel panel_5;
	private XLabel Finish;
	private JPanel panel_7;
	private JPanel panel_8;
	private JPanel panel_9;
	private XLabel Cancel;
	private JPanel AttributesPanel;
	private MainHandler mainHandler;
	private JLabel lblNewLabel_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Main window = new Main();
					if (window.MainFrame != null) {
						window.MainFrame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 *
	 * @param thisClass
	 */

	public Main() {

		// Load UI Mangers
		new UIColor();
		// initialize GUI
		initialize();
		// initialize image color
		initializeColors();
		// Handler
		mainHandler = new MainHandler(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		MainFrame = new XFrame();
		MainFrame.setBounds(100, 100, 878, 494);
		MainFrame.getContentPane().setLayout(new BorderLayout(0, 0));

		MainPanel = new XPanel();
		MainPanel.setBorder(new LineBorder(Color.black, 1));
		MainPanel.setBackground(FixedColor.BACKGROUND);
		MainFrame.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));

		content = new XPanel();
		content.setBackground(FixedColor.BACKGROUND);
		content.setPreferredSize(new Dimension(200, 200));
		MainPanel.add(content, BorderLayout.EAST);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(new Rectangle(0, 0, 50, 0));
		scrollPane.setSize(new Dimension(50, 0));
		scrollPane.setMinimumSize(new Dimension(50, 23));
		scrollPane.setMaximumSize(new Dimension(50, 32767));
		scrollPane.setPreferredSize(new Dimension(50, 2));
		scrollPane.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0,
				0, 0)));

		AttributesPanel = new JPanel();
		FlowLayout fl_AttributesPanel = (FlowLayout) AttributesPanel
				.getLayout();
		fl_AttributesPanel.setAlignment(FlowLayout.LEFT);
		AttributesPanel.setPreferredSize(new Dimension(150, 100));
		AttributesPanel.setMinimumSize(new Dimension(150, 100));
		JScrollPane _ScrollPane = new JScrollPane(AttributesPanel,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		_ScrollPane.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0,
				0, 0)));
		_ScrollPane.setMinimumSize(new Dimension(10, 10));
		_ScrollPane.setMaximumSize(new Dimension(50, 50));
		_ScrollPane.setPreferredSize(new Dimension(200, 50));

		AttributesPanel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) null));
		AttributesPanel.setBackground(FixedColor.WINDOWS);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 0,
				(Color) new Color(0, 0, 0)));
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(FixedColor.WINDOWS);
		panel_4.setAutoscrolls(true);
		panel_4.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		GroupLayout gl_content = new GroupLayout(content);
		gl_content.setHorizontalGroup(gl_content
				.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200,
						Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 200,
						Short.MAX_VALUE)
				.addComponent(_ScrollPane, Alignment.TRAILING,
						GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE));
		gl_content.setVerticalGroup(gl_content.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_content
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(_ScrollPane, GroupLayout.DEFAULT_SIZE,
								177, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE,
								172, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 38,
								GroupLayout.PREFERRED_SIZE).addGap(6)));

		rigidArea_8 = Box.createRigidArea(new Dimension(20, 0));
		rigidArea_8.setPreferredSize(new Dimension(40, 0));
		rigidArea_8.setMinimumSize(new Dimension(5, 0));
		rigidArea_8.setMaximumSize(new Dimension(5, 0));
		panel_4.add(rigidArea_8);

		MoveUp = new XLabel("");
		MoveUp.setToolTipText("Move up");
		MoveUp.setIcon(new ImageIcon(Main.class.getResource(Path.MOVE_UP)));
		panel_4.add(MoveUp);

		rigidArea_4 = Box.createRigidArea(new Dimension(20, 0));
		rigidArea_4.setPreferredSize(new Dimension(7, 0));
		rigidArea_4.setMinimumSize(new Dimension(5, 0));
		rigidArea_4.setMaximumSize(new Dimension(5, 0));
		panel_4.add(rigidArea_4);

		MoveDown = new XLabel("");
		MoveDown.setToolTipText("Move down");
		MoveDown.setIcon(new ImageIcon(Main.class.getResource(Path.MOVE_DOWN)));
		panel_4.add(MoveDown);

		rigidArea_7 = Box.createRigidArea(new Dimension(20, 0));
		rigidArea_7.setPreferredSize(new Dimension(7, 0));
		rigidArea_7.setMinimumSize(new Dimension(5, 0));
		rigidArea_7.setMaximumSize(new Dimension(5, 0));
		panel_4.add(rigidArea_7);

		Remove = new XLabel("");
		Remove.setToolTipText("Remove");
		Remove.setIcon(new ImageIcon(Main.class.getResource(Path.REMOVE)));
		panel_4.add(Remove);

		panel_3 = new JPanel();
		panel_3.setLocation(new Point(10, 0));
		panel_3.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
		AttributesPanel.add(panel_3);
		panel_3.setPreferredSize(new Dimension(175, 45));
		panel_3.setAlignmentY(Component.TOP_ALIGNMENT);
		panel_3.setBackground(FixedColor.WINDOWS);
		panel_3.setLayout(new BorderLayout(0, 0));

		CompLblName = new JLabel("CompLblName");
		CompLblName.setRequestFocusEnabled(false);
		CompLblName.setVisible(false);
		CompLblName.setForeground(FixedColor.TEXT);
		CompLblName.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_3.add(CompLblName, BorderLayout.WEST);

		lblProperties = new JLabel("Properties");
		lblProperties
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblProperties.setPreferredSize(new Dimension(77, 14));
		lblProperties.setMinimumSize(new Dimension(75, 14));
		lblProperties.setMaximumSize(new Dimension(75, 14));
		lblProperties.setForeground(new Color(40, 55, 71));
		lblProperties.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_3.add(lblProperties, BorderLayout.NORTH);

		ItemTree = new XTree();
		scrollPane.setViewportView(ItemTree);

		ItemTree.setBackground(FixedColor.WINDOWS);

		content.setLayout(gl_content);

		TopPanel = new XPanel();
		MainPanel.add(TopPanel, BorderLayout.NORTH);
		TopPanel.setLayout(new BorderLayout(0, 0));

		TitleBar = new XPanel();
		TopPanel.add(TitleBar);

		TitleBar.setBackground(FixedColor.BACKGROUND);
		TitleBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		TitleBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		TitleBar.setLayout(new BorderLayout(0, 0));

		XPanel NamePanel = new XPanel();
		NamePanel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NamePanel.setBackground(FixedColor.BACKGROUND);
		TitleBar.add(NamePanel, BorderLayout.WEST);

		ProjectNameLbl = new XLabel("ISC Project");
		ProjectNameLbl.setForeground(FixedColor.PROJECT_NAME);
		ProjectNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		NamePanel.add(ProjectNameLbl);

		WindowTool = new XPanel();
		WindowTool.setBackground(FixedColor.BACKGROUND);

		TitleBar.add(WindowTool, BorderLayout.EAST);
		WindowTool.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

		MinLabel = new XLabel();
		WindowTool.add(MinLabel);

		MinLabel.setIcon(new ImageIcon(Main.class.getResource(Path.MIN_WINDOW)));

		maxLabel = new XLabel("");
		WindowTool.add(maxLabel);

		maxLabel.setIcon(new ImageIcon(Main.class.getResource(Path.MAX_WINDOW)));

		ExitLabel = new XLabel("");
		WindowTool.add(ExitLabel);

		ExitLabel.setIcon(new ImageIcon(Main.class
				.getResource(Path.EXIT_WINDOW)));

		MenuPanel = new XPanel();
		MenuPanel.setBackground(FixedColor.MENU);
		TopPanel.add(MenuPanel, BorderLayout.SOUTH);
		MenuPanel.setLayout(new BorderLayout(0, 0));

		horizontalBox = Box.createHorizontalBox();
		horizontalBox.setPreferredSize(new Dimension(45, 45));

		horizontalBox
				.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		MenuPanel.add(horizontalBox, BorderLayout.CENTER);

		lblNewLabel = new XLabel("");
		lblNewLabel.setMaximumSize(new Dimension(100, 0));
		lblNewLabel.setMinimumSize(new Dimension(100, 0));
		lblNewLabel.setPreferredSize(new Dimension(100, 0));
		horizontalBox.add(lblNewLabel);

		New = new XLabel("");
		New.setVerticalTextPosition(SwingConstants.TOP);
		New.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		New.setHorizontalAlignment(SwingConstants.LEFT);
		New.setVerticalAlignment(SwingConstants.TOP);
		New.setToolTipText("new file");
		New.setIcon(new ImageIcon(
				Main.class
						.getResource("/com/stdplatform/gui_selector/res/new_file.png")));

		horizontalBox.add(New);
		horizontalBox.add(Box.createRigidArea(new Dimension(20, 0)));
		Load = new XLabel("");
		Load.setToolTipText("load file");
		Load.setIcon(new ImageIcon(Main.class.getResource(Path.LOAD_FILE)));
		horizontalBox.add(Load);
		horizontalBox.add(Box.createRigidArea(new Dimension(20, 0)));
		Save = new XLabel("");
		Save.setToolTipText("Import");
		Save.setIcon(new ImageIcon(Main.class.getResource(Path.SAVE)));
		horizontalBox.add(Save);

		lblNewLabel_2 = new JLabel("");
	
		lblNewLabel_2.setPreferredSize(new Dimension(70, 14));
		lblNewLabel_2.setSize(new Dimension(120, 0));
		MenuPanel.add(lblNewLabel_2, BorderLayout.EAST);

		panel = new XPanel();
		MainPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		ImagePanel = new XPanel();
		ImagePanel.setBorder(null);
		ImagePanel.setMinimumSize(new Dimension(32767, 32767));
		ImagePanel.setBackground(FixedColor.BACKGROUND);
		panel.add(ImagePanel, BorderLayout.CENTER);
		ImagePanel.setLayout(null);

		DropImage = new XLabel("");
		DropImage.setBounds(272, 117, 128, 128);
		ImagePanel.add(DropImage);
		DropImage
				.setIcon(new ImageIcon(Main.class.getResource(Path.DROP_IMAGE)));

		Image = new ImageLabelResized("");
		Image.setMinimumSize(new Dimension(100, 100));
		Image.setMaximumSize(new Dimension(9999, 9999));
		Image.setBounds(167, 152, 46, 14);
		ImagePanel.add(Image);

		PalettePanel = new XPanel();
		PalettePanel.setBackground(FixedColor.BACKGROUND);
		PalettePanel.setMinimumSize(new Dimension(120, 133));
		PalettePanel.setPreferredSize(new Dimension(130, 60));
		PalettePanel.setMaximumSize(new Dimension(120, 130));
		panel.add(PalettePanel, BorderLayout.SOUTH);
		PalettePanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		PalettePanel.add(panel_2, BorderLayout.WEST);

		panel_2.setBackground(FixedColor.BACKGROUND);
		PaletteIcon = new XLabel("");
		PaletteIcon
				.setIcon(new ImageIcon(Main.class.getResource(Path.PALETTE)));
		panel_2.add(PaletteIcon);

		panel_7 = new JPanel();
		panel_7.setBackground(FixedColor.BACKGROUND);
		PalettePanel.add(panel_7, BorderLayout.EAST);

		panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setSize(new Dimension(0, 30));
		panel_8.setPreferredSize(new Dimension(150, 55));
		panel_8.setMaximumSize(new Dimension(30, 30));
		panel_8.setBackground(new Color(38, 52, 59));
		panel_7.add(panel_8);

		panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setLocation(new Point(10, 15));
		panel_9.setBorder(null);
		panel_9.setBackground(new Color(65, 81, 90));
		panel_9.setBounds(15, 11, 130, 38);
		panel_8.add(panel_9);

		Cancel = new XLabel("Cancel");
		Cancel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Cancel.setHorizontalAlignment(SwingConstants.CENTER);
		Cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
		Cancel.setIcon(new ImageIcon(Main.class.getResource(Path.CANCEL)));
		Cancel.setPreferredSize(new Dimension(40, 14));
		Cancel.setMinimumSize(new Dimension(40, 14));
		Cancel.setMaximumSize(new Dimension(40, 14));
		Cancel.setForeground(Color.LIGHT_GRAY);
		Cancel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Cancel.setBounds(0, 0, 130, 38);
		panel_9.add(Cancel);

		panel_5 = new JPanel();
		panel_7.add(panel_5);
		panel_5.setMaximumSize(new Dimension(30, 30));
		panel_5.setSize(new Dimension(0, 30));
		panel_5.setBackground(FixedColor.BACKGROUND);
		panel_5.setPreferredSize(new Dimension(150, 55));
		panel_5.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setLocation(new Point(10, 15));
		panel_6.setBorder(null);
		panel_6.setBackground(HoverColor.EXIT_DROP_IMAGE);
		panel_6.setBounds(5, 11, 130, 38);
		panel_5.add(panel_6);
		panel_6.setLayout(null);

		Finish = new XLabel("Finish");
		Finish.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Finish.setHorizontalAlignment(SwingConstants.CENTER);
		Finish.setBounds(0, 0, 130, 38);
		panel_6.add(Finish);
		Finish.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Finish.setForeground(Color.LIGHT_GRAY);
		Finish.setMinimumSize(new Dimension(40, 14));
		Finish.setMaximumSize(new Dimension(40, 14));
		Finish.setPreferredSize(new Dimension(40, 14));
		Finish.setIcon(new ImageIcon(Main.class.getResource(Path.FINISH)));

	}

	private void initializeColors() {
		// title bar
		MinLabel.setIcon(new util().ChangeImageColor(Path.MIN_WINDOW,
				HoverColor.EXIT_COLOR));
		maxLabel.setIcon(new util().ChangeImageColor(Path.MAX_WINDOW,
				HoverColor.EXIT_COLOR));
		ExitLabel.setIcon(new util().ChangeImageColor(Path.EXIT_WINDOW,
				HoverColor.EXIT_COLOR));

		// Menus
		New.setIcon(new util().ChangeImageColor(Path.NEW_FILE,
				HoverColor.EXIT_COLOR_MENUS));
		Save.setIcon(new util().ChangeImageColor(Path.SAVE,
				HoverColor.EXIT_COLOR_MENUS));
		Load.setIcon(new util().ChangeImageColor(Path.LOAD_FILE,
				HoverColor.EXIT_COLOR_MENUS));

		// drop image
		DropImage.setIcon(new util().ChangeImageColor(Path.DROP_IMAGE,
				HoverColor.EXIT_DROP_IMAGE));

		// Palette icon
		PaletteIcon.setBackground(FixedColor.BACKGROUND);

		// Move Down
		MoveDown.setIcon(new util().ChangeImageColor(Path.MOVE_DOWN,
				HoverColor.EXIT_TREE_TOOLS));
		// Move up
		MoveUp.setIcon(new util().ChangeImageColor(Path.MOVE_UP,
				HoverColor.EXIT_TREE_TOOLS));
		// Remove
		Remove.setIcon(new util().ChangeImageColor(Path.REMOVE,
				HoverColor.EXIT_TREE_TOOLS));

	}

	public void close(Palette p) {
		if (p != null) {
			p.close();
		}
		this.MainFrame.setVisible(false);
		this.MainFrame.dispose();
		this.MainFrame = null;
	}

	public void ClearAttributesBoxes(){
		// remove All Boxes
		Component[] compList = AttributesPanel.getComponents();
		for (int i = 0; i < compList.length; i++) {
			if (compList[i] instanceof Box) {
				AttributesPanel.remove(compList[i]);
			}
		}
		AttributesPanel.repaint();
	}


	public void setHeightOfScroll(int length) {
		AttributesPanel.setPreferredSize(new Dimension(AttributesPanel
				.getWidth(), length ));
		AttributesPanel.setMaximumSize(new Dimension(
				AttributesPanel.getWidth(), length ));
	}

	public boolean isClosed() {
		return this.MainFrame == null || this.MainFrame.isVisible() == false;
	}

	public XFrame getMainFrame() {
		return MainFrame;
	}

	public XLabel getMinLabel() {
		return MinLabel;
	}

	public XLabel getMaxLabel() {
		return maxLabel;
	}

	public XLabel getExitLabel() {
		return ExitLabel;
	}

	public XPanel getMainPanel() {
		return MainPanel;
	}

	public XPanel getContent() {
		return content;
	}

	public XPanel getWindowTool() {
		return WindowTool;
	}

	public XLabel getProjectNameLbl() {
		return ProjectNameLbl;
	}

	public XPanel getTitleBar() {
		return TitleBar;
	}

	public XPanel getTopPanel() {
		return TopPanel;
	}

	public XPanel getMenuPanel() {
		return MenuPanel;
	}

	public Box getHorizontalBox() {
		return horizontalBox;
	}

	public XLabel getNew() {
		return New;
	}

	public XLabel getLblNewLabel() {
		return lblNewLabel;
	}

	public XLabel getLoad() {
		return Load;
	}

	public XLabel getSave() {
		return Save;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public XPanel getPanel() {
		return panel;
	}

	public XLabel getDropImage() {
		return DropImage;
	}

	public XPanel getImagePanel() {
		return ImagePanel;
	}

	public XLabel getPaletteIcon() {
		return PaletteIcon;
	}

	public XPanel getPalettePanel() {
		return PalettePanel;
	}

	public ImageLabelResized getImage() {
		return Image;
	}

	public XLabel getMoveUp() {
		return MoveUp;
	}

	public XLabel getMoveDown() {
		return MoveDown;
	}

	public XLabel getRemove() {
		return Remove;
	}

	public XTree getItemTree() {
		return ItemTree;
	}

	public JLabel getCompLblName() {
		return CompLblName;
	}

	public XLabel getFinish() {
		return Finish;
	}

	public XLabel getCancel() {
		return Cancel;
	}

	public MainHandler getMainHandler() {
		return mainHandler;
	}

	public JPanel getAttributesPanel() {
		return AttributesPanel;
	}

}
