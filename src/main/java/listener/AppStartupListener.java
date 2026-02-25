package listener;

import java.util.List;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.ProfileDAO;
import model.Profile;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Verificando banco de dados para criar perfis fictícios...");
        
        ProfileDAO dao = new ProfileDAO();
        List<Profile> perfisAtuais = dao.list();
        
        if (perfisAtuais == null || perfisAtuais.isEmpty()) {
            
            Profile p1 = new Profile();
            p1.setName("João Silva (Desenvolvedor)");
            dao.save(p1);
            
            Profile p2 = new Profile();
            p2.setName("Maria Souza (Analista)");
            dao.save(p2);
            
            Profile p3 = new Profile();
            p3.setName("Carlos Eduardo (Gerente)");
            dao.save(p3);
            
            System.out.println("3 Perfis fictícios criados com sucesso no PostgreSQL!");
        } else {
            System.out.println("Os perfis já existem. Nenhuma inserção necessária.");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Servidor sendo desligado...");
    }
}