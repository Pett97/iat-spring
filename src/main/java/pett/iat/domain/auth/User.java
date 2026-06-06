package pett.iat.domain.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pett.iat.domain.auth.dtos.DtoCreateLogin;
import pett.iat.enums.UsuarioRole;

@Entity(name = "User")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String name;

   private String login;

   private String password;

   private boolean ativo;

   @Enumerated(EnumType.STRING)
   @Column(name="role")
   private UsuarioRole usuarioRole;

   public User(String name,String login,String passwordEncoded,UsuarioRole usuarioRole) {
      this.name = name;
      this.login = login;
      this.password = passwordEncoded;
      this.usuarioRole = usuarioRole;
   }

   @Override
   public String getUsername() {
      return this.name;
   }

   @Override
   public String getPassword() {
      return password;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }


   //TODO ajustar para ver como configurar o tmepo de expiraca 
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_" + usuarioRole.name()));
   }

   @PrePersist
   private void prePersist() {
      this.name = this.name != null ? this.name.toUpperCase() : null;
   }

   @PreUpdate
   private void preUpdate() {
      this.name = this.name != null ? this.name.toUpperCase() : null;
   }
}
