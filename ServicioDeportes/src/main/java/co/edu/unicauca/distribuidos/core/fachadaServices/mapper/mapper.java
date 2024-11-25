package co.edu.unicauca.distribuidos.core.fachadaServices.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Permite crear un objeto para realizar u mapero entre las entitys y las DTO */
@Configuration
public class mapper {
    @Bean /*Permite guardar el objeto en el contenedor para utilizarlo */
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
