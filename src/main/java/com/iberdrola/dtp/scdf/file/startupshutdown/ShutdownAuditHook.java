package com.iberdrola.dtp.scdf.file.startupshutdown;

import com.iberdrola.dtp.scdf.file.db.FileAudit;
import com.iberdrola.dtp.scdf.file.db.FileAuditRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ShutdownAuditHook extends Thread {

  private final FileAuditRepository repo;
  private final RuntimeInfo runtimeInfo;

  public ShutdownAuditHook(final FileAuditRepository repo, final RuntimeInfo runtimeInfo) {
    this.repo = repo;
    this.runtimeInfo = runtimeInfo;
    setName("File Shutdown Hook");
  }

  @Override
  public void run() {
    log.info("JVM Shutdown initiated. Recording in database");
    try {
      final FileAudit audit = repo.auditShutdown(runtimeInfo.getAppName(), String.format("Shutdown on %s", runtimeInfo.getHost()));
      log.debug("Audit recorded: {}", audit);
    } catch (final Exception e) {
      log.error("Could not register audit of app shutting down: {}", e.getMessage(), e);
    }
  }
}
