package br.com.newkeepinshape.domain.exercicio;

import java.sql.SQLException;
import java.util.List;


/**
 * Created by root on 15/09/16.
 */
public interface ExercicioRepository {

    public int createExercicio(final Exercicio exercicio);
    public int deleteExercicio(final Integer id);
    public int atualizarExercicio(final Exercicio exercicio);
    public Exercicio findExercicio(final Integer id);
    public List<Exercicio> listAllExercicio();

}
