package MainUI;
import dao.ReadModules;
import Entity.*;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyForm {
   public JPanel getStudentsRecord() throws IOException {
      JPanel panel2=new JPanel();

      panel2.setBackground(new Color(255,255,255));
      //panel2.setLayout(null);
      //panel2.setPreferredSize(new DimensionUIResource( 800,800));

      ReadModules readModules=new ReadModules("2020213574");
      List<Modules> moduleList=readModules.getModulesList(readModules.getStudentId());
      // 定义表格列名数组
      String[] columnNames = {"ModuleId", "ModuleName","Grade","GPA","Credit"};
      // 创建指定列名和数据的表格
      JTable table = new JTable();
      DefaultTableModel dtm = (DefaultTableModel)table.getModel();
      dtm.setColumnIdentifiers(columnNames);

      double totalCredit=0;
      int courseCount=0;
      int totalMark=0;
      double weightedAverageMark=0;


      for(Modules modeules:moduleList){
         List arraylist = new ArrayList();
         arraylist.add(modeules.getModuleId());
         arraylist.add(modeules.getModuleName());
         arraylist.add(modeules.getGrade());


         double two=modeules.getGrade()*4/100;
         String str1=String.format("%.2f",two);
         double GPA=Double.parseDouble(str1);
         arraylist.add(GPA);

         arraylist.add(modeules.getCredit());

         Vector v=new Vector();
         v.add(arraylist.get(0));
         v.add(arraylist.get(1));
         v.add(arraylist.get(2));
         v.add(arraylist.get(3));
         v.add(arraylist.get(4));

         totalCredit+=modeules.getCredit();
         totalMark+=modeules.getGrade()*modeules.getCredit();
         courseCount++;

         dtm.addRow(v);
      }

      double one=totalMark/totalCredit;
      String  str = String.format("%.2f",one);
      weightedAverageMark = Double.parseDouble(str);

      double two=weightedAverageMark*4/100;
      String str1=String.format("%.2f",two);
      double GPA=Double.parseDouble(str1);


      DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
      cr.setHorizontalAlignment (JLabel.CENTER);
      table.setDefaultRenderer (Object.class, cr);

      table.setBounds(0,100,760,700);
      JScrollPane jScrollPane= new JScrollPane(table);
      jScrollPane.setBounds(17,100,780,700);
      jScrollPane.getViewport().setBackground(Color.WHITE);
      panel2.add(jScrollPane);
      JLabel title=new JLabel("Modules Record",JLabel.CENTER);
      title.setBounds(10,0,800,50);
      title.setFont(new Font("微软雅黑", Font.BOLD, 40));
      title.setBackground(new Color(255,255,255));
      panel2.add(title);

      JLabel detail=new JLabel("Number of courses taken:"+courseCount
              +" Total credits taken:"+totalCredit
              +"  Weighted average score:"+weightedAverageMark
              +"  GPA:"+GPA
      );
      detail.setBounds(18,50,800,50);
      detail.setBackground(new Color(255,255,255));
      panel2.add(detail);

      return panel2;

   }
}
