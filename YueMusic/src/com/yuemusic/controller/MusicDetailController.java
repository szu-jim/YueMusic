package com.yuemusic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuemusic.service.intf.IMusicDetailService;

@Controller
public class MusicDetailController {

	@Autowired
	private IMusicDetailService detailService;

	/**
	 * 获取所有歌曲
	 * @return
	 */
	@RequestMapping(value = "/getAllMusicDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getAllMusicDetail() {

		try {
			return detailService.getAllMusicDetail();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 根据id获取歌曲
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getMusicDetailByID", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getMusicDetailByID(@RequestParam("id") Integer id) {
		try {
			return detailService.getMusicDetailByID(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 根据number和歌手名字获取歌曲
	 * @param number
	 * @param singer
	 * @return
	 */
	@RequestMapping(value = "/getMusicDetailByNumber", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getMusicDetailByNumber(@RequestParam("number") Integer number,
			@RequestParam("musicGroup") String musicGroup) {
		try {
			//singer=new String(singer.getBytes("iso8859-1"),"UTF-8");
			return detailService.getMusicDetailByNumber(number, musicGroup);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 插入歌曲，暂时弃用
	 * @param id
	 * @param singer
	 * @param song
	 * @param songPath
	 * @return
	 */
	@RequestMapping(value = "/insertMusicDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object insertMusicDetail(@RequestParam("id") Integer id,
			@RequestParam("singer") String singer,
			@RequestParam("song") String song,
			@RequestParam(value = "songPath", required = false) String songPath) {
		try {
			singer=new String(singer.getBytes("iso8859-1"),"UTF-8");
			song=new String(song.getBytes("iso8859-1"),"UTF-8");
			songPath=new String(songPath.getBytes("iso8859-1"),"UTF-8");
			
			return detailService.insertMusicDetail(id,singer, song, songPath);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	/**
	 * 插入歌曲长度
	 * @return
	 */
	@RequestMapping(value = "/savePlayTime", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object savePlayTime(){
		try {
			
			return detailService.savePlayTime();
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	/**
	 * 分页显示歌曲
	 * @param pageNow
	 * @param pageSize
	 * @param singer
	 * @return
	 */
	@RequestMapping(value = "/getMusicDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getMusicDetail(			
			@RequestParam("pageNow") Integer pageNow,
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam("singer") String singer){
		try {
			return detailService.getMusicDetailBySinger(pageNow, pageSize, singer);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	/**
	 * 音乐改名（用于录入服务器）
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/MusicReName", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object MusicReName(@RequestParam("path") String path){
		try {
			return detailService.MusicReName(path);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
	/**
	 * 更新歌曲路径，供分组，排行等使用
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/MusicUpdateName", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object MusicUpdateName(@RequestParam("path") String path){
		try {
			return detailService.MusicUpdateName(path);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
	}
	
}
