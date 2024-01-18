package ua.testing.user_service.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import ua.testing.user_service.model.user.UserUpdateRequest;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MultipartJsonConverter extends MappingJackson2HttpMessageConverter {

    private static final Set<Class> SUPPORTED_CLASSES = Set.of(UserUpdateRequest.class);
    private final Set<String> supportedClassNames;

    public MultipartJsonConverter(ObjectMapper mapper){
        super(mapper);
        this.supportedClassNames = SUPPORTED_CLASSES.stream().map(Class::getCanonicalName).collect(Collectors.toSet());

    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType){
        return isSupported(type.getTypeName()) && mediaType.equals(MediaType.APPLICATION_OCTET_STREAM);

    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType){
        return isSupported(clazz.getCanonicalName()) && mediaType.equals(MediaType.APPLICATION_OCTET_STREAM);
    }
    private boolean isSupported(String fullClassName){
        return this.supportedClassNames.contains(fullClassName);
    }
}
