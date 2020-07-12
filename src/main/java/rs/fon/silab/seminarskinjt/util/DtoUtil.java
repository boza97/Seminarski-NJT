/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.fon.silab.seminarskinjt.dto.IDto;
import rs.fon.silab.seminarskinjt.entity.IEntity;

/**
 *
 * @author Bozidar
 */
@Component
public class DtoUtil {

    private final ModelMapper modelMapper;

    @Autowired
    public DtoUtil(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public IDto convertToDto(IEntity obj, IDto mapper) {
        return modelMapper.map(obj, mapper.getClass());
    }

    public IEntity convertToEntity(IDto obj, IEntity mapper) {
        return modelMapper.map(obj, mapper.getClass());
    }
}
