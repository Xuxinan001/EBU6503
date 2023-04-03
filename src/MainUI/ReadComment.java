package MainUI;
public class ReadComment{


    public void rc(String fname){
        String fileP = "./Data/" + fname + ".txt";
        readRoles rR = new readRoles();
        String comment = rR.reader(fileP);
        
        CommentUI cUI = new CommentUI(comment);
        cUI.run(comment);
    }
}