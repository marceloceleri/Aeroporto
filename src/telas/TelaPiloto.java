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

import objetos.Piloto;
import util.ConnectionFactory;
import util.DataUtil;
import dao.PilotoDao;



	public class TelaPiloto {
		
		
		private JLabel lblCodigo;
		private JLabel lblNome;
		private JLabel lblCpf;
		private JLabel lblRg;
		private JLabel lblIdade;
		private JLabel lblLogradouro;
		private JLabel lblBairro;
		private JLabel lblNumero;
		private JLabel lblCep;
		
		
		
		
		private JTextField txtCodigo;
		private JTextField txtNome;
		private JTextField txtCpf;
		private JTextField txtRg;
		private JTextField txtIdade;
		private JTextField txtLogradouro;
		private JTextField txtBairro;
		private JTextField txtNumero;
		private JTextField txtCep;
		
		
		private JButton btnCadastrar;
		private JButton btnLimpar;
		private JButton btnCancelar;
		
		private JPanel painelConteudo; 
		private JFrame janela;
		
		
	public void iniciar(){

		
		janela = new JFrame("Tela do Piloto");
		painelConteudo = (JPanel) janela.getContentPane();
		painelConteudo.setLayout(null);
		

		lblCodigo = new JLabel("Codigo: ");
		lblCodigo.setBounds(10,30,75,18);
		lblNome = new JLabel("Nome: ");
		lblNome.setBounds(10,50,75,18);
		lblCpf= new JLabel("Cpf: ");
		lblCpf.setBounds(10,70,71,18);
		lblRg = new JLabel("Rg: ");
		lblRg.setBounds(10,90,75,18);
		lblIdade = new JLabel("Idade: ");
		lblIdade.setBounds(10,110,75,18);
		lblLogradouro = new JLabel("Logradouro: ");
		lblLogradouro.setBounds(10,130,75,18);
		lblBairro = new JLabel("Bairro: ");
		lblBairro.setBounds(10,150,75,18);
		lblNumero = new JLabel("Numero: ");
		lblNumero.setBounds(10,170,75,18);
		lblCep = new JLabel("Cep: ");
		lblCep.setBounds(10,190,75,18);
		
		
		txtCodigo = new JTextField("");
		txtCodigo.setBounds(80,30,75,18);
		txtNome = new JTextField("");
		txtNome.setBounds(80,50,75,18);
		txtCpf = new JTextField("");
		txtCpf.setBounds(80,70,75,18);
		txtRg = new JTextField("");
		txtRg.setBounds(80,90,75,18);
		txtIdade = new JTextField("");
		txtIdade.setBounds(80,110,75,18);
		txtLogradouro = new JTextField("");
		txtLogradouro.setBounds(80,130,75,18);
		txtBairro = new JTextField("");
		txtBairro.setBounds(80,150,75,18);
		txtNumero = new JTextField("");
		txtNumero.setBounds(80,170,75,18);
		txtCep = new JTextField("");
		txtCep.setBounds(80,190,75,18);
		
	//	janela.add(lblCodigo);
		janela.add(lblNome);
		janela.add(lblCpf);
		janela.add(lblRg);
		janela.add(lblIdade);
		janela.add(lblLogradouro);
		janela.add(lblBairro);
		janela.add(lblNumero);
		janela.add(lblCep);
		
		
		
	//	janela.add(txtCodigo);
		janela.add(txtNome);
		janela.add(txtCpf);
		janela.add(txtRg);
		janela.add(txtIdade);
		janela.add(txtLogradouro);
		janela.add(txtBairro);
		janela.add(txtNumero);
		janela.add(txtCep);
		
				
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(10,230,100,18);
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(110,230,80,18);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(190,230,100,18);
		
		
		janela.add(btnCadastrar);
		janela.add(btnLimpar);
		janela.add(btnCancelar);

		janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janela.setSize(400,400);
		janela.setVisible(true);
		
		btnCadastrar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				
				Connection bd = ConnectionFactory.getConnection();
		

				Piloto pil = new Piloto();
				
				PilotoDao pilDao = new PilotoDao(bd);
				
				
			//	pil.setCodigo(Integer.parseInt(txtCodigo.getText()));
				pil.setNome(txtNome.getText());
				pil.setCpf(txtCpf.getText());
				pil.setRg(txtRg.getText());
				pil.setIdade(Integer.parseInt(txtIdade.getText()));
				pil.setLogradouro(txtLogradouro.getText());
				pil.setBairro(txtBairro.getText());
				pil.setNumero(txtNumero.getText());
				pil.setCep(txtCep.getText());
				
				
				
				try {
					pilDao.inserir(pil);
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
				txtCpf.setText(null);
				txtRg.setText(null);
				txtIdade.setText(null);
				txtLogradouro.setText(null);
				txtBairro.setText(null);
				txtNumero.setText(null);
				txtCep.setText(null);
				
				
				
				
			}
		});

		btnCancelar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				janela.dispose();
				
			}
		});


	   }

	
	public static void main(String[] args) {
		TelaPiloto piloto = new TelaPiloto();
		
		piloto.iniciar();
	}
	
}

