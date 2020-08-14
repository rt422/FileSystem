package com.src.practice.design.low.filesystem.actions;
import java.util.List;
import java.util.Optional;

import com.src.practice.design.low.filesystem.Folder;
import com.src.practice.design.low.filesystem.SystemElement;


class Node{
	Node next = null;
	Node prev = null;
	Folder folder;
	
	Node(Folder folder){
		this.folder = folder;
	}
}
public class SystemActions {
	private static String currentPath = "";
	public static Folder currentFolder;
	private static Node head = null;
	
	
	public static void goToPreviousFolder() {
		System.out.print("goToPreviousFolder : ");
		if(head.prev!=null) {
			head = head.prev;
			currentFolder = head.folder;
			currentPath = currentPath.substring(0,currentPath.lastIndexOf('/')+1);
			System.out.println("Path : "+currentPath+", Folder : "+currentFolder.getName());
		}
		else {
			System.out.println("Operation not supported cannot go back");
			//throw new RuntimeException("Cannot go back from " + currentFolder + ".");
		}
	}
	
	public static Folder getCurrentFolder() {
		return currentFolder;
	}
	public static void goToNextFolder() {
		System.out.print("goToNextFolder : ");
		if(head.next!=null) {
			head = head.next;
			currentFolder = head.folder;
			currentPath = currentPath + "/" + currentFolder.getName();
			System.out.println("Path : "+currentPath+", Folder : "+currentFolder.getName());
		}
		else {
			System.out.println("Operation not supported cannot go further");
			//1throw new RuntimeException("Cannot go further from " + currentFolder + ".");
		}
		
	}
	
	public static void startActions(Folder fromFolder) {
		currentFolder = fromFolder;
		currentPath = currentFolder.getName();
		head = new Node(currentFolder);
		//Node next = head.next;
		//
		//next.prev = head;
			
		
	}
	public static void goInFolder(String folderName) {
		System.out.print("goInFolder : ");
		List<SystemElement> list = currentFolder.getList();
		Optional<SystemElement> op = list.stream().filter((SystemElement e)->e.getName().equalsIgnoreCase(folderName)
				&& e.getType().equalsIgnoreCase("Folder")).findAny();
		if(op.isPresent()) {
			currentFolder = (Folder)op.get();
			Node temp = new Node(currentFolder);	
			head.next = temp;
			temp.prev = head;
			head = temp;
			currentPath = currentPath + "/" + currentFolder.getName();
			System.out.println("Path : "+currentPath+", Folder : "+currentFolder.getName());
		}
		else {
			System.out.println("Operation not supported cannot find folder "+folderName+".");
			//throw new RuntimeException("Operation not supported cannot find folder "+folderName+".");
		}
	}

}
