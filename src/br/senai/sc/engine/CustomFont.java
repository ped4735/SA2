package br.senai.sc.engine;

import java.awt.Font;
import java.io.File;
import java.net.URL;

public class CustomFont {

	private Font customFont;
	
	public CustomFont(String path, float size, int style) {
		try {
			//URL uc = this.getClass().getResource(path);
			URL uc = this.getClass().getClassLoader().getResource(path);
			customFont = Font.createFont(Font.TRUETYPE_FONT, new File(uc.getPath())).deriveFont(style, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Font getCustomFont() {
		return customFont;
	}

	public void setCustomFont(Font customFont) {
		this.customFont = customFont;
	}

}
