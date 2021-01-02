package com.stdplatform.gui_selector.Handler;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;


import com.stdplatform.gui_selector.selector.Shape;
import com.stdplatform.gui_selector.util.Chooser;
import com.stdplatform.gui_selector.util.MapComponent;
import com.stdplatform.gui_selector.util.util;
import com.stdplatform.gui_selector.animation.AfterAnimationListener;
import com.stdplatform.gui_selector.animation.Die;
import com.stdplatform.gui_selector.animation.GrowingUp;
import com.stdplatform.gui_selector.component.ImageLabelResized;
import com.stdplatform.gui_selector.component.XDialog;
import com.stdplatform.gui_selector.component.XLabel;
import com.stdplatform.gui_selector.generate.AttributesGenerator;
import com.stdplatform.gui_selector.generate.VirtualBenchXML;
import com.stdplatform.gui_selector.io.XML_component_loader;
import com.stdplatform.gui_selector.io.ioManager;
import com.stdplatform.gui_selector.Actions.Window.DragWindowRelocate;
import com.stdplatform.gui_selector.Constant.Color.FixedColor;
import com.stdplatform.gui_selector.Constant.Color.HoverColor;
import com.stdplatform.gui_selector.Constant.Path.Path;
import com.stdplatform.gui_selector.Dialogs.SelectorDialog;
import com.stdplatform.gui_selector.Frames.Main;
import com.stdplatform.gui_selector.Frames.Palette;

public class MainHandler {

	private DragWindowRelocate dwr;
	private JWindow FramePalette;
	private Palette PaletteObject;
	private Main MainDesign;
	private int PrevMainFrameWidth, PrevMainFrameHeight;
	private int PrevMainFrameX, PrevMainFrameY;
	private boolean fullscreen;
	private ArrayList<Shape> shapeList;
	private GrowingUp gp;
	private String LoadedImagePath;
	private VirtualBenchXML VBXML;
	private TreeMap<String, ArrayList<Shape>> ShapesMap;

	private Die die;
	// --------------------------------------
	private XML_component_loader components;
	HashMap<String, ArrayList<Box>> AttributesBoxMap;

	public MainHandler(Main MainDesign) {
		this.MainDesign = MainDesign;

		shapeList = new ArrayList<Shape>();
		initializeAction();
		LoadComponents();
	}

	private void LoadComponents() {
		components = new XML_component_loader(this);

	}

	public void initializeAction() {
		dwr = MainDesign.getMainFrame().getDragWindowRelocate();
		// relocation Action
		relocationAction();
		// Component Action
		DropImageActions();
		LoadActions();
		SaveActions();
		MinLabelActions();
		maxLabelActions();
		ExitLabelActions();
		NewActions();
		FinishActions();
		CancelActions();
		PaletteIconAction();
		// main frame Action
		MainFrameAction();
		// Hover JTree tools
		HoverJTreeTools();
	}

