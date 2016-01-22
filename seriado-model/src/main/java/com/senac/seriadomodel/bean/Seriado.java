package com.senac.seriadomodel.bean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author lossurdo
 */
@Entity
@Table(name = "seriado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seriado.findAll", query = "SELECT s FROM Seriado s"),
    @NamedQuery(name = "Seriado.findById", query = "SELECT s FROM Seriado s WHERE s.id = :id"),
    @NamedQuery(name = "Seriado.findByTitulo", query = "SELECT s FROM Seriado s WHERE s.titulo LIKE :titulo"),
    @NamedQuery(name = "Seriado.findByAno", query = "SELECT s FROM Seriado s WHERE s.ano = :ano"),
    @NamedQuery(name = "Seriado.findByUrlPoster", query = "SELECT s FROM Seriado s WHERE s.urlPoster = :urlPoster"),
    @NamedQuery(name = "Seriado.findByUrlTrailerYoutube", query = "SELECT s FROM Seriado s WHERE s.urlTrailerYoutube = :urlTrailerYoutube"),
    @NamedQuery(name = "Seriado.findByNota", query = "SELECT s FROM Seriado s WHERE s.nota = :nota"),
    @NamedQuery(name = "Seriado.findByCriadores", query = "SELECT s FROM Seriado s WHERE s.criadores = :criadores"),
    @NamedQuery(name = "Seriado.findByElenco", query = "SELECT s FROM Seriado s WHERE s.elenco = :elenco"),
    @NamedQuery(name = "Seriado.findByFinalizado", query = "SELECT s FROM Seriado s WHERE s.finalizado = :finalizado")})
public class Seriado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Basic(optional = false)
    @Lob
    @Column(name = "sumario")
    private String sumario;
    @Column(name = "ano")
    private Integer ano;
    @Column(name = "url_poster")
    private String urlPoster;
    @Column(name = "url_trailer_youtube")
    private String urlTrailerYoutube;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota")
    private Double nota;
    @Column(name = "criadores")
    private String criadores;
    @Column(name = "elenco")
    private String elenco;
    @Basic(optional = false)
    @Column(name = "finalizado")
    private boolean finalizado;
    
    @JoinTable(name = "seriado_genero", 
            joinColumns = {@JoinColumn(name = "seriado_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "genero_id", referencedColumnName = "id")}
    )
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<Genero> generos;

    public Seriado() {
    }

    public Seriado(Integer id) {
        this.id = id;
    }

    public Seriado(Integer id, String sumario, boolean finalizado) {
        this.id = id;
        this.sumario = sumario;
        this.finalizado = finalizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getUrlPoster() {
        return urlPoster;
    }

    public void setUrlPoster(String urlPoster) {
        this.urlPoster = urlPoster;
    }

    public String getUrlTrailerYoutube() {
        return urlTrailerYoutube;
    }

    public void setUrlTrailerYoutube(String urlTrailerYoutube) {
        this.urlTrailerYoutube = urlTrailerYoutube;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCriadores() {
        return criadores;
    }

    public void setCriadores(String criadores) {
        this.criadores = criadores;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seriado)) {
            return false;
        }
        Seriado other = (Seriado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Seriado{" + "id=" + id + ", titulo=" + titulo + ", sumario=" + sumario + ", ano=" + ano + ", urlPoster=" + urlPoster + ", urlTrailerYoutube=" + urlTrailerYoutube + ", nota=" + nota + ", criadores=" + criadores + ", elenco=" + elenco + ", finalizado=" + finalizado + ", generos=" + generos + '}';
    }
    
}
