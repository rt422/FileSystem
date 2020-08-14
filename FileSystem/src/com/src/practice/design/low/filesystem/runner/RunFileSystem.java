package com.src.practice.design.low.filesystem.runner;


import java.util.Scanner;
import com.src.practice.design.low.filesystem.Folder;
import com.src.practice.design.low.filesystem.actions.SystemActions;
import com.src.practice.design.low.filesystem.factory.SystemElementFactory;

public class RunFileSystem {

	public static void main(String [] args) {
		Folder currentFolder = SystemElementFactory.startFactory();
		SystemActions.startActions(currentFolder);
		//currentFolder is root Now
		System.out.println("SystemElementFactory started");
		Scanner scanner = new Scanner(System.in);
		int option;
		String elementName = "";
		String prm = "";
		while(true) {
			System.out.println("Type [1-5] with parameters to perform the task mentioned below");
			System.out.print("1. CREATE_FOLDER folderName, ");
			System.out.print("2. CREATE_FILE folderName, ");
			System.out.print("3. NEXT, ");
			System.out.print("4. PREV, ");
			System.out.print("5. GOTO folderName,");
			System.out.print("5. LIST ,");
			System.out.print("5. RENAME elemntName, newElementName");
			System.out.println("eg. to create a folder named 'data' you need to enter 1 data");
			String input = scanner.nextLine();
			//input = input.trim();
			//System.out.println("input[" + input+"]");
			String parms[] = input.split(" ");
			option = Integer.parseInt(parms[0]);
		
			
			
			switch(option) {
				case 1:
					//System.out.println("------------------------------CASE 1----------------------------------");
					SystemElementFactory.createFolder(parms[1], currentFolder);
					//System.out.println("------------------------------CASE 1 DONE 1----------------------------------");
					break;
				case 2:
					SystemElementFactory.createFile(parms[1], currentFolder);
					break;
				case 3:
					SystemActions.goToNextFolder();
					currentFolder = SystemActions.getCurrentFolder();
					break;
				case 4:
					SystemActions.goToPreviousFolder();
					currentFolder = SystemActions.getCurrentFolder();
					break;
				case 5:
					SystemActions.goInFolder(parms[1]);
					currentFolder = SystemActions.getCurrentFolder();
					break;
				case 6:
					currentFolder.listContents();
					break;
				
				case 7:
					currentFolder.rename(parms[1],parms[2]);
					break;
				
				default :
					System.out.println("Invalid choice");
					
			}
			//System.out.println("------------------------------AFTER SWITCH----------------------------------");
		}
	}
	
}
