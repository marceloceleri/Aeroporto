package dao;

	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Aviao;
import objetos.Voo;





public class VooDao {
		

				private Connection bd;
				
				public VooDao (Connection bd){
					this.bd = bd;
				}

				public void inserir (Voo voo) throws SQLException{
					String sql = "insert into voo (origem, codigoAviao, codigoPiloto, dataChegada, dataSaida, destino ) values (?,?,?,?,?,?)";
					
					PreparedStatement comando = bd.prepareStatement(sql);
				
					comando.setString(1,voo.getOrigem());
					comando.setInt(2, voo.getCodigoAviao());
					comando.setInt(3, voo.getCodigoPiloto());
					comando.setDate(4, new java.sql.Date(voo.getDataChegada().getTime()));
					comando.setDate(5, new java.sql.Date(voo.getDataSaida().getTime()));
					comando.setString(6, voo.getDestino());
					comando.execute();
				}
				
				
				public List<Voo> buscarTodos() throws SQLException {
					String sql = "select * from voo order by origem";
					
					PreparedStatement comando = bd.prepareStatement(sql);
					ResultSet cursor = comando.executeQuery();
					
					List<Voo> lista = new ArrayList<>();
					
					while (cursor.next()){
						Voo voo = new Voo();
						voo.setCodigo(cursor.getInt("Codigo"));
						voo.setOrigem(cursor.getString("origem"));
						voo.setDestino(cursor.getString("destino"));
						voo.setDataChegada(cursor.getDate("dataChegada"));
						voo.setDataSaida(cursor.getDate("dataSaida"));
						voo.setCodigoAviao(cursor.getInt("codigoAviao"));
						voo.setCodigoPiloto(cursor.getInt("codigoPiloto"));
						
						lista.add(voo);
							
					}
					return lista;
				}
				
				public void deletarVoo(Voo voo) throws SQLException{
					String sql = "delete from voo where codigo = ?";

					PreparedStatement comando = bd.prepareStatement(sql);
					comando.setInt(1, voo.getCodigo());
					comando.execute();

				}			
				
				
			}
