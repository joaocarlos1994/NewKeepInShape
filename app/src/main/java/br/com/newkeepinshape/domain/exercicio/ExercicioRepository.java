package br.com.newkeepinshape.domain.exercicio;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by root on 15/09/16.
 */
public interface ExercicioRepository {

    public int createExercicio(final Exercicio exercicio) throws SQLException;
    public int deleteExercicio(final Integer id) throws SQLException;
    public Exercicio findExercicio(final Integer id) throws SQLException;
    public List<Exercicio> listAllExercicio() throws SQLException;

}
