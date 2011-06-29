package br.com.dlcoding.vraptor.sample.json;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.HashMap;
import java.util.Map;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class SimpleJsonController { 
    
    private static Map<Long,Pessoa> pessoaMap = new HashMap<Long, Pessoa>();
	
    private Result result;

    public SimpleJsonController(Result result) {
        this.result = result;
    }

    
    @Post
    @Path("/pessoa/save")
    @Consumes({"application/json","json"})
    public void savePessoa(Pessoa pessoa) {
    	
    	if (pessoa.id == null) {
    		 pessoa.id = System.currentTimeMillis();
    		
    	}
    		
    	pessoaMap.put(pessoa.id, pessoa);
    	
        result.use(json()).from(pessoa).recursive().serialize();
    }
    
    @Get
    @Path({"/pessoa/{id}"})
    public void getPessoa(Long id) {
    	
        if (id == null) {
            throw new RuntimeException("Id da pessoa obrigatório");
        }
       
        Pessoa pessoa = pessoaMap.get(id);
        
        result.use(json()).from(pessoa).recursive().serialize();
    }
   
}