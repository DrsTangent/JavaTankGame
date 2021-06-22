package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;

import ScreenComponents.HUDSdisplay;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class Game extends Canvas implements Runnable, MouseListener, KeyListener {

    public static final int SQUARE = 64;                                                      
    public static final int FACTOR = 10;                                                       
    public static final int WIDTH = SQUARE*FACTOR;                                  
    public static final int HEIGHT = SQUARE*FACTOR;                                             
    public static final int SCALE = 1;                                                
    public final String TITLE = "The Tank Game";                                            

    private boolean running = false;                                                           
    private Thread thread;                                                          

    //Variables Declaration//
    private BufferedImage background[] = new BufferedImage[4];
    
    public static playersList playersData = new playersList();
    
    private Starter startGame = new Starter();
    public static Tank t1 = new Tank(WIDTH/4, HEIGHT/4, 0, 60, 60, "/player1Tank.png");
    public static Tank t2 = new Tank(WIDTH/2, HEIGHT/2, 0, 60, 60, "/player2Tank.png");
    public static TankAmmo t1Ammo = new TankAmmo(t1, t2, "/missile2.png") ;
    public static TankAmmo t2Ammo = new TankAmmo(t2, t1, "/missile.png") ;
    
    
    public static enum STATE{   
    	STARTMENU(0),
        GAME(1),  
        LEADERBOARD(2),
        WINNERBOARD(3);
    	
    	private int stateNum;
    	
    	STATE(int num)
    	{
    		this.stateNum = num;
    	}
    	
    	public int getValue()
    	{
    		return stateNum;
    	}
    };
    public static STATE state = STATE.STARTMENU;                                                 

    public void init(){                                                                        
        requestFocus();  
        
        playersData.loadData();
        
        WinnerScreen.setColors();
        t1.setEnemyTank(t2);
        t2.setEnemyTank(t1);
        //BufferedImageLoader loader = new BufferedImageLoader();                           
        try{
            background[STATE.STARTMENU.getValue()] = ImageIO.read(getClass().getResource("/startMenuBackground.png"));
            background[STATE.GAME.getValue()] = ImageIO.read(getClass().getResource("/Background2.jpg")); 
            background[STATE.LEADERBOARD.getValue()] = ImageIO.read(getClass().getResource("/startMenuBackground.png"));
            background[STATE.WINNERBOARD.getValue()] = ImageIO.read(getClass().getResource("/startMenuBackground.png"));
        }
        catch (IOException e){                             
            e.printStackTrace();
        }

        this.addKeyListener(this);
        this.addMouseListener(this);
    }


    public synchronized void start(){                                                         
        if(running){                                                                          
            return;
        }
        running = true;                                                                        
        thread = new Thread(this);                                                             
        thread.start();                                                                       
    }

    private synchronized void stop(){                                                         
        if(!running) {                                                                         
            return;
        }

        running = false;
        try {
            thread.join();                                                                    
        }
        catch (InterruptedException e){                                                         
            e.printStackTrace();
        }
        System.exit(1);                                                                        
    }



    public void run(){                                                                        
        init();                                                                                
        long lastTime = System.nanoTime();                                                     
        final double amountOfTicks = 60.0;                                                     
        double ns = 1000000000 / amountOfTicks;                                                
        double delta = 0;                                                                      
        int updates = 0;                                                                        
        int frames = 0;
        long timer = System.currentTimeMillis();                                                


        while(running){                                                                        
            long now = System.nanoTime();                                                       
            delta += (now - lastTime) / ns;                                                     
            lastTime = now;                                                                    
            if(delta >=1){    
            	updating();
            	gameOverCheck();                                                                        
                updates++;                                                                     
                delta--;                                                                       
            }
            t1Ammo.update();
            t2Ammo.update();
            render();
            frames++;                                                                          

           
            if(System.currentTimeMillis() - timer > 1000){                                     
                timer += 1000;                                                                 
                updates = 0;                                                                   
                frames = 0;
            }
        }
        stop();                                                                                 
    }
//updating Information//
    private void updating()
    {
    	if(state==STATE.GAME) { 
        	t1.playerFunctioning();
        	t2.playerFunctioning();
        }
    }
//Checking When to End the Game
    private void gameOverCheck(){                                                                       
        if(state==STATE.GAME) { 
        	if(t1.getHealth() <= 0)
        	{
        		startGame.p2.setWins(startGame.p2.getWins() + 1);
        		state=STATE.WINNERBOARD;
        		playersData.saveData();
        		WinnerScreen.winner = startGame.p2;
        	}
        	else if(t2.getHealth() <= 0)
        	{
        		startGame.p1.setWins(startGame.p1.getWins() + 1);
        		state=STATE.WINNERBOARD;
        		playersData.saveData();
        		WinnerScreen.winner = startGame.p1;
        	}
        }
    }
    
// Rendering Data //
    private void render(){                                                                     
        BufferStrategy bs = this.getBufferStrategy();                                           
        if (bs == null){
            createBufferStrategy(3);                                                            
            return;
        }

        Graphics g = bs.getDrawGraphics();                                                     
        g.drawImage(background[state.getValue()], 0, 0, Game.WIDTH, Game.HEIGHT, null);                                                   

        // My Own Code //
        if(state == STATE.GAME) {
        	HUDSdisplay.renderHealths(g, t1, t2);
        	t1.render(g);
        	t2.render(g);
        	t1Ammo.render(g);
        	t2Ammo.render(g);
        }
        else if(state == STATE.STARTMENU){                                                             
        	startGame.render(g);
        	
        }
        else if(state == STATE.LEADERBOARD)
        {
        	LeaderBoard.showTable(g);
        }
        else if(state == STATE.WINNERBOARD)
        {
        	WinnerScreen.render(g);
        }
        

        ////// ends here
        g.dispose();                                                                            
        bs.show();                                                                              
    }

//KeyBoard Events
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();   
        if(state == STATE.GAME)
        {
	        switch(key)
	    	{
	        	//Player 1 Controls// Top, Right, Bottom, Left, Enter
		    	case KeyEvent.VK_RIGHT:                                                    
		            t2.rotateDown(3);
		            break;
		    	case KeyEvent.VK_LEFT:                                               
		    		t2.rotateUP(3);
		    		break;
		    	case KeyEvent.VK_DOWN:
		    		t2.move(-4);
		            break;
		    	case KeyEvent.VK_UP:     
		    		t2.move(4);
		            break;
		    	case KeyEvent.VK_ENTER:
		    		t2Ammo.fire();
		            break;
		        //Player 2 Controls// W, D, S, A, F
		    	case KeyEvent.VK_D:
		    		t1.rotateDown(3);
		    		break;
		    	case KeyEvent.VK_A:
		    		t1.rotateUP(3);
		    		break;
		    	case KeyEvent.VK_S:
		    		t1.move(-4);
		    		break;
		    	case KeyEvent.VK_W:
		    		t1.move(4);
		    		break;
		    	case KeyEvent.VK_F:
		    		t1Ammo.fire();
		            break;
	    	}
        }
        else if(state == STATE.STARTMENU)
        {
        	startGame.t1.writeFunction(e);
        	startGame.t2.writeFunction(e);
        	startGame.pass1.writeFunction(e);
        	startGame.pass2.writeFunction(e);
        }
    }

    public void keyReleased(KeyEvent e){                                                        
        int key = e.getKeyCode();                                                               
        switch(key)
    	{
        	//Player 1 Controls// Top, Right, Bottom, Left, Enter
	    	case KeyEvent.VK_RIGHT:                                                    
	    		t2.rotateDown(0);
	            break;
	    	case KeyEvent.VK_LEFT:                                               
	    		t2.rotateUP(0);
	    		break;
	    	case KeyEvent.VK_DOWN:
	    	case KeyEvent.VK_UP:     
	    		t2.move(0);
	            break;
	        //Player 2 Controls// W, D, S, A, F
	    	case KeyEvent.VK_D:
	    		t1.rotateDown(0);
	    		break;
	    	case KeyEvent.VK_A:
	    		t1.rotateUP(0);
	    		break;
	    	case KeyEvent.VK_S:
	    	case KeyEvent.VK_W:
	    		t1.move(0);
	    		break;
    	}
    }
    
    @Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
    

//MOUSE EVENTS//
	@Override
	public void mouseClicked(MouseEvent e) {
		int key = e.getButton();
		if (state == STATE.STARTMENU)
		{
			if(key == MouseEvent.BUTTON1)
			{
				startGame.StartFunction(e.getX(), e.getY());
			}
		}
		else if (state == STATE.LEADERBOARD)
		{
			if(key == MouseEvent.BUTTON1)
			{
				LeaderBoard.StartFunction(e.getX(), e.getY());
			}
		}
		else if (state == STATE.WINNERBOARD)
		{
			if(key == MouseEvent.BUTTON1)
			{
				WinnerScreen.StartFunction(e.getX(), e.getY());
			}
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}

}
