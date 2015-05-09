package telas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import objetos.Aviao;
import objetos.Empresa;
import util.ConnectionFactory;
import util.DataUtil;
import dao.AviaoDao;
import dao.EmpresaDao;

public class TelaAviao {
	
		
		
//		private JLabel lblCodigo;
		private JLabel lblModelo;
		private JLabel lblCapacidade;
		private JLabel lblTamanhoTanqueLitro;
		private JLabel lblCodigoEmpresa;
		
		
		private JTextField txtCodigo;
		private JTextField txtModelo;
		private JTextField txtCapacidade;
		private JTextField txtTamanhoTanqueLitro;
		private JComboBox cboxCodigoEmpresa;
				
		
		private JButton btnCadastrar;
		private JButton btnLimpar;
		private JButton btnCancelar;
		
		private JPanel painelConteudo; 
		private JFrame janela;
		
		List<Empresa> empLista = new ArrayList<>();
		
	public void iniciar(){

		
		janela = new JFrame("Tela do Aviao");
		painelConteudo = (JPanel) janela.getContentPane();
		painelConteudo.setLayout(null);
		

	//	lblCodigo = new JLabel("Codigo: ");
	//	lblCodigo.setBounds(10,30,75,18);
		lblModelo = new JLabel("Modelo: ");
		lblModelo.setBounds(10,50,75,18);
		lblCapacidade= new JLabel("Capacidade: ");
		lblCapacidade.setBounds(10,70,81,18);
		lblTamanhoTanqueLitro = new JLabel("Tanque: ");
		lblTamanhoTanqueLitro.setBounds(10,90,175,18);
		lblCodigoEmpresa = new JLabel("Empresa: ");
		lblCodigoEmpresa.setBounds(10,110,75,18);
		
		
	//	txtCodigo = new JTextField("");
	//	txtCodigo.setBounds(80,30,75,18);
		txtModelo = new JTextField("");
		txtModelo.setBounds(130,50,175,18);
		txtCapacidade = new JTextField("");
		txtCapacidade.setBounds(130,70,175,18);
		txtTamanhoTanqueLitro = new JTextField("");
		txtTamanhoTanqueLitro.setBounds(130,90,175,18);
		
		//cria o menu retratil
		
		cboxCodigoEmpresa = new JComboBox();
		cboxCodigoEmpresa.setSelectedIndex(-1);
		cboxCodigoEmpresa.setBounds(130,110,175,18);
				
		
	//	janela.add(lblCodigo);
	//	janela.add(lblCodigo);
		janela.add(lblModelo);
		janela.add(lblCapacidade);
		janela.add(lblTamanhoTanqueLitro);
		janela.add(lblCodigoEmpresa);
		
		
		
	//	janela.add(txtCodigo);
		janela.add(txtModelo);
		janela.add(txtCapacidade);
		janela.add(txtTamanhoTanqueLitro);
		janela.add(cboxCodigoEmpresa);
		
		Connection bd = ConnectionFactory.getConnection();
		
		
		EmpresaDao empDao = new EmpresaDao(bd);
		
		
		try {
			empLista=empDao.buscarTodos();
			for (Empresa emp : empLista){
			
				cboxCodigoEmpresa.addItem(emp.getNome());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
				
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
			
				
				
				DataUtil dataUtil = new DataUtil();
				
				Connection bd = ConnectionFactory.getConnection();
		

				Aviao avi = new Aviao();
				
		
				AviaoDao aviDao = new AviaoDao(bd);
				
				avi.setModelo(txtModelo.getText());
				avi.setCapacidade(Integer.parseInt(txtCapacidade.getText()));
				avi.setTamanhoTanqueLitro(Integer.parseInt(txtTamanhoTanqueLitro.getText()));
				
				avi.setCodigoEmpresa(empLista.get(cboxCodigoEmpresa.getSelectedIndex()).getCodigo());
				
				
				try {
					aviDao.inserir(avi);
				} catch (SQLException e1) {
					System.out.println("Deu erro");
					e1.printStackTrace();
				}
						}
		});
		
		btnLimpar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				
				txtModelo.setText(null);
				txtCapacidade.setText(null);
				txtTamanhoTanqueLitro.setText(null);
				cboxCodigoEmpresa.setSelectedIndex(-1);
				
			}
		});

		btnCancelar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				janela.dispose();
				
			}
		});


	   }

	
	public static void main(String[] args) {
	new TelaAviao().iniciar();
		
		
	}
	
}



