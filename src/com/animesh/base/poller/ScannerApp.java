package com.animesh.base.poller;

import java.util.Scanner;

public class ScannerApp {
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter data :");
		String line = input.nextLine();
		
		System.out.println("-----------------------");
		
		System.out.println(line);
		
	}

}
