package com.mycompany.aula4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemTableModel extends AbstractTableModel {
  private final String[] colunas = {"ID", "Nome", "Descricao", "Marca", "Valor", "Estoque"};
  private final List<Item> dados = new ArrayList<>();

  public void setDados(List<Item> lista) {
    dados.clear();
    if (lista != null) dados.addAll(lista);
    fireTableDataChanged();
  }

  public Item getAt(int row) {
    if (row < 0 || row >= dados.size()) return null;
    return dados.get(row);
  }

  @Override public int getRowCount() { return dados.size(); }
  @Override public int getColumnCount() { return colunas.length; }
  @Override public String getColumnName(int column) { return colunas[column]; }

  @Override public Object getValueAt(int rowIndex, int columnIndex) {
    Item p = dados.get(rowIndex);
    switch (columnIndex) {
      case 0: return p.getId();
      case 1: return p.getNome();
      case 2: return p.getDescricao();
      case 3: return p.getMarca();
      case 4: return p.getValor();
      case 6: return p.getEstoque();
      default: return null;
    }
  }

  @Override 
  public Class<?> getColumnClass(int columnIndex) {
    return columnIndex == 0 ? Integer.class : String.class;
  }
}
