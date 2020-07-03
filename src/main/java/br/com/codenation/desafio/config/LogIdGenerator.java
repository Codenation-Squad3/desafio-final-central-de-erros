package br.com.codenation.desafio.config;


import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import br.com.codenation.desafio.repository.LogRepository;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;

public class LogIdGenerator extends SequenceStyleGenerator {
	
	private static final String PARAMETER = "annotation_name";
	private String annotationName;

	@Autowired
	private LogRepository repository;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

    	String baseString = getObjValues(object);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(baseString.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter
                    .printHexBinary(digest);

            return myHash.toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
    	super.configure(StringType.INSTANCE, params, serviceRegistry);
    	
    	if (params.containsKey(PARAMETER)) {
    		annotationName = params.getProperty(PARAMETER);
    	} else {
    		throw new MappingException("The " + this.getClass() + " must have " + PARAMETER + " parameter");
    	}
    }
    
    private String getObjValues(Object object) {
    	return Arrays.stream(object.getClass().getDeclaredFields())
	    	.filter(field -> {
	    		for (Annotation annotation : field.getAnnotations()) {
	    			if (annotation.toString().contains(annotationName)) {
	    				return true;
	    			}
	    		}
	    		return false;
	    	}).reduce("", (partial, field) -> {
	    		
	    		field.setAccessible(true);
				Class<?> targetType = field.getType();
				
				if (targetType.equals(String.class)) {
					try {						
						Optional<String> res = Optional.ofNullable((String) field.get(object));
						if (res.isPresent()) {
							return partial + res.get();
						}
					} catch (Exception e) {}
				}
	    		
	    		return "";
	    	}, String::concat);
    }
    
}
