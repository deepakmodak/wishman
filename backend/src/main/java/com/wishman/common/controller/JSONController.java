package com.wishman.common.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wishman.common.model.Item;
import com.wishman.common.model.ItemResponse;

@Controller
@RequestMapping("/ngo/list")
public class JSONController{
	
	@RequestMapping(value = "{price}", method = RequestMethod.GET)
	public @ResponseBody ItemResponse getShopInJSON(@PathVariable Long price){
		
		System.out.println("got " + price);
			
		ItemResponse itResp = new ItemResponse();
		
		List<Item> l1 = new ArrayList<Item>();
		
		BufferedReader br = null;
		String line = null;
		try {
			String csvFile="/tmp/list2.csv";
			System.out.println(this.getClass().getResourceAsStream(csvFile));
			
			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();
			while((line = br.readLine()) != null) {
				String []column = line.split(",");
				Item i = new Item();
				i.setKid_gender(column[7]);
				i.setKid_name(column[0]);
				i.setKid_pic(column[1]);
				i.setProduc_url(column[3]);
				i.setProduc_id(column[4]);
				System.out.println(column[6]);
				
				i.setProduct_price(Long.parseLong(column[6], 10)  );
				i.setProduc_name(column[2]);
				i.setProduc_img(column[5]);
				
				
				if(i.getProduct_price() <= price)
					l1.add(i);
				
			}
		} catch (Exception e) {
			System.out.println("Unable to read file");
			e.printStackTrace();
		}
		itResp.setList(l1);
		itResp.setCount(l1.size());
		
		return itResp;
		
	}
	
}