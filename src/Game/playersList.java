package Game;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/*
 * TANK PROJECT
 * @author : Ali Hussain
 */

public class playersList {
	ArrayList<PlayerData> list;
	public playersList()
	{
		list = new ArrayList<PlayerData>();
	}
	public PlayerData search(String name, String pass)
	{
		for(int i = 0;  i<list.size(); i++)
		{
			if(list.get(i).getName().equalsIgnoreCase(name))
			{
				if(list.get(i).getPassword().equalsIgnoreCase(pass))
				{
					System.out.printf("Account of %s is retrieved\n", name);
					return list.get(i);
				}
				else
				{
					System.out.printf("Password for %s is wrong\n", name);
					return null;
				}
			}
		}
		
		System.out.printf("Account of %s is not retrieved, So New Account will be made with Same Data\n", name);
		PlayerData newPlayer = new PlayerData(name, pass);
		list.add(newPlayer);
		return newPlayer;
	}
	public void add(PlayerData pD)
	{
		list.add(pD);
	}
	public void loadData()
	{
		list = new ArrayList<PlayerData>();
		try 
		{ 
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("playersData.dat"));
			while (true)
			{ 
				PlayerData a = (PlayerData) objectInputStream.readObject(); 
				list.add(a);
			} 
		} 
		catch(EOFException e)
		{
			System.out.println("Data has been retrieved");
		}
		catch (ClassNotFoundException e)
		{
			e.getStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void saveData()
	{
		try
		{
			FileOutputStream file = new FileOutputStream("playersData.dat");
			ObjectOutputStream oos = new ObjectOutputStream(file);
			for(int i = 0; i<list.size(); i++)
			{
				oos.writeObject(list.get(i));
			}
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		System.out.println("Data has been saved");
	}
}
