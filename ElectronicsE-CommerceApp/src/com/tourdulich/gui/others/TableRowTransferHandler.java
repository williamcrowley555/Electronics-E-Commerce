/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourdulich.gui.others;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.stream.Collectors;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

public class TableRowTransferHandler extends TransferHandler {
  protected static final DataFlavor FLAVOR = new DataFlavor(List.class, "List of items");
  private int[] indices;
  private int addIndex = -1; // Location where items were added
  private int addCount; // Number of items added.
  private JComponent source;

  // protected TableRowTransferHandler() {
  //   super();
  //   localObjectFlavor = new ActivationDataFlavor(
  //       Object[].class, DataFlavor.javaJVMLocalObjectMimeType, "Array of items");
  // }

  @Override protected Transferable createTransferable(JComponent c) {
    c.getRootPane().getGlassPane().setVisible(true);
    source = c;
    JTable table = (JTable) c;
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    // List<Object> list = new ArrayList<>();
    // indices = table.getSelectedRows();
    // for (int i: indices) {
    //   list.add(model.getDataVector().get(i));
    // }
    // Object[] transferData = list.toArray();
    indices = table.getSelectedRows();
    @SuppressWarnings("JdkObsolete")
    List<?> transferData = Arrays.stream(indices).mapToObj(model.getDataVector()::get).collect(Collectors.toList());
    // return new DataHandler(transferData, FLAVOR.getMimeType());
    return new Transferable() {
      @Override public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] {FLAVOR};
      }

      @Override public boolean isDataFlavorSupported(DataFlavor flavor) {
        return Objects.equals(FLAVOR, flavor);
      }

      @Override public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (isDataFlavorSupported(flavor)) {
          return transferData;
        } else {
          throw new UnsupportedFlavorException(flavor);
        }
      }
    };
  }

  @Override public boolean canImport(TransferHandler.TransferSupport info) {
    boolean canDrop = info.isDrop() && info.isDataFlavorSupported(FLAVOR);
    // XXX bug? The cursor flickering problem with JTableHeader:
    // info.getComponent().setCursor(canDrop ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
    Component glassPane = ((JComponent) info.getComponent()).getRootPane().getGlassPane();
    glassPane.setCursor(canDrop ? DragSource.DefaultMoveDrop : DragSource.DefaultMoveNoDrop);
    return canDrop;
  }

  @Override public int getSourceActions(JComponent c) {
    return TransferHandler.MOVE; // TransferHandler.COPY_OR_MOVE;
  }

  @Override public boolean importData(TransferHandler.TransferSupport info) {
    TransferHandler.DropLocation tdl = info.getDropLocation();
    if (!(tdl instanceof JTable.DropLocation)) {
      return false;
    }
    JTable.DropLocation dl = (JTable.DropLocation) tdl;
    JTable target = (JTable) info.getComponent();
    DefaultTableModel model = (DefaultTableModel) target.getModel();
    // boolean insert = dl.isInsert();
    int max = model.getRowCount();
    int index = dl.getRow();
    // index = index < 0 ? max : index; // If it is out of range, it is appended to the end
    // index = Math.min(index, max);
    index = index >= 0 && index < max ? index : max;
    addIndex = index;
    // target.setCursor(Cursor.getDefaultCursor());
    try {
      List<?> values = (List<?>) info.getTransferable().getTransferData(FLAVOR);
      if (Objects.equals(source, target)) {
        addCount = values.size();
      }
      Object[] type = new Object[0];
      for (Object o: values) {
        int row = index++;
        // model.insertRow(row, (Vector<?>) o);
        model.insertRow(row, ((List<?>) o).toArray(type));
        target.getSelectionModel().addSelectionInterval(row, row);
      }
      return true;
    } catch (UnsupportedFlavorException | IOException ex) {
      return false;
    }
  }

  @Override protected void exportDone(JComponent c, Transferable data, int action) {
    cleanup(c, action == TransferHandler.MOVE);
  }

  private void cleanup(JComponent c, boolean remove) {
    c.getRootPane().getGlassPane().setVisible(false);
    // c.setCursor(Cursor.getDefaultCursor());
    if (remove && Objects.nonNull(indices)) {
      DefaultTableModel model = (DefaultTableModel) ((JTable) c).getModel();
      if (addCount > 0) {
        for (int i = 0; i < indices.length; i++) {
          if (indices[i] >= addIndex) {
            indices[i] += addCount;
          }
        }
      }
      for (int i = indices.length - 1; i >= 0; i--) {
        model.removeRow(indices[i]);
      }
    }
    indices = null;
    addCount = 0;
    addIndex = -1;
  }
}
