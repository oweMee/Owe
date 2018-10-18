import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;



// Dialog-Fenster mit dem Suchfeld und -liste
public class SearchDialog extends JDialog implements UpdateDialog{

	private JList list = new JList(new DefaultListModel());
	private JTextField field;
	private ArrayList<Integer> adapterList;
	private ListenerClass.ExploreItemListener listener;
	private ListenerClass.ListItemKeyListener listener2;
	
	private MainFrame frame;
	
	public SearchDialog(MainFrame frame) {
		super(frame);
		this.frame = frame;
		this.setSize(600, 800);
		this.setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		listener = ListenerClass.makeExploreItemListener(adapterList);
		listener2 = ListenerClass.makeListItemKeyListener(this, adapterList);
		list.addMouseListener(listener);
		list.addKeyListener(listener2);
		//adapterList = new ArrayList<Integer>();
		
		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(598,600));
		this.add(scroll, BorderLayout.SOUTH);
		
		field = new JTextField();
		field.setPreferredSize(new Dimension(100,25));
		field.addCaretListener(ListenerClass.makeSearchChangedListener(this));
		
		
		
		JPanel northPanel = new JPanel();
		northPanel.add(field, BorderLayout.NORTH);
		
		this.add(northPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
	}
	
	public void updateList() {
		frame.refreshFrame();
		String search = field.getText();
		adapterList = ProjectHolder.getListHolder().searchItem(search);
		DefaultListModel<String> model = new DefaultListModel();
		for (int i = 0; i < adapterList.size(); i++) {
			String itemListName = ProjectHolder.getListHolder().getListItemInfoByIndex(adapterList.get(i));
			model.addElement(itemListName);
		}
		list.setModel(model);
		listener.updateListener(adapterList);
		listener2.updateListener(adapterList);
		this.revalidate();
	}
	
}
