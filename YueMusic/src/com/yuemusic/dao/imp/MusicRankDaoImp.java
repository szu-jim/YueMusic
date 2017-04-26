//package com.yuemusic.dao.imp;
//
//import java.util.List;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.stereotype.Repository;
//
//import com.yuemusic.dao.intf.IMusicRankDao;
//import com.yuemusic.model.YueMusicDetail;
//import com.yuemusic.model.YueMusicRank;
//
//@Repository
//public class MusicRankDaoImp extends BaseDao<YueMusicRank> implements IMusicRankDao {
//
//	@Override
//	public List<YueMusicRank> getAllMusicRank() {
//		// TODO Auto-generated method stub
//		return findAll();
//	}
//	
//	@Override
//	public List<YueMusicRank> getMusicRankByID(Integer rankID) {
//		// TODO Auto-generated method stub
//		String queryHQL = "FROM YueMusicRank WHERE rankId='"+rankID+"'";
//		return getByHQL(queryHQL);
//	}
//	
//	@Override
//	public int getMusicSizeBySinger(String singer){
//		Session session = getSession();
//		try{
//			return session.createQuery("FROM YueMusicRank WHERE singer='"+singer+"'").list().size();
//		}catch(Exception e){
//			e.printStackTrace();
//			return 0;
//		}finally{
//			session.close();
//		}
//	}
//
//	@Override
//	public Boolean updatePlayTime(String playTime, Integer rankID) {
//		// TODO Auto-generated method stub
//		String queryHQL = " UPDATE YueMusicRank SET playTime=? WHERE rankId=?";
//		Session session = null; 
//		Transaction ts = null; 
//		try {
//			session = getHibernateTemplate().getSessionFactory().openSession();
//
//			ts = session.beginTransaction();
//			session.createQuery(queryHQL).setString(0, playTime)
//					.setInteger(1, rankID)
//					.executeUpdate();
//
//			ts.commit();
//			return true;
//		} catch (Exception e) {
//			ts.rollback();
//			e.printStackTrace();
//			return false;
//		} finally {
//			if (session != null && session.isOpen() == true) {
//				session.close();
//			}
//		}
//	}
//
//	@Override
//	public List<YueMusicRank> getMusicDetailByNumber(Integer number,
//			String singer) {
//		// TODO Auto-generated method stub
//		String queryHQL = "FROM YueMusicRank WHERE number="+number+" AND singer='"+singer+"'";
//		return getByHQL(queryHQL);
//	}
//	
//	@Override
//	public YueMusicRank getMusicDetailByPath(String songPath) {
//		// TODO Auto-generated method stub
//		String queryHQL = "FROM YueMusicRank WHERE songPath='"+songPath+"'";
//		return getOneByHQL(queryHQL);
//	}
//	
//	@Override
//	public Boolean updateSongPath(String newName, Integer id, String oldPath) {
//		// TODO Auto-generated method stub
//		String queryHQL = " UPDATE YueMusicRank SET songPath=?, oldPath=? WHERE rankId=?";
//		Session session = null; 
//		Transaction ts = null; 
//		try {
//			session = getHibernateTemplate().getSessionFactory().openSession();
//
//			ts = session.beginTransaction();
//			session.createQuery(queryHQL).setString(0, newName)
//					.setString(1, oldPath)
//					.setInteger(2, id)
//					.executeUpdate();
//
//			ts.commit();
//			return true;
//		} catch (Exception e) {
//			ts.rollback();
//			e.printStackTrace();
//			return false;
//		} finally {
//			if (session != null && session.isOpen() == true) {
//				session.close();
//			}
//		}
//	}
//
//}
