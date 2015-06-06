package com.wishman.common.model;

import java.util.ArrayList;
import java.util.List;

public class ItemResponse {

	int count;
	List<Item> list= new ArrayList<Item>();
	
	public int getCount(){
		return count;
	}
	public void setCount(int count){
		this.count = count;
	}
	public List<Item> getList(){
		return list;
	}
	public void setList(List<Item> list){
		this.list = list;
	}
	
}