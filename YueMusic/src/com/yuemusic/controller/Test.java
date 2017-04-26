package com.yuemusic.controller;
import com.yuemusic.tool.VideoTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Test {

	@RequestMapping(value = "/Test", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object Test(@RequestParam("username") String username, @RequestParam("password") String password){
		if (username.equals(password))   
        {  
            return username;
        } else{
        	return null;
        }
	}
	
	public static void main(String args[]){
		System.out.print(VideoTool.getPlayTime("D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/songs/侧田-Volar.wma"));
	}
}
