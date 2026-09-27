package com.luizalebs.comunicacao_api.api.dto;

public record EnvioMensagemDTORecord(

        String nomeDestinatario,
        String emailDestinatario,
        String mensagem,
        String DataHoraEnvio) {
}
