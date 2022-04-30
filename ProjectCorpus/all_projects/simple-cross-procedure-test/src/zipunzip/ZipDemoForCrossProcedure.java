package zipunzip;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.zip.*;
import javax.swing.*;

//��ZIPѹ������ļ�

public class ZipDemoForCrossProcedure extends JFrame{
	JFileChooser fileChooser; //�ļ�ѡ����
	JList fileList;	//��ѹ�����ļ��б�
	ArrayList files;	//�ļ�����(��ѹ���ļ�)
	JButton jbAdd;	//�����ļ���ť
	JButton jbDelete; //ɾ���ļ���ť
	JButton jbZip; //ѹ����ť
	JTextField target; //Ŀ���ļ��ı���

	public ZipDemoForCrossProcedure(){
		super("��ZIPѹ������ļ�");	//���ø��๹�캯��
		fileChooser=new JFileChooser();	//ʵ�����ļ�ѡ����
		files=new ArrayList(); //ʵ�����ļ�����Vector
		fileList=new JList(files.toArray()); //ʵ������ѡ���ļ��б�
		jbAdd=new JButton("����"); //ʵ������ť���
		jbDelete=new JButton("ɾ��");
		jbZip=new JButton("ѹ��");
		target=new JTextField(18);
		JPanel panel=new JPanel(); //ʵ�������,�������ɰ�ť
		panel.add(jbAdd);	//��������������
		panel.add(jbDelete);
		panel.add(jbZip);
		JPanel panel2=new JPanel();
		panel2.add(new JLabel("Ŀ���ļ�"));
		panel2.add(target);
		JScrollPane jsp=new JScrollPane(fileList);
		Container container=getContentPane(); //�õ�����
		container.add(panel2,BorderLayout.NORTH); //�������������
		container.add(jsp,BorderLayout.CENTER);
		container.add(panel,BorderLayout.SOUTH);
		jsp.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));  //���ñ߽�

		jbAdd.addActionListener(new ActionListener(){	//�����ļ���ť�¼�����
			public void actionPerformed(ActionEvent event) {
				if (fileChooser.showOpenDialog(ZipDemoForCrossProcedure.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
					String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
					files.add(fileName);  //�����ļ���Vector
					fileList.setListData(files.toArray()); //�����ļ�ѡ���б������
      		}
			}
		});
		
		ArrayList sources = files;
		String targetText = target.getText();
		try{
			FileOutputStream fout=new FileOutputStream(targetText);	//�õ�Ŀ���ļ������
			ZipOutputStream zout=new ZipOutputStream(fout);	//�õ�ѹ�������
			byte[] buf=new byte[1024];//�趨���뻺�����ߴ�
			int num;
			FileInputStream fin=null;
			ZipEntry entry=null;
			for (int i=0;i<sources.size();i++){
				String filename=sources.get(i).toString(); //�õ���ѹ���ļ�·����
				String entryname=filename.substring(filename.lastIndexOf("\\")+1); //�õ��ļ���
				entry=new ZipEntry(entryname); //ʵ������Ŀ�б�
				zout.putNextEntry(entry); //��ZIP��Ŀ�б�д�������
				fin=new FileInputStream(filename); //��Դ�ļ��õ��ļ�������
				while ((num=fin.read(buf))!=-1){  //����ļ�δ����
					zout.write(buf,0,num);	//д�뻺������
				}
			}
			zout.close();	//�ر�ѹ�������
			fout.close();	//�ر��ļ������
			fin.close();	//�ر��ļ�������
			showMessage("ѹ���ɹ�");	//��ʾ������Ϣ
		}
		catch (Exception ex){
			ex.printStackTrace();	//��ӡ������Ϣ
			showMessage("ѹ��ʧ��");
		}

		files.remove(fileList.getSelectedValue());	//��Vector���Ƴ�ѡ���ļ�
		fileList.setListData(files.toArray()); //�����ļ�ѡ���б������

		setSize(330,250);	//���ô��ڳߴ�
		setVisible(true);	//���ô��ڿ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//�رմ���ʱ�˳�����
		
		int ff = ZipFUtil.F(10);
		System.out.println(ff);
	}
	
//	class SelectFileListener implements ActionListener {	//�ļ�ѡ����¼�����
//		public void actionPerformed(ActionEvent event) {
//			if (fileChooser.showOpenDialog(ZipDemo.this)==JFileChooser.APPROVE_OPTION){	//�����ļ�ѡ����,���ж��Ƿ����˴򿪰�ť
//				String fileName=fileChooser.getSelectedFile().getAbsolutePath();	//�õ�ѡ���ļ��ľ���·��
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