package br.com.brunosrgc.servicex.categoria.resources;

import br.com.brunosrgc.servicex.categoria.domain.Categoria;
import br.com.brunosrgc.servicex.categoria.repositores.CategoriaRepository;
import br.com.brunosrgc.servicex.categoria.services.CategoriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoriaResourceTest {

    @InjectMocks
    private CategoriaService categoriaService;


    @Mock
    private CategoriaRepository categoriaRepository;


    @Test
    public void testCriarCategoria_success(){
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria("New Category");


        when(categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())).thenReturn(false);
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoria);


        Categoria result = categoriaService.criarCategoria(categoria);


        assertNotNull(result);
        assertEquals("New Category", result.getNomeCategoria());
        verify(categoriaRepository).existsByNomeCategoria(categoria.getNomeCategoria());
        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void testDeletarCategoria_success() {
        Integer categoriaId = 1;

        when(categoriaRepository.existsById(categoriaId)).thenReturn(true);

        categoriaService.deletarCategoria(categoriaId);

        verify(categoriaRepository).existsById(categoriaId);
        verify(categoriaRepository).deleteById(categoriaId);
    }

}