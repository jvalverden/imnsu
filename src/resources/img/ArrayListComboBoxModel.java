/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources.img;

import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


public class ArrayListComboBoxModel extends AbstractListModel implements
    ComboBoxModel {
  private Object selectedItem;

  private ArrayList anArrayList;

  public ArrayListComboBoxModel(ArrayList arrayList) {
    anArrayList = arrayList;
  }

  public Object getSelectedItem() {
    return selectedItem;
  }

  public void setSelectedItem(Object newValue) {
    selectedItem = newValue;
  }

  public int getSize() {
    return anArrayList.size();
  }

  public Object getElementAt(int i) {
    return anArrayList.get(i);
  } 
}