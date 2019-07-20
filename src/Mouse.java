import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

	private int MouseX;
	private int MouseY;
	
	int newX, newY, oldX, oldY;

	
	public Mouse(int x, int y) {
		this();
		this.MouseX = x;
		this.MouseY = x;
		this.setMouseX(x);
		this.setMouseY(y);
	}
		
	
	
	public Mouse() {
		
	}
	
	
	public int getMouseX() {
		return MouseY;
	}

	public void setMouseX(int mouseX) {
		MouseX = mouseX;
	}
	
	
	public int getMouseY() {
		return MouseY;
	}

	public void setMouseY(int mouseY) {
		MouseY = mouseY;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = newX;
		setMouseY(newY);
		
		newX = e.getX();
		newY = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}



}
