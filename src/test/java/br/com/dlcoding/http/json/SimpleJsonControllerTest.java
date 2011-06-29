package br.com.dlcoding.http.json;

import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.dlcoding.vraptor.sample.json.Pessoa;
import br.com.dlcoding.vraptor.sample.json.Pessoa.Endereco;
import br.com.dlcoding.vraptor.sample.json.Pessoa.Telefone;

public class SimpleJsonControllerTest {

	private static String baseUrl;
	private static HttpServiceInvoke serviceInvoker;
	private static Long id;

	@BeforeClass
	public static void startUp() {
		baseUrl = "http://localhost:8080/vraptor-sample-with-json";
		serviceInvoker = new HttpServiceInvoke();
	}

	@Test
	public void salve() throws Exception {

		String uri = baseUrl + "/pessoa/save";

		Pessoa pessoa = new Pessoa();
		pessoa.nome = "Fulano";
		pessoa.dataNascimento = new Date();

		Endereco endereco = new Endereco();
		endereco.rua = "Rua onze";
		endereco.numero = "12";
		endereco.estado = "SP";
		endereco.cidade = "São Paulo";
		endereco.cep = 01234567;
		pessoa.endereco = endereco;

		pessoa.telefone = new ArrayList<Telefone>();

		Telefone telefone = new Telefone();
		telefone.ddd = 11;
		telefone.numero = 123456787;
		pessoa.telefone.add(telefone);

		Telefone telefone1 = new Telefone();
		telefone1.ddd = 12;
		telefone1.numero = 87654321;
		pessoa.telefone.add(telefone1);

		Pessoa pessoaSalva = serviceInvoker.invoke(uri, pessoa, Pessoa.class);

		id = pessoaSalva.id;

		System.out.println(id);

	}

	@Test
	public void consultaPessoa() throws Exception {

		String uri = baseUrl + "/pessoa/" + id;

		Pessoa pessoa = serviceInvoker.invoke(uri, null, Pessoa.class);

		assertEquals(id, pessoa.id);

	}

}
