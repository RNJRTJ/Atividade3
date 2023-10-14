package principal;

import java.util.Date;
import java.util.List;

import principal.DAO.ContatoDAO;
import principal.DAO.DestinoDAO;
import principal.DAO.PromocoeDAO;
//import principal.DAO.DestinoDAO;
//import principal.DAO.PromocoeDAO;

public class Main {

	public static void main(String[] args) {
		
		//CREATE - CRIAR
		
		//CONTATO - Funcionou
		ContatoDAO contatoDAO = new ContatoDAO();
		Contato contato = new Contato();
		
		contato.setNome("Robson");
		contato.setCpf("009.090.009-90");
		contato.setEmail("J@hotmail.com");
		contato.setTelefone("87878-8585");
		
		//contatoDAO.saveContato(contato);
		
		//READ - LER OU LISTAR
		
		//CONTATO
		//for(Contato c : contatoDAO.getContato()) {
	//		System.out.println("Contato: "+c.getNome());
	//		System.out.println("Contato: "+c.getCpf());
	//		System.out.println("Contato: "+c.getEmail());
	//		System.out.println("Contato: "+c.getTelefone());
	//	}
		
		//contatoDAO.buscarContato(2);
		
		//UPDATE - ATUALIZAR
		
		//CONTATO
		//Contato c = new Contato();
		contato.setNome("Junior Gomes");
		contato.setCpf("000.000.000-00");
		contato.setEmail("JuniorGomes@gmail.com");
		contato.setTelefone("99999-9999");
		contato.setId(1); // É O NÚMERO QUE ESTÁ NO BANCO DE DADOS DA PK
		
		//contatoDAO.updateContato(contato);
		
		//DELETE - DELETAR OU APAGAR
		
		//CONTATO
		//contatoDAO.deleteContatoByID(2); // É O ID QUE VOCÊ DESEJA REMOVER
		
		//CREATE - CRIAR
		
		//PROMOCOE - Funcionou
		PromocoeDAO promocoeDAO = new PromocoeDAO();
		Promocoe promocoe = new Promocoe();
						
		promocoe.setPreco("1000R$");
		promocoe.setViagem("Madrid");
									
		//promocoeDAO.savePromocoe(promocoe);
		
		//READ - LER OU LISTAR
		
		//PROMOCOE
	//	for(Promocoe p : promocoeDAO.getPromocoe()) {
	//		System.out.println("Promocoe: "+p.getPreco());
	//		System.out.println("Promocoe: "+p.getViagem());
	//	}
		
		//promocoeDAO.buscarPromocoe(1);
		
		//UPDATE - ATUALIZAR
		
		//PROMOCOE
		promocoe.setPreco("2000");
		promocoe.setViagem("Madrid");
		promocoe.setId(1); // É O NÚMERO QUE ESTÁ NO BANCO DE DADOS DA PK
		
		//promocoeDAO.updatePromocoe(promocoe);
		
		//DELETE - DELETAR OU APAGAR
		
		//PROMOCOE
		//promocoeDAO.deletePromocoeByID(3); // É O ID QUE VOCÊ DESEJA REMOVER
		
		//CREATE - CRIAR
		
		//DESTINO - Não Funcionou
		DestinoDAO destinoDAO = new DestinoDAO();
		Destino destino = new Destino();
				
		destino.setContato(contato);
		destino.setPromocoe(promocoe);
		destino.setDataViagem(new Date());
				
		//destinoDAO.saveDestino(destino);
		
		//READ - LER OU LISTAR
		
		//DESTINOS
	//	for(Destino d : destinoDAO.getDestino()) {
	//		System.out.println("Destino: "+d.getContato());
	//		System.out.println("Destino: "+d.getPromocoe());
	//		System.out.println("Destino: "+d.getDataViagem());
	//	}
		
		//destinoDAO.buscarDestino(1);
		
		//UPDATE - ATUALIZAR
		
		//DESTINO
		destino.setContato(contato);
		destino.setPromocoe(promocoe);
		destino.setDataViagem(new Date());
		destino.setId(2); // É O NÚMERO QUE ESTÁ NO BANCO DE DADOS DA PK
		
		//destinoDAO.updateDestino(destino);
		
		//DELETE - DELETAR OU APAGAR
		
		//DESTINO
		destinoDAO.deleteDestinoByID(2); // É O ID QUE VOCÊ DESEJA REMOVER
		
	}

}
