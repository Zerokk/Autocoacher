/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import datasets.Topic;
import java.awt.Color;
import java.awt.Component;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author hp
 */
public class StatusCellRenderer  extends DefaultTableCellRenderer {
    
  Topic topic;
  
  public StatusCellRenderer(Topic topic){
      this.topic = topic;
  }
  
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

    //Get the status for the current row.
    for(int i = 0;i<topic.getSatisfactionLevels().length;i++){
    if (topic.getSatisfactionLevels()[i].equals((String)table.getValueAt(row, col))) {
        Color c = new Color(topic.getColors()[i]);
        l.setBackground(c);
  }
    }
  //Return the JLabel which renders the cell.
  return l;


}}
