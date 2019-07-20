
import java.applet.Applet;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class GUI extends Applet implements ActionListener,TableCellRenderer {

	TextField moveXTxt = new TextField("200", 15), moveYTxt = new TextField("220", 15), repetitionTxt = new TextField("1", 15), intervalTxt = new TextField("0", 15);
	TextArea console = new TextArea("", 20, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
	String consoleText = "";
	Choice actionDropdown = new Choice();
	Button run = new Button("Click");
	
	Object[][] tableContents = {
			{"1", "1", "1"},
			{"1", "1", "1"}
	};
	
	String[] tableHeaders = 
		{ "Action", "Location (x, y)", "RGB" };
	
	
	int count = 0;
	int x, y, repeat = 0, interval;
	String typeClick;
	boolean boolInterval = true;
	DefaultTableModel model = new DefaultTableModel(); 
	JTable table = new JTable(model);
//	JTable table = new JTable(tableContents, tableHeaders);

	public void init() {
		setSize(300, 500);
		
		add(new Label("Enter x coordinate:"));
		add(moveXTxt);
		add(new Label("Enter y coordinate:"));
		add(moveYTxt);
		add(new Label("How many times:"));
		add(repetitionTxt);
		add(new Label("Interval clicks (ms):")); //Wait inbetween each clicks.
		add(intervalTxt);
		add(new Checkbox("Have interval?", boolInterval));

		
		add(actionDropdown);
		actionDropdown.addItem("Left click");
		actionDropdown.addItem("Right click");
		actionDropdown.addItem("Middle click");

		add(run);
		run.addActionListener(this);
//		add(console);
		
	
		model.addColumn(tableHeaders[0]);
		model.addColumn(tableHeaders[1]);
		model.addColumn(tableHeaders[2]);
		add(table);

//		table.setDoubleBuffered(true);
		this.add(new JScrollPane(table));
		table.setVisible(true);

//		pack();
//		setVisible(true);
		
//		add(  new JTable(tableContents, tableHeaders) );

	}

	public void paint(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == run) {

			if (!moveXTxt.getText().isEmpty() && !moveYTxt.getText().isEmpty() && !repetitionTxt.getText().isEmpty() && !intervalTxt.getText().isEmpty()) {

				x = Integer.parseInt(moveXTxt.getText());
				y = Integer.parseInt(moveYTxt.getText());
				repeat = Integer.parseInt(repetitionTxt.getText());
				interval = Integer.parseInt(repetitionTxt.getText());
				
				System.out.println("X: " + x + " Y: " + y);

				switch (actionDropdown.getSelectedIndex()) {
				case 0:
					for(int i = 0; i < repeat; i++)
					try {
						consoleAdd(getTypeAction());
						mouseMove();
							leftclick();
						} catch (Exception e2) {}
					break;
				case 1:
					try {
						consoleAdd(getTypeAction());
						mouseMove();
						rightclick();
						} catch (Exception e2) {}
					break;
				case 2:
					try {
						consoleAdd(getTypeAction());
						mouseMove();
						middleclick();
						} catch (Exception e2) {}
					break;

				}
				Color newColor = new Color(255,25,25);
				addRow(getTypeAction(), printXY(), "placeholder");
				console.setText("" + consoleText);
			}
		}
	}
	
	public String getTypeAction() {
		String typeAction = "";
		switch (actionDropdown.getSelectedIndex()) {
		case 0:
			typeAction = "Left click";
			break;
		case 1:
			typeAction = "Right click";
			break;
		case 2:
			typeAction = "Middle click";
			break;

		}
		return typeAction;
	}

	public void consoleAdd(String typeClick) {
		Date date = new Date();
		consoleText += time() + " | " + typeClick + " at " + x + ", " + y + "\n";
	}
	
	public String time() {
		String time = "";
		Date date = new Date();
		
		return time = date.toString().substring(11, 19 - 3);
		
	}
	public void mouseMove() throws AWTException {
		Robot r = new Robot();
		r.mouseMove(x, y);
	}
	
	public void mouseMove(int x, int y) throws AWTException {
		Robot r = new Robot();
		r.mouseMove(x, y);
	}

	public void leftclick() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON1_MASK);
		r.mouseRelease(InputEvent.BUTTON1_MASK);
		if(boolInterval)
			r.delay(interval);
	}

	public void rightclick() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON3_MASK);
		r.mouseRelease(InputEvent.BUTTON3_MASK);
		if(boolInterval)
			r.delay(interval);
	}
	
	public void middleclick() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON2_MASK);
		r.mouseRelease(InputEvent.BUTTON2_MASK);
		if(boolInterval)
			r.delay(interval);
	}
//	public void delay()
	
	public void addRow(String s, String s1, String s2) {
		model.addRow(new Object[] {s, s1, s2});
	}

	public String printXY() {
		return x + ", " + y;
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		return null;
	}
}
