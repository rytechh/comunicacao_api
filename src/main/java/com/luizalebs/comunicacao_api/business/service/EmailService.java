package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.in.EnvioEmailDTO;
import com.luizalebs.comunicacao_api.infraestructure.client.NotificacaoClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final NotificacaoClient notificacaoClient;


    public void enviarEmail(@RequestBody EnvioEmailDTO dto) {
        notificacaoClient.enviarEmail(dto);
    }
}