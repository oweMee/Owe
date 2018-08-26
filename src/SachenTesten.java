import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileSystemView;

// hier werden Sachen getestet, die nicht wirklich zum Projekt gehören. Die Klasse sollte später gelöscht werden
public class SachenTesten {

	public static void test() {
		
		//SearchDialog fff = new SearchDialog(new JFrame());
		//MessageBox dd = new MessageBox("Ihoo");
		StartMenu start = new StartMenu();
		//FindSimilarDialog aaa = new FindSimilarDialog(start);
		
		
		/*
		JList list = new JList();
		DefaultListModel<String> model = new DefaultListModel();
		model.addElement("jjj");
		model.addElement("ggg");
		
		list.setModel(model);
		
		SachenTesten jjj = new SachenTesten();
		//list.addListSelectionListener(jjj.new gListener());
		list.addMouseListener(jjj.new gListener());
		
		JFrame frame = new JFrame();
		frame.setSize(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(list);
		*/
		
		/*
		FileSystemView ff = FileSystemView.getFileSystemView();
		File gg = ff.getHomeDirectory();
		File ggg = ff.getDefaultDirectory();
		File gggg = ggg.getParentFile();
		String ggggg = ff.getSystemDisplayName(new File("D:\\Fake_A"));
		System.out.println(ggggg);
		try {
			Desktop.getDesktop().open(ggg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(ggggg);
		//for (int i = 0; i < gggg.length; i++) {System.out.println(gggg[i].getPath());System.out.println(gggg.length);}
		//LoggingClass.makeLog("Hallo", 'w');
		*/
	}
	
	class gListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			int anzahl = arg0.getClickCount();
			if (anzahl >= 2) {
				 JList list = (JList) arg0.getComponent();
				 System.out.println(list.getSelectedIndex());
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		
		
	}
}
