package com.iberdrola.dtp.scdf.file.db;

import com.google.common.collect.Sets;
import com.iberdrola.dtp.comms.common.jpa.AbsEntity;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileAudit extends AbsEntity<FileAudit, Integer> {

  private static final Set<String> NON_DOMAIN_FIELDS = Sets.newHashSet("id");

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false)
  private String app;

  @Column(nullable = false, length = 100)
  @Enumerated(value = EnumType.STRING)
  private AuditType auditType;

  @Column(length = 1000)
  private String eventDetail;

  @Override
  protected Set<String> getFieldsToExcludeFromEqualsDomain() {
    return NON_DOMAIN_FIELDS;
  }
}
