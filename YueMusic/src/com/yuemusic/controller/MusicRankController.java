//package com.yuemusic.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.yuemusic.service.intf.IMusicRankService;
//
//@Controller
//public class MusicRankController {
//	
//	@Autowired
//	private IMusicRankService rankService;
//	
//	/**
//	 * 根据rankID获取歌曲
//	 * @param rankID
//	 * @return
//	 */
//	@RequestMapping(value = "/getMusicRankByID", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object getMusicRankByID(@RequestParam("rankID") Integer rankID) {
//		try {
//			return rankService.getMusicRankByID(rankID);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//	
//	/**
//	 * 根据number和歌手名字获取歌曲
//	 * @param number
//	 * @param singer
//	 * @return
//	 */
//	@RequestMapping(value = "/getMusicRankByNumber", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object getMusicRankByNumber(@RequestParam("number") Integer number,
//			@RequestParam("singer") String singer) {
//		try {
//			//singer=new String(singer.getBytes("iso8859-1"),"UTF-8");
//			return rankService.getMusicRankByNumber(number, singer);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//	
//	/**
//	 * 插入歌曲长度，后台用
//	 * @return
//	 */
//	@RequestMapping(value = "/saveRankPlayTime", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object savePlayTime(){
//		try {
//			
//			return rankService.savePlayTime();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "false";
//		}
//	}
//	
//	@RequestMapping(value = "/MusicRankReName", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object MusicRankReName(@RequestParam("path") String path){
//		try {
//			return rankService.MusicReName(path);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "false";
//		}
//	}
//}
