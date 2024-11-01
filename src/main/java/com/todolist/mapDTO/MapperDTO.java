package com.todolist.mapDTO;

import com.todolist.entities.Task;
import com.todolist.entities.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MapperDTO {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Task atualizarTask(TaskDTO dto, @MappingTarget Task task) throws Exception;

    User userDTOtoUser(UserDTO userDto);

    Task taskDTOtoTask(TaskDTO taskdto) throws Exception;

}
