//package com.yuemusic.dao.intf;
//
//import java.util.List;
//
//import com.yuemusic.model.YueMusicDetail;
//import com.yuemusic.model.YueMusicRank;
//
//public interface IMusicRankDao extends IBaseDao<YueMusicRank>{
//	public List<YueMusicRank> getMusicRankByID(Integer rankID);
//
//	public int getMusicSizeBySinger(String singer);
//
//	public List<YueMusicRank> getAllMusicRank();
//
//	public Boolean updatePlayTime(String playTime, Integer rankId);
//
//	public List<YueMusicRank> getMusicDetailByNumber(Integer number, String singer);
//
//	YueMusicRank getMusicDetailByPath(String songPath);
//
//	public Boolean updateSongPath(String newName, Integer id, String oldPath);
//}
