package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.EnvioMensagemDTOOutRecord;
import com.luizalebs.comunicacao_api.business.mapper.ComunicacaoMapper;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoClient;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoMapper converter;
    private final NotificacaoClient notificacaoClient;

    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converter.paraEntity(dto);
        repository.save(entity);
        return converter.paraDTO(entity);
    }

    private ComunicacaoEntity buscarComunicacao(String emailDestinatario) {

        ComunicacaoEntity entity =
                repository.findByEmailDestinatario(emailDestinatario);

        if (Objects.isNull(entity)) {
            throw new RuntimeException("Comunicação não encontrada");
        }

        return entity;
    }

    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {

        ComunicacaoEntity entity =
                buscarComunicacao(emailDestinatario);

        return converter.paraDTO(entity);
    }

    public ComunicacaoOutDTO cancelarStatus(String emailDestinatario) {

        ComunicacaoEntity entity =
                buscarComunicacao(emailDestinatario);

        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);

        repository.save(entity);

        return converter.paraDTO(entity);
    }

    public boolean comunicacaoFoiEnviada(Long id) {
        ComunicacaoEntity entity = repository.findById(id)
                .orElseThrow(()
                        -> new RuntimeException("Comunicação não encontrada")
                );

        return entity.getStatusEnvio() == StatusEnvioEnum.ENVIADO;
    }

    public ComunicacaoOutDTO enviarComunicacao(Long comunicacaoId, EnvioMensagemDTOOutRecord mensagem) {
        ComunicacaoEntity comunicacaoEntity = repository.findById(comunicacaoId)
                .orElseThrow(() ->
                        new RuntimeException("Comunicação não encontrada"));

        if (comunicacaoEntity.getStatusEnvio() == StatusEnvioEnum.ENVIADO) {
            return converter.paraDTO(comunicacaoEntity);
        }

        notificacaoClient.enviarEmail(mensagem);

        comunicacaoEntity.setStatusEnvio(StatusEnvioEnum.ENVIADO);

        repository.save(comunicacaoEntity);

        return converter.paraDTO(comunicacaoEntity);
    }

}
