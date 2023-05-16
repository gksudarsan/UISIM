package com.employerContibution.EM;

import java.sql.SQLException;
import java.util.Map;

import stepDefinitions.commonStepDefinitions;

public class SqlQuery {

	public static void main(String[] args) throws SQLException {
		commonStepDefinitions cf= new commonStepDefinitions();
		Map<String, String> databaseResults = cf.database_SelectQuery("SELECT * FROM T_EMPLOYER_ACCOUNT tea JOIN T_EMPLOYER_TRANSFER tr ON TEA.EMPLOYER_ACCOUNT_ID = tr.FROM_EMPLOYER_ID ORDER BY tea.UPDATED_TS DESC");
		String feinValue = databaseResults.get("Fein");
		String eanValue = databaseResults.get("Ean");
		System.out.println("The Fein Value is:"+ feinValue);
		System.out.println("The EAN Value is:"+ eanValue);
		
		

	}

}
