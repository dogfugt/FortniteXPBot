/*import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings({"serial","Duplicates"})

public class Applet extends Canvas {
	
	private JTable table;
	
	String[] title = { "super nice", "very nice", "Made by Brendan Edser"};
	String randTitle = title[(int) (Math.random() * title.length)];
	
	String[] headers = { "Action", "Coordinates (x, y)", "RGB (r, g, b)"};
	String[][] data = { { "Left Click", "50, 60", RSm.rockCakeRGB.toString() },
						{	"hi"	}
	};
	
	public static RSMethods RSm = new RSMethods();

	public Main main = new Main();

	
	public Applet(int width, int height) {
		JFrame frame = new JFrame("Computer vision - " + randTitle);
		JPanel panel = new JPanel();
//		table = new JTable(data, headers);
//		panel.setPreferredSize(new Dimension(width,height));

//		main.setPreferredSize(new Dimension(width, height));
//		main.setMaximumSize(new Dimension(width, height)); // dont let user resize
//		main.setMinimumSize(new Dimension(width, height));

//		frame.add(table);
//		frame.add(main);
		frame.pack();
		frame.getContentPane().add(panel);

		frame.setResizable(false);


		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	

	}
}
*/