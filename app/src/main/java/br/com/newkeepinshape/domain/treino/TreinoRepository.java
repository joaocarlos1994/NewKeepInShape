package br.com.newkeepinshape.domain.treino;

import java.util.List;


/**
 * Created by root on 15/11/16.
 */

public interface TreinoRepository {

    public int createTreino(final Treino exercicio);
    public int deleteTreino(final Integer id);
    public int atualizarTreino(final Treino exercicio);
    public Treino findTreino(final Integer id);
    public List<Treino> listAllTreino();

}
