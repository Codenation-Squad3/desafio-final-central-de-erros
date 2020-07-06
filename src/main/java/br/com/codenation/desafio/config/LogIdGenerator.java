package br.com.codenation.desafio.config;


import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import javax.xml.bind.DatatypeConverter;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;

public class LogIdGenerator extends SequenceStyleGenerator {
	
	private List<String> annotationName = new ArrayList<String>();

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

		params.forEach((k, v) -> {
			String key = (String) k;
			if (key.contains("use_as_"))
				annotationName.add((String) v);
		});
		
		if (annotationName.size() == 0) 
			throw new MappingException("The " + this.getClass() + " must have field parameters");
	}
	
	private String getFieldValue(Field field, Object object) {

		field.setAccessible(true);
		Class<?> targetType = field.getType();

		if (targetType.equals(String.class)) {
			try {
				Optional<String> res = Optional.ofNullable((String) field.get(object));
				if (res.isPresent()) {
					return res.get();
				}
			} catch (Exception e) {}
		}
		
		return "";
	}

	private String getObjValues(Object object) {

		return Arrays.stream(object.getClass().getDeclaredFields())
				.filter(field -> {
					if (annotationName.contains(field.getName()))
						return true;
					return false;
				}).reduce("", (partial, field) -> {
					return getFieldValue(field, object);
				}, String::concat);
	}

}
