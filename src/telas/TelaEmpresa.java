package telas;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Empresa;
import util.ConnectionFactory;
import dao.EmpresaDao;



public class TelaEmpresa {
		private JLabel lblCodigo;
		private JLabel lblNome;
		private JLabel lblSigla;
				
		
		
		
		private JTextField txtCodigo;
		private JTextField txtNome;
		private JTextField txtSigla;
				
		
		private JButton btnCadastrar;
		private JButton btnLimpar;
		private JButton btnCancelar;
		
		private JPanel painelConteudo; 
		private JFrame janela;
		
		
	public void iniciar(){

		
		janela = new JFrame("Tela de Empresa");
		painelConteudo = (JPanel) janela.getContentPane();
		painelConteudo.setLayout(null);
		

		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setBounds(10,30,75,18);
		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10,50,75,18);
		lblSigla= new JLabel("Sigla: ");
		lblSigla.setBounds(10,70,71,18);
		
		
		txtCodigo = new JTextField("");
		txtCodigo.setBounds(80,30,75,18);
		txtNome = new JTextField("");
		txtNome.setBounds(80,50,75,18);
		txtSigla = new JTextField("");
		txtSigla.setBounds(80,70,75,18);
		
		
	//	janela.add(lblCodigo);
		janela.add(lblNome);
		janela.add(lblSigla);
		
		
		
	//	janela.add(txtCodigo);
		janela.add(txtNome);
		janela.add(txtSigla);
		
				
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10,150,100,18);
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(110,150,80,18);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(190,150,100,18);
		
		
		janela.add(btnCadastrar);
		janela.add(btnLimpar);
		janela.add(btnCancelar);

		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(350,250);
		janela.setVisible(true);
		
		btnCadastrar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Connection bd = ConnectionFactory.getConnection();
				Empresa emp = new Empresa();
				EmpresaDao empDao = new EmpresaDao(bd);

			//	if(empresaSelecionada != null)
				
				emp.setNome(txtNome.getText());
				emp.setSigla(txtSigla.getText());
				
				
				
				
				try {
					empDao.inserir(emp);
				} catch (SQLException e1) {
					System.out.println("Deu erro");
					e1.printStackTrace();
				}
			}
		});
		
		btnLimpar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				txtCodigo.setText(null);
				txtNome.setText(null);
				txtSigla.setText(null);
				
				
			}
		});

		btnCancelar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				janela.dispose();
				
			}
		});


	   }

	
	public static void main(String[] args) {
		TelaEmpresa empresa = new TelaEmpresa();
		
		empresa.iniciar();
	}
	
}


