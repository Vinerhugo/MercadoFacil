package br.una.prova.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
public class JasperConfig {

	@Bean
    public JasperReportsViewResolver getJasperReportsViewResolver() {
        JasperReportsViewResolver resolver = new JasperReportsViewResolver();
        resolver.setPrefix("classpath:/");
        resolver.setSuffix(".jrxml");
        resolver.setReportDataKey("datasource");
        resolver.setViewNames("reports/*");
        resolver.setViewClass(DinamicJasperReportsMultiFormatView.class);
        resolver.setOrder(0);
        return resolver;
    }
	
}
	class DinamicJasperReportsMultiFormatView extends  JasperReportsMultiFormatView{
	    public DinamicJasperReportsMultiFormatView() {
	        Properties contentDispositionMappings = new Properties();
	        contentDispositionMappings.put("pdf","attachment; filename=relatorio.pdf");
	        contentDispositionMappings.put("xlsx","attachment; filename=relatorio.xlsx");
	        setContentDispositionMappings(contentDispositionMappings);
	    }
	
}
