package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.RoleEntity;
import com.entity.UserEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class UserDao {
	
	@Autowired
	EntityManager em;
	
	public List<UserEntity> getAllUsers(String data)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query=cb.createQuery(UserEntity.class);  //To run the query we have cretaed this query object
		Root<UserEntity> root=query.from(UserEntity.class); // from grpby having select
		Predicate p=cb.like(root.get("firstName"), data+"%");
		Predicate q=cb.lessThan(root.get("age"), 30);
		query.where(p,q);
//		query.where(cb.and(p,q)); explicit and
//		query.where(cb.or(p,q)); to have OR operation perform over to query
		TypedQuery<UserEntity> userQuery=em.createQuery(query);
		return userQuery.getResultList();
	}
	
	public List<UserEntity> getAllUsers1(String data)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query=cb.createQuery(UserEntity.class);
		Root<UserEntity> root=query.from(UserEntity.class);
		List<Predicate> predicate=new ArrayList<Predicate>();
		predicate.add(cb.like(root.get("firstName"), data+"%"));
		predicate.add(cb.lessThan(root.get("age"), 30));
		predicate.add(cb.isNotNull(root.get("token")));
		query.where(predicate.stream().toArray(Predicate[]::new)); //individual where clause is passed in the Predicate array 
//		predicate.stream().forEach(p->System.out.println(p)); forEach loop using stream Api
		TypedQuery<UserEntity> userQuery=em.createQuery(query);
		return userQuery.getResultList();
	}
	
	public List<UserEntity> getAllUsers2(String data)
	{
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<UserEntity> query=cb.createQuery(UserEntity.class);
		Root<UserEntity> root=query.from(UserEntity.class);
		Join<UserEntity, RoleEntity> join=root.join("role",JoinType.INNER);
//		user is the main table and role with whom join has to done
		List<Predicate> predicate=new ArrayList<Predicate>();
		predicate.add(cb.like(join.get("firstName"), data+"%"));
		predicate.add(cb.lessThan(join.get("age"), 30));
		predicate.add(cb.isNotNull(join.get("token")));
		query.where(predicate.stream().toArray(Predicate[]::new)); //individual where clause is passed in the Predicate array 
		TypedQuery<UserEntity> userQuery=em.createQuery(query);
		return userQuery.getResultList();
//		return userQuery.getSingleResult();
	}
}


// to need to make critertiaQuery we need CriteriaBuilder so from that we will make criteriaQuery
//Bcause here we are using factory design pattern to produce the object of one class using other
// now we have query object so we run select * from users; here we need a class on which the query should be run
// so UserEntity and after that the where clause is written in Predicate and we can have multiple 
//Predicate if we have multiple where clauses
//and to join all the where clause we combine them by AND operation
