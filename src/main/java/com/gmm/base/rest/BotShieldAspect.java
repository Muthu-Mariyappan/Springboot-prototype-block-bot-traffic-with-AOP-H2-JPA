package com.gmm.base.rest;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.gmm.entities.IPEntry;
import com.gmm.services.IPEntryService;

@Aspect
@Component
class BotShieldAspect {
	
	@Autowired
	IPEntryService ipEntryService;
	
	private final int RESET_HOURS = 8;
	private final int MAX_HITS = 30;
	private final int DURATION = 30; //in seconds
	
	@Around("(execution(* GreetingsController.greet*(..)))") //Around advice to get control on target method invocation
	public Object checkBotAccess(ProceedingJoinPoint jp) throws Throwable {

		HttpServletRequest request = (HttpServletRequest) jp.getArgs()[0]; // to get remote machine's ip address
		HttpServletResponse response = (HttpServletResponse) jp.getArgs()[2]; // to throw 503 error
		String ip = request.getRemoteAddr().toString();
		IPEntry entry = this.ipEntryService.findByIP(ip);
		if (entry == null)//if IP is fresh entry
			this.ipEntryService.insert(new IPEntry(ip, 1L, new Date()));
		else {

			if (entry.getHitCount() > MAX_HITS) { // if APi is accessed faster for 30 times then deny service for next 8 hours
				response.sendError(HttpStatus.SERVICE_UNAVAILABLE.value(),
						"Too many requests in short time! Try again after 8 hours");
				return null;
			}

			long now = new Date().getTime() / 1000; //converting ms to seconds
			long lastHit = entry.getLastHitTime().getTime() / 1000;

			Long duration = now - lastHit;

			System.out.println("Duration is " + duration);

			if ((duration / (60 * 60)) > RESET_HOURS) { // If 8 hours passed since last block, mark IP as fresh entry
				entry.setHitCount(1L);
			} else if (duration <= DURATION) { // if request is made within 30 second span add it to fast hit count
				entry.setHitCount(entry.getHitCount() + 1);
			}
			
			entry.setLastHitTime(new Date()); //update hit entry
			
			this.ipEntryService.insert(entry);
		}

		return jp.proceed(); // if call is ok, then make the actual method invocation and return the result

	}
}