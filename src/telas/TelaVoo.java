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
import objetos.Piloto;
import objetos.Voo;
import util.ConnectionFactory;
import util.DataUtil;
import dao.AviaoDao;
import dao.PilotoDao;
import dao.VooDao;

public class TelaVoo {
	

		
		
		private JLabel lblOrigem;
		private JLabel lblDestino;
		private JLabel lblDataChegada;
		private JLabel lblDataSaida;
		private JLabel lblCodigoAviao;
		private JLabel lblCodigoPiloto;
		
		private JTextField txtOrigem;
		private JTextField txtDestino;
		private JTextField txtDataChegada;
		private JTextField txtDataSaida;
		private JComboBox cboxCodigoAviao;
		private JComboBox cboxCodigoPiloto;		
		
		private JButton btnCadastrar;
		private JButton btnLimpar;
		private JButton btnCancelar;
		
		private JPanel painelConteudo; 
		private JFrame janela;
		
		List<Piloto> pilLista = new ArrayList<>();
		List<Aviao> aviLista = new ArrayList<>();
		
	public void iniciar(){

		
		janela = new JFrame("Tela de voo");
		painelConteudo = (JPanel) janela.getContentPane();
		painelConteudo.setLayout(null);
		
		lblOrigem = new JLabel("Origem: ");
		lblOrigem.setBounds(10,50,75,18);
		lblDestino= new JLabel("Destino: ");
		lblDestino.setBounds(10,70,75,18);
		lblDataChegada = new JLabel("Data Chegada: ");
		lblDataChegada.setBounds(10,90,175,18);
		lblDataSaida = new JLabel("Data Saida: ");
		lblDataSaida.setBounds(10,110,75,18);
		lblCodigoAviao = new JLabel("Codigo Aviao: ");
		lblCodigoAviao.setBounds(10,130,100,18);
		lblCodigoPiloto = new JLabel("Codigo Piloto: ");
		lblCodigoPiloto.setBounds(10,150,100,18);
	
		
		txtOrigem = new JTextField("");
		txtOrigem.setBounds(120,50,175,18);
		txtDestino = new JTextField("");
		txtDestino.setBounds(120,70,175,18);
		txtDataChegada = new JTextField("");
		txtDataChegada.setBounds(120,90,75,18);
		
		txtDataSaida = new JTextField("");
		txtDataSaida.setBounds(120,110,75,18);
		
		cboxCodigoAviao = new JComboBox();
		cboxCodigoAviao.setSelectedIndex(-1);
		cboxCodigoAviao.setBounds(120,130,175,18);
		
		cboxCodigoPiloto = new JComboBox();
		cboxCodigoPiloto.setSelectedIndex(-1);
		cboxCodigoPiloto.setBounds(120,150,175,18);
		
				
		janela.add(lblOrigem);
		janela.add(lblDestino);
		janela.add(lblDataChegada);
		janela.add(lblDataSaida);
		janela.add(lblCodigoAviao);
		janela.add(lblCodigoPiloto);
		
		
		
	
		janela.add(txtOrigem);
		janela.add(txtDestino);
		janela.add(txtDataChegada);
		janela.add(txtDataSaida);
		janela.add(cboxCodigoAviao);
		janela.add(cboxCodigoPiloto);
		
		Connection bd = ConnectionFactory.getConnection();
		
		PilotoDao pilDao = new PilotoDao(bd);
		AviaoDao aviDao = new AviaoDao(bd);
		
		VooDao vooDao = new VooDao(bd);
		
		//preenche o combo box de piloto
		try {
			pilLista=pilDao.buscarTodos();
			for (Piloto pil : pilLista){
			
				cboxCodigoPiloto.addItem(pil.getNome());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			aviLista=aviDao.buscarTodos();
			for (Aviao avi : aviLista){
			
				cboxCodigoAviao.addItem(avi.getModelo());
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
		

				Voo voo = new Voo();
				
		
				VooDao vooDao = new VooDao(bd);
				
				voo.setOrigem(txtOrigem.getText());
				voo.setDestino(txtDestino.getText());
				
				try {
					voo.setDataChegada(dataUtil.formataData(txtDataChegada.getText()));
					} catch (Exception e1) {
					e1.printStackTrace();
				}
							
				try {
					voo.setDataSaida(dataUtil.formataData(txtDataSaida.getText()));
					} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				voo.setCodigoAviao(aviLista.get(cboxCodigoAviao.getSelectedIndex()).getCodigo());
				voo.setCodigoPiloto(pilLista.get(cboxCodigoPiloto.getSelectedIndex()).getCodigo());
				
				
				
				try {
					vooDao.inserir(voo);
				} catch (SQLException e1) {
					System.out.println("Deu erro");
					e1.printStackTrace();
				}
						}
		}
		
		);
		
		btnLimpar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				
				txtOrigem.setText(null);
				txtDestino.setText(null);
				txtDataChegada.setText(null);
				txtDataSaida.setText(null);
				cboxCodigoAviao.setSelectedIndex(-1);
				cboxCodigoPiloto.setSelectedIndex(-1);
				
			}
		});

		btnCancelar.addActionListener (new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				janela.dispose();
				
			}
		});


	   }

	
	public static void main(String[] args) {
	new TelaVoo().iniciar();
		
		
	}
	
}



