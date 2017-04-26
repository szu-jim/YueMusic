package com.yuemusic.model;

/**
 * YueMusicDetail entity. @author MyEclipse Persistence Tools
 */

public class YueMusicDetail implements java.io.Serializable {

	// Fields

	private Integer id;
	private String singer;
	private String song;
	private String playTime;
	private String songPath;
	private Integer number;
	private String oldPath;
	private String musicGroup;
	private Short sourceFlag;

	// Constructors

	/** default constructor */
	public YueMusicDetail() {
	}

	/** minimal constructor */
	public YueMusicDetail(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public YueMusicDetail(Integer id, String singer, String song,
			String playTime, String songPath, Integer number, String oldPath,
			String musicGroup, Short sourceFlag) {
		this.id = id;
		this.singer = singer;
		this.song = song;
		this.playTime = playTime;
		this.songPath = songPath;
		this.number = number;
		this.oldPath = oldPath;
		this.musicGroup = musicGroup;
		this.sourceFlag = sourceFlag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSinger() {
		return this.singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getSong() {
		return this.song;
	}

	public void setSong(String song) {
		this.song = song;
	}

	public String getPlayTime() {
		return this.playTime;
	}

	public void setPlayTime(String playTime) {
		this.playTime = playTime;
	}

	public String getSongPath() {
		return this.songPath;
	}

	public void setSongPath(String songPath) {
		this.songPath = songPath;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getOldPath() {
		return this.oldPath;
	}

	public void setOldPath(String oldPath) {
		this.oldPath = oldPath;
	}

	public String getMusicGroup() {
		return this.musicGroup;
	}

	public void setMusicGroup(String musicGroup) {
		this.musicGroup = musicGroup;
	}

	public Short getSourceFlag() {
		return this.sourceFlag;
	}

	public void setSourceFlag(Short sourceFlag) {
		this.sourceFlag = sourceFlag;
	}

}