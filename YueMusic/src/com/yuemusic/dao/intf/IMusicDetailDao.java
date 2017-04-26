package com.yuemusic.dao.intf;

import java.util.List;

import com.yuemusic.model.YueMusicDetail;

public interface IMusicDetailDao extends IBaseDao<YueMusicDetail>{
	public List<YueMusicDetail> getAllMusicDetail();

	public Boolean insertMusicDetail(YueMusicDetail detail);

	public YueMusicDetail getMusicDetailByID(Integer id);
	
	public Boolean updatePlayTime(String playTime, Integer id);
	
	public List<YueMusicDetail> getMusicDetailBySinger(int pageNow, int pageSize, String singer);

	public YueMusicDetail getMusicDetailByNumber(Integer number,
			String musicGroup);

	public int getMusicSizeBySinger(String musicGroup);
	
	public YueMusicDetail getMusicDetailByPath(String songPath);

	public Boolean updateSongPath(String newName, Integer id, String oldPath, String musicGroup);

	public YueMusicDetail getMusicDetailBySong(String song);

	public List<YueMusicDetail> getMusicDetailByGroup(String musicGroup);

	public Boolean updateMusicPath(String songPath, String oldPath, Integer id);

	
}
