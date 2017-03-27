package domain;

public class Player {
	private String name;
	private float money;
	private int jumps;
	private int cards;
	private int universityStudents;
	
	public Player(String name){
		this.name=name;
		this.money=0;
		this.jumps=2;
		this.cards=1;
		this.universityStudents=1;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getJumps() {
		return jumps;
	}
	public void setJumps(int jumps) {
		this.jumps = jumps;
	}
	public int getCards() {
		return cards;
	}
	public void setCards(int cards) {
		this.cards = cards;
	}
	public int getUniversityStudents() {
		return universityStudents;
	}
	public void setUniversityStudents(int universityStudents) {
		this.universityStudents = universityStudents;
	}
	
	public float getNextMoney(){
		return getMoney()+100000;
	}
}
