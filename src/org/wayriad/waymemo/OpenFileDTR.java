package org.wayriad.waymemo;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

public class OpenFileDTR{
	String path = null;
	public OpenFileDTR() throws ClassNotFoundException,  
                InstantiationException, IllegalAccessException,  
                UnsupportedLookAndFeelException
	{
		
		//
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
            JFileChooser jdir = new JFileChooser();  
            //
            jdir.setFileSelectionMode(JFileChooser.FILES_ONLY);  
            // 
            FileNameExtensionFilter filter = new FileNameExtensionFilter("DTR file(*.dtr)","dtr");   
            jdir.setFileFilter(filter);  
            // 
            jdir.setDialogTitle("Choose the .dtr file");  
            if (JFileChooser.APPROVE_OPTION == jdir.showOpenDialog(null)) { 
                path = jdir.getSelectedFile().getAbsolutePath();  
            }
           
 
   
	}  
	
	public String getPathDTR(){
		return path;
	}
	
}  

