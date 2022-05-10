package com.cunningdj.grokJava;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;

/*
 * RATE LIMITER - SLIDING WINDOW PER-USER ALGORITHM
 */
class RateLimiter {
	// user ID : queue of request DTs (phased out of window as-you-go)
	private HashMap<String, LinkedList<LocalDateTime>> dtQueue;
	public final int MAX_REQS_PER_MINUTE;
	// FOR TESTING ONLY (otherwise null)
	private final LocalDateTime CURRENT_DT_OVERRIDE;

	public RateLimiter(int maxReqsPerMinute) {
		this.dtQueue = new HashMap<>();
		this.MAX_REQS_PER_MINUTE = maxReqsPerMinute;
		this.CURRENT_DT_OVERRIDE = null;
	}

	// MAIN
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// TEST CLASS METHODS HERE USING TESTER CLASS
		testTitle = "RATE_LIMITER";
		String TEST_USER = "El Duderino";
		String OTHER_TEST_USER = "Mr. Lebowski";
		RateLimiter rl = new RateLimiter(3);
		tester.isTrue(rl.newRequest(new Request(TEST_USER, RateLimiter.makeTestTime(10, 0))), testTitle);
		tester.isTrue(rl.newRequest(new Request(TEST_USER, RateLimiter.makeTestTime(10, 20))), testTitle);
		tester.isTrue(rl.newRequest(new Request(TEST_USER, RateLimiter.makeTestTime(10, 40))), testTitle);
		// False, and doesn't add it to the queue
		tester.isFalse(rl.newRequest(new Request(TEST_USER, RateLimiter.makeTestTime(10, 59))), testTitle);
		// Different user; shouldn't fail
		tester.isTrue(rl.newRequest(new Request(OTHER_TEST_USER, RateLimiter.makeTestTime(10, 59))), testTitle);
		// True; Skipoed the 10:59 request, and 10:00 request is now > 60s old, leaving 2 within the window during check
		tester.isTrue(rl.newRequest(new Request(TEST_USER, RateLimiter.makeTestTime(11, 01))), testTitle);
	}

	// PUBLIC
	public boolean newRequest(Request req) {
		if (dtQueue.containsKey(req.user)) {
			if (dtQueue.get(req.user).size() == MAX_REQS_PER_MINUTE) {
				while (dtQueue.get(req.user).size() > 0
					&& Duration.between(dtQueue.get(req.user).peek(), req.dt).toMinutes() > 0) {
						dtQueue.get(req.user).poll();
				}
			}
		} else {
			LinkedList<LocalDateTime> userRequestQ = new LinkedList<>();
			dtQueue.put(req.user, userRequestQ);
		}

		if (dtQueue.get(req.user).size() < MAX_REQS_PER_MINUTE) {
			dtQueue.get(req.user).add(req.dt);
			return true;
		} else {
			return false;
		}
	}

	// PRIVATE
	// TEST ONLY
	private static LocalDateTime makeTestTime(int minutes, int seconds) {
		return LocalDateTime.of(2022, Month.MAY, 10, 10, minutes, seconds);
	}

	// Request
	static class Request {
		public LocalDateTime dt;
		public String user;
		public Request(String user) {
			this.user = user;
			this.dt = LocalDateTime.now();
		}

		// TESTING ONLY
		private Request(String user, LocalDateTime dt) {
			this.user = user;
			this.dt = dt;
		}
	}
}