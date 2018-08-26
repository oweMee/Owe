import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;

// soll kurz Nachricht einblenden
public class MessageBox extends JDialog {

	public MessageBox(String message){
		this.setTitle("Info");
		this.setSize(500, 300);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		FlowLayout layout = new FlowLayout();
		//layout.setHgap(100);
		//layout.setVgap(100);
		layout.setAlignment(FlowLayout.CENTER);
		this.setLayout(new FlowLayout());
		JLabel label = new JLabel(message);
		this.add(label);
		
		this.setVisible(true);
	}
}
