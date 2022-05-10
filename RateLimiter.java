package com.cunningdj.grokJava;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.time.Duration;
import java.time.Month;

class RateLimiter {
	private LinkedList<LocalDateTime> dtQueue;
	private final int SECONDS_PER_MINUTE = 60;
	public final int MAX_REQS_PER_MINUTE;
	// FOR TESTING ONLY (otherwise null)
	private final LocalDateTime CURRENT_DT_OVERRIDE;

	public RateLimiter(int maxReqsPerMinute) {
		this.dtQueue = new LinkedList<>();
		this.MAX_REQS_PER_MINUTE = maxReqsPerMinute;
		this.CURRENT_DT_OVERRIDE = null;
	}

	private RateLimiter(int maxReqsPerMinute, LocalDateTime currentDtOverride) {
		// FOR TESTING ONLY
		this.dtQueue = new LinkedList<>();
		this.MAX_REQS_PER_MINUTE = maxReqsPerMinute;
		this.CURRENT_DT_OVERRIDE = currentDtOverride;
	}

	// MAIN
	public static void main(String[] args) {
		Tester tester = new Tester();
		String testTitle="";

		// TEST CLASS METHODS HERE USING TESTER CLASS
		testTitle = "RATE_LIMITER";
		RateLimiter rl = new RateLimiter(3);
		tester.isTrue(rl.newRequest(new Request(RateLimiter.makeTestTime(10, 0))), testTitle);
		tester.isTrue(rl.newRequest(new Request(RateLimiter.makeTestTime(10, 20))), testTitle);
		tester.isTrue(rl.newRequest(new Request(RateLimiter.makeTestTime(10, 40))), testTitle);
		// False, and doesn't add it to the queue
		tester.isFalse(rl.newRequest(new Request(RateLimiter.makeTestTime(10, 59))), testTitle);
		// True; Skipoed the 10:59 request, and 10:00 request is now > 60s old, leaving 2 within the window during check
		tester.isTrue(rl.newRequest(new Request(RateLimiter.makeTestTime(11, 01))), testTitle);
	}

	// PUBLIC
	public boolean newRequest(Request req) {
		if (dtQueue.size() == MAX_REQS_PER_MINUTE) {
			while (dtQueue.size() > 0
				&& Duration.between(dtQueue.peek(), req.dt).getSeconds() > SECONDS_PER_MINUTE) {
					dtQueue.poll();
			}
		}
		if (dtQueue.size() < MAX_REQS_PER_MINUTE) {
			dtQueue.add(req.dt);
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
		public Request() {
			this.dt = LocalDateTime.now();
		}

		// TESTING ONLY
		private Request(LocalDateTime dt) {
			this.dt = dt;
		}
	}
}