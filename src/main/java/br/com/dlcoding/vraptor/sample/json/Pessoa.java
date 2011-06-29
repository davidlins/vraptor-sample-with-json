/**
 * 
 */
package br.com.dlcoding.vraptor.sample.json;

import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author david
 *
 */
@XStreamAlias("pessoa")
public class Pessoa {
	
	public Long id;
	public String nome;
	public Date dataNascimento;
	public Endereco endereco;
	
	@XStreamImplicit
	public List<Telefone> telefone;
	
	 
	public static class Endereco {
		 
		 public String rua;
		 public String numero;
		 public String complemento;
		 public Integer cep;
		 public String estado;
		 public String cidade;
		 
	 }
	
	 @XStreamAlias("telefone")
	 public static class Telefone {
		 
		 public short ddd;
		 public Integer numero;
	 
	 }
}
