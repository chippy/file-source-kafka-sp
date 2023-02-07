package com.iberdrola.dtp.scdf.file.startupshutdown;

import com.iberdrola.dtp.scdf.file.db.FileAuditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty(value = "file.audit", havingValue = "true", matchIfMissing = true)
public class StartupAuditRunner implements CommandLineRunner {

  private final FileAuditRepository repo;

  @Override
  public void run(final String... args) throws Exception {
    log.error("HI I'M THE COMMAND LINE RUNNER");
    repo.auditStartup("file");
  }
}
