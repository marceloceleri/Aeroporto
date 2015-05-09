package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Piloto;
import objetos.Voo;


	public class PilotoDao {
		

		private Connection bd;
		
		public PilotoDao (Connection bd){
			this.bd = bd;
		}

		public void inserir (Piloto piloto) throws SQLException{
			String sql = "insert into piloto (nome,cpf,rg,idade,logradouro,bairro,numero,cep) values (?,?,?,?,?,?,?,?)";
			
			PreparedStatement comando = bd.prepareStatement(sql);
		//	comando.setInt(1, piloto.getCodigo());
			comando.setString(1, piloto.getNome());
			comando.setString(2, piloto.getCpf());
			comando.setString(3, piloto.getRg());
			comando.setInt(4, piloto.getIdade());
			comando.setString(5, piloto.getLogradouro());
			comando.setString(6, piloto.getBairro());
			comando.setString(7, piloto.getNumero());
			comando.setString(8, piloto.getCep());
			
			comando.execute();
		}
		
		
		
		
		public List<Piloto> buscarTodos() throws SQLException {
			String sql = "select * from piloto order by nome";
			
			PreparedStatement comando = bd.prepareStatement(sql);
			ResultSet cursor = comando.executeQuery();
			
			List<Piloto> lista = new ArrayList<>();
			
			while (cursor.next()){
				Piloto p = new Piloto();
				p.setCodigo(cursor.getInt("codigo"));
				p.setNome(cursor.getString("nome"));
				p.setCpf(cursor.getString("cpf"));
				p.setRg(cursor.getString("rg"));
				p.setIdade(cursor.getInt("idade"));
				p.setLogradouro(cursor.getString("logradouro"));
				p.setBairro(cursor.getString("bairro"));
				p.setNumero(cursor.getString("numero"));
				p.setCep(cursor.getString("cep"));				
				lista.add(p);
			}
			return lista;
		}
		
	/*	public void alterarPiloto(Piloto pil) throws SQLException{
			
			String sql = "uptade piloto (nome,cpf,rg,idade,logradouro,bairro,numero,cep) values (?,?,?,?,?,?,?,?)";
			PreparedStatement comando = bd.prepareStatement(sql);
			
			comando.setString(1, pil.getNome());
			comando.setString(2, pil.getCpf());
			comando.setString(3, pil.getRg());
			comando.setInt(4, pil.getIdade());
			comando.setString(5, pil.getLogradouro());
			comando.setString(6, pil.getBairro());
			comando.setString(7, pil.getNumero());
			comando.setString(8, pil.getCep());

			comando.execute();
			
			
		}*/
		
		public void deletarPiloto(Piloto pil) throws SQLException{
			
			String sql = "delete from piloto where codigo = ?";
			
			PreparedStatement comando = bd.prepareStatement(sql);
			comando.setInt(1, pil.getCodigo());
			comando.execute();
			
		}
		
	}
