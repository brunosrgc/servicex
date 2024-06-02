package br.com.brunosrgc.servicex.categoria.services;

import br.com.brunosrgc.servicex.categoria.domain.Categoria;
import br.com.brunosrgc.servicex.categoria.domain.CategoriaDTO;
import br.com.brunosrgc.servicex.categoria.domain.CategoriaDTOResponse;
import br.com.brunosrgc.servicex.categoria.repositores.CategoriaRepository;
import br.com.brunosrgc.servicex.exceptios.NomeCategoriaJaExistenteException;
import br.com.brunosrgc.servicex.exceptios.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    public Categoria criarCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNomeCategoria(categoria.getNomeCategoria())) {
            throw new NomeCategoriaJaExistenteException("Já existe uma categoria com esse nome");
        }
        categoria.setIdCategoria(null);
        categoria.getNomeCategoria();
        return categoriaRepository.save(categoria);
    }


    public Categoria buscarCategoriaPorId(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrato! ID: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }


    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }


    public Categoria atualizarCategoria(Categoria categoria) {
        Categoria novaCategoria = buscarCategoriaPorId(categoria.getIdCategoria());
        updateData(novaCategoria, categoria);
        return categoriaRepository.save(novaCategoria);
    }


    public void deletarCategoria(Integer id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Categoria não encontrada");
        }
    }
    public Categoria fromDTO(CategoriaDTO categoriaDTO){
        return new Categoria(categoriaDTO.getIdCategoria(), categoriaDTO.getNomeCategoria());


    }
    public Categoria fromDTOResponse(CategoriaDTOResponse responseDTO) {
        Categoria categoria = new Categoria();
        categoria.setNomeCategoria(responseDTO.getNomeCategoria());
        return categoria;
    }


    private void updateData(Categoria novaCategoria, Categoria categoria){
        novaCategoria.setNomeCategoria(categoria.getNomeCategoria());
    }
}

