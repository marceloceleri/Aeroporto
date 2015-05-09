package relatorios;

	
	
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import objetos.Empresa;
import objetos.Piloto;
import util.ConnectionFactory;
import util.DataUtil;
import dao.EmpresaDao;
import dao.PilotoDao;


public class RelatorioEmpresa {

	
		private JFrame janela;
		private JPanel painelDaJanela;
		private JTable tabela;
		private String[] colunas = new String[] { "Nome", "Sigla"};

		
		private JButton btnAlterar;
		private JButton btnDeletar;
		private JButton btnCancelar;
		
		
		List<Empresa> listaEmpresa = new ArrayList();
		
		private JScrollPane painelDeScroll;

		public void iniciar() {

			janela = new JFrame("Relatorio de Pilotos");
			janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			janela.setSize(600, 400);

			painelDaJanela = (JPanel) janela.getContentPane();
			painelDaJanela.setLayout(null);

			// cria a tabela
			// ao inves de passar direto, colocamos os dados em um modelo
			DefaultTableModel modelo = new DefaultTableModel(null, colunas);
			// e passamos o modelo para criar a jTable
			tabela = new JTable(modelo);
			tabela.setBounds(10, 40, 300, 120);
			tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			btnAlterar = new JButton("Alterar");
			btnAlterar.setBounds(10,230,100,18);
			btnDeletar = new JButton("Deletar");
			btnDeletar.setBounds(110,230,80,18);
			btnCancelar = new JButton("Cancelar");
			btnCancelar.setBounds(190,230,100,18);
			
			
			janela.add(btnAlterar);
			janela.add(btnDeletar);
			janela.add(btnCancelar);
			
			Connection bd = ConnectionFactory.getConnection();
			
		
			EmpresaDao empDao = new EmpresaDao(bd);
			
			try {
				listaEmpresa = empDao.buscarTodos();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			for (Empresa emp : listaEmpresa){
			
				modelo.addRow(new Object[]{emp.getNome(), emp.getSigla()});
			}
			
			
	
			// inserindo a tabela em um painel de scroll
			painelDeScroll = new JScrollPane(tabela);
			painelDeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			painelDeScroll.setBounds(10, 50, 550, 110);

			painelDaJanela.add(painelDeScroll);

			janela.setVisible(true);

			btnAlterar.addActionListener (new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					DataUtil dataUtil = new DataUtil();
					
					Connection bd = ConnectionFactory.getConnection();
					
					Empresa emp = new Empresa();
					
					EmpresaDao empDao = new EmpresaDao(bd);
			
					empDao.alterarEmpresa();
				}
			});
			
			btnDeletar.addActionListener (new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				
					
					Connection bd = ConnectionFactory.getConnection();
			
					Empresa emp = new Empresa();
					
					EmpresaDao empDao = new EmpresaDao(bd);
					
					
				    try {
						empDao.deletarEmpresa(listaEmpresa.get(tabela.getSelectedRow()));
						
					} catch (SQLException e1) {
						System.out.println("nao apagou, deu erro!!!!");
						e1.printStackTrace();
					}
					
					
					
					
				}
			});

			btnCancelar.addActionListener (new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
				
					janela.dispose();
					
				}
			});


		   }

			
			
			
	
		
		public static void main(String[] args) {
			new RelatorioEmpresa().iniciar();
		}
		
	}
	
	
	
	
	
	
	
	


	
	