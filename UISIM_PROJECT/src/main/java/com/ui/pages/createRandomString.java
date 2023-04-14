package com.ui.pages;

import com.ui.base.TestBase;

public class createRandomString extends TestBase{

	public static String randomString(int intValue) throws Exception {
        char c[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int randomPosition;
        String randomString = "";
        for (int i = 0; i < intValue; i++) {
            randomPosition = generateRandomIntIntRange(0, 51);
            randomString = randomString + c[randomPosition]; 
        }
        System.out.println(randomString);
        return randomString;        
    }

	private static int generateRandomIntIntRange(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

}
