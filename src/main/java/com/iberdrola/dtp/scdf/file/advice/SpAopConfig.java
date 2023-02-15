package com.iberdrola.dtp.scdf.file.advice;

import org.springframework.cloud.fn.supplier.file.FileSupplierConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy
@Import({FileSupplierConfiguration.class})
public class SpAopConfig {

}
