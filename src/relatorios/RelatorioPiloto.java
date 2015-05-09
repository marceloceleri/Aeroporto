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

import objetos.Piloto;
import util.ConnectionFactory;
import dao.PilotoDao;

public class RelatorioPiloto {

	private JFrame janela;
	private JPanel painelDaJanela;
	private JTable tabela;
	private String[] colunas = new String[] { "Nome", "Cpf", "Rg", "Idade",
			"Logradouro", "Bairro", "Numero", "Cep" };

	private JButton btnAlterar;
	private JButton btnDeletar;
	private JButton btnCancelar;

	List<Piloto> listaPiloto = new ArrayList();

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
		btnAlterar.setBounds(10, 230, 100, 18);
		btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(110, 230, 80, 18);
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(190, 230, 100, 18);

		janela.add(btnAlterar);
		janela.add(btnDeletar);
		janela.add(btnCancelar);

		Connection bd = ConnectionFactory.getConnection();

		PilotoDao pilDao = new PilotoDao(bd);

		try {
			listaPiloto = pilDao.buscarTodos();
		} catch (SQLException e2) {
			System.out.println("Deu erro aqui na listagem");
			e2.printStackTrace();
		}

		for (Piloto pil : listaPiloto) {

			modelo.addRow(new Object[] { pil.getNome(), pil.getCpf(),
					pil.getRg(), pil.getIdade(), pil.getLogradouro(),
					pil.getBairro(), pil.getNumero(), pil.getCep() });
		}

		// inserindo a tabela em um painel de scroll
		painelDeScroll = new JScrollPane(tabela);
		painelDeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painelDeScroll.setBounds(10, 50, 550, 110);

		painelDaJanela.add(painelDeScroll);

		janela.setVisible(true);

		btnAlterar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

			/*	Connection bd = ConnectionFactory.getConnection();

				Piloto pil = new Piloto();

				PilotoDao pilDao = new PilotoDao(bd);

				try {
					pilDao.inserir(pil);
				} catch (SQLException e1) {
					System.out.println("Deu erro");
					e1.printStackTrace();
				}*/
			}
		});

		btnDeletar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				Connection bd = ConnectionFactory.getConnection();
				PilotoDao pilDao = new PilotoDao(bd);
				try {
					pilDao.deletarPiloto(listaPiloto.get(tabela.getSelectedRow()));
				} catch (SQLException e1) {
					System.out.println("nao apagou, deu erro!!!!");
					e1.printStackTrace();
				}

			}

		});

		btnCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				janela.dispose();

			}
		});

	}

	public static void main(String[] args) {
		new RelatorioPiloto().iniciar();
	}

}
