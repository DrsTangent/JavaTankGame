package Game;
import java.awt.*;

/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public interface Entity {
	boolean withinBorder(double displacement);
	void position(int x, int y);
	void render(Graphics g);
}
