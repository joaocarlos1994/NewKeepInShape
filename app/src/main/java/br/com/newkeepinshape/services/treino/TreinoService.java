package br.com.newkeepinshape.services.treino;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.domain.treino.Treino;
import br.com.newkeepinshape.domain.treino.TreinoRepository;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.exercicio.ExercicioService;

/**
 * Created by root on 15/11/16.
 */

public class TreinoService  {

    private final TreinoRepository treinoRepository;

    public TreinoService(final TreinoRepository treinoRepository) throws SQLException {
        this.treinoRepository = treinoRepository;
    }

    public void salvarOuAtualizarTreino(final TextView idTxtView, final EditText nomeText, final EditText diaText, final List<Exercicio> exercicios) {

    }

    private void saveTreino(final Treino treino) {
        treinoRepository.createTreino(treino);
    }

}
