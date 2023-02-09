package com.iberdrola.dtp.scdf.file.startupshutdown;

import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.fn.common.metadata.store.MetadataStoreProperties;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RuntimeInfo {

  private final MetadataStoreProperties metadataStoreProperties;
  private String host;
  private String appName;

  public String getHost() {
    if (host == null) {
      try {
        final InetAddress localHost = InetAddress.getLocalHost();
        host = String.format("%s (%s)", localHost.getHostAddress(), localHost.getHostName());
      } catch (final UnknownHostException exception) {
        log.error("Unable to determine host: {}", exception.getMessage(), exception);
        host = "<unknown>";
      }
    }
    return host;
  }

  public String getAppName() {
    if (appName == null) {
      appName = metadataStoreProperties.getJdbc()
                                       .getRegion();
    }
    return appName;
  }
}
