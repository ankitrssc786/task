package com.mini.project.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mini.project.task.entity.Restaurants;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurants, Long> {

	@Query(value = "SELECT * FROM restaurants WHERE session_id = :sessionId", nativeQuery = true)
	public List<Restaurants> getBySessionId(Long sessionId);
	
	@Query(value = "SELECT * FROM restaurants WHERE vote = (SELECT MAX(vote) FROM restaurants);", nativeQuery = true)
	public List<Restaurants> getByVote();

}
