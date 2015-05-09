package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Aviao;
import objetos.Empresa;



public class AviaoDao {

	

			private Connection bd;
			
			public AviaoDao (Connection bd){
				this.bd = bd;
			}

			public void inserir (Aviao aviao) throws SQLException{
				String sql = "insert into aviao (modelo, capacidade, tamanhoTanqueLitros, codigoEmpresa ) values (?,?,?,?)";
				
				PreparedStatement comando = bd.prepareStatement(sql);
			
				comando.setString(1, aviao.getModelo());
				comando.setInt(2, aviao.getCapacidade());
				comando.setInt(3, aviao.getTamanhoTanqueLitro());
				comando.setInt(4, aviao.getCodigoEmpresa());
				
				comando.execute();
			}
			
			
			public List<Aviao> buscarTodos() throws SQLException {
				String sql = "select * from aviao order by modelo";
				
				PreparedStatement comando = bd.prepareStatement(sql);
				ResultSet cursor = comando.executeQuery();
				
				List<Aviao> lista = new ArrayList<>();
				
				while (cursor.next()){
					Aviao avi = new Aviao();
					avi.setCodigo(cursor.getInt("codigo"));
					avi.setModelo(cursor.getString("modelo"));
					avi.setCapacidade(cursor.getInt("capacidade"));
					avi.setTamanhoTanqueLitro(cursor.getInt("tamanhoTanqueLitros"));
					avi.setCodigoEmpresa(cursor.getInt("codigoEmpresa"));
					lista.add(avi);
						
				}
				return lista;
			}
			
			
			public void deletarAviao(Aviao avi) throws SQLException{
				String sql = "delete from aviao where codigo = ?";

				PreparedStatement comando = bd.prepareStatement(sql);
				comando.setInt(1, avi.getCodigo());
				comando.execute();

			}
			
		}
