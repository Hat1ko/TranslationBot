package com.greenboy.ms.translation.integration.gcloud.shell.rest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.sun.jdi.InternalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GoogleShellServiceImpl implements GoogleShellService {

	private final ReentrantLock reentrantLock = new ReentrantLock();

	@Override
	public String getAccessToken() {

		reentrantLock.lock();
		try {
			String[] args = { "bash", "scripts/getting_access_token.sh" };
			Process shell = Runtime.getRuntime().exec(args);
			try (BufferedReader input = new BufferedReader(new InputStreamReader(shell.getInputStream()));
					BufferedReader errorInput = new BufferedReader(new InputStreamReader(shell.getErrorStream()))) {

				String error = errorInput.lines().collect(Collectors.joining());
				String accessToken = input.lines().collect(Collectors.joining());

				if (StringUtils.isNotBlank(error)) {
					String errorInputMessage = String.format("Command %s throw Error: %s", Arrays.toString(args),
							error);
					log.error(errorInputMessage);
					throw new InternalException(errorInputMessage);
				}

				log.info("gcloud access token recieved | accessToken : {}", accessToken);

				return String.format("Bearer %s", accessToken);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new InternalException(e.getMessage());
		} finally {
			reentrantLock.unlock();
		}
	}
}
