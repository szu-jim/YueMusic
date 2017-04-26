package com.yuemusic.service.intf;

public interface IMusicDetailService {
	public Object getAllMusicDetail() throws Exception;

	public Object insertMusicDetail(Integer id, String singer, String song, String songPath)
			throws Exception;

	public Object getMusicDetailByID(Integer id) throws Exception;
	
	public Object savePlayTime();

	public Object getMusicDetailByNumber(Integer number, String musicGroup);
	
	public Object getMusicDetailBySinger(Integer pageNow, Integer pageSize, String singer);

	public Object MusicReName(String path);

	Object MusicUpdateName(String path);
}
