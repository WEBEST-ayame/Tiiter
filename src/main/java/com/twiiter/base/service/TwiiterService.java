package com.twiiter.base.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.twiiter.base.dao.TwiiterDao;
import com.twiiter.base.entity.TwiiterTwiitEntity;
import com.twiiter.base.entity.TwiiterUserEntity;

@Service
@Transactional
public class TwiiterService implements UserDetailsService {
	
	@Autowired
	TwiiterDao twiiterDao;
	
	@Autowired
	EntityManager em;
	
	
    //DBからユーザ情報を検索するメソッドを実装したクラス

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

    	TwiiterUserEntity user = twiiterDao.findUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User" + userName + "was not found in the database");
        }
        //権限のリスト
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
        //権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        //rawDataのパスワードは渡すことができないので、暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        //UserDetailsはインタフェースなのでUserクラスのコンストラクタで生成したユーザオブジェクトをキャスト
        UserDetails userDetails = (UserDetails)new User(user.getTwiiterUserName(), encoder.encode(user.getTwiiterPassword()),grantList);

        return userDetails;
    }
	
	
	// ユーザー登録処理
	public void twiiterInsert(TwiiterUserEntity twiiterUserEntity) {
		twiiterDao.twiiterInsert(twiiterUserEntity);
	}

	// twiit一覧表示処理
	public List<TwiiterTwiitEntity> getAllTwiit() {
		return twiiterDao.getAllTwiit();
	}
	
	// 参照処理
	public TwiiterTwiitEntity getOne(int id){
		return twiiterDao.getOne(id);
	}

	public void twiitDelete(int id) {
		twiiterDao.twiitDelete(id);
		
	}

}
