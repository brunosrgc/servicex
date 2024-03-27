package br.com.brunosrgc.servicex.pagamento.repositores;

import br.com.brunosrgc.servicex.pagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento,Integer> {
}
