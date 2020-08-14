package com.src.practice.design.low.filesystem;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class Folder implements SystemElement{
	private String name;//name cannot be duplicate
	private String id;//Direct access to folder RandomAccess

	public Folder() {
		super();
	}

	public Folder(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	//Folder can have files and sub folders
	//All the elements will be ordered in the order in which they are added
	private List<SystemElement> elements = new ArrayList<SystemElement>();

	@Override
	public String getDetails() {// returns details for this folder
		return "[ name : "+name+", id : "+id+" ]";
	}

	public String getName() {// return name for this folder
		return name;
	}
	public void setName(String name) {// sets name for this folder
		this.name = name;
	}
	public String getType() {//It will always be "Folder" for folders
		return "Folder";
	}
	public String toString() {
		return "[ name : "+name+", id : "+id+",elements : "+elements.toString()+"]";
	}

	//Add an element in the Folder
	//Does not add if name present already
	public String add(SystemElement element) {
		System.out.print("Add element : ");
		long count = elements.stream().map((SystemElement e)->e.getName()).filter((String name)->name==element.getName()).count();
		String output = "";
		if(count==0) {
			elements.add(element);
			output = element.getType() + " " + element.getName()+" added successfully";
		}else {
			String existingType = elements.stream().filter((SystemElement e)->e.getName()==element.getName()).findAny().get().getType();
			output = "Cannot add "+element.getType() + " " + element.getName()+", Reason same name "+existingType+"exist in"+getName();
		}
		System.out.println(output);
		return output;
	}

	//lists all the elements in the folder
	public String listContents() {
		System.out.print("listContents : ");
		String output = elements.toString();
		System.out.println(output);
		return output;
	}

	public List<SystemElement> getList(){
		return elements;
	}

	public String getByName(String elementName) {
		System.out.print("getByName : ");
		String output = "";
		Optional<SystemElement> op = elements.stream().filter((SystemElement e)->e.getName().equalsIgnoreCase(elementName)).findAny();
		if(op.isPresent()) {
			output = op.get().toString();
		}
		else {
			output = "404. No such element exists in Folder "+getName();
		}
		System.out.println(output);
		return output;
	}
	//rename an element in the folder
	public String rename(String currentName, String newName) {
		System.out.print("rename : ");
		String output = "";
		Optional<SystemElement> op = elements.stream().filter((SystemElement e)->e.getName().equalsIgnoreCase(currentName)).findAny();
		if(op.isPresent()) {
			SystemElement element = op.get();
			element.setName(newName);
			output = "Name changed from "+currentName+"to new name"+newName;
		}
		else {
			output = "No such element exists in Folder "+getName();
		}
		System.out.println(output);
		return output;
	}

	public String removeByName(String elementName) {
		System.out.print("removeByName : ");
		String output = "";
		Iterator<SystemElement> itr = elements.iterator();
		SystemElement currentElement;
		while(itr.hasNext()) {
			currentElement = itr.next();
			if(currentElement.getName().equalsIgnoreCase(elementName)) {
				itr.remove();
				output = currentElement.getType()+" named "+currentElement.getName()+" removed successfully from the Folder "+ getName(); 
				System.out.println(output);
				return output;
			}
		}
		output = "Cannot find "+elementName+" in the Folder"+getName();
		System.out.println(output);
		return output;
	}

	public String searchByName(String elementName, List<SystemElement> elements) {
		System.out.print("searchByName : ");
		String output = "";
		String response = getByName(elementName);//Search in the curretn folder
		if(response.startsWith("No")) {
			Iterator<SystemElement> itr = elements.iterator();
			while(itr.hasNext()) {
				SystemElement currentElement = itr.next();
				if(elementName.equalsIgnoreCase(currentElement.getName())) {
					output = currentElement.toString();
					System.out.println(output);
					return output;
				}
				if(currentElement.getType().equalsIgnoreCase("Folder")) {
					//cast at runtime only folders can have a list
					output = searchByName(elementName,((Folder)currentElement).getList());
					System.out.println(output);
					return output;
				}
			}

		}
		output = "Cannot find "+elementName;
		System.out.println(output);
		return output;	
	}


}
