package com.yuemusic.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.yuemusic.dao.intf.IMusicDetailDao;
import com.yuemusic.model.YueMusicDetail;

@Repository
public class MusicDetailDaoImp extends BaseDao<YueMusicDetail> implements IMusicDetailDao {

	@Override
	public List<YueMusicDetail> getAllMusicDetail() {
		// TODO Auto-generated method stub
		return findAll();
	}

	@Override
	public Boolean insertMusicDetail(YueMusicDetail detail) {
		// TODO Auto-generated method stub
		try{
			save(detail);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public YueMusicDetail getMusicDetailByID(Integer id) {
		// TODO Auto-generated method stub
		String queryHQL = "FROM YueMusicDetail WHERE id='"+id+"'";
		return getOneByHQL(queryHQL);
	}

	@Override
	public Boolean updatePlayTime(String playTime, Integer id) {
		// TODO Auto-generated method stub
		String queryHQL = " UPDATE YueMusicDetail SET playTime=? WHERE id=?";
		Session session = null; 
		Transaction ts = null; 
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();

			ts = session.beginTransaction();
			session.createQuery(queryHQL).setString(0, playTime)
					.setInteger(1, id)
					.executeUpdate();

			ts.commit();
			return true;
		} catch (Exception e) {
			ts.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	@Override
	public List<YueMusicDetail> getMusicDetailBySinger(int pageNow, int pageSize, String singer) {
		// TODO Auto-generated method stub
		Session session = getSession();
		Transaction ts = null;
		try{
			ts = session.beginTransaction();
			String hql = "From YueMusicDetail WHERE singer='"+singer+"'";
			Query query = session.createQuery(hql);
			int firstResult = (pageNow-1)*pageSize;
			query.setFirstResult(firstResult);
			query.setMaxResults(pageSize);
			@SuppressWarnings("unchecked")
			List<YueMusicDetail> list = query.list();
			ts.commit();
			return list;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			session.close();
		}
	}

	@Override
	public YueMusicDetail getMusicDetailByNumber(Integer number,
			String musicGroup) {
		// TODO Auto-generated method stub
		String queryHQL = "FROM YueMusicDetail WHERE number="+number+" AND musicGroup='"+musicGroup+"'";
		return getOneByHQL(queryHQL);
	}
	
	@Override
	public int getMusicSizeBySinger(String musicGroup){
		Session session = getSession();
		try{
			return session.createQuery("FROM YueMusicDetail WHERE musicGroup='"+musicGroup+"'").list().size();
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			session.close();
		}
	}

	@Override
	public YueMusicDetail getMusicDetailByPath(String songPath) {
		// TODO Auto-generated method stub
		String queryHQL = "FROM YueMusicDetail WHERE songPath='"+songPath+"'";
		return getOneByHQL(queryHQL);
	}

	@Override
	public Boolean updateSongPath(String newName, Integer id, String oldPath, String musicGroup) {
		// TODO Auto-generated method stub
		String queryHQL = " UPDATE YueMusicDetail SET songPath=?, oldPath=?, musicGroup=? WHERE id=?";
		Session session = null; 
		Transaction ts = null; 
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();

			ts = session.beginTransaction();
			session.createQuery(queryHQL).setString(0, newName)
					.setString(1, oldPath)
					.setString(2, musicGroup)
					.setInteger(3, id)
					.executeUpdate();

			ts.commit();
			return true;
		} catch (Exception e) {
			ts.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}
	
	@Override
	public List<YueMusicDetail> getMusicDetailByGroup(String musicGroup) {
		// TODO Auto-generated method stub
		String queryHQL = "FROM YueMusicDetail WHERE musicGroup='"+musicGroup+"'";
		return getByHQL(queryHQL);
	}
	
	@Override
	public YueMusicDetail getMusicDetailBySong(String song) {
		// TODO Auto-generated method stub
		String queryHQL = "FROM YueMusicDetail WHERE song='"+song+"' AND sourceFlag=1";
		return getOneByHQL(queryHQL);
	}

	@Override
	public Boolean updateMusicPath(String songPath, String oldPath, Integer id) {
		// TODO Auto-generated method stub
		String queryHQL = " UPDATE YueMusicDetail SET songPath=?, oldPath=? WHERE id=?";
		Session session = null; 
		Transaction ts = null; 
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();

			ts = session.beginTransaction();
			session.createQuery(queryHQL).setString(0, songPath)
					.setString(1, oldPath)
					.setInteger(2, id)
					.executeUpdate();

			ts.commit();
			return true;
		} catch (Exception e) {
			ts.rollback();
			e.printStackTrace();
			return false;
		} finally {
			if (session != null && session.isOpen() == true) {
				session.close();
			}
		}
	}

	
	
}
