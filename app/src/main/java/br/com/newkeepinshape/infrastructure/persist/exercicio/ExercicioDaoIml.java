package br.com.newkeepinshape.infrastructure.persist.exercicio;

import android.content.Context;

import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;
import java.util.List;

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
    public int createExercicio(Exercicio exercicio) throws SQLException {
        return super.create(exercicio);
    }

    @Override
    public int deleteExercicio(Integer id) throws SQLException {
        return super.deleteById(id);
    }

    @Override
    public Exercicio findExercicio(Integer id) throws SQLException {
        return super.queryForId(id);
    }

    @Override
    public List<Exercicio> listAllExercicio() throws SQLException {
        return super.queryForAll();
    }
}
