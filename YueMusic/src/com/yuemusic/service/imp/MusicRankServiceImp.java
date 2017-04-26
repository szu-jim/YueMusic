//package com.yuemusic.service.imp;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.yuemusic.dao.intf.IMusicRankDao;
//import com.yuemusic.model.YueMusicDetail;
//import com.yuemusic.model.YueMusicRank;
//import com.yuemusic.service.intf.IMusicRankService;
//import com.yuemusic.tool.StaticConstant;
//import com.yuemusic.tool.TimeFormatTool;
//import com.yuemusic.tool.VideoTool;
//
//@Service
//public class MusicRankServiceImp implements IMusicRankService {
//
//	private Map<String, Object> successInfo;
//	private List<Map<String, Object>> detailInfo;
//	private Map<String, Object> dataMap;
//	
//	@Autowired
//	IMusicRankDao rankDao;
//	
//	/**
//	 * 根据rankID获取歌曲
//	 */
//	@Override
//	public Object getMusicRankByID(Integer rankID) {
//		// TODO Auto-generated method stub
//		successInfo = new HashMap<String, Object>();
//		detailInfo = new ArrayList<Map<String, Object>>();
//		dataMap = new HashMap<String, Object>();
//		List<YueMusicRank> rankList = rankDao.getMusicRankByID(rankID);
//		
//		if (rankList == null || rankList.size() <= 0) {
//			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
//			dataMap.put("successInfo", successInfo);
//			return dataMap;
//		}
//		//获取歌手的歌曲文件个数
//		String singer = rankList.get(0).getSinger();
//		int amount = rankDao.getMusicSizeBySinger(singer);
//		
//		//将歌曲长度转成long型，方便转换成时分秒格式
//		if(rankList.get(0).getPlayTime() == ""){
//			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
//			dataMap.put("successInfo", successInfo);
//			return dataMap;
//		}
//		long playTime = Long.parseLong(rankList.get(0).getPlayTime()); 
//		
//		for (YueMusicRank tempList : rankList) {
//			Map<String, Object> tempMap = new HashMap<String, Object>();
//
//			tempMap.put("id", tempList.getRankId());
//			tempMap.put("singer", tempList.getSinger());
//			tempMap.put("song", tempList.getSong());
//			tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));//传递时分秒格式的时长
//			tempMap.put("song_path", tempList.getSongPath());
//			tempMap.put("number", tempList.getNumber());
//			tempMap.put("amount", amount);
//			detailInfo.add(tempMap);
//		}
//		successInfo.put("success", true);
//		dataMap.put("successInfo", successInfo);
//		dataMap.put("detailInfo", detailInfo);
//		return dataMap;
//	}
//	
//	/**
//	 * 根据number和歌手名字获取歌曲
//	 */
//	@Override
//	public Object getMusicRankByNumber(Integer number, String singer) {
//		// TODO Auto-generated method stub
//		successInfo = new HashMap<String, Object>();
//		detailInfo = new ArrayList<Map<String, Object>>();
//		dataMap = new HashMap<String, Object>();
//		List<YueMusicRank> rankList = rankDao.getMusicDetailByNumber(number, singer);
//		if (rankList == null || rankList.size() <= 0) {
//			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
//			dataMap.put("successInfo", successInfo);
//			return dataMap;
//		}
//		//获取歌手的歌曲文件个数
//		int amount = rankDao.getMusicSizeBySinger(singer);
//		
//		//将歌曲长度转成long型，方便转换成时分秒格式
//		long playTime = Long.parseLong(rankList.get(0).getPlayTime()); 
//		
//		for (YueMusicRank tempList : rankList) {
//			Map<String, Object> tempMap = new HashMap<String, Object>();
//
//			tempMap.put("id", tempList.getRankId());
//			tempMap.put("singer", tempList.getSinger());
//			tempMap.put("song", tempList.getSong());
//			tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));//传递时分秒格式的时长
//			tempMap.put("song_path", tempList.getSongPath());
//			tempMap.put("amount", amount);
//
//			detailInfo.add(tempMap);
//		}
//		successInfo.put("success", true);
//		dataMap.put("successInfo", successInfo);
//		dataMap.put("detailInfo", detailInfo);
//		return dataMap;
//	}
//	
//	/**
//	 * 插入歌曲长度
//	 */
//	@Override
//	public Object savePlayTime() {
//		// TODO Auto-generated method stub
//		successInfo = new HashMap<String, Object>();
//		detailInfo = new ArrayList<Map<String, Object>>();
//		dataMap = new HashMap<String, Object>();
//		//获取所有歌曲信息
//		List<YueMusicRank> rankList = rankDao.getAllMusicRank();
//		if (rankList == null || rankList.size() <= 0) {
//			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
//			dataMap.put("successInfo", successInfo);
//			return dataMap;
//		}
//		Boolean result = false;
//		//对所有歌曲插入歌曲长度
//		for (YueMusicRank tempList : rankList) {
//			String playTime = VideoTool.getPlayTime(StaticConstant.PATH+tempList.getSongPath());
//			result = rankDao.updatePlayTime(playTime, tempList.getRankId());
//			if(!result){
//				successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
//				dataMap.put("successInfo", successInfo);
//				return dataMap;
//			}
//		}
//		successInfo.put("success", true);
//		dataMap.put("successInfo", successInfo);
//		return dataMap;
//	}
//
//	@Override
//	public Object MusicReName(String path){
//		successInfo = new HashMap<String, Object>();
//		dataMap = new HashMap<String, Object>();
//		File [] dirs = new File[] {	
//			new File("D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/music/"+path)
//		};
//		
//		for(File dir : dirs) {
//			File [] fs = dir.listFiles();
//			for(File file : fs) {
//				Random ra = new Random();
//				Integer ranum = ra.nextInt(100000)+1;
//				String fileName = file.getName();
//				String suffix = fileName.split("\\.")[fileName.split("\\.").length-1];
//				if(suffix.equals("mp3")){
//					YueMusicRank music = rankDao.getMusicDetailByPath("music/"+path+"/"+fileName);
//					if (music == null) {
//						
//					}else{
//						String oldPath = music.getSongPath();
//						String newName = path+"."+music.getRankId()+"."+ranum+"."+suffix;
//						Boolean result = rankDao.updateSongPath(newName, music.getRankId(), oldPath);
//						if(result){
//							file.renameTo(new File("D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/music/"+path+"/"+newName));
//							System.out.println(file.getName());
//						}else{
//							
//						}
//					}
//					
//					
//				}
//				
//			}
//		}
//		successInfo.put("success", true);
//		dataMap.put("successInfo", successInfo);
//		return dataMap;
//	}
//}
