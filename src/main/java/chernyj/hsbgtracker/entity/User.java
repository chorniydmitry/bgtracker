package chernyj.hsbgtracker.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends Model {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "btag")
	private int bTag;
	
	

	public User() {
		super();
	}

	public User(Long id) {
		super(id);
	}
	
	public User(String name, int bTag) {
		this.name = name;
		this.bTag = bTag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getbTag() {
		return bTag;
	}

	public void setbTag(int bTag) {
		this.bTag = bTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bTag;
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
		User other = (User) obj;
		if (bTag != other.bTag)
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
		return "User [name=" + name + ", bTag=" + bTag + "]";
	}
	
	
}
