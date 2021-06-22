package Game;

import java.io.Serializable;
/*
 * TANK PROJECT
 * @author : Ali Hussain
 */
public class PlayerData implements Serializable, Comparable<PlayerData>{
	private String name;
	private String Password;
	private int speed;
	private int health;
	private int fireDmg;
	private int wins;
	public PlayerData(String name, String Password)
	{
		this.name = name;
		this.Password = Password;
		this.speed = 2;
		this.health = 100;
		this.fireDmg = 5;
		this.wins = 0;
	}
	public PlayerData(String name, String Password, int speed, int health, int fireDmg, int wins) {
		this.name = name;
		this.Password = Password;
		this.speed = speed;
		this.health = health;
		this.fireDmg = fireDmg;
		this.wins = wins;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getFireDmg() {
		return fireDmg;
	}
	public void setFireDmg(int fireDmg) {
		this.fireDmg = fireDmg;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Override
	public int compareTo(PlayerData o) {
		return o.wins - wins;
	}
	
	
}
