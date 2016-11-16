package br.com.newkeepinshape.infrastructure.persist.exercicio;

import android.content.Context;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import br.com.newkeepinshape.domain.exercicio.Exercicio;
import br.com.newkeepinshape.domain.exercicio.ExercicioRepository;
import br.com.newkeepinshape.services.DatabaseHelperFactory;


/**
 * Created by root on 15/09/16.
 */
public class ExercicioDaoIml extends BaseDaoImpl<Exercicio, Integer> implements ExercicioRepository {

    public ExercicioDaoIml(Context context) throws SQLException {
        super(Exercicio.class);
        setConnectionSource(DatabaseHelperFactory.getIntanceConnection(context));
        initialize();
    }

    @Override
    public int createExercicio(Exercicio exercicio) {
        try {
            return super.create(exercicio);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao criar Exercicio");
    }

    @Override
    public int deleteExercicio(Integer id) {
        try {
            return super.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao deletar Exercicio");
    }

    @Override
    public int atualizarExercicio(Exercicio exercicio) {
        try {
            return super.update(exercicio);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Erro ao atualizar Exercicio");
    }

    @Override
    public Exercicio findExercicio(Integer id) {
        List exercicios;
        try {
            return super.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao buscar Exercicio");
    }

    @Override
    public List<Exercicio> listAllExercicio() {

        try {
            final List<Exercicio> exercicios = super.queryForAll();
            Collections.sort(exercicios);
            return Collections.unmodifiableList(exercicios);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Erro ao listar os Exercicios");
    }

    @Override
    public List<Map<String, String>> getExerciciosCursor(final List<Exercicio> exercicios) {

        final List<Map<String, String>> datas = new ArrayList<>();

        for (Exercicio exercicio : exercicios) {
            final Map<String, String> exercicioData = new WeakHashMap<>();
            exercicioData.put("id", exercicio.get_id().toString());
            exercicioData.put("Nome", exercicio.getNome());
            exercicioData.put("Peso", Double.toString(exercicio.getPeso()));
            datas.add(exercicioData);
        }


        return datas;
    }
}
