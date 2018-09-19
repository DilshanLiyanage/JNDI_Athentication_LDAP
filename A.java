
import javax.naming.directory.DirContext;


public class A {

    public static void main(String[] args) {


            boolean bb = B.getAuthenticated("tdilshan","Tharu@1995");
            System.out.println("End "+bb);
            System.out.println("--------------------------------------------------------");
            DirContext dc = B.dirContext;
            B.getAttributesInfo("kuathukorala", dc ,null);



    }


}
