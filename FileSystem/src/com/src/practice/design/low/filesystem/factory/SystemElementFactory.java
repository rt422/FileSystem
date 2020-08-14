package com.src.practice.design.low.filesystem.factory;

import com.src.practice.design.low.filesystem.File;
import com.src.practice.design.low.filesystem.Folder;
import com.src.practice.design.low.filesystem.SystemElement;

public class SystemElementFactory {
	public static Folder root = null;
	//public static Folder currentFolder = null;
	public static Folder startFactory() {
		Folder folder = new Folder("root","root");
		root = folder;
		//currentFolder = root;
		System.out.println("Root created succesfullly.");
		return root;
	}


	public static boolean checkIfAlreadyPresent(String name, Folder currentFolder) {
		String response = currentFolder.getByName(name);
		if(response.startsWith("404")) {
			return false;
		}
		return true;
	}
	public static void createFolder(String name, Folder currentFolder) {
		if(!checkIfAlreadyPresent(name, currentFolder)) {
			Folder folder = null;
			try {
				folder = new Folder(name,name); 
				currentFolder.getList().add(folder);

			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			System.out.println("Folder "+name+" created auccessfully in "+currentFolder);
			return ;
		}
		System.out.println("Cannot create folder "+name+". A SystemElement with same name already exists");

	}

	public static void createFile(String name, Folder currentFolder) {
		if(!checkIfAlreadyPresent(name, currentFolder)) {
			File file = new File(name,name);
			currentFolder.getList().add(file);
			System.out.println("File "+name+"created succesfullly in "+currentFolder);
			return ;
		}
		System.out.println("Cannot create file "+name+". A SystemElement with same name already exists");
	}

}
