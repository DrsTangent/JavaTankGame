package ScreenComponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class TextField extends TextBox {
	protected boolean Selected = false;
	public TextField(int baseX, int baseY) // default Button Size
	{
		super(baseX, baseY);
	}
	public TextField(int baseX, int baseY, int width, int height, String Text) 
	{
		super(baseX, baseY,width, height, Text);
	}
	public TextField(int baseX, int baseY, int width, int height) 
	{
		super(baseX, baseY,width, height);
	}
	public void select(float mouseX, float mouseY)
	{
		if(isInside(mouseX, mouseY))
		{
			Selected = true;
		}
		else
		{
			Selected = false;
		}
	}
	public boolean isInside(float mouseX, float mouseY)
	{
		if(mouseX > baseX && mouseX < baseX + width
				&& mouseY > baseY && mouseY < baseY + height)
			return true;
		else 
			return false;
	}
	public void writeFunction(KeyEvent e)
	{
		if(!Selected)
			return;
		if(Text.length() < 30)
		{
			switch(e.getKeyCode()){
			case KeyEvent.VK_A: Text+="A"; break;
			case KeyEvent.VK_B: Text+="B"; break;
			case KeyEvent.VK_C: Text+="C"; break;
			case KeyEvent.VK_D: Text+="D"; break;
			case KeyEvent.VK_E: Text+="E"; break;
			case KeyEvent.VK_F: Text+="F"; break;
			case KeyEvent.VK_G: Text+="G"; break;
			case KeyEvent.VK_H: Text+="H"; break;
			case KeyEvent.VK_I: Text+="I"; break;
			case KeyEvent.VK_J: Text+="J"; break;
			case KeyEvent.VK_K: Text+="K"; break;
			case KeyEvent.VK_L: Text+="L"; break;
			case KeyEvent.VK_M: Text+="M"; break;
			case KeyEvent.VK_N: Text+="N"; break;
			case KeyEvent.VK_O: Text+="O"; break;
			case KeyEvent.VK_P: Text+="P"; break;
			case KeyEvent.VK_Q: Text+="Q"; break;
			case KeyEvent.VK_R: Text+="R"; break;
			case KeyEvent.VK_S: Text+="S"; break;
			case KeyEvent.VK_T: Text+="T"; break;
			case KeyEvent.VK_U: Text+="U"; break;
			case KeyEvent.VK_V: Text+="V"; break;
			case KeyEvent.VK_W: Text+="W"; break;
			case KeyEvent.VK_X: Text+="X"; break;
			case KeyEvent.VK_Y: Text+="Y"; break;
			case KeyEvent.VK_Z: Text+="Z"; break;
			case KeyEvent.VK_0: Text+="0"; break;
			case KeyEvent.VK_1: Text+="1"; break;
			case KeyEvent.VK_2: Text+="2"; break;
			case KeyEvent.VK_3: Text+="3"; break;
			case KeyEvent.VK_4: Text+="4"; break;
			case KeyEvent.VK_5: Text+="5"; break;
			case KeyEvent.VK_6: Text+="6"; break;
			case KeyEvent.VK_7: Text+="7"; break;
			case KeyEvent.VK_8: Text+="8"; break;
			case KeyEvent.VK_9: Text+="9"; break;
			case KeyEvent.VK_SPACE: Text+=" "; break;
			}
		}
		if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
		{
			Text=(Text.length() != 0)?Text.substring(0,Text.length()-1):Text;
		}
	}
}
