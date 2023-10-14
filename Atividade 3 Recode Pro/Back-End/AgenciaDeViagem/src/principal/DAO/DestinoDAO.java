package principal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import principal.Conexao;
import principal.Destino;

public class DestinoDAO {
	private Connection conexao;
	ContatoDAO contatoDAO = new ContatoDAO();
	PromocoeDAO promocoeDAO = new PromocoeDAO();
	
	public DestinoDAO() {
		try {
			conexao = Conexao.conectar();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//CRIAR
	public void saveDestino(Destino destino) {
		String sql = "INSERT INTO destinos(contato_id, promocoe_id, dataViagem) VALUES (?, ?, ?)"; 
		Connection conn = null;
		PreparedStatement pstm = null;
		try  {
			//Criar uma conexão com o banco de dados
			conn = Conexao.conectar();
			//Criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adicionar os valores que são esperados pela query
			pstm.setInt(1, destino.getContato().getId());
			pstm.setInt(2, destino.getPromocoe().getId());
			pstm.setTimestamp(3, new java.sql.Timestamp(destino.getDataViagem().getTime()));
			//Executar a query
			pstm.execute();
			System.out.println("Destino salvo com sucesso!");
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
	public Destino buscarDestino(int id) {
				Destino destino = null;
				String sql = "SELECT * FROM destinos WHERE id = ?";
				try (PreparedStatement pstm = conexao.prepareStatement(sql)) {
					pstm.setInt(1, id);
					ResultSet rset = pstm.executeQuery();
					if (rset.next()) {
						destino = new Destino();
						destino.setId(rset.getInt("id"));
						destino.setContato(contatoDAO.buscarContato(rset.getInt("contato_id")));
						destino.setPromocoe(promocoeDAO.buscarPromocoe(rset.getInt("promocoe_id")));
						destino.setDataViagem(rset.getTimestamp("dataViagem"));
					} 		
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return destino;
	}
	
	//READ
	public List<Destino> getDestino(){		
		String sql = "SELECT * FROM destinos";	
		List<Destino> destinos = new ArrayList<>();		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;		
		try {
			conn = Conexao.conectar();			
			pstm = (PreparedStatement) conn.prepareStatement(sql);			
			rset = pstm.executeQuery();
			System.out.println("Destino lido feito com sucesso!");
		while (rset.next()) {
			Destino destino = new Destino();				
			//Recuperar o id
			destino.setId(rset.getInt("id"));
			destino.setContato(contatoDAO.buscarContato(rset.getInt("contato_id")));
			destino.setPromocoe(promocoeDAO.buscarPromocoe(rset.getInt("promocoe_id")));
			destino.setDataViagem(rset.getTimestamp("dataViagem"));
			destinos.add(destino);
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
		
			return destinos;
	}
	
	//UPDATE
	public void updateDestino(Destino destino) {
			String sql = "UPDATE destinos SET contato_id = ?, promocoe_id = ?, dataViagem = ? WHERE id = ?";
			Connection conn = null;
			PreparedStatement pstm = null;		
			try {
				//Criar conexao com o banco
				conn = Conexao.conectar();			
				//Criar a classe para executar a query
				pstm = (PreparedStatement) conn.prepareStatement(sql);				
			//Adicionar os valores para atualizar
				//Qual o ID do registro que desejo atualizar?
				pstm.setInt(1, destino.getContato().getId());
				pstm.setInt(2, destino.getPromocoe().getId());;
				//Data da Viagem
				pstm.setTimestamp(3, new java.sql.Timestamp(destino.getDataViagem().getTime()));
				//Qual o ID do registro que desejo atualizar?
				pstm.setInt(4, destino.getId());
				//Executar a query
				pstm.execute();
				System.out.println("Destino atualizado com sucesso!");
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
	public void deleteDestinoByID(int id) {			
			String sql = "DELETE FROM destinos WHERE id = ?";			
			Connection conn = null;			
			PreparedStatement pstm = null;		
			try {
				conn = Conexao.conectar();				
				pstm = (PreparedStatement) conn.prepareStatement(sql);				
				pstm.setInt(1, id);
				pstm.execute();
				System.out.println("Destino deletado com sucesso!");
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