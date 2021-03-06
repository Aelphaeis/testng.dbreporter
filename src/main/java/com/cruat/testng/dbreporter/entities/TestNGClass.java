package com.cruat.testng.dbreporter.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.testng.ITestResult;

import com.cruat.testng.dbreporter.utilities.ResultComparator;

@Entity
@Table(name = "testng_classes")
public class TestNGClass implements ReportEntity {
	
	public static final String DEFAULT_PACKAGE_NAME = "[default]";
	
	private long id;
	private String name;
	private String pkgName;
	
	private TestNGTest context;
	private List<TestNGMethod> methods;
	
	public TestNGClass() { 
		this.methods = new ArrayList<>();
	}
	
	public TestNGClass(Entry<String, List<ITestResult>> e) {
		this();
		
		String classname = e.getKey();
		int dotIndex = classname.lastIndexOf('.');
		
		if(dotIndex > -1) {
			this.name = classname.substring(dotIndex + 1);
			this.pkgName = classname.substring(0, dotIndex);
		}
		else {
			this.name = classname;
			this.pkgName = DEFAULT_PACKAGE_NAME;
		}
		
		e.getValue().sort(ResultComparator.INSTANCE);
		for(int i = 0; i < e.getValue().size(); i++) {
			TestNGMethod method = new TestNGMethod(e.getValue().get(i));
			method.setOrder(i + 1);
			method.setGroup(this);
			methods.add(method);
		}
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
	 * @return the pkgName
	 */
	@Column(name = "packageName")
	public String getPkgName() {
		return pkgName;
	}
	
	/**
	 * @param pkgName
	 *            the pkgName to set
	 */
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	
	/**
	 * @return the context
	 */
	@ManyToOne(targetEntity = TestNGTest.class)
	@JoinColumn(name = "testng_test_id", referencedColumnName = "id")
	public TestNGTest getContext() {
		return context;
	}

	
	/**
	 * @param context the context to set
	 */
	public void setContext(TestNGTest context) {
		this.context = context;
	}

	
	/**
	 * @return the methods
	 */
	@Transient
	public List<TestNGMethod> getTestMethods() {
		return methods;
	}

	
	/**
	 * @param methods the methods to set
	 */
	public void setTestMethods(List<TestNGMethod> testMethods) {
		this.methods = Objects.requireNonNull(testMethods);
	}
	
}
