package com.luizalebs.comunicacao_api.api;

import com.luizalebs.comunicacao_api.api.dto.in.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.out.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.out.EnvioMensagemDTOOutRecord;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunicacao")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "Cria agendamentos com mensagens personalizadas")
public class ComunicacaoController {

    private final ComunicacaoService comunicacaoService;

    @PostMapping("/agendar")
    @Operation(summary = "Salva agendamentos", description = "Cria novos agendamentos")
    @ApiResponse(responseCode = "200", description = "Agendamento salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro do cliente") //revisar isso aqui
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto) {
        return ResponseEntity.ok(comunicacaoService.agendarComunicacao(dto));
    }

    public ResponseEntity<ComunicacaoOutDTO> enviarComunicacao(@RequestParam("id") Long id,
                                                               @RequestBody EnvioMensagemDTOOutRecord envioMensagemDTORecord) {
        return ResponseEntity.ok(comunicacaoService.enviarComunicacao(id, envioMensagemDTORecord));
    }

    @GetMapping()
    @Operation(summary = "Buscar status de agendamento", description = "Busca status de agendamento via email")
    @ApiResponse(responseCode = "200", description = "Status encontrado com sucesso") //*
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(comunicacaoService.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/cancelar")
    @Operation(summary = "Atualiza status de envio", description = "Muda status de envio para cancelado")
    @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(comunicacaoService.buscarStatusComunicacao(emailDestinatario));
    }
}
