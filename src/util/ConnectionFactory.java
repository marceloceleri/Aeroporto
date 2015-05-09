package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static Connection getConnection() {
	
	final String driver = "com.mysql.jdbc.Driver";	
	String local = "jdbc:mysql://localhost:3307/aeroporto";
	String login = "root";
	String senha = "kihk!9";
	
	Connection conexao = null;
	
	try {
		Class.forName(driver);
		conexao = DriverManager.getConnection(local, login, senha);
	}catch(ClassNotFoundException e){
		System.out.println("Driver nao encontrado!");
	}catch (Exception e) {
		e.printStackTrace();
		System.out.println("Erro durante a conexao");
	}
	
	return conexao;
}
	
}
