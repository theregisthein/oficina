package com.mycompany.aula4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PessoaTableModel extends AbstractTableModel {
    
  private final String[] colunas = {"ID", "Nome", "Telefone", "Endereco", "CpfCnpj", "Tipo"};
  private final List<Pessoa> dados = new ArrayList<>();

  public void setDados(List<Pessoa> lista) {
    dados.clear();
    if (lista != null) dados.addAll(lista);
    fireTableDataChanged();
  }

  public Pessoa getAt(int row) {
    if (row < 0 || row >= dados.size()) return null;
    return dados.get(row);
  }

  @Override public int getRowCount() { return dados.size(); }
  @Override public int getColumnCount() { return colunas.length; }
  @Override public String getColumnName(int column) { return colunas[column]; }

  @Override public Object getValueAt(int rowIndex, int columnIndex) {
    Pessoa p = dados.get(rowIndex);
    switch (columnIndex) {
      case 0: return p.getId();
      case 1: return p.getNome();
      case 2: return p.getTelefone();
      case 3: return p.getEndereco();
      case 4: return p.getCpfcnpj();
      case 5: return p.getTipo();
      default: return null;
    }
  }

  @Override 
  public Class<?> getColumnClass(int columnIndex) {
    return columnIndex == 0 ? Integer.class : String.class;
  }
}

