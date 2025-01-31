package com.insurances.manager.spring;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.insurances.manager.domain.entity.AuthorityEntity;
import com.insurances.manager.domain.entity.UserEntity;
import com.insurances.manager.domain.repository.AuthorityRepository;
import com.insurances.manager.domain.repository.UserRepository;

@Configuration
public class ApplicationConfig {

	@Autowired
	private AuthorityRepository arepository;
	
	@Autowired
	private UserRepository ureRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Bean
	ApplicationRunner applicationRunner() {
		return args -> {
			List<AuthorityEntity> authorities = Collections.emptyList();
			
			if(arepository.count() == 0) {
				authorities = Stream.of(
					AuthorityEntity.builder().name("administrator").build(),
					AuthorityEntity.builder().name("client").build()
				).toList();
				
				arepository.saveAll(authorities);
			}
			
			if(ureRepository.count() == 0) {
				AuthorityEntity authority = null;
				if(!authorities.isEmpty()) {
					authority = authorities.stream().filter(a -> "administrator".equals(a.getName())).findFirst().orElse(null);
				}
				
				if(Objects.isNull(authority)) {
					authority = arepository.findByName("administrator").get();
				}
				
				ureRepository.save(UserEntity.builder()
							.username("admin@domain.io")
							.password(encoder.encode("1234567890"))
							.authorities(Collections.singleton(authority))
							.build());
			}
			
		};
	}
	
}
