import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

// beinhaltet die ganzen Listener-Klassen
public class ListenerClass {

	private class NewProjectListener implements ActionListener{
		
		JFrame frame;  // Referrenz zum Fenster
		
		public NewProjectListener(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ProjectHolder.newProject();
			MainFrame mainFrame = new MainFrame();
			frame.dispose();
		}
		
	}
	
	public static NewProjectListener makeNewProjectListener(JFrame frame) {
		ListenerClass container = new ListenerClass();
		NewProjectListener listener = container.new NewProjectListener(frame);
		return listener;
	}
	
	
	
	
	
	
	
	
	
	private class LoadProjectListener implements ActionListener{
		
		JFrame frame;
		
		public LoadProjectListener(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int stateOfChoosing = chooser.showOpenDialog(null);
			if (stateOfChoosing == JFileChooser.APPROVE_OPTION) {
				ProjectHolder.loadProject(chooser.getSelectedFile().getAbsolutePath());
				MainFrame mainFrame = new MainFrame();
				frame.dispose();
				mainFrame.refreshFrame();
			}
			
		}
		
	}
	
	public static LoadProjectListener makeLoadProjectListener(JFrame frame) {
		ListenerClass container = new ListenerClass();
		return container.new LoadProjectListener(frame);
	}
	
	
	
	
	
	
	
	
	
