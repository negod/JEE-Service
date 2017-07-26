package se.backede.archetype.entity;

import com.negod.generics.persistence.entity.GenericEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author joakim.backede@outlook.com
 */
@Table(name = "SERVICE")
@Entity
@XmlRootElement(name = "service")
@XmlAccessorType(XmlAccessType.NONE)
@Data
@Indexed
@AnalyzerDef(name = "service_customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
            @TokenFilterDef(factory = LowerCaseFilterFactory.class)
        })
public class ServiceEntity extends GenericEntity {

    @Analyzer(definition = "service_customanalyzer")
    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Column(name = "name", insertable = true, unique = true)
    @XmlElement
    private String name;

    @XmlElement
    @IndexedEmbedded
    @OneToOne(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ServiceDetailEntity serviceDetail;

    @XmlElement
    @IndexedEmbedded
    @JoinColumn(name = "domain_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DomainEntity domain;

    @XmlElement
    @IndexedEmbedded
    @JoinTable(name = "SERVICE_USER",
            joinColumns = {
                @JoinColumn(name = "service_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "user_id")})
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserEntity> users = new HashSet<>();

}
