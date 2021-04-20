package com.twiiter.base.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.twiiter.base.entity.TwiiterTwiitEntity;
import com.twiiter.base.entity.TwiiterUserEntity;

@Repository
public class TwiiterDao {
	
	@Autowired
	EntityManager em;
	
	// ユーザー登録処理
	public void twiiterInsert(TwiiterUserEntity twiiterUserEntity) {
		em.persist(twiiterUserEntity);
	}
	
	/**
	 * フォームの入力値から該当するユーザを検索 合致するものが無い場合Nullが返される
	 * @param userName
	 * @return 一致するユーザが存在するとき:UserEntity、存在しないとき:Null
	 */
	public TwiiterUserEntity findUser(String userName) {
		String query = "";
		query += "SELECT * ";
		query += "FROM USER ";
		query += "WHERE USER_NAME = :userName "; //setParameterで引数の値を代入できるようにNamedParameterを利用
		
		//EntityManagerで取得された結果はオブジェクトとなるので、LoginUser型へキャストが必要となる
		return (TwiiterUserEntity)em.createNativeQuery(query, TwiiterUserEntity.class).setParameter("userName", userName)
				.getSingleResult();
	}
	
	// twiit登録処理
	public void twiiterInsert(TwiiterTwiitEntity twiiterTwiitEntity) {
		em.persist(twiiterTwiitEntity);
	}
	
	// twiit一覧表示処理
	@SuppressWarnings("unchecked")
	public List<TwiiterTwiitEntity> getAllTwiit(){

		StringBuilder findAll = new StringBuilder();

		findAll.append("SELECT * FROM TWIIT");
		
		TypedQuery<TwiiterTwiitEntity> resultList = (TypedQuery<TwiiterTwiitEntity>) em.createNativeQuery(findAll.toString(), TwiiterTwiitEntity.class);
		List<TwiiterTwiitEntity> list = resultList.getResultList();
		return list;
	}
	
	// 参照処理
	public TwiiterTwiitEntity getOne(int id) {

		StringBuilder findTwiit = new StringBuilder();

		findTwiit.append("SELECT * FROM TODO WHERE TODO_ID = ?1");

		TwiiterTwiitEntity twiit = (TwiiterTwiitEntity)em.createNativeQuery(findTwiit.toString(), TwiiterTwiitEntity.class)
				.setParameter(1, id).getSingleResult();

		return twiit;
	}
	
	// twiit削除処理
	public void twiitDelete(int id) {
		StringBuilder findTask = new StringBuilder();
		findTask.append("SELECT * FROM TODO WHERE TODO_ID = ?1");
		
		TwiiterTwiitEntity task = (TwiiterTwiitEntity)em.createNativeQuery(findTask.toString(), TwiiterTwiitEntity.class)
				.setParameter(1, id).getSingleResult();
		em.remove(task);
	}

}
