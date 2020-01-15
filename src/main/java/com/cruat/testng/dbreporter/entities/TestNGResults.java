package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "testng_results")
public class TestNGResults implements ReportEntity {
	
	private long id;
	private int passed;
	private int failed;
	private int skipped;
	private int total;
	private OffsetDateTime startDatetime;
	private OffsetDateTime endDateTime;
	
	private List<TestNGSuite> suites;
	
	public TestNGResults() {
		suites = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	
	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the passed
	 */
	@Column(name = "passed")
	public int getPassed() {
		return passed;
	}
	
	/**
	 * @param passed
	 *            the passed to set
	 */
	public void setPassed(int passed) {
		this.passed = passed;
	}
	
	/**
	 * @return the failed
	 */
	@Column(name = "failed")
	public int getFailed() {
		return failed;
	}
	
	/**
	 * @param failed
	 *            the failed to set
	 */
	public void setFailed(int failed) {
		this.failed = failed;
	}
	
	/**
	 * @return the skipped
	 */
	@Column(name = "skipped")
	public int getSkipped() {
		return skipped;
	}
	
	/**
	 * @param skipped
	 *            the skipped to set
	 */
	public void setSkipped(int skipped) {
		this.skipped = skipped;
	}
	
	/**
	 * @return the total
	 */
	@Column(name = "total")
	public int getTotal() {
		return total;
	}
	
	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	
	/**
	 * @return the startDatetime
	 */
	@Column(name = "start")
	public OffsetDateTime getStartDatetime() {
		return startDatetime;
	}
	
	/**
	 * @param startDatetime
	 *            the startDatetime to set
	 */
	public void setStartDatetime(OffsetDateTime startDatetime) {
		this.startDatetime = startDatetime;
	}
	
	/**
	 * @return the endDateTime
	 */
	@Column(name = "end")
	public OffsetDateTime getEndDateTime() {
		return endDateTime;
	}
	
	/**
	 * @param endDateTime
	 *            the endDateTime to set
	 */
	public void setEndDateTime(OffsetDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	
	/**
	 * @return the suites
	 */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "result")
	public List<TestNGSuite> getSuites() {
		return suites;
	}

	
	/**
	 * @param suites the suites to set
	 */
	public void setSuites(List<TestNGSuite> suites) {
		this.suites = suites;
	}
}
