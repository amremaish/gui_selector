package com.stdplatform.gui_selector.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;

import com.stdplatform.gui_selector.Dialogs.SelectorDialog;
import com.stdplatform.gui_selector.component.XDialog;

public class Chooser {

	public static final int IMAGE = 1;
	public static final int GSEL = 2;
	public static final int All = 3;

	public static File OpenChooser(final int type) {

		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {

			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return filter(type, f);
			}

			@Override
			public String getDescription() {
				return "Image files";
			}

		});
		File file = null;
		int ret = fc.showOpenDialog(new JPanel());
		if (ret == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
		}
		return file;
	}

	public static String SaveChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("/home/me/desktop"));
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			try {
				return chooser.getSelectedFile().getPath();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null ;
	}

	private static boolean filter(int type, File f) {
		if (type == IMAGE) {
			return f.getName().endsWith(".jpg")
					|| f.getName().endsWith(".jpeg")
					|| f.getName().endsWith(".png")
					|| f.getName().endsWith(".wbmp")
					|| f.getName().endsWith(".gif")
					|| f.getName().endsWith(".bmp");
		} else if (type == GSEL) {
			return f.getName().endsWith(".gsel");
		}
		return true;
	}

}
