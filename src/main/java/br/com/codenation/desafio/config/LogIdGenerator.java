package br.com.codenation.desafio.config;

import br.com.codenation.desafio.model.Log;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogIdGenerator extends SequenceStyleGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

        try {

            Log callerLog = (Log) object;
            String baseString = callerLog.getTitle() + callerLog.getOrigin() + callerLog.getDescription() ;

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(baseString.getBytes());
            byte[] digest = md.digest();

            String generatedLogId = DatatypeConverter.printHexBinary(digest);

            return generatedLogId.toLowerCase();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

}
