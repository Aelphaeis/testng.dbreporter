package com.cruat.testng.dbreporter.entities;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "testng_suites")
public class TestNGSuite implements ReportEntity {

	private long id;
	private String name;
	private OffsetDateTime startDatetime;
	private OffsetDateTime endDateTime;
	private TestNGResults result;
	
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
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the startDatetime
	 */
	@Column(name = "start")
	public OffsetDateTime getStartDatetime() {
		return startDatetime;
	}
	
	/**
	 * @param startDatetime the startDatetime to set
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
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(OffsetDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}
	
	/**
	 * @return the resultId
	 */
	
	@ManyToOne(targetEntity = TestNGResults.class)
	@JoinColumn(name = "testng_results_id", referencedColumnName = "id")
	public TestNGResults getResult() {
		return result;
	}
	
	/**
	 * @param resultId the resultId to set
	 */
	public void setResult(TestNGResults result) {
		result.getSuites().add(this);
		this.result = result;
	}
}
