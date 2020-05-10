package _02_Pixel_Art;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GridInputPanel extends JPanel implements Serializable, ActionListener{
	private static final long serialVersionUID = 1L;
	private JTextField windowWidthField;
	private JTextField windowHeightField;
	private JTextField rowsField;
	private JTextField colsField;
	private JButton submitButton;
			
	PixelArtMaker pam;
	
	public GridInputPanel(PixelArtMaker pam) {
		this.pam = pam;
		
		windowWidthField = new JTextField(5);
		windowHeightField = new JTextField(5);
		rowsField = new JTextField(5);
		colsField = new JTextField(5);
		submitButton = new JButton("Submit");
		
		add(new JLabel("screen width:"));
		add(windowWidthField);
		add(windowHeightField);
		add(new JLabel("\ttotal rows:"));
		add(rowsField);
		add(new JLabel("\ttotal columns:"));
		add(colsField);
		add(submitButton);
		
		submitButton.addActionListener(this);
	}
	
	private void submit() {
		boolean valid = false;
		int w = -1;
		int h = -1;
		int r = -1;
		int c = -1;
		try {
			w = Integer.parseInt(windowWidthField.getText());
			h = Integer.parseInt(windowHeightField.getText());
			r = Integer.parseInt(rowsField.getText());
			c = Integer.parseInt(colsField.getText());
			
			if(w <= 0 || h <= 0 || r <= 0 || c <= 0) {
				invalidateInput();
			}else {
				valid = true;
			}
		}catch(NumberFormatException e) {
			invalidateInput();
		}
		
		if(valid) {
			pam.submitGridData(w, h, r, c);
		}
	}
	
	private void invalidateInput() {
		JOptionPane.showMessageDialog(null, "Be sure all fields are complete with positive numbers.", "ERROR", 0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
//		submit();
		// TODO Auto-generated method stub
		try (FileInputStream fis = new FileInputStream(new File("src/_02_Pixel_Art/ImageSave")); 
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			ois.readObject();
		} catch (IOException a) {
			a.printStackTrace();
		} catch (ClassNotFoundException b) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			b.printStackTrace();
		}
	}
	
	
}
