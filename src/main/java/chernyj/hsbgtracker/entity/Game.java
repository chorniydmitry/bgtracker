package chernyj.hsbgtracker.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game extends Model {
	
	@Column(name = "timeStarted")
	private Date timeStarted;

	@Column(name = "timeFinished")
	private Date timeFinished;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="result", fetch = FetchType.EAGER)
	private Set<Result> results;
	
	
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

	public Set<Result> getResults() {
		return results;
	}

	public void setResults(Set<Result> results) {
		this.results = results;
	}
	
}


