package hakan.questapp.business.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoConverterService {
    @Autowired
    private ModelMapper modelMapper;


    public DtoConverterService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T, U> U entityToDto(T entity, Class<U> dtoClass) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(entity, dtoClass);
    }

    public <T, U> U dtoToEntity(T dto, Class<U> entityClass) {
//        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(dto, entityClass);
    }
}
