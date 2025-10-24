
// package com.mycompany.aula4;

// import java.awt.BorderLayout;
// import java.awt.event.ActionEvent;

// import javax.swing.GroupLayout;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.ListSelectionModel;
// import javax.swing.border.EmptyBorder;


// public class MainFrame extends JFrame {

//   private final JTextField txtId = new JTextField(5);
//   private final JTextField txtNome = new JTextField(20);
//   private final JTextField txtTipo = new JTextField(20);
//   private final JTextField txtMarca = new JTextField(12);
//   private final JTextField txtVolume = new JTextField(12);
//   private final JTextField txtUnidadeMedida = new JTextField(12);
//   private final JTextField txtPreco = new JTextField(12);
//   private final JTextField txtValidade = new JTextField(12);
//   private final JTextField txtAtivo = new JTextField(12);

//   private final JButton btnNovo = new JButton("Novo");
//   private final JButton btnSalvar = new JButton("Salvar");
//   private final JButton btnExcluir = new JButton("Excluir");
//   private final JButton btnAtualizar = new JButton("Atualizar Lista");

//   private final JTable tabela = new JTable();
//   private final ProdutoTableModel tableModel = new ProdutoTableModel();

//   private final ProdutoDAO dao = new ProdutoDAO();

//   public MainFrame() {
//     super("Produtos - CRUD (Swing + MySQL)");
//     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     setSize(820, 520);
//     setLocationRelativeTo(null);

//     tabela.setModel(tableModel);
//     tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//     tabela.setAutoCreateRowSorter(true); // habilita ordenação por coluna
//     tabela.getSelectionModel().addListSelectionListener(e -> {
//       if (!e.getValueIsAdjusting()) preencherFormularioDaSelecao();
//     });

//     JPanel form = montarFormulario();
//     JScrollPane scroll = new JScrollPane(tabela);

//     JPanel root = new JPanel(new BorderLayout(8, 8));
//     root.setBorder(new EmptyBorder(10, 10, 10, 10));
//     root.add(form, BorderLayout.NORTH);
//     root.add(scroll, BorderLayout.CENTER);

//     setContentPane(root);

//     // Ações
//     btnNovo.addActionListener(this::onNovo);
//     btnSalvar.addActionListener(this::onSalvar);
//     btnExcluir.addActionListener(this::onExcluir);
//     btnAtualizar.addActionListener(this::onAtualizar);

//     carregarTabela();
//   }

//   private JPanel montarFormulario() {
//     JPanel p = new JPanel();
//     GroupLayout gl = new GroupLayout(p);
//     p.setLayout(gl);
//     gl.setAutoCreateGaps(true);
//     gl.setAutoCreateContainerGaps(true);

//     JLabel lId = new JLabel("ID:");
//     JLabel lNome = new JLabel("Nome:");
//     JLabel lTipo = new JLabel("Tipo:");
//     JLabel lMarca = new JLabel("Marca:");
//     JLabel lVolume = new JLabel("Volume:");
//     JLabel lUnidadeMedida = new JLabel("Un. Medida:");
//     JLabel lPreco = new JLabel("Preco:");
//     JLabel lValidade = new JLabel("Validade:");
//     JLabel lAtivo = new JLabel("Ativo:");
  

//     txtId.setEditable(false);

//     gl.setHorizontalGroup(gl.createSequentialGroup()
//       .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
//         .addComponent(lId).addComponent(lNome))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(txtId).addComponent(txtNome))
//       .addGroup(gl.createParallelGroup(GroupLayout.Alignment.TRAILING)
//         .addComponent(lTipo).addComponent(lMarca))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(txtTipo).addComponent(txtMarca))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(lVolume).addComponent(lUnidadeMedida))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(txtVolume).addComponent(txtUnidadeMedida))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(lPreco).addComponent(lValidade))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(txtPreco).addComponent(txtValidade))
//       .addGroup(gl.createParallelGroup()
//         .addComponent(lAtivo)
//       .addGroup(gl.createParallelGroup()
//         .addComponent(txtAtivo))
//         .addGroup(gl.createSequentialGroup()
//           .addComponent(btnNovo)
//           .addComponent(btnSalvar)
//           .addComponent(btnExcluir)
//           .addComponent(btnAtualizar)))
//     );

