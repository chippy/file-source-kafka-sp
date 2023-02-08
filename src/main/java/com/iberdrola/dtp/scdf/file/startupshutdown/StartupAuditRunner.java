package com.iberdrola.dtp.scdf.file.startupshutdown;

import com.iberdrola.dtp.scdf.file.db.FileAuditRepository;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
    repo.auditStartup("file", String.format("Startup on %s", getHost()));
  }

  private String getHost() {
    try {
      final InetAddress localHost = InetAddress.getLocalHost();
      return String.format("%s (%s)", localHost.getHostAddress(), localHost.getHostName());
    } catch (final UnknownHostException exception) {
      log.error("Unable to determine host: {}", exception.getMessage(), exception);
      return "<unknown>";
    }
  }
}
