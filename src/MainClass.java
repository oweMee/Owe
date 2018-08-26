
public class MainClass {

	public static void main(String[] args) {

		//SachenTesten.test();
		
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandlerClass());  // sorgt dafür, dass alle nicht abgefangenden Fehler gelogt werden
		
		if (args.length == 1) {
				if (args[0].endsWith(".owe")) {  // hier soll eine Datei schon von der Konsole oder exe Datei geladen werden können
					ProjectHolder.loadProject(args[0]);
					MainFrame mainFrame = new MainFrame();
					mainFrame.refreshFrame();
				}
			}
			else {  // normaler Start
				StartMenu start = new StartMenu();
			}
		
		
	}
	

}
