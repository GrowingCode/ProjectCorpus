package zipunzip;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

//��ZIPѹ������ļ�

public class ZipDemoForCrossProcedure extends JFrame{
	JFileChooser fileChooser;
	JList fileList;
	ArrayList files;
	JButton jbAdd;
	JButton jbDelete;
	JButton jbZip;
	JTextField target;

	public ZipDemoForCrossProcedure(){
		super("asas");
		fileChooser=new JFileChooser();
		files=new ArrayList();
		fileList=new JList(files.toArray());
		jbAdd=new JButton("asas");
		jbDelete=new JButton("asas");
		jbZip=new JButton("asas");
		target=new JTextField(18);
		JPanel panel=new JPanel();
		panel.add(jbAdd);
		panel.add(jbDelete);
		panel.add(jbZip);
		JPanel panel2=new JPanel();
		panel2.add(new JLabel("asas"));
		panel2.add(target);
		JScrollPane jsp=new JScrollPane(fileList);
		Container container=getContentPane();
		container.add(panel2,BorderLayout.NORTH);
		container.add(jsp,BorderLayout.CENTER);
		container.add(panel,BorderLayout.SOUTH);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		jbAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipDemoForCrossProcedure.this)==JFileChooser.APPROVE_OPTION){
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();
					files.add(fileName);
					fileList.setListData(files.toArray());
      		}
			}
		});
		
		ArrayList sources = files;
		String targetText = target.getText();
		try{
			FileOutputStream fout=new FileOutputStream(targetText);
			ZipOutputStream zout=new ZipOutputStream(fout);
			byte[] buf=new byte[1024];
			int num;
			FileInputStream fin=null;
			ZipEntry entry=null;
			for (int i=0;i<sources.size();i++){
				String filename=sources.get(i).toString();
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
			showMessage("asas");
		}
		catch (Exception ex){
			ex.printStackTrace();
			showMessage("sds");
		}

		files.remove(fileList.getSelectedValue());
		fileList.setListData(files.toArray());

		setSize(330,250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		int ff = ZipFUtil.F(10);
		System.out.println(ff);
	}
	
//	class SelectFileListener implements ActionListener {
//		public void actionPerformed(ActionEvent event) {
//			if (fileChooser.showOpenDialog(ZipDemo.this)==JFileChooser.APPROVE_OPTION){
//				String fileName=fileChooser.getSelectedFile().getAbsolutePath();
//      	}
//		}
//	}

	private void showMessage(String message){
		JOptionPane.showMessageDialog(ZipDemoForCrossProcedure.this,message); //��ʾ��Ϣ
	}

	public static void main(String[] args){
		new ZipDemoForCrossProcedure();
	}
}