//     gl.setVerticalGroup(gl.createSequentialGroup()
//       .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
//         .addComponent(lId).addComponent(txtId)
//         .addComponent(lTipo).addComponent(txtTipo)
//         .addComponent(lUnidadeMedida).addComponent(txtUnidadeMedida)
//         .addComponent(lValidade).addComponent(txtValidade)
//         .addComponent(btnNovo).addComponent(btnSalvar)
//         .addComponent(btnExcluir).addComponent(btnAtualizar))
//         .addGroup(gl.createParallelGroup(GroupLayout.Alignment.BASELINE)
//         .addComponent(lNome).addComponent(txtNome)
//         .addComponent(lMarca).addComponent(txtMarca)
//         .addComponent(lVolume).addComponent(txtVolume)
//         .addComponent(lPreco).addComponent(txtPreco)
//         .addComponent(lAtivo).addComponent(txtAtivo))
//     );

//     return p;
//   }

//   private void carregarTabela() {
//     tableModel.setDados(dao.listar());
//     limparFormulario();
//   }

//   private void limparFormulario()  {
//     txtId.setText("");
//     txtNome.setText("");
//     txtTipo.setText("");
//     txtMarca.setText("");
//     txtVolume.setText("");
//     txtUnidadeMedida.setText("");
//     txtPreco.setText("");
//     txtValidade.setText("");
//     txtAtivo.setText("");
//   }

//   private void preencherFormularioDaSelecao() {
//     int row = tabela.getSelectedRow();
//     if (row >= 0) {
//       int modelRow = tabela.convertRowIndexToModel(row); 
//       Produto p = tableModel.getAt(modelRow);
//       if (p != null) {
//         txtId.setText(p.getId() == null ? "" : p.getId().toString());
//         txtNome.setText(p.getNome());
//         txtTipo.setText(p.getTipo());
//         txtMarca.setText(p.getMarca());
//         txtVolume.setText(p.getVolume());
//         txtUnidadeMedida.setText(p.getUnidadeMedida());
//         txtPreco.setText(p.getPreco());
//         txtValidade.setText(p.getValidade());
//         txtAtivo.setText(p.getAtivo());

//       }
//     }
//   }

//   private void onNovo(ActionEvent e) {
//     tabela.clearSelection();
//     limparFormulario();
//     txtNome.requestFocus();
//   }

//   private void onSalvar(ActionEvent e) {
//     String nome = txtNome.getText();
//     String Tipo = txtTipo.getText();
//     String Marca = txtMarca.getText();
//     String Volume = txtVolume.getText();
//     String UnidadeMedida = txtUnidadeMedida.getText();
//     String Preco = txtPreco.getText();
//     String Validade = txtValidade.getText();
//     String Ativo = txtAtivo.getText();


//     if (nome == null || nome.trim().isEmpty() || Tipo == null || Tipo.trim().isEmpty()) {
//       JOptionPane.showMessageDialog(this, "Nome e Tipo são obrigatórios.", "Validação", JOptionPane.WARNING_MESSAGE);
//       return;
//     }


//     try {
//       String idStr = txtId.getText();
//       if (idStr == null || idStr.isEmpty()) {
//         dao.inserir(new Produto(null, nome, Tipo, Marca, Volume, UnidadeMedida, Preco, Validade, Ativo));
//       } else { 
//         dao.atualizar(new Produto(Integer.parseInt(idStr), nome, Tipo, Marca, Volume, UnidadeMedida, Preco, Validade, Ativo));
//       }
//       carregarTabela();
//     } catch (Exception ex) {
//       ex.printStackTrace();
//       JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//     }
//   }

//   private void onExcluir(ActionEvent e) {
//     int row = tabela.getSelectedRow();
//     if (row < 0) {
//       JOptionPane.showMessageDialog(this, "Selecione um Produto para excluir.", "Atenção", JOptionPane.INFORMATION_MESSAGE);
//       return;
//     }
//     int modelRow = tabela.convertRowIndexToModel(row);
//     Produto p = tableModel.getAt(modelRow);

//     int resp = JOptionPane.showConfirmDialog(this,
//       "Excluir o Produto " + p.getNome() + "?", "Confirmar", JOptionPane.OK_CANCEL_OPTION);
//     if (resp != JOptionPane.OK_OPTION) return;

//     try {
//       dao.excluir(p.getId());
//       carregarTabela();
//     } catch (Exception ex) {
//       ex.printStackTrace();
//       JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
//     }
//   }

//   private void onAtualizar(ActionEvent e) {
//     carregarTabela();
//   }
// }

