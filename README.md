## Endpoints da Aplicação


**POST/api/comunicacao/agendar**: Cria um agendamento de comunicação.
O dto deve ser preenchido com os seguintes dados: {"dataHoraEnvio": "yyyy-MM-dd HH:mm:ss",
"nomeDestinatario": "",
"emailDestinatario": "",
"telefoneDestinatario": "",
"mensagem": "",
"ModoEnvioEnum": ""]

**GET/api/comunicacao/{email}**: Busca o status da comunicação via email do destinatário.

**PATCH/api/comunicacao/cancelar/{email}**: Atualiza status da comunicação para CANCELADO através do email do destinatário.




