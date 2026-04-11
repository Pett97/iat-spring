package pett.iat.domain.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    boolean existsBySkuIgnoreCase(String sku);

    boolean existsByNomeIgnoreCase(String nome);
    
    Optional<Produto> findBySku(String sku);
}