package com.iberdrola.dtp.scdf.file.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(value = "file.audit", havingValue = "true", matchIfMissing = true)
public interface FileAuditRepository extends CrudRepository<FileAudit, Integer> {

  default FileAudit auditStartup(final String appName, final String details) {
    return audit(appName, AuditType.STARTUP, details);
  }

  default FileAudit auditShutdown(final String appName, final String details) {
    return audit(appName, AuditType.SHUTDOWN, details);
  }

  default FileAudit audit(final String appName, final AuditType auditType, final String details) {
    final FileAudit audit = FileAudit.builder()
                                     .app(appName)
                                     .auditType(auditType)
                                     .eventDetail(details)
                                     .build();

    return save(audit);
  }
}
