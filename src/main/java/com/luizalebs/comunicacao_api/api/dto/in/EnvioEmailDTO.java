package com.luizalebs.comunicacao_api.api.dto.in;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnvioEmailDTO {

    private String nomeDestinatario;
    private String emailDestinatario;
    private String mensagem;
    @JsonFormat(shape =JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private String DataHoraEnvio;
}
