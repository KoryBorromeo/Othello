package com.MarqusKoryEdwin.Othello;

// Kory Borromeo
// Marqus Pino
// Edwin Reyes

import java.awt.Canvas;



public class Othello extends Canvas implements Runnable
{
	
	private static final long serialVersionUID = 1273281369632494595L;

	public final int WIDTH = 1280, HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	
	public Othello()
	{
		new Menu(WIDTH,HEIGHT,"Othello",this);
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		long lastTime = System.nanoTime();			// time in-game in nanoseconds
		double amountOfTicks = 60.0;				// limit game frames per second to 60
		double ns = 1000000000 / amountOfTicks;
		double x = 0;
		long timer = System.currentTimeMillis();	// time in-game in milliseconds
		int frames = 0;
		while(running)								// while the program is running
		{											
			long now = System.nanoTime();			
			x += (now - lastTime) / ns;
			lastTime = now;
			while(x >= 1)
			{
				tick();
				x--;
			}
			if(running)
			{
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void render()
	{	
		
	}

	private void tick() 
	{
		
	}

	public static void main(String args[])
	{
		new Othello();
	}
}
