package de.effectivetrainings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ResponseInfo {

    private String host;
    private int port;
    private String correlationId;
    private ResponseInfo responseInfo;
}
