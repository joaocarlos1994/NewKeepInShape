package br.com.newkeepinshape.domain.exercicio;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * Created by root on 15/09/16.
 */
public interface ExercicioRepository {

    public int createExercicio(final Exercicio exercicio);
    public int deleteExercicio(final Integer id);
    public int atualizarExercicio(final Exercicio exercicio);
    public Exercicio findExercicio(final Integer id);
    public List<Exercicio> listAllExercicio();
    public List<Map<String, String>> getExerciciosCursor(final List<Exercicio> exercicios);

}
