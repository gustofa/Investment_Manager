package groupone.java.repositories;

import java.util.List;

import javax.persistence.EntityManager;

import groupone.java.bean.Account;
import groupone.java.bean.Indicator;
import groupone.java.bean.User;;

public class UserRepository extends Repository {
	UserRepository(EntityManager em) {
		super(em);
	}
	
	public User findByName(String name) {
		User user = null;
		List<User> users = em.createNamedQuery("getUser")
				.setParameter("pname", name)
				.getResultList();
		
		if(users.size() == 1)
		{
			user = users.get(0);
		}
		
		return user;
	}
}