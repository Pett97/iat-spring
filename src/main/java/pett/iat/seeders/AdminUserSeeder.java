package pett.iat.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import pett.iat.domain.auth.User;
import pett.iat.domain.auth.UserRepository;
import pett.iat.enums.UsuarioRole;

@Component
public class AdminUserSeeder implements CommandLineRunner {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public void run(String... args) throws Exception {
      String masterUsername = "peterson";
      System.out.println("NOVANOVAO04");
      if (userRepository.findByLogin(masterUsername) == null) {
         User master = new User();
         master.setLogin(masterUsername);
         master.setName("peterson");
         master.setAtivo(true);
         master.setPassword(passwordEncoder.encode("peterson"));
         master.setUsuarioRole(UsuarioRole.MASTER);

         System.out.println("✅ Criando usuário master no banco...");
         userRepository.save(master);
         System.out.println("✅ Usuário master criado com sucesso!");
      } else {
         System.out.println("ℹ️ Usuário master já existe no banco.");
      }
   }
}