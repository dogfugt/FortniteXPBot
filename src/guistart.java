
import java.applet.Applet;
import java.awt.AWTException;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.Robot;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Date;

@SuppressWarnings("serial")
public class guistart extends Applet implements ActionListener {

	TextField moveXtxt = new TextField("200", 15), moveYtxt = new TextField("155", 15), repetitionTxt = new TextField("1", 15);
	TextArea console = new TextArea("", 20, 30, TextArea.SCROLLBARS_VERTICAL_ONLY);
	String consoleText = "";
	Choice action = new Choice();
	Button run = new Button("Click");
	int count = 0;
	int x, y, repeat = 0;
	String typeClick;

	public void init() {
		setSize(300, 500);


		add(new Label("Enter x coordinate:"));
		add(moveXtxt);
		add(new Label("Enter y coordinate:"));
		add(moveYtxt);
		add(new Label("How many times:"));
		add(repetitionTxt);
		add(action); // FIXME
		action.addItem("Left click");
		action.addItem("Right click");
		action.addItem("Middle click");

		add(run);
		run.addActionListener(this);
		add(console);
	}

	public void paint(Graphics g) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == run) {

			if (!moveXtxt.getText().isEmpty() && !moveYtxt.getText().isEmpty() && !repetitionTxt.getText().isEmpty()) {

				x = Integer.parseInt(moveXtxt.getText());
				y = Integer.parseInt(moveYtxt.getText());
				repeat = Integer.parseInt(repetitionTxt.getText());
				
				System.out.println("X: " + x + " Y: " + y);

				switch (action.getSelectedIndex()) {
				case 0:
					for(int i = 0; i < repeat; i++)
					try {
						consoleAdd("Left click");
							mouseMove();
							leftclick();
						} catch (Exception e2) {}
					break;
				case 1:
					try {
						consoleAdd("Right click");
						mouseMove();
						rightclick();
						} catch (Exception e2) {}
					break;
				case 2:
					try {
						consoleAdd("Middle click");
						mouseMove();
						middleclick();
						} catch (Exception e2) {}
					break;

				}
				console.setText("" + consoleText);

			}
		}
	}

	public void consoleAdd(String typeClick) {
		Date date = new Date();
		consoleText += time() + " │ " + typeClick + " at " + x + ", " + y + "\n";
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
	}

	public void rightclick() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON3_MASK);
		r.mouseRelease(InputEvent.BUTTON3_MASK);
	}
	
	public void middleclick() throws AWTException {
		Robot r = new Robot();
		r.mousePress(InputEvent.BUTTON2_MASK);
		r.mouseRelease(InputEvent.BUTTON2_MASK);
	}

}
