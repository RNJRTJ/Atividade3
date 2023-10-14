package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Conexao;
import principal.Contato;

public class ContatoDAO {
private Connection conexao;
	
	public ContatoDAO() {
		try {
			conexao = Conexao.conectar();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//CRIAR
	public void saveContato(Contato contato) {
		String sql = "INSERT INTO contatos(cpf, nome, email, telefone) VALUES (?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.conectar();
			
			//Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, contato.getCpf());
			pstm.setString(2, contato.getNome());
			pstm.setString(3, contato.getEmail());
			pstm.setString(4, contato.getTelefone());
			
			//Executar a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			//Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
	
	}
	
	//BUSCAR
	public Contato buscarContato (int id) {
		Contato contato = null;
		String sql = "SELECT * FROM contatos WHERE id = ?";
		try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
			pstm.setInt(1, id);
			ResultSet rset = pstm.executeQuery();
			if (rset.next()) {
				contato = new Contato();
				contato.setId(rset.getInt("id"));
				contato.setCpf(rset.getString("cpf"));
				contato.setNome(rset.getString("nome"));
				contato.setEmail(rset.getString("email"));
				contato.setTelefone(rset.getString("telefone"));
			}
		} catch (SQLException e) {
				e.printStackTrace();
	}
		return contato;
	}
	
	//READ
	public List<Contato> getContato(){
		String sql = "SELECT * FROM contatos";	
		List<Contato> contatos = new ArrayList<>();	
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;	
		try {
			conn = Conexao.conectar();		
			pstm = (PreparedStatement) conn.prepareStatement(sql);			
			rset = pstm.executeQuery();			
			while (rset.next()) {				
				Contato contato = new Contato();				
				//Recuperar o id
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar o cpf
				contato.setCpf(rset.getString("cpf"));
				// Recuperar o email
				contato.setEmail(rset.getString("email"));
				//Recuperar o telefone
				contato.setTelefone(rset.getString("telefone"));
				
				contatos.add(contato);
				System.out.println("Contato lido com sucesso!");
			}			
		}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(rset!=null) {
						rset.close();
					}				
					if(pstm!=null) {
						pstm.close();
					}				
					if(conn!=null) {
						conn.close();
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}	
			return contatos;
	}
	
	//UPDATE
	public void updateContato (Contato contato) {
		
		String sql = "UPDATE contatos SET cpf = ?, nome = ?, email = ?, telefone = ? WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;	
		try {
			//Criar conexao com o banco
			conn = Conexao.conectar();		
			//Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);		
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getCpf());
			pstm.setString(2, contato.getNome());
			pstm.setString(3, contato.getEmail());
			pstm.setString(4, contato.getTelefone());		
			//Qual o ID do registro que desejo atualizar?
			pstm.setInt(5, contato.getId());		
			//Executar a query
			pstm.execute();		
			System.out.println("Contato atualizado com sucesso!");		
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	//DELETE
	public void deleteContatoByID(int id) {	
		String sql = "DELETE FROM contatos WHERE id = ?";	
		Connection conn = null;	
		PreparedStatement pstm = null;	
		try {
			conn = Conexao.conectar();		
			pstm = (PreparedStatement) conn.prepareStatement(sql);		
			pstm.setInt(1, id);			
			pstm.execute();
			System.out.println("Contato deletado com sucesso!");
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null) {
					pstm.close();
				}		
				if(conn != null) {
					conn.close();
				}
			}catch (SQLException e) {
			  e.printStackTrace();
			}
		}	
	}
	
	//FECHAR
		public void fecharConexao() {
			try {
				if (conexao != null && !conexao.isClosed()) {
					conexao.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}
		}
	
}