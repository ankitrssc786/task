package com.mini.project.task.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mini.project.task.entity.Restaurants;
import com.mini.project.task.entity.Session;
import com.mini.project.task.entity.User;
import com.mini.project.task.repository.RestaurantRepository;
import com.mini.project.task.repository.SessionRepository;
import com.mini.project.task.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class SessionController {

	@Autowired
	SessionRepository sessionRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RestaurantRepository restaurantRepository;

	List<User> userIds = new ArrayList<>();
	User user = new User();

	List<Restaurants> restaurantsIds = new ArrayList<>();
	Restaurants restaurants = new Restaurants();

	@GetMapping("/session")
	public List<Session> getAllSession() {
		return sessionRepository.findAll();
	}

	@PostMapping("/session")
	public Session createSession(@Valid @RequestBody Session session) {
		Optional<User> userDetails = userRepository.findById(session.getIntitatorUserId());
		userDetails.get().setId(session.getIntitatorUserId());
		session.setIntitatorUserId(userDetails.get().getId());
		session.setSessionActive(true);
		return sessionRepository.save(session);
	}

	@PostMapping("/join")
	public ResponseEntity<String> joinSession(@RequestParam Long sessionId, @RequestBody User user) {

		Session session = sessionRepository.getById(sessionId);
		try {
			if (session.getSessionId() != null) {
				if (session.isSessionActive()) {
					user.setId(user.getId());
					userIds.add(user);
					session.setParticipants(userIds);
					sessionRepository.save(session);
					return ResponseEntity.status(HttpStatus.OK).body("Sessiom Joined");
				} else if (!session.isSessionActive() && session.getSessionId() != null) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Session has already ended");
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not Found");
	}

	@GetMapping("/restaurants")
	public List<Restaurants> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	@GetMapping("/getRestaurants/{sessionId}")
	public List<Restaurants> getRestaurants(@PathVariable Long sessionId) {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));
		List<Restaurants> activeSessionReasturantDetails = restaurantRepository.getBySessionId(session.getSessionId());
		return activeSessionReasturantDetails;
	}

	@PostMapping("/submitRestaurant")
	public void submitRestaurant(@RequestParam Long sessionId, @RequestBody Restaurants restaurant) {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));
		if (!session.isSessionActive()) {
			throw new RuntimeException("Session has already ended");
		}
		int countPlus = 0;
		Restaurants restrnt = restaurantRepository.getById(restaurant.getId());
		List<Restaurants> activeSessionReasturantDetails = restaurantRepository.getBySessionId(session.getSessionId());
		int count = activeSessionReasturantDetails.size();
		count++;
		countPlus = count;
		restaurants.setId(restrnt.getId());
		restaurants.setName(restrnt.getName());
		restaurants.setVote(countPlus);
		restaurantRepository.save(restaurants);
		restaurantsIds.add(restaurants);
		session.setRestaurants(restaurantsIds);
		sessionRepository.save(session);
	}

	@PostMapping("/endSession")
	public Restaurants endSession(@RequestParam Long sessionId, @RequestBody User initiator) {
		Session session = sessionRepository.findById(sessionId)
				.orElseThrow(() -> new RuntimeException("Session not found"));
		if (!session.getIntitatorUserId().equals(initiator.getId())) {
			throw new RuntimeException("Only the initiator can end the session");
		}
		if (!session.isSessionActive()) {
			throw new RuntimeException("Session has already ended");
		}
		List<Restaurants> activeSessionReasturantDetails = restaurantRepository.getByVote();
		Restaurants pickedRestaurant = activeSessionReasturantDetails.get(0);
		session.setSessionActive(false);
		sessionRepository.save(session);
		return pickedRestaurant;
	}

}
