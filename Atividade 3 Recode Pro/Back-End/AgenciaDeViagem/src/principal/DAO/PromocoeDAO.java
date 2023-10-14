package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Conexao;
import principal.Promocoe;

public class PromocoeDAO {
	private Connection conexao;
	
	public PromocoeDAO() {
		try {
			conexao = Conexao.conectar();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//CRIAR
	public void savePromocoe(Promocoe promocoe) {
		String sql = "INSERT INTO promocoes (preco, viagem) VALUES (?, ?)";	
		Connection conn = null;
		PreparedStatement pstm = null;		
		try {
			//Criar uma conexão com o banco de dados
			conn = Conexao.conectar();		
			//Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adicionar os valores que são esperados pela query
			pstm.setString(1, promocoe.getPreco());
			pstm.setString(2, promocoe.getViagem());		
			//Executar a query
			pstm.execute();		
			System.out.println("Promocoes salvo com sucesso!");
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
	public Promocoe buscarPromocoe (int id) {
				Promocoe promocoe = null;
				String sql = "SELECT * FROM promocoes WHERE id = ?";
				try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
					pstm.setInt(1, id);
					ResultSet rset = pstm.executeQuery();
					if (rset.next()) {
						promocoe = new Promocoe();
						promocoe.setId(rset.getInt("id"));
						promocoe.setPreco(rset.getString("preco"));
						promocoe.setViagem(rset.getString("viagem"));	
					}
					} catch (SQLException e) {
						e.printStackTrace();	
			}
				return promocoe;
			}
		
	//READ
	public List<Promocoe> getPromocoe(){
			String sql = "SELECT * FROM promocoes";
			List<Promocoe> promocoes = new ArrayList<>();		
			Connection conn = null;
			PreparedStatement pstm = null;
			//Classe que vai recuperar os dados do banco. ***SELECT***
			ResultSet rset = null;	
			try {
				conn = Conexao.conectar();	
				pstm = (PreparedStatement) conn.prepareStatement(sql);		
				rset = pstm.executeQuery();		
				while (rset.next()) {					
					Promocoe promocoe = new Promocoe();				
					//Recuperar o id
					promocoe.setId(rset.getInt("id"));
					//Recuperar o preco
					promocoe.setPreco(rset.getString("preco"));
					//Recuperar a viagem
					promocoe.setViagem(rset.getString("viagem"));
		
					promocoes.add(promocoe);
					System.out.println("Promocoes lido com sucesso!");
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
				return promocoes;
		}
	
	//UPDATE
	public void updatePromocoe(Promocoe promocoe) {		
			String sql = "UPDATE promocoes SET preco = ?, viagem = ? WHERE id = ?";		
			Connection conn = null;
			PreparedStatement pstm = null;		
			try {
				//Criar conexao com o banco
				conn = Conexao.conectar();			
				//Criar a classe para executar a query
				pstm = (PreparedStatement) conn.prepareStatement(sql);			
				//Adicionar os valores para atualizar
				pstm.setString(1, promocoe.getPreco());	
				pstm.setString(2, promocoe.getViagem());	
				//Qual o ID do registro que desejo atualizar?
				pstm.setInt(3, promocoe.getId());	
				//Executar a query
				pstm.execute();
				System.out.println("Promocoes atualizado com sucesso!");
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
	public void deletePromocoeByID(int id) {	
			String sql = "DELETE FROM promocoes WHERE id = ?";	
			Connection conn = null;
			PreparedStatement pstm = null;
			try {
				conn = Conexao.conectar();	
				pstm = (PreparedStatement) conn.prepareStatement(sql);		
				pstm.setInt(1, id);		
				pstm.execute();
				System.out.println("Promocoes deletado com sucesso!");
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