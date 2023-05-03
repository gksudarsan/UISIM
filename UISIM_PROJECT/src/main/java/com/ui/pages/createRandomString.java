package com.ui.pages;

import org.openqa.selenium.support.PageFactory;

import com.jcraft.jsch.jce.Random;
import com.ui.base.TestBase;

public class createRandomString {
	public static void main(String[] args) {
		createRandomString.generateRandomString();
	}

	public static void generateRandomString() {

		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 15; i++) {
			int index = (int) (alphabet.length() * Math.random());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		System.out.println(randomString);
	}

}
