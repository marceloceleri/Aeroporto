package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Empresa;
import objetos.Piloto;

public class EmpresaDao {

	private Connection bd;

	public EmpresaDao(Connection bd) {
		this.bd = bd;
	}

	public void inserir(Empresa empresa) throws SQLException {
		String sql = "insert into empresa (nome,sigla) values (?,?)";

		PreparedStatement comando = bd.prepareStatement(sql);

		comando.setString(1, empresa.getNome());
		comando.setString(2, empresa.getSigla());
		comando.execute();
	}

	public List<Empresa> buscarTodos() throws SQLException {
		String sql = "select * from empresa order by nome";

		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();

		List<Empresa> lista = new ArrayList<>();

		while (cursor.next()) {
			Empresa emp = new Empresa();
			emp.setCodigo(cursor.getInt("codigo"));
			emp.setNome(cursor.getString("nome"));
			emp.setSigla(cursor.getString("sigla"));

			lista.add(emp);

		}
		return lista;
	}

	public void alterarEmpresa() {
		String sql = "update empresa (nome,sigla) values (?,?) where 'codigo' = ";

		// PreparedStatement comando = bd.prepareStatement(sql);
		// ResultSet cursor = comando.executeQuery();

		// comando.executeQuery(codigo);

		return;

	}

	public void deletarEmpresa(Empresa emp) throws SQLException{
		String sql = "delete from empresa where codigo = ?";

		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, emp.getCodigo());
		comando.execute();

	}

}
