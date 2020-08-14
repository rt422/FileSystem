package com.src.practice.design.low.filesystem;

public class File implements SystemElement{
	private String name;
	private String id;//Direct access to file RandomAccess
	
	public File() {
		super();
	}

	public File(String name, String id) {
		super();
		this.name = name;
		this.id = id;
	}

	@Override
	public String getDetails() {
		// TODO Auto-generated method stub
		return" [ name : "+name+", id : "+id+" ]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return "File";
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[ name : "+name+", id : "+id+" ]";
	}
	

}
