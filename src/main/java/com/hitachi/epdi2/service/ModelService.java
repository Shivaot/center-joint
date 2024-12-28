package com.hitachi.epdi2.service;

import com.hitachi.epdi2.dto.ModelDto;
import com.hitachi.epdi2.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<ModelDto> getAllModels() {
        ModelMapper modelMapper = new ModelMapper();
        return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ModelDto.class))
                .collect(Collectors.toList());
    }
}
