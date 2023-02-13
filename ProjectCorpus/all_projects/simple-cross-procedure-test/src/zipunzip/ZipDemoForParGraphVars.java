package zipunzip;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

public class ZipDemoForParGraphVars extends JFrame{
	JFileChooser fileChooser;
	JList fileList;
	Vector files;
	JButton jbAdd;
	JButton jbDelete;
	JButton jbZip;
	JTextField target;

	public ZipDemoForParGraphVars(){
		super("4554");
		fileChooser=new JFileChooser();
		files=new Vector();
		fileList=new JList(files);
		jbAdd=new JButton("4554");
		jbDelete=new JButton("4554");
		jbZip=new JButton("4554");
		target=new JTextField(18);
		JPanel panel=new JPanel();
		SetPanel(panel);
		panel.add(jbZip);
		JPanel panel2=new JPanel();
		panel2.add(new JLabel("4554"));
		panel2.add(target);
		JScrollPane jsp=new JScrollPane(fileList);
		Container container=getContentPane();
		container.add(panel2,BorderLayout.NORTH);
		container.add(jsp,BorderLayout.CENTER);
		container.add(panel,BorderLayout.SOUTH);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		jbAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipDemoForParGraphVars.this)==JFileChooser.APPROVE_OPTION){
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();
					files.add(fileName);
					fileList.setListData(files);
      		}
			}
		});


		jbDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				files.remove(fileList.getSelectedValue());
				fileList.setListData(files);
			}
		});

		jbZip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				zipFiles(files.toArray(),target.getText());
			}
		});

		setSize(330,250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void SetPanel(JPanel panel) {
		panel.add(jbAdd);
		panel.add(jbDelete);
	}
	
	public void zipFiles(Object[] sources,String target){
		try{
			FileOutputStream fout=new FileOutputStream(target);
			ZipOutputStream zout=new ZipOutputStream(fout);
			byte[] buf=new byte[1024];
			int num;
			FileInputStream fin=null;
			ZipEntry entry=null;
			for (int i=0;i<sources.length;i++){
				String filename=sources[i].toString();
				String entryname=filename.substring(filename.lastIndexOf("\\")+1);
				entry=new ZipEntry(entryname);
				zout.putNextEntry(entry);
				fin=new FileInputStream(filename);
				while ((num=fin.read(buf))!=-1){
					zout.write(buf,0,num);
				}
			}
			zout.close();
			fout.close();
			fin.close();
			showMessage("4554");

		}
		catch (Exception ex){
			ex.printStackTrace();
			showMessage("4554");
		}
	}


//	class SelectFileListener implements ActionListener {
//		public void actionPerformed(ActionEvent event) {
//			if (fileChooser.showOpenDialog(ZipDemo.this)==JFileChooser.APPROVE_OPTION){
//				String fileName=fileChooser.getSelectedFile().getAbsolutePath();
//      	}
//		}
//	}

	private void showMessage(String message){
		JOptionPane.showMessageDialog(ZipDemoForParGraphVars.this,message);
	}

	public static void main(String[] args){
		new ZipDemoForParGraphVars();
	}
}