package com.stdplatform.gui_selector.Dialogs;

import java.awt.*;

import javax.swing.*;

import com.stdplatform.gui_selector.Constant.Color.FixedColor;
import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Frames.Palette;
import com.stdplatform.gui_selector.Handler.MainHandler;
import com.stdplatform.gui_selector.Handler.PaletteHandler;
import com.stdplatform.gui_selector.component.XDialog;
import com.stdplatform.gui_selector.component.XLabel;
import com.stdplatform.gui_selector.component.XPanel;
import com.stdplatform.gui_selector.component.itemPalette;
import com.stdplatform.gui_selector.util.util;

import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectorDialog {
	private XDialog dialog;
	public static final int WIDTH = 420, HEIGHT = 175;
	private JPanel MainPanel;
	private XLabel ExitLabel;
	private JLabel msgTxt;
	private JButton btnOK, btnCancel;
	private int type = XDialog.OK_CANCEL;
	private int result;

	public SelectorDialog(JFrame mainFrame,String Msg , int type) {
		this.type = type;
		initialize(mainFrame);
		msgTxt.setText(Msg);
		initializeColors();
		StartAction();
		dialog.setVisible(true);
	}

	public SelectorDialog(String Msg , int type) {
		this.type = type;
		initialize(null);
		msgTxt.setText(Msg);
		initializeColors();
		StartAction();
		dialog.setVisible(true);
	}



	private void initialize(JFrame mainFrame) {
		dialog = new XDialog(mainFrame);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		dialog.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		dialog.setBounds(0, 0, WIDTH, HEIGHT);
		dialog.setLocationRelativeTo(mainFrame);
		dialog.setResizable(false);

		MainPanel = new JPanel();
		MainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.BLACK));
		dialog.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(new BorderLayout(0, 0));
		XPanel TitleBar = new XPanel();
		TitleBar = new XPanel();
		dialog.setDragPanel(TitleBar);
		MainPanel.add(TitleBar, BorderLayout.NORTH);
		TitleBar.setBackground(FixedColor.BACKGROUND);
		TitleBar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		TitleBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		TitleBar.setLayout(new BorderLayout(0, 0));
		XPanel NamePanel = new XPanel();
		NamePanel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NamePanel.setBackground(FixedColor.BACKGROUND);
		TitleBar.add(NamePanel, BorderLayout.WEST);
		XLabel ProjectNameLbl = new XLabel("ISC Project");
		ProjectNameLbl.setText("Message");
		ProjectNameLbl.setForeground(FixedColor.PROJECT_NAME);
		ProjectNameLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		NamePanel.add(ProjectNameLbl);
		XPanel WindowTool = new XPanel();
		WindowTool.setBackground(FixedColor.BACKGROUND);
		TitleBar.add(WindowTool, BorderLayout.EAST);
		WindowTool.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		ExitLabel = new XLabel("");
		WindowTool.add(ExitLabel);
		ExitLabel.setIcon(new ImageIcon(Main.class
				.getResource(Path.EXIT_WINDOW)));
		Panel panel = new Panel();
		panel.setPreferredSize(new Dimension(10, 40));
		MainPanel.add(panel, BorderLayout.SOUTH);

		// ------------------------------- OK

		btnOK = new JButton("OK");
		btnOK.setForeground(FixedColor.BACKGROUND);
		btnOK.setFont(new Font("Meiryo", Font.BOLD, 12));
		btnOK.setPreferredSize(new Dimension(80, 30));
		btnOK.setAlignmentY(7.0f);
		btnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnOK.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
		panel.add(btnOK);
		// ---------------------------------------------
		if (type == XDialog.OK_CANCEL) {
			// ------------------------------- Cancel
			btnCancel = new JButton("Cancel");
			btnCancel.setForeground(FixedColor.BACKGROUND);
			btnCancel.setFont(new Font("Meiryo", Font.BOLD, 12));
			btnCancel.setPreferredSize(new Dimension(80, 30));
			btnCancel.setDebugGraphicsOptions(DebugGraphics.BUFFERED_OPTION);
			panel.add(btnCancel);
		}
		Panel Content = new Panel();
		MainPanel.add(Content, BorderLayout.CENTER);
		msgTxt = new JLabel("No text here :D");
		msgTxt.setBounds(72, 29, 336, 70);
		JLabel InfoLabel = new JLabel("");
		InfoLabel.setBounds(30, 47, 32, 37);
		InfoLabel.setIcon(new ImageIcon(SelectorDialog.class
				.getResource(Path.INFO)));
		Content.setLayout(null);
		Content.add(InfoLabel);
		Content.add(msgTxt);

	}

	private void StartAction() {

		ExitLabel.setHoverAction(Path.EXIT_WINDOW,
				HoverColor.ENTER_COLOR, HoverColor.EXIT_COLOR);
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				result = 1;
				dialog.dispose();
			}
		});

		ExitLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				result = 0;
				dialog.dispose();
			}

		});

		if (btnCancel != null) {
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					result = 0;
					dialog.dispose();
				}

			});

		}

	}

	private void initializeColors() {
		ExitLabel.setIcon(new util().ChangeImageColor(Path.EXIT_WINDOW,
				HoverColor.EXIT_COLOR));
	}

	public JButton getOKButton() {
		return btnOK;
	}

	public JButton getCancelButton() {
		return btnCancel;
	}

	public void setMessage(String txt) {
		msgTxt.setText(txt);
	}

	public int getResult(){
		return result ;
	}
}
