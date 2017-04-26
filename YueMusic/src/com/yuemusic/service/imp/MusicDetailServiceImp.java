package com.yuemusic.service.imp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuemusic.dao.intf.IMusicDetailDao;
import com.yuemusic.model.YueMusicDetail;
import com.yuemusic.service.intf.IMusicDetailService;
import com.yuemusic.tool.ParamentCheckUtils;
import com.yuemusic.tool.StaticConstant;
import com.yuemusic.tool.TimeFormatTool;
import com.yuemusic.tool.VideoTool;

@Service
public class MusicDetailServiceImp implements IMusicDetailService {

	private Map<String, Object> successInfo;
	private List<Map<String, Object>> detailInfo;
	private Map<String, Object> dataMap;

	@Autowired
	IMusicDetailDao detailDao;

	/**
	 * 获取所有歌曲
	 */
	@Override
	public Object getAllMusicDetail() throws Exception {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		detailInfo = new ArrayList<Map<String, Object>>();
		dataMap = new HashMap<String, Object>();
		long playTime;
		List<YueMusicDetail> detailList = detailDao.getAllMusicDetail();
		if (detailList == null || detailList.size() <= 0) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}

		for (YueMusicDetail tempList : detailList) {
			Map<String, Object> tempMap = new HashMap<String, Object>();
			// 将歌曲长度的格式从秒数转成时分秒
			playTime = Long.parseLong(tempList.getPlayTime());
			tempMap.put("id", tempList.getId());
			tempMap.put("singer", tempList.getSinger());
			tempMap.put("song", tempList.getSong());
			tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));// 传递时分秒格式的时长
			tempMap.put("song_path", tempList.getSongPath());

			detailInfo.add(tempMap);
		}
		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		dataMap.put("detailInfo", detailInfo);
		return dataMap;
	}

	/**
	 * 根据id获取歌曲
	 */
	@Override
	public Object getMusicDetailByID(Integer id) throws Exception {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		detailInfo = new ArrayList<Map<String, Object>>();
		dataMap = new HashMap<String, Object>();
		YueMusicDetail musicDetail = detailDao.getMusicDetailByID(id);

		if (musicDetail == null) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
		// 获取歌手的歌曲文件个数
		int amount = detailDao.getMusicSizeBySinger(musicDetail.getMusicGroup());

		// 将歌曲长度转成long型，方便转换成时分秒格式
		long playTime = Long.parseLong(musicDetail.getPlayTime());

		Map<String, Object> tempMap = new HashMap<String, Object>();

		tempMap.put("id", musicDetail.getId());
		tempMap.put("singer", musicDetail.getSinger());
		tempMap.put("song", musicDetail.getSong());
		tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));// 传递时分秒格式的时长
		tempMap.put("song_path", musicDetail.getSongPath());
		tempMap.put("number", musicDetail.getNumber());
		tempMap.put("amount", amount);
		tempMap.put("music_group", musicDetail.getMusicGroup());
		detailInfo.add(tempMap);

		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		dataMap.put("detailInfo", detailInfo);
		return dataMap;
	}

	/**
	 * 根据number和歌手名字获取歌曲
	 */
	@Override
	public Object getMusicDetailByNumber(Integer number, String musicGroup) {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		detailInfo = new ArrayList<Map<String, Object>>();
		dataMap = new HashMap<String, Object>();
		YueMusicDetail byNumberDetail = detailDao.getMusicDetailByNumber(
				number, musicGroup);
		if (byNumberDetail == null) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
		// 获取歌手的歌曲文件个数
		int amount = detailDao.getMusicSizeBySinger(byNumberDetail.getMusicGroup());

		// 将歌曲长度转成long型，方便转换成时分秒格式
		long playTime = Long.parseLong(byNumberDetail.getPlayTime());

		Map<String, Object> tempMap = new HashMap<String, Object>();

		tempMap.put("id", byNumberDetail.getId());
		tempMap.put("singer", byNumberDetail.getSinger());
		tempMap.put("song", byNumberDetail.getSong());
		tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));// 传递时分秒格式的时长
		tempMap.put("song_path", byNumberDetail.getSongPath());
		tempMap.put("amount", amount);

		detailInfo.add(tempMap);

		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		dataMap.put("detailInfo", detailInfo);
		return dataMap;
	}

	/**
	 * 插入歌曲长度
	 */
	@Override
	public Object savePlayTime() {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		detailInfo = new ArrayList<Map<String, Object>>();
		dataMap = new HashMap<String, Object>();
		List<YueMusicDetail> detailList = detailDao.getAllMusicDetail();
		if (detailList == null || detailList.size() <= 0) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
		Boolean result = false;
		for (YueMusicDetail tempList : detailList) {
			String playTime = VideoTool.getPlayTime(StaticConstant.PATH
					+ tempList.getSongPath());
			result = detailDao.updatePlayTime(playTime, tempList.getId());
			if (!result) {
				successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
				dataMap.put("successInfo", successInfo);
				return dataMap;
			}
		}
		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		return dataMap;
	}

	/**
	 * 分页显示歌曲
	 */
	@Override
	public Object getMusicDetailBySinger(Integer pageNow, Integer pageSize,
			String singer) {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		detailInfo = new ArrayList<Map<String, Object>>();
		dataMap = new HashMap<String, Object>();
		List<YueMusicDetail> detailList = detailDao.getMusicDetailBySinger(
				pageNow, pageSize, singer);
		// 获取歌手的歌曲文件个数
		int amount = detailDao.getMusicSizeBySinger(detailList.get(0).getMusicGroup());

		// 将歌曲长度转成long型，方便转换成时分秒格式
		long playTime = Long.parseLong(detailList.get(0).getPlayTime());
		if (detailList == null || detailList.size() <= 0) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
		for (YueMusicDetail tempList : detailList) {
			Map<String, Object> tempMap = new HashMap<String, Object>();

			tempMap.put("id", tempList.getId());
			tempMap.put("singer", tempList.getSinger());
			tempMap.put("song", tempList.getSong());
			tempMap.put("play_time", TimeFormatTool.convertTimeFormat(playTime));// 传递时分秒格式的时长
			tempMap.put("song_path", tempList.getSongPath());
			tempMap.put("amount", amount);

			detailInfo.add(tempMap);
		}
		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		dataMap.put("detailInfo", detailInfo);
		return dataMap;
	}

	/**
	 * 插入歌曲信息，暂时弃用
	 */
	@Override
	public Object insertMusicDetail(Integer id, String singer, String song,
			String songPath) throws Exception {
		// TODO Auto-generated method stub
		successInfo = new HashMap<String, Object>();
		dataMap = new HashMap<String, Object>();
		Boolean validate = ParamentCheckUtils.isValidateString(singer, 20);
		validate &= ParamentCheckUtils.isValidateString(song, 20);
		validate &= ParamentCheckUtils.isValidateString(songPath, 50);
		if (!validate) {
			successInfo.put("success", StaticConstant.REQUEST_PARAM_ERROR);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}

		// String playTime =
		// VideoTool.getPlayTime(StaticConstant.PATH+songPath);
		YueMusicDetail detail = new YueMusicDetail();
		detail.setId(id);
		detail.setSinger(singer);
		detail.setSong(song);
		detail.setPlayTime("");
		detail.setSongPath(songPath);

		Boolean result = detailDao.insertMusicDetail(detail);
		if (result) {
			successInfo.put("success", true);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		} else {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
	}

	@Override
	public Object MusicReName(String path) {
		successInfo = new HashMap<String, Object>();
		dataMap = new HashMap<String, Object>();
		File[] dirs = new File[] { new File(
				"D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/music/"
						+ path) };

		for (File dir : dirs) {
			File[] fs = dir.listFiles();
			for (File file : fs) {
				Random ra = new Random();
				Integer ranum = ra.nextInt(100000) + 1;
				String fileName = file.getName();
				String suffix = fileName.split("\\.")[fileName.split("\\.").length - 1];
				if (suffix.equals("mp3")) {
					YueMusicDetail music = detailDao
							.getMusicDetailByPath("music/" + path + "/"
									+ fileName);
					if (music == null) {
						successInfo.put("success",
								StaticConstant.REQUEST_CONTENT_NONE);
						dataMap.put("successInfo", successInfo);
						return dataMap;
					}
					String oldPath = music.getSongPath();
					String newName = "music/" + path + "/" + path + "."
							+ music.getId() + "." + ranum + "." + suffix;
					String group = path;
					Boolean result = detailDao.updateSongPath(newName,
							music.getId(), oldPath, group);
					if (result) {
						file.renameTo(new File(
								"D:/Program Files/MyEclipse Professional 2014/xworkspace/YueMusic/WebRoot/"
										+ newName));
						System.out.println(file.getName());
					} else {
						successInfo.put("success",
								StaticConstant.REQUEST_CONTENT_NONE);
						dataMap.put("successInfo", successInfo);
						return dataMap;
					}

				}

			}
		}
		successInfo.put("success", true);
		dataMap.put("successInfo", successInfo);
		return dataMap;
	}
	
	/**
	 * 更新歌曲路径，供分组，排行等使用
	 */
	@Override
	public Object MusicUpdateName(String musicGroup) {
		successInfo = new HashMap<String, Object>();
		dataMap = new HashMap<String, Object>();
		Boolean result = true;
		//获取被导入的列表的所有歌曲
		List<YueMusicDetail> musicList = detailDao.getMusicDetailByGroup(musicGroup);
		if (musicList == null || musicList.size() <= 0) {
			successInfo.put("success", StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
		for(YueMusicDetail templist : musicList){
			//获取源文件的歌曲信息
			YueMusicDetail sourceMusic = detailDao.getMusicDetailBySong(templist.getSong());
			//导入路径
			if(sourceMusic != null){
				result &= detailDao.updateMusicPath(sourceMusic.getSongPath(), sourceMusic.getOldPath(), templist.getId());				
			}
		}
		if(result){
			successInfo.put("success", true);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}else{
			successInfo.put("success",
					StaticConstant.REQUEST_CONTENT_NONE);
			dataMap.put("successInfo", successInfo);
			return dataMap;
		}
	}

	
}
