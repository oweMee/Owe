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
import javax.swing.JToolBar;


// Dialog Fenster für Find Similar
public class FindSimilarDialog extends JDialog implements UpdateDialog{
	
	private JList list = new JList(new DefaultListModel<String>());
	ArrayList<Integer> adapterList;
	ListenerClass.ExploreItemListener listener;
	ListenerClass.ListItemKeyListener listener2;

	public FindSimilarDialog(MainFrame frame) {
		super(frame);
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
		
		JToolBar toolBar = new JToolBar();
		JButton updateListButton = new JButton("Update List");
		updateListButton.addActionListener(ListenerClass.makeRefreshListListener(frame, this));
		toolBar.add(updateListButton);
		
		JPanel northPanel = new JPanel();
		northPanel.add(toolBar, BorderLayout.SOUTH);
		
		this.add(northPanel, BorderLayout.NORTH);
		
		this.setVisible(true);
		updateList();
		
	}
	
	public void updateList() {
		adapterList = ProjectHolder.getListHolder().findSimilar();
		
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
