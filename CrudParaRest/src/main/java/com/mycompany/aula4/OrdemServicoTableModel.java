package com.mycompany.aula4;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
public class OrdemServicoTableModel extends AbstractTableModel {
    
  private final String[] colunas = {"ID", "Cliente_id", "Veiculo_id", "Data_emissao", "Status"};
  private final List<OrdemServico> dados = new ArrayList<>();

  public void setDados(List<OrdemServico> lista) {
    dados.clear();
    if (lista != null) dados.addAll(lista);
    fireTableDataChanged();
  }

  public OrdemServico getAt(int row) {
    if (row < 0 || row >= dados.size()) return null;
    return dados.get(row);
  }

  @Override public int getRowCount() { return dados.size(); }
  @Override public int getColumnCount() { return colunas.length; }
  @Override public String getColumnName(int column) { return colunas[column]; }

  @Override public Object getValueAt(int rowIndex, int columnIndex) {
    OrdemServico os = dados.get(rowIndex);
    switch (columnIndex) {
      case 0: return os.getId();
      case 1: return os.getCliente_id();
      case 2: return os.getVeiculo_id();
      case 3: return os.getdata_emissao();
      case 4: return os.getStatus();
      default: return null;
    }
  }

  @Override 
  public Class<?> getColumnClass(int columnIndex) {
    return columnIndex == 0 ? Integer.class : String.class;
  }
}

