package ScreenComponents;

import java.awt.event.KeyEvent;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class PasswordField extends TextField{
	String Password = "";
	public PasswordField(int baseX, int baseY, int width, int height) 
	{
		super(baseX, baseY,width, height);
	}
	public String getText()
	{
		return Password;
	}
	public void writeFunction(KeyEvent e)
	{
		if(!Selected)
			return;
		if(Text.length() < 30)
		{
			switch(e.getKeyCode()){
			case KeyEvent.VK_A: Password+="A"; Text+="*"; break;
			case KeyEvent.VK_B: Password+="B"; Text+="*"; break;
			case KeyEvent.VK_C: Password+="C"; Text+="*"; break;
			case KeyEvent.VK_D: Password+="D"; Text+="*"; break;
			case KeyEvent.VK_E: Password+="E"; Text+="*"; break;
			case KeyEvent.VK_F: Password+="F"; Text+="*"; break;
			case KeyEvent.VK_G: Password+="G"; Text+="*"; break;
			case KeyEvent.VK_H: Password+="H"; Text+="*"; break;
			case KeyEvent.VK_I: Password+="I"; Text+="*"; break;
			case KeyEvent.VK_J: Password+="J"; Text+="*"; break;
			case KeyEvent.VK_K: Password+="K"; Text+="*"; break;
			case KeyEvent.VK_L: Password+="L"; Text+="*"; break;
			case KeyEvent.VK_M: Password+="M"; Text+="*"; break;
			case KeyEvent.VK_N: Password+="N"; Text+="*"; break;
			case KeyEvent.VK_O: Password+="O"; Text+="*"; break;
			case KeyEvent.VK_P: Password+="P"; Text+="*"; break;
			case KeyEvent.VK_Q: Password+="Q"; Text+="*"; break;
			case KeyEvent.VK_R: Password+="R"; Text+="*"; break;
			case KeyEvent.VK_S: Password+="S"; Text+="*";  break;
			case KeyEvent.VK_T: Password+="T"; Text+="*"; break;
			case KeyEvent.VK_U: Password+="U"; Text+="*"; break;
			case KeyEvent.VK_V: Password+="V"; Text+="*"; break;
			case KeyEvent.VK_W: Password+="W"; Text+="*"; break;
			case KeyEvent.VK_X: Password+="X"; Text+="*"; break;
			case KeyEvent.VK_Y: Password+="Y"; Text+="*"; break;
			case KeyEvent.VK_Z: Password+="Z"; Text+="*"; break;
			case KeyEvent.VK_0: Password+="0"; Text+="*"; break;
			case KeyEvent.VK_1: Password+="1"; Text+="*"; break;
			case KeyEvent.VK_2: Password+="2"; Text+="*"; break;
			case KeyEvent.VK_3: Password+="3"; Text+="*"; break;
			case KeyEvent.VK_4: Password+="4"; Text+="*"; break;
			case KeyEvent.VK_5: Password+="5"; Text+="*"; break;
			case KeyEvent.VK_6: Password+="6"; Text+="*"; break;
			case KeyEvent.VK_7: Password+="7"; Text+="*"; break;
			case KeyEvent.VK_8: Password+="8"; Text+="*"; break;
			case KeyEvent.VK_9: Password+="9"; Text+="*"; break;
			case KeyEvent.VK_SPACE: Password+=" "; Text+="*"; break;
			}
		}
		if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE)
		{
			Password=(Password.length() != 0)?Password.substring(0,Password.length()-1):Text;
			Text=(Text.length() != 0)?Text.substring(0,Text.length()-1):Text;
		}
	}
}
