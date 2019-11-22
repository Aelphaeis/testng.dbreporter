package com.cruat.testng.dbreporter.common;

import java.sql.Connection;
import java.util.Objects;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

public class ReportLiquibaseRunner implements Runnable {
	
	private final String changelogPath;
	private final Connection source;
	
	public ReportLiquibaseRunner(Connection source, String changelogPath) {
		this.changelogPath = Objects.requireNonNull(changelogPath);
		this.source = Objects.requireNonNull(source);
	}
	
	@Override
	public void run() {
		try {
			runLiquibase();
		} catch (LiquibaseException e) {
			throw new ReportingException(e);
		}
	}
	
	private void runLiquibase() throws LiquibaseException {
		DatabaseFactory dbFactory = DatabaseFactory.getInstance();
		JdbcConnection connection = new JdbcConnection(source);
		Database db = dbFactory.findCorrectDatabaseImplementation(connection);
		ResourceAccessor accessor = new ClassLoaderResourceAccessor();
		Liquibase liquibase = new Liquibase(changelogPath, accessor, db);
		liquibase.update(new Contexts(), new LabelExpression());
	}
	
}
