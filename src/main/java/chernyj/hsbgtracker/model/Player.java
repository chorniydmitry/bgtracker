package chernyj.hsbgtracker.model;

import chernyj.hsbgtracker.entity.Hero;

public class Player {
	private int id;
	private String name;
	private String bTag;
	private int place;
	private Hero hero;
	private boolean isMainPlayer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public boolean isMainPlayer() {
		return isMainPlayer;
	}

	public void setMainPlayer(boolean isMainPlayer) {
		this.isMainPlayer = isMainPlayer;
	}

	public String getbTag() {
		return bTag;
	}

	public void setbTag(String bTag) {
		this.bTag = bTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bTag == null) ? 0 : bTag.hashCode());
		result = prime * result + ((hero == null) ? 0 : hero.hashCode());
		result = prime * result + id;
		result = prime * result + (isMainPlayer ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + place;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (bTag == null) {
			if (other.bTag != null)
				return false;
		} else if (!bTag.equals(other.bTag))
			return false;
		if (hero == null) {
			if (other.hero != null)
				return false;
		} else if (!hero.equals(other.hero))
			return false;
		if (id != other.id)
			return false;
		if (isMainPlayer != other.isMainPlayer)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (place != other.place)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", bTag=" + bTag + ", place=" + place + ", health=" + ", hero="
				+ hero + ", isMainPlayer=" + isMainPlayer + "]";
	}

}