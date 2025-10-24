package com.mycompany.aula4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class VeiculoTableModel extends AbstractTableModel {
    
  private final String[] colunas = {"ID", "Marca", "Modelo", "Placa", "Ano", "Cor"};
  private final List<Veiculo> dados = new ArrayList<>();

  public void setDados(List<Veiculo> lista) {
    dados.clear();
    if (lista != null) dados.addAll(lista);
    fireTableDataChanged();
  }

  public Veiculo getAt(int row) {
    if (row < 0 || row >= dados.size()) return null;
    return dados.get(row);
  }

  @Override public int getRowCount() { return dados.size(); }
  @Override public int getColumnCount() { return colunas.length; }
  @Override public String getColumnName(int column) { return colunas[column]; }

  @Override public Object getValueAt(int rowIndex, int columnIndex) {
    Veiculo v = dados.get(rowIndex);
    switch (columnIndex) {
      case 0: return v.getId();
      case 1: return v.getMarca();
      case 2: return v.getModelo();
      case 3: return v.getPlaca();
      case 4: return v.getAno();
      case 5: return v.getCor();

      default: return null;
    }
  }

  @Override 
  public Class<?> getColumnClass(int columnIndex) {
    return columnIndex == 0 ? Integer.class : String.class;
  }
}

