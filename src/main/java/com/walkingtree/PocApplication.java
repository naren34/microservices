package com.walkingtree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages={"com.walkingtree.repository"})
@EnableJpaRepositories(basePackages={"com.walkingtree.repository"})
@EntityScan(basePackages={"com.walkingtree.entity"})
public class PocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocApplication.class, args);
	}
}
