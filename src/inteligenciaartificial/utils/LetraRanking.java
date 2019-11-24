package inteligenciaartificial.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter@Setter
@NoArgsConstructor
public class LetraRanking {

    private Integer id;
    private String letra;
    private Integer certo;
    private Integer errado;

}
