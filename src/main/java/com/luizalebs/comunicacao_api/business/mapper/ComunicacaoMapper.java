package com.luizalebs.comunicacao_api.business.mapper;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "Spring")
public interface ComunicacaoMapper {

    ComunicacaoEntity paraEntity(ComunicacaoInDTO comunicacaoInDTO);

    ComunicacaoOutDTO paraDTO(ComunicacaoEntity entity);
}
