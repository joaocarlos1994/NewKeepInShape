package br.com.newkeepinshape.services;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.List;

import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.domain.treino.Treino;
import br.com.newkeepinshape.domain.treino.TreinoRepository;

/**
 * Created by root on 15/11/16.
 */

public class TreinoService  {

    private final ExercicioRepository exercicioRepository;

    public TreinoService(final ExercicioRepository exercicioRepository) {
        this.exercicioRepository = exercicioRepository;
    }

}
