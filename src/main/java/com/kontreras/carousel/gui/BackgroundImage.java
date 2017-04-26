package com.kontreras.carousel.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundImage extends JPanel {

	private static final String IMG_PATH = "images/background.png";
	private BufferedImage image;
	
	public BackgroundImage() {
		try {
			image = ImageIO.read(new File(IMG_PATH));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, null); 
		}
	}

	@Override 
	public Dimension getPreferredSize() {
		if (image != null) {
			return new Dimension(image.getWidth(), image.getHeight());
		}
		return super.getPreferredSize();
	}
}
