import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

// Startmenü des Programmes
public class StartMenu extends JFrame {

	public StartMenu() {
		
		LookAndFeelClass.setLookAndFeel();
		this.setTitle("OWE");
		this.setSize(1000, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(255,255,255));
		this.setResizable(false);
		this.setIconImage(IconClass.getIcon());
		
		JButton newProjectButton = new JButton("New Project");
		newProjectButton.setBounds(200, 170, 200, 200);
		newProjectButton.addActionListener(ListenerClass.makeNewProjectListener(this));
		
		JButton loadProjectButton = new JButton("Load Project");
		loadProjectButton.setBounds(600, 170, 200, 200);
		loadProjectButton.addActionListener(ListenerClass.makeLoadProjectListener(this));
		
		this.add(newProjectButton);
		this.add(loadProjectButton);
		this.setVisible(true);
	}
}
