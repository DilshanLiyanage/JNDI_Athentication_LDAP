import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.*;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

public class B {

        static DirContext dirContext;
    public static boolean getAuthenticated(String name, String pass){
            try {
                String domain = "virtusa.com";
                String username = name+"@"+domain;
                String password = pass;
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
                env.put(Context.PROVIDER_URL, "ldap://cmvdc01.virtusa.com:3268");
                env.put(Context.SECURITY_AUTHENTICATION, "simple");
                env.put(Context.SECURITY_PRINCIPAL, username);
                env.put(Context.SECURITY_CREDENTIALS, password);


               dirContext = new InitialDirContext(env);


            }catch (Exception e){
                //System.out.println(e);
                return false;
            }


            return true;

        }




        public static void getAttributesInfo(String username, DirContext ctx, SearchControls searchControls){
            System.out.println("User name : "+username);
            String baseDN = "dc=virtusa,dc=com";
            try {
                SearchControls controls = new SearchControls();
                controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                NamingEnumeration<SearchResult> answer = ctx.search(baseDN,"sAMAccountName="+username,controls);
                if(answer.hasMore()){
                    Attributes attrs = answer.next().getAttributes();
                    NamingEnumeration e = attrs.getAll();
                    System.out.println("--------------------------------------------------");
                    while (e.hasMoreElements()){
                        Attribute attr = (Attribute) e.nextElement();
                        System.out.print(attr.getID()+" = ");
                        for (int i=0; i<attr.size(); i++){
                            if(i>0) System.out.print(", ");
                            System.out.print(attr.get(i));
                        }
                        System.out.println();
                    }
                    System.out.println("---------------------------------------------------");
                }

            }catch (Exception e){
                System.out.println(e);
            }

        }

}
