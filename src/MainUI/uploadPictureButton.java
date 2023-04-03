package MainUI;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.HashSet;

public class uploadPictureButton {
    public static ImageIcon addPicture(JButton button) {
        JFileChooser chooser = new JFileChooser();
        ImageIcon imageIcon = null;
        chooser.setMultiSelectionEnabled(true);
        /**
         * Select the type of the document!
         */
        FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg","png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(button);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            /**
             *  Get the uploaded document.
             */
            File[] arrfiles = chooser.getSelectedFiles();
            if (arrfiles == null || arrfiles.length == 0) {
                return imageIcon;
            }

            File  ff = chooser.getSelectedFile();
            String fileName =ff.getName();
            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
            if(!(prefix.equals("jpg") || prefix.equals("png"))){
                JOptionPane.showMessageDialog(new JDialog(),":only .jpg or .png!!!");
                return imageIcon;
            }
            FileInputStream input = null;
            FileOutputStream out = null;
            /**
             * the path of the picture uploaded!!!
             * Attention!
             */
            String path = "./photos";
            try {
                for (File f : arrfiles) {
                    File dir = new File(path);
                    /**
                     * directory to save!
                     */
                    File[] fs = dir.listFiles();
                    HashSet<String> set = new HashSet<String>();
                    for (File file : fs) {
                        set.add(file.getName());
                    }
                    /**
                     * Judge if exist from name!
                     * Attention: the same name NOT allowed!
                     */
                    if (set.contains(f.getName())) {
                        JOptionPane.showMessageDialog(new JDialog(),
                                f.getName() + ": Already exist!");
                        return imageIcon;
                    }

                    String absolutePath = chooser.getSelectedFile().getAbsolutePath();
                    //创建一个ImageIcon对象 传入图片文件的绝对路径
                    imageIcon = new ImageIcon(absolutePath);

                    input = new FileInputStream(f);
                    byte[] buffer = new byte[1024];
                    File des = new File(path, f.getName());
                    out = new FileOutputStream(des);
                    int len = 0;
                    while (-1 != (len = input.read(buffer))) {
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    input.close();
                }
                JOptionPane.showMessageDialog(null, "Success！", "Notice",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Failed！", "Notice",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Failed！", "Notice",
                        JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        }
        return imageIcon;
    }
}
