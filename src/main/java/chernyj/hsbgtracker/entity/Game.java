package chernyj.hsbgtracker.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game extends Model {

	@Column(name = "timeStarted")
	private Date timeStarted;

	@Column(name = "timeFinished")
	private Date timeFinished;

	@Column(name = "place")
	private int place;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hero_id")
	private Hero hero;

	public Game() {
		super();
	}

	public Game(Long id) {
		super(id);
	}

	public Game(User user, Hero hero, int place, Date timeStarted, Date timeFinished) {
		super();
		this.user = user;
		this.hero = hero;
		this.place = place;
		this.timeStarted = timeStarted;
		this.timeFinished = timeFinished;
	}

	public Date getTimeStarted() {
		return timeStarted;
	}

	public void setTimeStarted(Date timeStarted) {
		this.timeStarted = timeStarted;
	}

	public Date getTimeFinished() {
		return timeFinished;
	}

	public void setTimeFinished(Date timeFinished) {
		this.timeFinished = timeFinished;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timeStarted == null) ? 0 : timeStarted.hashCode());
		result = prime * result + ((timeFinished == null) ? 0 : timeFinished.hashCode());
		result = prime * result + ((hero == null) ? 0 : hero.hashCode());
		result = prime * result + place;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Game other = (Game) obj;
		if (timeStarted == null) {
			if (other.timeStarted != null)
				return false;
		} else if (!timeStarted.equals(other.timeStarted))
			return false;
		if (timeFinished == null) {
			if (other.timeFinished != null)
				return false;
		} else if (!timeFinished.equals(other.timeFinished))
			return false;
		if (hero == null) {
			if (other.hero != null)
				return false;
		} else if (!hero.equals(other.hero))
			return false;
		if (place != other.place)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Game [timeStarted=" + timeStarted + ", timeFinished=" + timeFinished + ", place=" + place + ", user="
				+ user + ", hero=" + hero + "]";
	}

}
