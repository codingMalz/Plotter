/*
 *  @author Jerome Bastien
 *  @credit Owen McCloe
 */

public class Main {
	static int mainFrameWidth = 1000, mainFrameHeight = 1000;
	static int toolsFrameWidth = 900, toolsFrameHeight = 60;
	static int listFrameWidth = 300, listFrameHeight = 1000;
	
	static MainFrame mainFrame;
	static ToolsFrame toolsFrame;
	static ListFrame listFrame;
	
	public static void main(String[] args) {
		mainFrame = new MainFrame(mainFrameWidth, mainFrameHeight);
		toolsFrame = new ToolsFrame(toolsFrameWidth, toolsFrameHeight);
		listFrame = new ListFrame(listFrameWidth, listFrameHeight);
	}
}
