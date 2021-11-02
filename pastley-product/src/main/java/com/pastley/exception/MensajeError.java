package com.pastley.exception;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensajeError {

    private String mensaje;
    private String excepcion;
    private String url;
    private int status;


}
