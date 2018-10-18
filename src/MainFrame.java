import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

// Hauptfenster des Programms
public class MainFrame extends JFrame{
	
	private JList listItemList = new JList(new DefaultListModel());
	private JList superDirectoryList = new JList(new DefaultListModel());
	
	private JScrollPane listItemListScroll;
	private JScrollPane superDirectoryListScroll;
	
	private JToolBar toolBar;

	public MainFrame() {
		
		LookAndFeelClass.setLookAndFeel();
		this.setTitle("OWE");
		this.setSize(1280, 1028);
		this.getContentPane().setBackground(new Color(255,255,255));
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setLayout(new BorderLayout());
		this.setLayout(null);
		this.addComponentListener(ListenerClass.makeFrameSizeChangedListener());
		this.addWindowStateListener(ListenerClass.makeFrameStateChangedListener());
		this.setIconImage(IconClass.getIcon());
		this.setJMenuBar(makeMenuBar());
		
		ListenerClass.ExploreItemListener listItemListListener = ListenerClass.makeExploreItemListener();
		listItemList.addMouseListener(listItemListListener);
		listItemList.addKeyListener(ListenerClass.makeListItemKeyListener(this));
		
		superDirectoryList.addMouseListener(ListenerClass.makeShowDeleteSuperDirectoryDialogListener(this));
		
		toolBar = makeToolBar();
		toolBar.setBounds(0, 0, 500, 40);
		this.add(toolBar);
		
		listItemListScroll = new JScrollPane(listItemList);
		listItemListScroll.setBounds(650, 104, 615, 850);  // vllt 104 zu 105 verändern bei beiden Scrolls
		this.add(listItemListScroll);
		
		superDirectoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		superDirectoryListScroll = new JScrollPane(superDirectoryList);
		superDirectoryListScroll.setBounds(0, 104, 615, 850);
		this.add(superDirectoryListScroll);
		
		/*
		JPanel listsPanel = new JPanel(new BorderLayout());
		listsPanel.setBackground(new Color(255,255,255));
		listItemListScroll = new JScrollPane(listItemList);
		listItemListScroll.setPreferredSize(new Dimension(600,875));  // größe der "sichbaren" Liste wird hier eingestellt
		listsPanel.add(listItemListScroll, BorderLayout.EAST);
		
		superDirectoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		superDirectoryListScroll = new JScrollPane(superDirectoryList);
		superDirectoryListScroll.setPreferredSize(new Dimension(600,875));
		listsPanel.add(superDirectoryListScroll, BorderLayout.WEST);
		add(listsPanel, BorderLayout.SOUTH);
		
		
		this.add(makeToolBar(), BorderLayout.NORTH);
		*/
		this.setVisible(true);
	}
	
	public void resizeScrollLists() {
		int frameWidthSize = this.getSize().width;
		int frameHeightSize = this.getSize().height;
		int newListWidth = (frameWidthSize * 615) / 1280;
		int newListHeight = frameHeightSize - 181;
		int newXPosition = frameWidthSize - newListWidth - 17;
		
		listItemListScroll.setBounds(newXPosition, 104, newListWidth, newListHeight);
		superDirectoryListScroll.setBounds(0, 104,  newListWidth, newListHeight);
		
	}
	
	private JMenuBar makeMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		newMenuItem.addActionListener(ListenerClass.makeNewProjectListener(this));
		fileMenu.add(newMenuItem);
		JMenuItem loadMenuItem = new JMenuItem("Load...");
		loadMenuItem.addActionListener(ListenerClass.makeLoadProjectListener(this));
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		fileMenu.addSeparator();
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.addActionListener(ListenerClass.makeSaveProjectListener());
		fileMenu.add(saveMenuItem);
		JMenuItem saveAsMenuItem = new JMenuItem("Save As...");
		saveAsMenuItem.addActionListener(ListenerClass.makeSaveAsProjectListener());
		fileMenu.add(saveAsMenuItem);
		fileMenu.addSeparator();
		JMenuItem printTOTxt = new JMenuItem("Print to .txt");
		printTOTxt.addActionListener(ListenerClass.makePrintToTXTListener());
		fileMenu.add(printTOTxt);
		
		return menuBar;
	}
	
	private JToolBar makeToolBar() {
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(new Color(255,255,255));
		toolBar.setFloatable(false);
		
		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(ListenerClass.makeSaveProjectListener());
		toolBar.add(saveButton);
		
		JButton addDirectoryButton = new JButton("Add Directory");
		addDirectoryButton.addActionListener(ListenerClass.makeAddSuperDirectoryListener(this));
		toolBar.add(addDirectoryButton);
		
		JButton updateListsButton = new JButton("Update Lists");
		updateListsButton.addActionListener(ListenerClass.makeRefreshListListener(this));
		toolBar.add(updateListsButton);
		
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(ListenerClass.makeShowSearchListener(this));
		toolBar.add(searchButton);
		
		JButton findSimilarButton = new JButton("Find Similars");
		findSimilarButton.addActionListener(ListenerClass.makeShowFindSimilarListener(this));
		toolBar.add(findSimilarButton);
		
		JButton deleteSubDirectoryButton = new JButton("Delete Subdirectory");
		deleteSubDirectoryButton.addActionListener(ListenerClass.makeDeleteDirectoryFromSystemListener(this, listItemList));
		toolBar.add(deleteSubDirectoryButton);
		
		return toolBar;
	}
	
	public void refreshFrame() {
		DefaultListModel listItemListModel = new DefaultListModel();
		for (int i = 0; i < ProjectHolder.getListHolder().getListItemListSize(); i++) {
			String itemInfo = ProjectHolder.getListHolder().getListItemInfoByIndex(i);
			listItemListModel.addElement(itemInfo);
		}
		
		DefaultListModel superDirectoryListModel = new DefaultListModel();
		for (int i = 0; i < ProjectHolder.getListHolder().getSuperDirectoryListSize(); i++) {
			String itemInfo = ProjectHolder.getListHolder().getSuperDirectoryItemNameByIndex(i);
			superDirectoryListModel.addElement(itemInfo);
		}
		
		listItemList.setModel(listItemListModel);
		superDirectoryList.setModel(superDirectoryListModel);
	}
}
