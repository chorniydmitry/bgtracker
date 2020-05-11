package chernyj.hsbgtracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hero")
public class Hero extends Model {
	@Column(name = "dbfId")
	long dbfId;
	@Column(name = "hsId")
	String hsId;
	@Column(name = "health")
	int health;
	@Column(name = "name")
	String name;
	
	public Hero() {
		super();
	}

	public Hero(Long id) {
		super(id);
	}
	
	
	public Hero(long dbfId, String hsId, int health, String name) {
		super();
		setDbfId(dbfId);
		setHsId(hsId);
		setHealth(health);
		setName(name);
	}


	public long getDbfId() {
		return dbfId;
	}


	public void setDbfId(long dbfId) {
		this.dbfId = dbfId;
	}


	public String getHsId() {
		return hsId;
	}


	public void setHsId(String hsId) {
		this.hsId = hsId;
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dbfId ^ (dbfId >>> 32));
		result = prime * result + health;
		result = prime * result + ((hsId == null) ? 0 : hsId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Hero other = (Hero) obj;
		if (dbfId != other.dbfId)
			return false;
		if (health != other.health)
			return false;
		if (hsId == null) {
			if (other.hsId != null)
				return false;
		} else if (!hsId.equals(other.hsId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Hero [dbfId=" + dbfId + ", hsId=" + hsId + ", health=" + health + ", name=" + name + "]";
	}
	
	

}
