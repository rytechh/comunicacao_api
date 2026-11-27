package com.luizalebs.comunicacao_api.api.dto.out;

public record EnvioMensagemDTOOutRecord(

        String nomeDestinatario,
        String emailDestinatario,
        String mensagem,
        String DataHoraEnvio) {
}
