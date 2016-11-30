package br.com.newkeepinshape.infrastructure.persist.treino;

import android.content.Context;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.domain.treino.Treino;
import br.com.newkeepinshape.domain.treino.TreinoRepository;
import br.com.newkeepinshape.infrastructure.persist.exercicio.ExercicioDaoIml;
import br.com.newkeepinshape.services.DatabaseHelperFactory;

/**
 * Created by root on 15/11/16.
 */

public class TreinoDaoIml extends BaseDaoImpl<Treino, Integer> implements TreinoRepository {

    private final ExercicioRepository exercicioRepository;

    public TreinoDaoIml(final Context context) throws SQLException {
        super(Treino.class);
        setConnectionSource(DatabaseHelperFactory.getIntanceConnection(context));
        initialize();
        this.exercicioRepository = new ExercicioDaoIml(context);
    }


    @Override
    public int createTreino(final Treino treino) {
        try {

            final int  i = super.create(treino);

            for (final Exercicio eachExericio : treino.getExercicios()) {
                final Exercicio exercicio = eachExericio;
                eachExericio.setTreino(treino);
                exercicioRepository.createExercicio(eachExericio);
            }

            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao salvar treino");
    }

    @Override
    public int deleteTreino(final Integer id) {
        try {
            return super.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao deletar treino");
    }

    @Override
    public int atualizarTreino(final Treino treino) {
        try {

            final int  i = super.update(treino);

            for (final Exercicio eachExericio : treino.getExercicios()) {

                final Exercicio exercicio = Exercicio.valueOfExercico(null, eachExericio.getNome(), eachExericio.getPeso(),
                        eachExericio.getQuantidade(), eachExericio.getPontuacao());

                if (eachExericio.getTreino() == null) {
                    exercicio.setTreino(treino);
                    exercicioRepository.createExercicio(exercicio);
                }
            }

            return i;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao atualizar treino");
    }

    @Override
    public Treino findTreino(final Integer id) {
        try {
            return super.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao buscar treino");
    }

    @Override
    public List<Treino> listAllTreino() {
        try {
            return Collections.unmodifiableList(super.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao buscar treinos");
    }
}