	private void CancelActions() {
		MainDesign.getCancel().setHoverAction(HoverColor.ENTER_DROP_IMAGE,
				HoverColor.EXIT_DROP_IMAGE);

		MainDesign.getCancel().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (LoadedImagePath != null) {
					SelectorDialog sd = new SelectorDialog(
							MainDesign.getMainFrame(),
							"Do you want to cancel the selector without saving ? ",
							XDialog.OK_CANCEL);

					if (sd.getResult() == 0) {
						return;
					}
				}
				shapeList = null;
				MainDesign.close(PaletteObject);
			}
		});

	}

	private void FinishActions() {
		MainDesign.getFinish().setHoverAction(HoverColor.ENTER_DROP_IMAGE,
				HoverColor.EXIT_DROP_IMAGE);

		MainDesign.getFinish().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (LoadedImagePath == null) {
					new SelectorDialog(MainDesign.getMainFrame(),
							"You Must Load Image first.", XDialog.OK);
					return;
				}
				VBXML = new VirtualBenchXML(shapeList, LoadedImagePath);
					new SelectorDialog(
					MainDesign.getMainFrame(),
					"Successfully exported" ,
					XDialog.OK);
			}
		});

	}

	private void HoverJTreeTools() {
		MainDesign.getMoveDown().setHoverAction(Path.MOVE_DOWN,
				HoverColor.ENTER_TREE_TOOLS, HoverColor.EXIT_TREE_TOOLS);

		MainDesign.getMoveUp().setHoverAction(Path.MOVE_UP,
				HoverColor.ENTER_TREE_TOOLS, HoverColor.EXIT_TREE_TOOLS);

		MainDesign.getRemove().setHoverAction(Path.REMOVE,
				HoverColor.ENTER_TREE_TOOLS, HoverColor.EXIT_TREE_TOOLS);
	}

	private void DropImageActions() {
		MainDesign.getDropImage().setHoverAction(Path.DROP_IMAGE,
				HoverColor.ENTER_DROP_IMAGE, HoverColor.EXIT_DROP_IMAGE);

		MainDesign.getDropImage().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getDropImageAction(null);
			}

		});

	}

	private void MainFrameAction() {
		MainDesign.getMainFrame().addWindowStateListener(
				new WindowStateListener() {
					public void windowStateChanged(WindowEvent e) {
						// minimized
						if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
							if (FramePalette != null) {
								FramePalette.setVisible(false);
								FramePalette = null;
							}
						}
					}
				});

	}

	private void PaletteIconAction() {

		final MainHandler handler = this;

		MainDesign.getPaletteIcon().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (LoadedImagePath == null) {
					new SelectorDialog(MainDesign.getMainFrame(),
							"Please , load first the target image.", XDialog.OK);
					return;
				}

				if (gp != null && gp.isRunning()) {
					return;
				}
				if (die != null && die.isRunning()) {
					return;
				}
				if (FramePalette == null) {
					// growing up
					PaletteObject = new Palette(handler);
					FramePalette = PaletteObject.getFramePalette();
					dwr.setPaletteFrame(FramePalette,
							MainDesign.getPalettePanel(),
							MainDesign.getMainFrame());

					int PaletteX = MainDesign.getMainFrame().getX() + 5;
					int PaletteY = MainDesign.getMainFrame().getY()
							+ MainDesign.getMainFrame().getHeight()
							- MainDesign.getPalettePanel().getHeight()
							- Palette.WINDOW_HEIGHT;

					FramePalette.setBounds(PaletteX, PaletteY, 0, 0);
					FramePalette.setVisible(true);

					gp = new GrowingUp(FramePalette);
					gp.SetOriginalSize(Palette.WINDOW_WIDTH,
							Palette.WINDOW_HEIGHT);
					gp.setAfterAnimationListener(new AfterAnimationListener() {
						@Override
						public void afterAction() {
							if (FramePalette == null) {
								return;
							}
							gp = null;
							FramePalette.setOpacity(0.8f);
							for (int i = 0; i < PaletteObject.getItemList().length; i++) {
								Component thisComp = PaletteObject
										.getItemList()[i];
								thisComp.repaint();
								thisComp.setVisible(true);
							}
						}
					});
					gp.Start();

					for (int i = 0; i < PaletteObject.getItemList().length; i++) {
						Component thisComp = PaletteObject.getItemList()[i];
						GrowingUp gp1 = new GrowingUp(thisComp);
						gp1.SetOriginalSize(thisComp.getWidth(),
								thisComp.getHeight());
						gp1.WaitForFinish(gp);
						gp1.Start();

					}
					FramePalette.setLocation(PaletteX, PaletteY);

				} else {
					// die
					if (!FramePalette.isVisible()) {
						return;
					}
					die = new Die(FramePalette);
					die.setAfterAnimationListener(new AfterAnimationListener() {
						@Override
						public void afterAction() {
							FramePalette = null;
							die = null;
						}
					});
					die.Start();
				}
			}

		});

		MainDesign.getPaletteIcon().setHoverAction(
				HoverColor.ENTER_PALETTE_ICON, FixedColor.BACKGROUND);
	}

	private void LoadActions() {
		// Load
		final MainHandler handler = this;
		MainDesign.getLoad().setHoverAction(Path.LOAD_FILE,
				HoverColor.ENTER_COLOR_MENUS, HoverColor.EXIT_COLOR_MENUS);
		MainDesign.getLoad().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				ioManager Load = new ioManager(handler);
				Load.LoadDataModel();
			}
		});

	}

	private void SaveActions() {
		// Save
		final MainHandler handler = this;
		MainDesign.getSave().setHoverAction(Path.SAVE,
				HoverColor.ENTER_COLOR_MENUS, HoverColor.EXIT_COLOR_MENUS);

		MainDesign.getSave().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				ioManager Save = new ioManager(handler);
				Save.SaveDataModel();
			}
		});
	}

	private void MinLabelActions() {

		final JFrame MainFrame = MainDesign.getMainFrame();
		// minLabel
		MainDesign.getMinLabel().setHoverAction(Path.MIN_WINDOW,
				HoverColor.ENTER_COLOR, HoverColor.EXIT_COLOR);
		MainDesign.getMinLabel().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				MainFrame.setExtendedState(Frame.ICONIFIED);
			}
		});
	}

	private void maxLabelActions() {
		// max label
		MainDesign.getMaxLabel().setHoverAction(Path.MAX_WINDOW,
				HoverColor.ENTER_COLOR, HoverColor.EXIT_COLOR);

		MainDesign.getMaxLabel().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (!fullscreen) {
					PrevMainFrameHeight = MainDesign.getMainFrame().getHeight();
					PrevMainFrameWidth = MainDesign.getMainFrame().getWidth();
					PrevMainFrameX = MainDesign.getMainFrame().getX();
					PrevMainFrameY = MainDesign.getMainFrame().getY();

					// Screen set location
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					int width = (int) screenSize.getWidth();
					int height = (int) screenSize.getHeight();

					MainDesign.getMainFrame().setBounds(0, 0, width, height);
					MainDesign.getMainFrame().toFront();
					if (FramePalette != null) {
						FramePalette.setVisible(false);
						FramePalette = null;
					}

				} else {
					MainDesign.getMainFrame().setBounds(PrevMainFrameX,
							PrevMainFrameY, PrevMainFrameWidth,
							PrevMainFrameHeight);
				}
				dwr.UpdateAllAfter(20);
				fullscreen = !fullscreen;
			}

		});

	}

	private void ExitLabelActions() {

		MainDesign.getExitLabel().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (LoadedImagePath != null) {
					SelectorDialog sd = new SelectorDialog(
							MainDesign.getMainFrame(),
							"Do you want to cancel the selector without saving ? ",
							XDialog.OK_CANCEL);

					if (sd.getResult() == 0) {
						return;
					}
				}
				MainDesign.close(PaletteObject);
			}
		});
		MainDesign.getExitLabel().setHoverAction(Path.EXIT_WINDOW,
				HoverColor.ENTER_COLOR, HoverColor.EXIT_COLOR);
	}

	private void NewActions() {
		MainDesign.getNew().setHoverAction(Path.NEW_FILE,
				HoverColor.ENTER_COLOR_MENUS, HoverColor.EXIT_COLOR_MENUS);
		MainDesign.getNew().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getNewActionClick(true);
			}
		});

	}

	private void relocationAction() {
		MainDesign.getMainFrame().DragWindow();
		dwr.setDropImage(MainDesign.getDropImage(), MainDesign.getImagePanel());
		MainDesign.getMainFrame().setDragPanel(MainDesign.getTitleBar());
		dwr.setShapeList(shapeList);
		// Activate Selected Item
		MainDesign.getItemTree().ActivateSelectItemPalette(this.shapeList);
	}

	// get Actions
	// ----------------------------------------------------------------------------

	public void getNewActionClick(Boolean WithDialog) {
		if (WithDialog) {
			if (LoadedImagePath != null) {
				SelectorDialog sd = new SelectorDialog(
						MainDesign.getMainFrame(),
						"Do you want to clear the selector without saving ? ",
						XDialog.OK_CANCEL);

				if (sd.getResult() == 0) {
					return;
				}
			}
		}
		// remove all components
		XLabel dropImage = MainDesign.getDropImage();
		ImageLabelResized img = MainDesign.getImage();
		Component[] all = MainDesign.getImagePanel().getComponents();
		for (int i = 0; i < all.length; i++) {
			if (dropImage == all[i]) {
				dropImage.setVisible(true);
			} else if (img == all[i]) {
				img.setIcon(null);
			} else {
				MainDesign.getImagePanel().remove(all[i]);
			}
		}
		// remove All Boxes
		MainDesign.ClearAttributesBoxes();
		MainDesign.setHeightOfScroll(100);
		MainDesign.getCompLblName().setVisible(false);
		MainDesign.getItemTree().clear();
		MainDesign.getImagePanel().repaint();
		MainDesign.getAttributesPanel().repaint();
		// assign LoadedImagePath with null to reset
		LoadedImagePath = null;
	}

	public void getDropImageAction(File img) {
		// choose image
		if (img == null) {
			img = Chooser.OpenChooser(Chooser.IMAGE);
			if (img == null) {
				return;
			}
		}
		LoadedImagePath = img.getPath();
		dwr.setImage(MainDesign.getImage(), img.getPath());
		dwr.UpdateAll();
		MainDesign.getImage().setIcon(new ImageIcon(img.getPath()));
		MainDesign.getDropImage().setVisible(false);
	}

	// -------------------------------------------- get classes
	public Main getMainDesign() {
		return MainDesign;
	}

	public DragWindowRelocate getDragWindowRelocate() {
		return dwr;
	}

	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	public TreeMap<String, ArrayList<Shape>> getShapesMap() {
		return ShapesMap;
	}

	public VirtualBenchXML getVBXML() {
		return VBXML;
	}

	public String getLoadedImagePath() {
		return LoadedImagePath;
	}

	public XML_component_loader getComponents() {
		return components;
	}

	public HashMap<String, ArrayList<Box>> getAttributesBoxMap() {
		return AttributesBoxMap;
	}

}