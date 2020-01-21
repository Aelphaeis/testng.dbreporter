package com.cruat.testng.dbreporter.entities;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.testng.ITestResult;

@Entity
@Table(name = "testng_methods")
public class TestNGMethod implements ReportEntity {
	
	private long id;
	private ITestResultStatus status;
	private boolean retried;
	private boolean isConfig;
	private String name;
	private String instanceName;
	private String description;
	
	private int order;
	private long duration;
	private OffsetDateTime start;
	private OffsetDateTime end;
	private TestNGClass group;

	public TestNGMethod() {}
	
	public TestNGMethod(ITestResult itr) {
		this();
		
		this.status = ITestResultStatus.valueOf(itr.getStatus());
		this.isConfig = !itr.getMethod().isTest();
		this.name = itr.getMethod().getMethodName();
		this.instanceName = itr.getInstanceName();
		this.description = itr.getMethod().getDescription();
		this.retried = itr.wasRetried();
		
		this.start = new Date(itr.getStartMillis())
				.toInstant()
				.atOffset(ZoneOffset.UTC);
		
		this.end = new Date(itr.getEndMillis())
				.toInstant()
				.atOffset(ZoneOffset.UTC);
		
		this.duration = Duration.between(start, end).toMillis();
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
	 * @return the status
	 */
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	public ITestResultStatus getStatus() {
		return status;
	}
	
	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(ITestResultStatus status) {
		this.status = status;
	}
	
	/**
	 * @return the retried
	 */
	@Column(name = "is_retried")
	public boolean isRetried() {
		return retried;
	}
	
	/**
	 * @param retried
	 *            the retried to set
	 */
	public void setRetried(boolean retried) {
		this.retried = retried;
	}
	
	/**
	 * @return the isConfig
	 */
	@Column(name = "is_config")
	public boolean isConfig() {
		return isConfig;
	}
	
	/**
	 * @param isConfig
	 *            the isConfig to set
	 */
	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}
	
	/**
	 * @return the name
	 */
	@Column(name = "name")
	public String getName() {
		return name;
	}
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the description
	 */
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the start
	 */
	@Column(name = "start")
	public OffsetDateTime getStart() {
		return start;
	}
	
	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(OffsetDateTime start) {
		this.start = start;
	}
	
	/**
	 * @return the end
	 */
	@Column(name = "end")
	public OffsetDateTime getEnd() {
		return end;
	}
	
	/**
	 * @param end
	 *            the end to set
	 */
	public void setEnd(OffsetDateTime end) {
		this.end = end;
	}
	
	/**
	 * @return the duration
	 */
	@Column(name = "duration")
	public long getDuration() {
		return duration;
	}
	
	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	/**
	 * @return the order
	 */
	@Column(name = "method_order")
	public int getOrder() {
		return order;
	}
	
	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * @return the group
	 */
	@ManyToOne(targetEntity = TestNGClass.class)
	@JoinColumn(name = "testng_class_id", referencedColumnName = "id")
	public TestNGClass getGroup() {
		return group;
	}
	
	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(TestNGClass group) {
		this.group = group;
	}
	
	/**
	 * @return the instanceName
	 */
	@Column(name = "instance_name")
	public String getInstanceName() {
		return instanceName;
	}
	
	/**
	 * @param instanceName
	 *            the instanceName to set
	 */
	public void setInstanceName(String instanceName) {
		this.instanceName = instanceName;
	}
}
