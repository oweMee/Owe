import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

// fragt vorher ab, ob du wirkliche löschen möchtest
public class DeleteSubDirectoryDialog extends JDialog {
	
	private MainFrame frame;
	private int[] indeces;
	private UpdateDialog dialog = null;
	
	private class OptionListener implements ActionListener{
		
		private boolean option;
		
		public OptionListener(boolean option) {
			this.option = option;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (option) {
				for (int i = 0; i < indeces.length; i++) {
					ProjectHolder.getListHolder().deleteSubDirectoryByPosition(indeces[i]);
					if(dialog != null) {
						dialog.updateList();
					}
					else {
						frame.refreshFrame();
					}
				}
				closeDialog();
			}
			else {
				closeDialog();
			}
		}
		
	}
	
	private void closeDialog() {
		this.dispose();
	}

	public DeleteSubDirectoryDialog(MainFrame frame, UpdateDialog dialog, int[] indeces) {
		this.dialog = dialog;
		this.frame = frame;
		this.indeces = indeces;
		
		this.setLayout(null);
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		
		String message = "Do you really want to delete " + indeces.length + " Subdiretories? This can\'t be undone.";
		JLabel messageLabel = new JLabel(message);
		messageLabel.setBounds(75, 50, 400, 40);
		this.add(messageLabel);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new OptionListener(true));
		deleteButton.setBounds(50, 175, 150, 40);
		this.add(deleteButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new OptionListener(false));
		cancelButton.setBounds(300, 175, 150, 40);
		this.add(cancelButton);
		
		this.setVisible(true);
	}
}
