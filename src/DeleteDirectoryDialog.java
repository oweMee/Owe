import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

// fragt, ob ein Directory gel�scht werden soll
public class DeleteDirectoryDialog extends JDialog {

	private MainFrame mainFrame;
	private int superDirectoryPosition;
	
	public DeleteDirectoryDialog(MainFrame mainFrame, int position) {
		super(mainFrame);
		this.mainFrame = mainFrame;
		superDirectoryPosition = position;
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		JLabel textLabel = new JLabel("Do you want to remove the selected Directory from the List?");
		textLabel.setBounds(100, 50, 400, 40);
		this.add(textLabel);
		
		JButton deleteButton = new JButton("Remove Directory");
		deleteButton.setBounds(50, 175, 200, 40);
		deleteButton.addActionListener(ListenerClass.makeDeleteSuperDirectoryListener(mainFrame, position, this));
		this.add(deleteButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(330, 175, 200, 40);
		cancelButton.addActionListener(ListenerClass.makeCancelOptionListener(this));
		this.add(cancelButton);
		
		
		this.setVisible(true);
	}
}