	private class SaveProjectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ProjectHolder.saveProject();
		}
		
	}
	
	public static SaveProjectListener makeSaveProjectListener() {
		ListenerClass container = new ListenerClass();
		return container. new SaveProjectListener();
	}
	
	
	
	
	
	
	
	
	
	private class SaveAsProjectListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			
			int stateOfChoosing = chooser.showOpenDialog(null);
			if(stateOfChoosing == JFileChooser.APPROVE_OPTION) {
				String path = chooser.getSelectedFile().getAbsolutePath();
				ProjectHolder.changeLoadingPath(path);
				ProjectHolder.saveProject();
			}
		}
		
	}
	
	public static SaveAsProjectListener makeSaveAsProjectListener() {
		ListenerClass container = new ListenerClass();
		return container. new SaveAsProjectListener();
	}
	
	
	
	
	
	
	
	
	
	private class PrintToTXTListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ProjectHolder.printToTXT();
		}
		
	}
	
	public static PrintToTXTListener makePrintToTXTListener() {
		ListenerClass container = new ListenerClass();
		return container. new PrintToTXTListener();
	}
	
	
	
	
	
	
	
	
	private class RefreshListListener implements ActionListener{
		
		private MainFrame frame;
		private FindSimilarDialog dialog;
		private boolean fromFindSimilar;
		
		public RefreshListListener(MainFrame frame) {
			this.dialog = null;
			this.frame = frame;
			fromFindSimilar = false;
		}
		
		public RefreshListListener(MainFrame frame, FindSimilarDialog dialog) {
			this.dialog = dialog;
			this.frame = frame;
			fromFindSimilar = true;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ProjectHolder.getListHolder().updateList();
			if (fromFindSimilar) {
				dialog.updateList();
			}
			frame.refreshFrame();
		}
		
	}
	
	public static RefreshListListener makeRefreshListListener(MainFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new RefreshListListener(frame);
	}
	
	public static RefreshListListener makeRefreshListListener(MainFrame frame, FindSimilarDialog dialog) {
		ListenerClass container = new ListenerClass();
		return container. new RefreshListListener(frame, dialog);
	}
	
	
	
	
	
	
	
	
	
	
	private class DeleteSuperDirectoryListener implements ActionListener{
		
		private MainFrame frame;
		private JDialog dialog;
		private int position;
		
		public DeleteSuperDirectoryListener(MainFrame frame, int position, JDialog dialog) {
			this.frame = frame;
			this.position = position;
			this.dialog = dialog;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			ProjectHolder.getListHolder().deleteSuperDirectoryByPosition(position);
			dialog.dispose();
			frame.refreshFrame();
		}
		
	}
	
	public static DeleteSuperDirectoryListener makeDeleteSuperDirectoryListener(MainFrame frame, int position, JDialog dialog) {
		ListenerClass container = new ListenerClass();
		return container. new DeleteSuperDirectoryListener(frame, position, dialog);
	}
	
	
	
	
	
	
	
	
	
	
	
	private class ShowSearchListener implements ActionListener{
		
		private JFrame frame;
		
		public ShowSearchListener(JFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			SearchDialog dialog = new SearchDialog((MainFrame) frame);
		}
		
	}
	
	public static ShowSearchListener makeShowSearchListener(JFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new ShowSearchListener(frame);
	}
	
	
	
	
	
	
	
	
	
	
	
	private class ShowFindSimilarListener implements ActionListener{
		
		private MainFrame frame;
		
		public ShowFindSimilarListener(MainFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			FindSimilarDialog dialog = new FindSimilarDialog(frame);
		}
		
	}
	
	public static ShowFindSimilarListener makeShowFindSimilarListener(MainFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new ShowFindSimilarListener(frame);
	}
	
	
	
	
	
	
	
	
	
	
	
	private class AddSuperDirectoryListener implements ActionListener{
		
		private MainFrame frame;
		
		public AddSuperDirectoryListener(MainFrame frame) {
			this.frame = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int stateOfChoosing = chooser.showOpenDialog(null);
			if (stateOfChoosing == JFileChooser.APPROVE_OPTION) {
				MakeList.makeList(chooser.getSelectedFile());
				frame.refreshFrame();
			}
		}
		
	}
	
	public static AddSuperDirectoryListener makeAddSuperDirectoryListener(MainFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new AddSuperDirectoryListener(frame);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public class SearchChangedListener implements CaretListener{  // für Search
		
		private SearchDialog dialog;
		
		public SearchChangedListener(SearchDialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void caretUpdate(CaretEvent e) {
			dialog.updateList();
		}
		
	}
	
	public static SearchChangedListener makeSearchChangedListener(SearchDialog dialog) {
		ListenerClass container = new ListenerClass();
		return container. new SearchChangedListener(dialog);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class ExploreItemListener implements MouseListener{
		
		private ArrayList<Integer> adapterList;  // "Adapter-Liste", die die Listen von Search und Find Similar in die Orginalliste "übersetzen"
		private boolean isMainList;
		
		public ExploreItemListener() {
			adapterList = null;
			isMainList = true;
		}
		
		public ExploreItemListener(ArrayList<Integer> list) {
			adapterList = list;
			isMainList = false;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int amountOfClicks = e.getClickCount();
			if (amountOfClicks >= 2) {
				JList list = (JList) e.getComponent();
				int selected = list.getSelectedIndex();
				if (isMainList) {
					ListItem item = ProjectHolder.getListHolder().getListItemByIndex(selected);
					try {
						Desktop.getDesktop().open(new File(item.getPath()));
					} catch (IOException e1) {
						MessageBox message = new MessageBox("Directory can't be find");
						LoggingClass.makeLog(e1.getMessage(), 'i');
					}
				}
				else {
					System.out.println(adapterList.get(0));
					ListItem item = ProjectHolder.getListHolder().getListItemByIndex(adapterList.get(selected));
					try {
						Desktop.getDesktop().open(new File(item.getPath()));
					} catch (IOException e1) {
						MessageBox message = new MessageBox("Directory can't be find");
						LoggingClass.makeLog(e1.getMessage(), 'i');
					}
				}
			}
			
			
		}
		
		public void updateListener(ArrayList<Integer> list) {
				adapterList = list;
			}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// wird nicht gebraucht
		}
		
	}
	
	public static ExploreItemListener makeExploreItemListener() {
		ListenerClass container = new ListenerClass();
		return container. new ExploreItemListener();
	}
	
	public static ExploreItemListener makeExploreItemListener(ArrayList<Integer> adapterList) {
		ListenerClass container = new ListenerClass();
		return container. new ExploreItemListener(adapterList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private class CancelOptionListener implements ActionListener{
		
		private JDialog dialog;
		
		public CancelOptionListener(JDialog dialog) {
			this.dialog = dialog;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			dialog.dispose();
		}
		
	}
	
	public static CancelOptionListener makeCancelOptionListener(JDialog dialog) {
		ListenerClass container = new ListenerClass();
		return container. new CancelOptionListener(dialog);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	private class ShowDeleteSuperDirectoryDialogListener implements MouseListener{
		
		private MainFrame frame;
		
		public ShowDeleteSuperDirectoryDialogListener(MainFrame frame) {
			this.frame = frame;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int amountOfClicks = e.getClickCount();
			if (amountOfClicks >= 2) {
				JList list = (JList) e.getComponent();
				if (list.getSelectedIndex() != -1) {
					RemoveDirectoryDialog dialog = new RemoveDirectoryDialog(frame, list.getSelectedIndex());
				}
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// wird nicht gebraucht
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// wird nicht gebraucht
		}
		
	}
	public static ShowDeleteSuperDirectoryDialogListener makeShowDeleteSuperDirectoryDialogListener(MainFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new ShowDeleteSuperDirectoryDialogListener(frame);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class FrameSizeChangedListener implements ComponentListener{

		@Override
		public void componentHidden(ComponentEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void componentMoved(ComponentEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void componentResized(ComponentEvent e) {
			MainFrame frame = (MainFrame) e.getComponent();
			frame.resizeScrollLists();
		}

		@Override
		public void componentShown(ComponentEvent arg0) {
			// wird nicht gebraucht
		}
		
	}
	
	public static FrameSizeChangedListener makeFrameSizeChangedListener() {
		ListenerClass container = new ListenerClass();
		return container. new FrameSizeChangedListener();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private class FrameStateChangedListener implements WindowStateListener{

		@Override
		public void windowStateChanged(WindowEvent e) {
			MainFrame frame = (MainFrame) e.getWindow();
			frame.resizeScrollLists();
		}
		
	}
	
	public static FrameStateChangedListener makeFrameStateChangedListener() {
		ListenerClass container = new ListenerClass();
		return container. new FrameStateChangedListener();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public class ListItemKeyListener implements KeyListener{
		
		private boolean isMain;
		private ArrayList<Integer> adapterList;
		private MainFrame frame;
		private UpdateDialog dialog;
		
		public ListItemKeyListener(MainFrame frame) {
			isMain = true;
			adapterList = null;
			dialog = null;
			this.frame = frame;
		}
		
		public ListItemKeyListener(UpdateDialog dialog, ArrayList<Integer> list) {
			isMain = false;
			adapterList = list;
			frame = null;
			this.dialog = dialog;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if (e.getKeyChar() == '\n') {  // Multi-Explore-Funktion
				JList list = (JList) e.getComponent();
				int[] indeces = list.getSelectedIndices();
				for (int i = 0; i < indeces.length; i++) {
					ListItem item;
					if (isMain) {
						item = ProjectHolder.getListHolder().getListItemByIndex(indeces[i]);
					}
					else {
						item = ProjectHolder.getListHolder().getListItemByIndex(adapterList.get(indeces[i]));
					}
					try {
						Desktop.getDesktop().open(new File(item.getPath()));
					} catch (IOException e1) {
						LoggingClass.makeWarningLog(e1.getMessage());
					}
				}
			}
			else if (e.getKeyCode() == KeyEvent.VK_DELETE) {  // Löschfunktion
				JList list = (JList) e.getComponent();
				int[] indeces = list.getSelectedIndices();
				if (indeces.length == 0) {
					return;
				}
				if (isMain) {
					DeleteSubDirectoryDialog deleteDialog = new DeleteSubDirectoryDialog(frame, null, indeces);
				}
				else {
					int[] newList = new int[indeces.length];
					for (int i = 0; i < newList.length; i++) {
						newList[i] = adapterList.get(indeces[i]);
					}
					
					
					DeleteSubDirectoryDialog deleteDialog = new DeleteSubDirectoryDialog(null, dialog, newList);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// wird nicht gebraucht
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// wird nicht gebraucht
		}
		
		public void updateListener(ArrayList<Integer> list){
			adapterList = list;
		}
		
	}
	
	public static ListItemKeyListener makeListItemKeyListener(MainFrame frame) {
		ListenerClass container = new ListenerClass();
		return container. new ListItemKeyListener(frame);
	}
	
	public static ListItemKeyListener makeListItemKeyListener(UpdateDialog dialog, ArrayList<Integer> adapterList) {
		ListenerClass container = new ListenerClass();
		return container. new ListItemKeyListener(dialog, adapterList);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static class DeleteDirectoryFromSystemListener implements ActionListener{
		
		private JList list;
		private MainFrame frame;
		
		
		public DeleteDirectoryFromSystemListener(MainFrame frame, JList list) {
			this.list = list;
			this.frame = frame;
			
		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			int[] indeces = list.getSelectedIndices();
			if (indeces.length == 0) {
				return;
			}
			DeleteSubDirectoryDialog dialog = new DeleteSubDirectoryDialog(frame, null, indeces);
		}

		
		
		
	}
	
	public static DeleteDirectoryFromSystemListener makeDeleteDirectoryFromSystemListener(MainFrame frame, JList list) {
		return new DeleteDirectoryFromSystemListener(frame, list);
	}
}
