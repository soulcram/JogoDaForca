package inteligenciaartificial.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Letra {
	
        private Integer id;
	private Integer posicao;
	private String letraAnterior;
	private String letraPosterior;
        private String letraEscolhida;
	private boolean primeira;
	private boolean ultima;
        private Integer certo;
        private Integer errado;
        
	public Letra(Integer posicao, String letraAnterior, String letraPosterior) {
		super();
		this.posicao = posicao;
		this.letraAnterior = letraAnterior;
		this.letraPosterior = letraPosterior;
	}
	
	
	

}
