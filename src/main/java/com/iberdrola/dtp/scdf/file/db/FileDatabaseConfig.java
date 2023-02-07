package com.iberdrola.dtp.scdf.file.db;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@Configuration
@EnableJpaAuditing              //This will ensure @Created and @LastModifiedDate of AbsEntities are respected
@RequiredArgsConstructor
@EnableTransactionManagement
@EntityScan(basePackageClasses = {FileAudit.class})
@EnableJpaRepositories(basePackageClasses = {FileAuditRepository.class})
@ConditionalOnProperty(value = "file.audit", havingValue = "true", matchIfMissing = true)
public class FileDatabaseConfig {

}
