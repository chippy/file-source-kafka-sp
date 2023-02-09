package com.iberdrola.dtp.scdf.file.startupshutdown;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.fn.common.metadata.store.MetadataStoreProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.integration.jdbc.metadata.JdbcMetadataStore;
import org.springframework.integration.metadata.MetadataStore;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppVerifier implements CommandLineRunner {

  private static final String METADATA_STORE_PROPERTIES_MISSING = "The Metadata store was verified as JDBC but the properties can no longer be found";
  private static final String APP_NAME_VIA_REGION_MISSING =
      "Cannot start application without a region being set. This is used as the app name and identifier"
      + " for audit records. Please set \"metadata.store.jdbc.region\"";
  private final MetadataStore metadataStore;
  private final MetadataStoreProperties metadataStoreProperties;
  private final RuntimeInfo runtimeInfo;

  @Override
  public void run(final String... args) throws Exception {

    if (!(metadataStore instanceof JdbcMetadataStore)) {
      throw new IllegalStateException(String.format("The Metadata store was supposed to be an instance of %s but was %s",
                                                    JdbcMetadataStore.class.getSimpleName(), metadataStore.getClass()
                                                                                                          .getSimpleName()));
    }
    if (metadataStoreProperties.getJdbc() == null) {
      throw new IllegalStateException(METADATA_STORE_PROPERTIES_MISSING);
    }
    if (StringUtils.isBlank(runtimeInfo.getAppName())) {
      throw new IllegalStateException(APP_NAME_VIA_REGION_MISSING);
    }

    log.info("Starting File application with app audit name of \"{}\"", runtimeInfo.getAppName());
  }
}
