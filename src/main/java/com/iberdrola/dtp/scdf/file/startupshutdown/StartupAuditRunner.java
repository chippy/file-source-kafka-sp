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
  private final RuntimeInfo runtimeInfo;

  @Override
  public void run(final String... args) throws Exception {
    auditStartup();
    registerShutdownHook();
  }

  private void auditStartup() {
    repo.auditStartup(runtimeInfo.getAppName(), String.format("Startup on %s", runtimeInfo.getHost()));
  }

  private void registerShutdownHook() {
    final ShutdownAuditHook shutdownHook = new ShutdownAuditHook(repo, runtimeInfo);
    log.info("Registering shutdown hook: {}", shutdownHook.getName());
    Runtime.getRuntime()
           .addShutdownHook(shutdownHook);
  }
}
