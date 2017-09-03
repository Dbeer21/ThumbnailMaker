//Programmed by Aaron Weiss 9/2/2017

import javax.swing.*;
import java.awt.*;
import java.beans.*;
import java.awt.Toolkit;
import java.net.*;
import javax.swing.ImageIcon;
import java.util.*;

import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.font.GlyphVector;

import java.awt.geom.Area;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ThumbnailMaker {

	BufferedImage img = null;

	JFrame window;
	URL url;
	Toolkit kit;
	BufferedImage icon;

	JFileChooser chooser;
	BufferedImage fullPreview;
	BufferedImage newBackground;

	String player1Previous = "--";
	String player2Previous = "--";

	JComboBox player1Char, player1Color, player2Char, player2Color;
	JTextField player1Name, player2Name;

	JLabel previewIconLabel;
	ImageIcon previewIcon;

	JPanel previewPanel;

	public ThumbnailMaker() {

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Select Image");
		chooser.setMultiSelectionEnabled(true);

		window = new JFrame("Thumbnail Maker");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		//Favicon
		url = getClass().getResource("/res/icon.png");
		kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(url);
		window.setIconImage(img);

		//Preview Panel
		previewPanel = new JPanel();

		previewIconLabel = new JLabel(previewIcon);

		previewPanel.add(previewIconLabel);
		previewPanel.setBorder(BorderFactory.createTitledBorder("Image Preview"));

		//Directory Panel
		JPanel directoryPanel = new JPanel();

		String[] characters = { "--", "Fox", "Falco", "Marth", "Sheik", "Puff", "Peach", "Icies", "Falcon",
								"Pikachu", "Samus", "Doc", "Yoshi", "Luigi", "Ganon", "Mario", "YoungLink",
								"DK", "Link", "GnW", "Roy", "Mewtwo", "Zelda", "Ness", "Pichu", "Bowser",
								"Kirby" };

		String[] colors = { "--" };

		JPanel player1Panel = new JPanel();
		JLabel player1Label = new JLabel("Player 1:");
		player1Name = new JTextField(15);
		player1Char = new JComboBox(characters);
		player1Color = new JComboBox(colors);

		player1Panel.setLayout(new GridLayout(0,2));
		player1Panel.add(player1Label);
		player1Panel.add(player1Name);
		player1Panel.add(player1Char);
		player1Panel.add(player1Color);

		JPanel player2Panel = new JPanel();
		JLabel player2Label = new JLabel("Player 2:");
		player2Name = new JTextField(15);
		player2Char = new JComboBox(characters);
		player2Color = new JComboBox(colors);

		player2Panel.setLayout(new GridLayout(0,2));
		player2Panel.add(player2Label);
		player2Panel.add(player2Name);
		player2Panel.add(player2Char);
		player2Panel.add(player2Color);



		PropertyChangeListener updateImage = new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
				updatePreview();
			}
		};

		player1Color.addPropertyChangeListener(updateImage);
		player2Color.addPropertyChangeListener(updateImage);
		player1Name.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				updatePreview();
			  }
			  public void removeUpdate(DocumentEvent e) {
				updatePreview();
			  }
			  public void insertUpdate(DocumentEvent e) {
				updatePreview();
			  }
		});

		player2Name.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
				updatePreview();
			  }
			  public void removeUpdate(DocumentEvent e) {
				updatePreview();
			  }
			  public void insertUpdate(DocumentEvent e) {
				updatePreview();
			  }
		});

		player1Char.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					String selection = (String)player1Char.getSelectedItem();
					if(selection == player1Previous)
						return;

						player1Previous = selection;
						player1Color.removeAllItems();
						switch (selection) {
							case "Bowser":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Black");

								player1Color.setSelectedIndex(0);
							break;
							case "Falcon":
								player1Color.addItem("Default");
								player1Color.addItem("Black");
								player1Color.addItem("Red");
								player1Color.addItem("White");
								player1Color.addItem("Green");
								player1Color.addItem("Blue");

								player1Color.setSelectedIndex(0);
							break;
							case "DK":
								player1Color.addItem("Default");
								player1Color.addItem("Black");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
							break;
							case "Doc":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("Black");

								player1Color.setSelectedIndex(0);
								break;
							case "Falco":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Fox":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Ganon":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("Purple");

								player1Color.setSelectedIndex(0);
								break;
							case "Icies":
								player1Color.addItem("Default");
								player1Color.addItem("Green");
								player1Color.addItem("Orange");
								player1Color.addItem("Red");

								player1Color.setSelectedIndex(0);
								break;
							case "Puff":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("Yellow");

								player1Color.setSelectedIndex(0);
								break;
							case "Kirby":
								player1Color.addItem("Default");
								player1Color.addItem("Yellow");
								player1Color.addItem("Blue");
								player1Color.addItem("Red");
								player1Color.addItem("Green");
								player1Color.addItem("White");

								player1Color.setSelectedIndex(0);
								break;
							case "Link":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Black");
								player1Color.addItem("White");

								player1Color.setSelectedIndex(0);
								break;
							case "Luigi":
								player1Color.addItem("Default");
								player1Color.addItem("White");
								player1Color.addItem("Blue");
								player1Color.addItem("Pink");

								player1Color.setSelectedIndex(0);
								break;
							case "Mario":
								player1Color.addItem("Default");
								player1Color.addItem("Yellow");
								player1Color.addItem("Black");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Marth":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Green");
								player1Color.addItem("Black");
								player1Color.addItem("White");

								player1Color.setSelectedIndex(0);
								break;
							case "Mewtwo":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "GnW":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Ness":
								player1Color.addItem("Default");
								player1Color.addItem("Yellow");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Peach":
								player1Color.addItem("Default");
								player1Color.addItem("Yellow");
								player1Color.addItem("White");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Pichu":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Pikachu":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");

								player1Color.setSelectedIndex(0);
								break;
							case "Roy":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("Yellow");

								player1Color.setSelectedIndex(0);
								break;
							case "Samus":
								player1Color.addItem("Default");
								player1Color.addItem("Pink");
								player1Color.addItem("Black");
								player1Color.addItem("Green");
								player1Color.addItem("Purple");

								player1Color.setSelectedIndex(0);
								break;
							case "Sheik":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("White");

								player1Color.setSelectedIndex(0);
								break;
							case "Yoshi":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Yellow");
								player1Color.addItem("Pink");
								player1Color.addItem("Cyan");

								player1Color.setSelectedIndex(0);
								break;
							case "YoungLink":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("White");
								player1Color.addItem("Black");

								player1Color.setSelectedIndex(0);
								break;
							case "Zelda":
								player1Color.addItem("Default");
								player1Color.addItem("Red");
								player1Color.addItem("Blue");
								player1Color.addItem("Green");
								player1Color.addItem("White");

								player1Color.setSelectedIndex(0);
								break;
							default:
								player1Color.addItem("--");
								break;
						}
						updatePreview();
					}
		});

		player2Char.addPropertyChangeListener(new PropertyChangeListener() {
						public void propertyChange(PropertyChangeEvent e) {
							String selection = (String)player2Char.getSelectedItem();
							if(selection == player2Previous)
								return;

								player2Previous = selection;
								player2Color.removeAllItems();
								switch (selection) {
									case "Bowser":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Black");

										player2Color.setSelectedIndex(0);
									break;
									case "Falcon":
										player2Color.addItem("Default");
										player2Color.addItem("Black");
										player2Color.addItem("Red");
										player2Color.addItem("White");
										player2Color.addItem("Green");
										player2Color.addItem("Blue");

										player2Color.setSelectedIndex(0);
									break;
									case "DK":
										player2Color.addItem("Default");
										player2Color.addItem("Black");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
									break;
									case "Doc":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("Black");

										player2Color.setSelectedIndex(0);
										break;
									case "Falco":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Fox":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Ganon":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("Purple");

										player2Color.setSelectedIndex(0);
										break;
									case "Icies":
										player2Color.addItem("Default");
										player2Color.addItem("Green");
										player2Color.addItem("Orange");
										player2Color.addItem("Red");

										player2Color.setSelectedIndex(0);
										break;
									case "Puff":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("Yellow");

										player2Color.setSelectedIndex(0);
										break;
									case "Kirby":
										player2Color.addItem("Default");
										player2Color.addItem("Yellow");
										player2Color.addItem("Blue");
										player2Color.addItem("Red");
										player2Color.addItem("Green");
										player2Color.addItem("White");

										player2Color.setSelectedIndex(0);
										break;
									case "Link":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Black");
										player2Color.addItem("White");

										player2Color.setSelectedIndex(0);
										break;
									case "Luigi":
										player2Color.addItem("Default");
										player2Color.addItem("White");
										player2Color.addItem("Blue");
										player2Color.addItem("Pink");

										player2Color.setSelectedIndex(0);
										break;
									case "Mario":
										player2Color.addItem("Default");
										player2Color.addItem("Yellow");
										player2Color.addItem("Black");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Marth":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Green");
										player2Color.addItem("Black");
										player2Color.addItem("White");

										player2Color.setSelectedIndex(0);
										break;
									case "Mewtwo":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "GnW":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Ness":
										player2Color.addItem("Default");
										player2Color.addItem("Yellow");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Peach":
										player2Color.addItem("Default");
										player2Color.addItem("Yellow");
										player2Color.addItem("White");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Pichu":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Pikachu":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");

										player2Color.setSelectedIndex(0);
										break;
									case "Roy":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("Yellow");

										player2Color.setSelectedIndex(0);
										break;
									case "Samus":
										player2Color.addItem("Default");
										player2Color.addItem("Pink");
										player2Color.addItem("Black");
										player2Color.addItem("Green");
										player2Color.addItem("Purple");

										player2Color.setSelectedIndex(0);
										break;
									case "Sheik":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("White");

										player2Color.setSelectedIndex(0);
										break;
									case "Yoshi":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Yellow");
										player2Color.addItem("Pink");
										player2Color.addItem("Cyan");

										player2Color.setSelectedIndex(0);
										break;
									case "YoungLink":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("White");
										player2Color.addItem("Black");

										player2Color.setSelectedIndex(0);
										break;
									case "Zelda":
										player2Color.addItem("Default");
										player2Color.addItem("Red");
										player2Color.addItem("Blue");
										player2Color.addItem("Green");
										player2Color.addItem("White");

										player2Color.setSelectedIndex(0);
										break;
									default:
										player2Color.addItem("--");
										break;
								}
								updatePreview();
							}
		});

		JButton directoryButton = new JButton("Save File As..");

		JButton backgroundButton = new JButton("Change Background..");

		directoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setDialogTitle("Save Image");
				//Fill save dialog with suggested filename.
				File outputfiletemp = new File(player1Name.getText() + " vs. " + player2Name.getText() + ".png");
				chooser.setSelectedFile(outputfiletemp);
				if (chooser.showSaveDialog(window) == JFileChooser.APPROVE_OPTION) {
					try {
					    //Save the image
						File outputfile = new File(chooser.getSelectedFile().getPath());
						System.out.println(chooser.getCurrentDirectory() + "\\" + chooser.getSelectedFile().getName());
					    ImageIO.write(fullPreview, "png", outputfile);
					} catch (IOException q) {

					}
				}
				else {
					System.out.println("No Selection");
     			}
			}
		});

		backgroundButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Fill save dialog with suggested filename.
						chooser.setDialogTitle("Select Image");
						if (chooser.showOpenDialog(window) == JFileChooser.APPROVE_OPTION) {
							try {
							    //Save the image
							    File file = chooser.getSelectedFile();
								BufferedImage image = ImageIO.read(file);

								int w = image.getWidth();
        						int h = image.getHeight();

        						if (w != 1280 || h != 720) {
									JFrame frame = new JFrame();
									String warning = "Error: Cannot use any image that does not have 1280x720 dimensions.";
									JOptionPane.showMessageDialog(frame,warning,"Notice!",JOptionPane.WARNING_MESSAGE);
								}
								else {
									newBackground = image;
									updatePreview();
								}
							} catch (IOException q) {

							}
						}
						else {
							System.out.println("No Selection");
		     			}
					}
		});


		directoryPanel.add(player1Panel);


		JPanel directorySubPanel = new JPanel();
		//directorySubPanel.setLayout(new GridLayout(0,2));
		directorySubPanel.add(directoryButton);
		directorySubPanel.add(backgroundButton);

		directoryPanel.add(directorySubPanel);

		directoryPanel.add(player2Panel);

		directoryPanel.setLayout(new GridLayout(0,3));
		directoryPanel.setBorder(BorderFactory.createTitledBorder("Match Information"));

		//build scene
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		window.add(directoryPanel, c);

		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		window.add(previewPanel, c);

		updatePreview();
		window.pack();
		window.setVisible(true);
	}

	public static void main(String[] args) {
		try {
				// Set System L&F
				UIManager.setLookAndFeel(
				UIManager.getSystemLookAndFeelClassName());
			}
			catch (UnsupportedLookAndFeelException e) {
				// handle exception
			}
			catch (ClassNotFoundException e) {
				// handle exception
			}
			catch (InstantiationException e) {
				// handle exception
			}
			catch (IllegalAccessException e) {
				// handle exception
    	}
		new ThumbnailMaker();
	}

	public void updatePreview() {

		try {
			BufferedImage newPreview = ImageIO.read(ThumbnailMaker.class.getResourceAsStream("/res/ss_bg.png"));

			if(newBackground != null)
				newPreview = newBackground;

			int w = 1280;
			int h = 720;
			fullPreview = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
			//Graphics g = fullPreview.getGraphics();
			Graphics2D g = fullPreview.createGraphics();
			g.drawImage(newPreview, 0, 0, null);

			BufferedImage player1Image = ImageIO.read(ThumbnailMaker.class.getResourceAsStream("/res/chars/" + player1Char.getSelectedItem().toString().toLowerCase() + "-" + player1Color.getSelectedItem().toString().toLowerCase() + ".png" ));
			g.drawImage(player1Image, 256-(player1Image.getWidth()/2), 360-(player1Image.getHeight()/2), null);

			BufferedImage player2Image = ImageIO.read(ThumbnailMaker.class.getResourceAsStream("/res/chars/" + player2Char.getSelectedItem().toString().toLowerCase() + "-" + player2Color.getSelectedItem().toString().toLowerCase() + ".png" ));

			//mirror image for better look
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-player2Image.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			player2Image = op.filter(player2Image, null);

			g.drawImage(player2Image, 1024-(player2Image.getWidth()/2), 360-(player2Image.getHeight()/2), null);

			//Add text
			g.setFont(g.getFont().deriveFont(Font.BOLD, 60f));
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			FontRenderContext frc = g.getFontRenderContext();
			g.setStroke(new BasicStroke(2));

			GlyphVector gv = g.getFont().createGlyphVector(frc, player1Name.getText());
			Shape glyph = gv.getOutline(256-(float)(gv.getOutline().getBounds().getWidth()/2), 670);

			g.setColor(Color.WHITE);
			g.fill(glyph);
			g.setColor(Color.BLACK);
			g.draw(glyph);

			GlyphVector gv2 = g.getFont().createGlyphVector(frc, player2Name.getText());
			Shape glyph2 = gv2.getOutline(1024-(float)(gv2.getOutline().getBounds().getWidth()/2), 670);

			g.setColor(Color.WHITE);
			g.fill(glyph2);
			g.setColor(Color.BLACK);
			g.draw(glyph2);

			previewIcon = new ImageIcon(fullPreview, "Image Preview");
			previewIconLabel.setIcon(previewIcon);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}