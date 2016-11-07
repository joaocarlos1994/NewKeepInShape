package br.com.newkeepinshape.domain.atleta;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import br.com.newkeepinshape.domain.avaliacaofisica.AvaliacaoFisica;
import br.com.newkeepinshape.domain.treino.Treino;

/**
 * Created by root on 05/10/16.
 */
@DatabaseTable
public class Atleta {

    @DatabaseField(generatedId = true)
    private Integer _id;
    @DatabaseField
    private String nome;
    @ForeignCollectionField
    private Collection<Treino> treinos;
    @DatabaseField
    private double peso;
    @DatabaseField
    private double altura;
    @DatabaseField
    private int idade;
    @DatabaseField
    private double cintura;
    @DatabaseField
    private double quadril;
    @ForeignCollectionField
    private Collection<AvaliacaoFisica> avaliacaoFisicas;

    public Atleta() {}

    public void adicionarTreinos(final Treino treino){
        this.treinos.add(treino);
    }

    public void adicionarAvaliacaoFisica(final AvaliacaoFisica avaliacaoFisica) {
        this.avaliacaoFisicas.add(avaliacaoFisica);
    }

    public Integer get_id() {
        return _id;
    }

    public String getNome() {
        return nome;
    }

    public Collection<Treino> getTreinos() {
        return Collections.unmodifiableCollection(treinos);
    }

    public double getPeso() {
        return peso;
    }

    public double getAltura() {
        return altura;
    }

    public int getIdade() {
        return idade;
    }

    public double getCintura() {
        return cintura;
    }

    public double getQuadril() {
        return quadril;
    }

    public Collection<AvaliacaoFisica> getAvaliacaoFisicas() {
        return Collections.unmodifiableCollection(avaliacaoFisicas);
    }

    public static class Builder {

        private final String nome;
        private final Collection<Treino> treinos;
        private double peso;
        private double altura;
        private int idade;
        private double cintura;
        private double quadril;
        private final Collection<AvaliacaoFisica> avaliacaoFisicas;

        public Builder (final String nome) {
            this.nome = nome;
            this.treinos = new ArrayList<>();
            this.avaliacaoFisicas = new ArrayList<>();
        }

        public Builder peso(final double peso) {
            this.peso = peso;
            return this;
        }

        public Builder altura(final double altura) {
            this.altura = altura;
            return this;
        }

        public Builder idade(final int idade) {
            this.idade = idade;
            return this;
        }

        public Builder cintura(final double cintura) {
            this.cintura = cintura;
            return this;
        }

        public Builder quadril(final double quadril) {
            this.quadril = quadril;
            return this;
        }

        public Atleta build() {
            return new Atleta(this);
        }

    }

    private Atleta(final Builder builder) {
        this.nome = builder.nome;
        this.treinos = builder.treinos;
        this.peso = builder.peso;
        this.altura = builder.altura;
        this.idade = builder.idade;
        this.cintura = builder.cintura;
        this.quadril = builder.quadril;
        this.avaliacaoFisicas = builder.avaliacaoFisicas;

    }

}
