import org.springframework.web.multipart.commons.CommonsMultipartResolver;

// Place your Spring DSL code here
beans = {
	commonsMultipartResolver(org.springframework.web.multipart.commons.CommonsMultipartResolver){
		maxUploadSize = 1000000
	}
}
